package com.zadani.repository.impl;

import com.zadani.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CryptoWalletChangePricesDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WalletDto findLastPriceWalletChangeByWalletName(String name) {
        try {
            String sql = findLastPriceWalletChangeByWalletNameQuery();
            return jdbcTemplate.query(sql, new MapSqlParameterSource("name" ,name), (rs, rowNum) -> WalletDto.builder()
                    .walletId(rs.getLong("wallet_id"))
                    .cwId(rs.getLong("cw_id"))
                    .name(rs.getString("name"))
                    .prices(rs.getBigDecimal("prices"))
                    .build()).stream()
                    .findFirst()
                    .orElse(null);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch all crypto wallets", e);
        }
    }

    private String findLastPriceWalletChangeByWalletNameQuery() {
        return "WITH last_prices AS (\n" +
                "    SELECT DISTINCT\n" +
                "        crypto_wallet.id AS wallet_id,\n" +
                "        cwc.id AS cw_id,\n" +
                "        crypto_wallet.name,\n" +
                "        cwc.prices,\n" +
                "        ROW_NUMBER() OVER (PARTITION BY crypto_wallet.id ORDER BY cwc.prices DESC) AS row_num\n" +
                "    FROM crypto_wallet\n" +
                "             JOIN crypto_wallet_changeprices cwc ON crypto_wallet.id = cwc.fk_id_crypto_wallet\n" +
                "    WHERE is_track = TRUE\n" +
                "    and name = :name\n" +
                ")\n" +
                "SELECT DISTINCT *\n" +
                "FROM last_prices\n" +
                "WHERE row_num = 1;";
    }
}
