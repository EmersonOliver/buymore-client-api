package com.buymore.client.api.adapters.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class TransactionResponse {

    private String clientIdSend;
    private String clientIdReceive;
    private String transactionId;
    private String typeTransaction;
    private String movementDate;
    private BigDecimal transactionValue;
}
