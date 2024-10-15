package com.zadani.scheduler;

import com.zadani.service.CryptoWalletChangePricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CryptoScheduler {
    private final CryptoWalletChangePricesService cryptoWalletChangePricesService;

    @Scheduled(fixedRate = 60000)
    public void checkWallet(String codeWallet) {
        cryptoWalletChangePricesService.saveLastChangePriceWallet(codeWallet);
    }
}
