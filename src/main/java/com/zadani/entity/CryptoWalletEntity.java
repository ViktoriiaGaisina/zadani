package com.zadani.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoWalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isTrack;
    @OneToMany(mappedBy = "cryptoWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CryptoWalletChangePrices> cryptoWalletChangePrices;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private UserEntity user;
}
