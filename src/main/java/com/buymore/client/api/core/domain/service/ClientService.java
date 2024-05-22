package com.buymore.client.api.core.domain.service;

import com.buymore.client.api.adapters.consumer.TransactionResponse;
import com.buymore.client.api.core.api.exceptions.BuymoreApiException;
import com.buymore.client.api.core.domain.entity.ClientEntity;
import com.buymore.client.api.core.domain.entity.ClientPK;
import com.buymore.client.api.core.domain.records.ClientRecord;
import com.buymore.client.api.core.domain.repository.ClientRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClientService {

    @Inject
    Logger LOG;

    @Inject
    private ClientRepository clientRepository;

    @Transactional
    public UUID saveClient(ClientRecord clientRecord) {
        LOG.warn("--> Creating client... ");
        if (clientRecord.document() != null
                && !"".equalsIgnoreCase(clientRecord.document())
                && clientRecord.email() != null
                && !"".equalsIgnoreCase(clientRecord.email())) {

            if (userExists(clientRecord.email(), clientRecord.document())) {
                throw new BuymoreApiException("User exists same document or email.", Response.Status.BAD_REQUEST);
            }
            ClientEntity clientEntity = new ClientEntity().create(clientRecord);
            this.clientRepository.persistAndFlush(clientEntity);
            return clientEntity.getClientPk().getClientId();
        }
        throw new BuymoreApiException("Incorrect parameters values on the request", Response.Status.BAD_REQUEST);
    }

    @Transactional
    public List<ClientEntity> listAllClients() {
        return this.clientRepository
                .find("order by clientName asc ").list();
    }

    @Transactional
    public void updateBalance(TransactionResponse rsp) {
        ClientEntity client = this.loadClientEntity(rsp.getPayer()).orElse(null);
        if (client == null) {
            throw new BuymoreApiException("Client not found by id", Response.Status.BAD_REQUEST);
        }
        BigDecimal wallet = client.getWalletBalance();
        BigDecimal transaction = rsp.getValTransaction();
        BigDecimal result = wallet.add(transaction);
        client.setWalletBalance(result);
        this.clientRepository.persistAndFlush(client);
    }

    @Transactional
    public Optional<ClientEntity> loadClientEntity(String uuid) {
        ClientPK pkclient = new ClientPK();
        pkclient.setClientId(UUID.fromString(uuid));
        return this.clientRepository.find("clientPk.clientId =?1 ", UUID.fromString(uuid)).stream().findFirst();
    }

    private boolean userExists(String email, String document) {
        return !this.clientRepository.find("clientPk.email=:mail or clientPk.document =:doc",
                        Parameters.with("doc", document)
                                .and("mail", email))
                .list().isEmpty();
    }

}
