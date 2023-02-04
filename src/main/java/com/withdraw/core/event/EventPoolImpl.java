package com.withdraw.core.event;

import com.withdraw.config.ProcessSetting;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * read in https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/package-summary.html
 */
@Component
@Slf4j
@Getter
@DependsOn("processSetting")
public class EventPoolImpl implements EventPool, InitializingBean {
    @Autowired
    ProcessSetting processSetting;
    private ThreadPoolExecutor executorPool;
    private int corePoolSize;
    private ArrayBlockingQueue workQueue;

    private int maximumPoolSize;
    public EventPoolImpl() {
        this.corePoolSize = 2;
    }

    @Override
    public Future<?> postEvent(Event event) throws RejectedExecutionException {
        if (executorPool.getCorePoolSize() < executorPool.getMaximumPoolSize() && executorPool.getQueue().size() > 0) {
            synchronized (this) {
                executorPool.setCorePoolSize(executorPool.getCorePoolSize() + 1);
            }
        }
        Future<?> future = executorPool.submit(new EventListener(event));

        return future;
    }

    @Override
    public void afterPropertiesSet() {
        this.maximumPoolSize = processSetting.getMaximumPoolSize();
        this.workQueue = new ArrayBlockingQueue<>(processSetting.getMaxPendingQueueSize());
        executorPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 1l,
                TimeUnit.SECONDS, workQueue, Executors.defaultThreadFactory());
        executorPool.allowCoreThreadTimeOut(true);
    }
}

