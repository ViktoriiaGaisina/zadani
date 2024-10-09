package com.zadani.service;

import com.zadani.dto.WalletFilterDto;
import com.zadani.entity.CryptoWalletEntity;
import com.zadani.repository.CryptoWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoWalletService {
    private final CryptoWalletRepository cryptoWalletRepository;

    public void save (WalletFilterDto filterDto) {
        //сначала посмотрим в бд эту валюту если нет кода, то идём в апи
        //идем в при ищем код валюты
        // если нашли возвращаем, если нет-возращаем такой валюты нет
        CryptoWalletEntity cryptoWalletEntity = new CryptoWalletEntity();
        cryptoWalletRepository.save(cryptoWalletEntity);
    }

    public List<CryptoWalletEntity> getAll(){
        return cryptoWalletRepository.findAll();
    }
}
