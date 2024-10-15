package com.zadani.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WalletFilterDto {
    private LocalDate time;
    private String codeWallet;
}
