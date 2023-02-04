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
public class DepositAnonymousEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DepositAnonymousEntity(Long transId, Long userId, Double amount, byte retryCount, String errorLog, String status) {
        this.transId = transId;
        this.userId = userId;
        this.retryCount = retryCount;
        this.errorLog = errorLog;
        this.status = status;
        this.amount = amount;
    }

    public DepositAnonymousEntity(Long transId, Long userId, Double amount) {
        MoneyTransferVerification.validateDeposit();

        this.transId = transId;
        this.userId = userId;
        this.amount = amount;
    }

    @Embedded
    private Audit audit = new Audit();

    @Column(length = 100, nullable = false)
    private Long transId;

    @Column(length = 100, nullable = false)
    private String merchantId;

    @Column(length = 200, nullable = false)
    private Long userId;
    @Column(length = 100, nullable = false)
    private Double amount;
    @Column(length = 50, nullable = false)
    private byte retryCount = 0;

    private String errorLog;

    @Column(nullable = false)
    private String status = STATUS.INIT.name();

    @Column(length = 2000)
    private String merchantResponse0;

    @Column(length = 2000)
    private String merchantResponse1;

    @Column(length = 2000)
    private String merchantResponse2;

    @Column(length = 2000)
    private String merchantResponse3;

    @Column(length = 2000)
    private String merchantResponse4;

    public void increaseRetryCountAndSetMerchantResponse(String merchantResponse) {
        switch (retryCount) {
            case 0: {
                merchantResponse0 = merchantResponse;
                break;
            }

            case 1: {
                merchantResponse1 = merchantResponse;
                break;
            }

            case 2: {
                merchantResponse2 = merchantResponse;
                break;
            }

            case 3: {
                merchantResponse3 = merchantResponse;
                break;
            }

            case 4: {
                merchantResponse4 = merchantResponse;
                break;
            }
        }
        retryCount += 1;
    }

    enum STATUS {
        INIT,
        SUCCESS,
        ERROR
    }
}
