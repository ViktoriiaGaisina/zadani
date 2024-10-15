package com.zadani.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletDto {
    private Long walletId;
    private Long cwId;
    private String name;
    private BigDecimal prices;
}
