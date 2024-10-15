package com.zadani.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoWalletDto {
    private String symbol;
    private String lastPrice;
}
