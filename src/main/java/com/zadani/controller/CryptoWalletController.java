package com.zadani.controller;

import com.zadani.dto.CryptoWalletDto;
import com.zadani.dto.WalletFilterDto;
import com.zadani.service.CryptoWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CryptoWalletController {
    private final CryptoWalletService cryptoWalletService;

    @PostMapping("/create-crypto")
    public void createCryptoWallet(WalletFilterDto walletFilter) {
        cryptoWalletService.save(walletFilter);
    }

    @GetMapping("/wallets")
    public List<CryptoWalletDto> getAllCryptoWallets() {
       return cryptoWalletService.getAllCryptoWallets();
    }
}
