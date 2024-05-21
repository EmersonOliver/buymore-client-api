package com.buymore.client.api.core.domain.service;

import com.buymore.client.api.core.domain.entity.ClientEntity;
import com.buymore.client.api.core.domain.entity.ClientPK;
import com.buymore.client.api.core.domain.records.ClientRecord;
import com.buymore.client.api.core.domain.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClientService {

    @Inject
    private ClientRepository clientRepository;

    @Transactional
    public UUID saveClient(ClientRecord clientRecord){
        if(clientRecord.document() != null
                && !"".equalsIgnoreCase(clientRecord.document())
                && clientRecord.email() != null
                && !"".equalsIgnoreCase(clientRecord.email())){
            ClientEntity clientEntity = new ClientEntity().create(clientRecord);
            this.clientRepository.persistAndFlush(clientEntity);
            return clientEntity.getClientPk().getClientId();
        }
        return null;
    }

    @Transactional
    public List<ClientEntity> listAllClients(){
        return this.clientRepository
                .find("order by clientName asc ").list();
    }

    @Transactional
    public Optional<ClientEntity> loadClientEntity(String uuid){
        ClientPK pkclient = new ClientPK();
        pkclient.setClientId(UUID.fromString(uuid));
        return this.clientRepository.findByIdOptional(pkclient);
    }

}
