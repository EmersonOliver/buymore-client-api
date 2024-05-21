package com.buymore.client.api.core.domain.records;

import java.math.BigDecimal;

public record ClientRecord(String clientName, String document, Long typeClient,
                           String password, BigDecimal balance,
                           String email) {
}
