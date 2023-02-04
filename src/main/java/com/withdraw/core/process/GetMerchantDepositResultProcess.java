package com.withdraw.core.process;

import com.withdraw.api.service.DepositInitialService;
import com.withdraw.config.ProcessSetting;
import com.withdraw.core.event.EventPoolImpl;
import com.withdraw.core.event.PoolTimer;
import com.withdraw.core.event.model.DepositEvent;
import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.repository.DepositInitialRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Process(key = "GMDR")
@Component
public class GetMerchantDepositResultProcess implements ProcessBase {
    @Autowired
    ProcessSetting processSetting;
    @Autowired
    DepositInitialService depositInitialService;
    @Autowired
    DepositInitialRepository depositInitialRepository;
    @Autowired
    EventPoolImpl eventPool;

    private final PoolTimer poolTimer;

    private volatile Set<Long> taskIdList = Collections.synchronizedSet(new HashSet<Long>());

    public GetMerchantDepositResultProcess() {
        // fix, not change
        int maximumPoolTimerSize = 2;
        // fix, not change
        int corePoolTimerSize = 2;
        this.poolTimer = new PoolTimer(corePoolTimerSize, maximumPoolTimerSize); //fix

    }

    @Override
    public void startProcess() {
        Integer warningTime = processSetting.getGMDR().getWarningTime();
        Integer timeout = processSetting.getGMDR().getCancelTime();

        while (processSetting.getGMDR().isRunning()) {
            // select only one record
            Pageable limit = PageRequest.of(0, 1);

            Page<DepositInitialEntity> page = depositInitialService.getAll(limit);
            page.stream()
                    .findFirst()
                    .ifPresent(depositInitialEntity -> {
                        Long id = depositInitialEntity.getId();
                        taskIdList.add(id);

                        DepositEvent depositEvent = new DepositEvent(taskIdList);
                        depositEvent.setRecordId(id);
                        Future<?> future;
                        try {
                            future = eventPool.postEvent(depositEvent);

                            // WARNING for slow tasks
                            if (warningTime > 0) {
                                TimerTask warnTimer = new TimerTask(depositEvent.getEventId(), future,
                                        TaskTimerLevel.WARN, poolTimer, warningTime, timeout);
                                poolTimer.executeTask(warnTimer, warningTime, TimeUnit.MILLISECONDS);
                            } else if (timeout > 0) {
                                // Using timeout for tasks
                                TimerTask timeoutTimer = new TimerTask(depositEvent.getEventId(), future,
                                        TaskTimerLevel.TIMEOUT, poolTimer, timeout, -1);
                                poolTimer.executeTask(timeoutTimer, timeout, TimeUnit.MILLISECONDS);
                            }

                            log.info("handle record id ={}", id);
                        } catch (Exception e) {
                            taskIdList.remove(id);

                            log.error("post event exception e ={}, queue size ={}, corePoolSize ={}, maxPoolSize ={}",
                                    ExceptionUtils.getStackTrace(e),
                                    eventPool.getWorkQueue().size(),
                                    eventPool.getCorePoolSize(),
                                    eventPool.getMaximumPoolSize());
                        }
                    });
        }
    }

    /**
     * Check task is slow or not, if low then make warning, if exceed timeout then cancel task
     */
    protected class TimerTask implements Runnable {

        private final Future<?> future;
        private final long taskId;
        private TaskTimerLevel level;
        private final PoolTimer poolTimer;
        private final long preTiming;
        private final long nextTiming;

        public TimerTask(long id, Future<?> f, TaskTimerLevel lvl, PoolTimer timer, long preTiming, long nextTiming) {
            this.taskId = id;
            this.future = f;
            this.level = lvl;
            this.poolTimer = timer;
            this.preTiming = preTiming;
            this.nextTiming = nextTiming;
        }

        @Override
        public void run() {
            if (future.isDone()) {
                log.debug("Task " + taskId + " was completed");
                return;
            }

            if (!future.isDone() && level == TaskTimerLevel.WARN) {
                log.info("Task " + taskId + " slow");
                this.level = TaskTimerLevel.TIMEOUT;
                poolTimer.executeTask(this, (nextTiming - preTiming), TimeUnit.MILLISECONDS);
                return;
            }

            if (!future.isDone() && level == TaskTimerLevel.TIMEOUT) {
                log.info("TIMEOUT : Task " + taskId + " try to cancel");
                boolean cancel = future.cancel(true);
                log.info("Try to cancel task " + taskId + " is " + cancel);
            }
        }
    }

    public enum TaskTimerLevel {
        WARN, TIMEOUT
    }
}
