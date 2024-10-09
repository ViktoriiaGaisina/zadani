package com.zadani.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CryptoScheduler {
    @Scheduled
    public void checkWallet(String codeWallet) {
        //МЕТОД ИЩЕТ В АПИ ТЕКУЩУЮ ЦЕНУ ВАЛЮТЫ
        //ЕСЛИ ЦЕНА ИЗМЕНИЛАСЬ СОЗРАНЯЕМ В БД

    }
}
