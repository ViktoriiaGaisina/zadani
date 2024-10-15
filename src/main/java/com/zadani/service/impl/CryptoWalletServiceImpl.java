package com.zadani.service.impl;

import com.zadani.dto.CryptoWalletDto;
import com.zadani.dto.WalletFilterDto;
import com.zadani.entity.CryptoWalletChangePrices;
import com.zadani.entity.CryptoWalletEntity;
import com.zadani.repository.CryptoWalletRepository;
import com.zadani.repository.impl.CryptoWalletDao;
import com.zadani.service.CryptoWalletService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoWalletServiceImpl implements CryptoWalletService {
    private final CryptoWalletRepository cryptoWalletRepository;
    private final CryptoWalletDao cryptoWalletDao;
    private final WebClient webClient;
    private static boolean IS_TRACK = true;

    @Override
    @Transactional
    public void save(WalletFilterDto filter) {
        Optional<CryptoWalletEntity> cryptoEntity = this.getByName(filter.getCodeWallet());
        CryptoWalletEntity cryptoWalletEntity = cryptoEntity.get();
        if (cryptoWalletEntity == null) {
            CryptoWalletDto cryptoWallet = this.getTradingDayByCryptoCode(filter.getCodeWallet()).block();
             cryptoWalletEntity = CryptoWalletEntity.builder()
                    .name(cryptoWallet.getSymbol())
                    .isTrack(IS_TRACK)
                    .build();
           CryptoWalletChangePrices pricesEntity = new CryptoWalletChangePrices();
           pricesEntity.setPrices(BigDecimal.valueOf(Integer.valueOf(cryptoWallet.getLastPrice())));
             cryptoWalletEntity.getCryptoWalletChangePrices().add(pricesEntity);
            cryptoWalletRepository.save(cryptoWalletEntity);
        }
        if (cryptoWalletEntity != null) {
            cryptoWalletEntity.setIsTrack(IS_TRACK);
            cryptoWalletRepository.save(cryptoWalletEntity);
        }
        if (cryptoWalletEntity != null && cryptoWalletEntity.getIsTrack()) {
            throw new IllegalArgumentException(String.format("crypto all ready is track name code: %s ", filter.getCodeWallet()));
        }
    }

   @Override
    public Optional<CryptoWalletEntity> getByName(String name) {
       return cryptoWalletRepository.findByName(name);
    }

    @Override
    public List<CryptoWalletDto> getAllCryptoWallets() {
       return cryptoWalletDao.findAllCryptoWallets()
               .stream()
               .map(entity -> CryptoWalletDto.builder()
                       .lastPrice(StringUtils.defaultString(String.valueOf(entity.getPrices())))
                       .symbol(entity.getName())
                       .build()).collect(Collectors.toList());
    }

    @Override
    public Mono<CryptoWalletDto> getTradingDayByCryptoCode(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/tradingDay")
                        .queryParam("symbol", symbol)
                        .build())
                .retrieve()
                .bodyToMono(CryptoWalletDto.class);
    }
}
