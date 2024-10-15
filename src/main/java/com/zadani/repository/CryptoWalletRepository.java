package com.zadani.repository;

import com.zadani.entity.CryptoWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CryptoWalletRepository extends JpaRepository<CryptoWalletEntity, Long> {

    Optional<CryptoWalletEntity> findByName(String name);
}
