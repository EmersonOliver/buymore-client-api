package com.buymore.client.api.adapters.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class BalanceInquiryConsumer {

    @Incoming("transaction-balance")
    public void consumeTransaction(TransactionResponse response){

    }

}
