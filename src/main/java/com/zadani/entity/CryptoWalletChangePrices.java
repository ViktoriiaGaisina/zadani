package com.zadani.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoWalletChangePrices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal prices;
    @ManyToOne
    @JoinColumn(name = "fk_id_crypto_wallet")
    private CryptoWalletEntity cryptoWallet;
}
