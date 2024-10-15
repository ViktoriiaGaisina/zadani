package com.zadani.service.impl;

import com.zadani.dto.CryptoWalletDto;
import com.zadani.dto.WalletDto;
import com.zadani.entity.CryptoWalletChangePrices;
import com.zadani.repository.CryptoWalletChangePricesRepository;
import com.zadani.repository.impl.CryptoWalletChangePricesDao;
import com.zadani.service.CryptoWalletChangePricesService;
import com.zadani.service.CryptoWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CryptoWalletChangePricesServiceImpl implements CryptoWalletChangePricesService {
    private final CryptoWalletChangePricesDao cryptoWalletChangePricesDao;
    private final CryptoWalletService cryptoWalletService;
    private final CryptoWalletChangePricesRepository cryptoWalletChangePricesRepository;

    @Override
    public void saveLastChangePriceWallet(String codeWallet) {
        CryptoWalletDto cryptoWallet = cryptoWalletService.getTradingDayByCryptoCode(codeWallet).block();
        WalletDto lastPriceWallet = cryptoWalletChangePricesDao.findLastPriceWalletChangeByWalletName(codeWallet);
        BigDecimal wallet = BigDecimal.valueOf(Integer.valueOf(cryptoWallet.getLastPrice()));
        if (lastPriceWallet != null && lastPriceWallet.getPrices().compareTo(wallet) > 0) {
            CryptoWalletChangePrices cryptoWalletChangePrices = CryptoWalletChangePrices.builder()
                    .prices(wallet)
                    .build();
            cryptoWalletChangePricesRepository.save(cryptoWalletChangePrices);
        }
    }
}
