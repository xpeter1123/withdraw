package com.withdraw.domain.entity;

import com.withdraw.core.money.exception.MoneyException;
import com.withdraw.core.money.exception.Rule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user_detail")
@Table(name = "user_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Audit audit = new Audit();

    @Column(length = 100, nullable = false)
    private String userId;

    @Column(length = 200, nullable = false)
    private volatile double balance;

    public synchronized void increaseBalance(double amount) {
        if (amount % 1000 != 0) {
            throw new MoneyException("money must divisible by 1000");
        }

        if (amount < 0) {
            throw new MoneyException("money must be positive number");
        }

        if ((balance + amount) >= Rule.MAXIMUM) {
            throw new MoneyException("money must not greater than Rule.MAXIMUM");
        }

        //...

        balance += amount;
    }
}
