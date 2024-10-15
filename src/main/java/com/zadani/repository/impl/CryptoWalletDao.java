package com.zadani.repository.impl;

import com.zadani.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CryptoWalletDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<WalletDto> findAllCryptoWallets() {
        try {
            String sql = findAllCryptoWalletsQuery();
            return jdbcTemplate.query(sql, (rs, rowNum) -> WalletDto.builder()
                    .walletId(rs.getLong("wallet_id"))
                    .cwId(rs.getLong("cw_id"))
                    .name(rs.getString("name"))
                    .prices(rs.getBigDecimal("prices"))
                    .build());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch all crypto wallets", e);
        }
    }

    private String findAllCryptoWalletsQuery() {
        return "with last_price as (select crypto_wallet.id as wallet_id,\n" +
                "                           cwc.id as cw_id,\n" +
                "                           crypto_wallet.name,\n" +
                "                           cwc.prices,\n" +
                "                           row_number() over (partition by cwc.prices order by cwc.prices) AS row_num\n" +
                "                    from crypto_wallet\n" +
                "                             join crypto_wallet_changeprices cwc on crypto_wallet.id = cwc.fk_id_crypto_wallet\n" +
                "                    where is_track = true)\n" +
                "select distinct *\n" +
                "from last_price\n" +
                "where row_num = 1\n";
    }
}
