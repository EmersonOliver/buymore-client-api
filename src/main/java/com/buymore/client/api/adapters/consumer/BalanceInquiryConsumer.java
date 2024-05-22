package com.buymore.client.api.adapters.consumer;

import com.buymore.client.api.core.domain.repository.ClientRepository;
import com.buymore.client.api.core.domain.service.ClientService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class BalanceInquiryConsumer {

    @Inject
    Logger LOG;

    @Inject
    ClientService service;

    @Incoming("transaction-balance")
    public void consumeTransaction(TransactionResponse response){
        LOG.info("Received Transaction by kafka");
            this.service.updateBalance(response);
    }

}
