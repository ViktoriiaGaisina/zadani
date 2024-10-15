package com.zadani.service;

import com.zadani.dto.CryptoWalletDto;
import com.zadani.dto.WalletFilterDto;
import com.zadani.entity.CryptoWalletEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CryptoWalletService {
    void save(WalletFilterDto filter);
    List<CryptoWalletDto> getAllCryptoWallets();

    Mono<CryptoWalletDto> getTradingDayByCryptoCode(String symbol);
    Optional<CryptoWalletEntity> getByName(String name);
}
