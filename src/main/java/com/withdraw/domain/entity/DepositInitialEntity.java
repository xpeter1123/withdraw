package com.withdraw.domain.entity;

import com.withdraw.utils.MoneyTransferVerification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity(name = "deposit_initial")
@Table(name = "deposit_initial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositInitialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DepositInitialEntity(Long transId, String userId, Double amount, byte retryCount, String errorLog, String status) {
        this.transId = transId;
        this.userId = userId;
        this.retryCount = retryCount;
        this.errorLog = errorLog;
        this.status = status;
        this.amount = amount;
    }

    public DepositInitialEntity(Long transId, String userId, Double amount) {
        MoneyTransferVerification.validateDeposit();

        this.transId = transId;
        this.userId = userId;
        this.amount = amount;
    }

    @Embedded
    private Audit audit = new Audit();

    @Column(length = 100, nullable = false)
    private Long transId;

    @Column(length = 200, nullable = false)
    private String userId;
    @Column(length = 100, nullable = false)
    private Double amount;
    @Column(length = 50, nullable = false)
    private byte retryCount = 0;

    private String errorLog;

    @Column(nullable = false)
    private String status = STATUS.INIT.name();

    enum STATUS {
        INIT,
        SUCCESS,
        ERROR
    }
}
