package com.withdraw.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "merchant_info")
@Table(name = "merchant_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Audit audit = new Audit();

    @Column(length = 100, nullable = false)
    private String merchantId;

    @Column(length = 200, nullable = false)
    private String merchantApiKey;
    @Column(length = 1000, nullable = false)
    private String apiUrl;
    @Column(length = 50, nullable = false)
    private Short readTimeOut = 1000;

    @Column(length = 50, nullable = false)
    private Short requestTimeOut = 1000;
}
