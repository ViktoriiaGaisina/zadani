package com.zadani.repository;

import com.zadani.entity.CryptoWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CryptoWalletRepository extends JpaRepository<CryptoWalletEntity, Long> {
}
