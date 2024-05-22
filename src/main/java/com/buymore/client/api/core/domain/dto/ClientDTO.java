package com.buymore.client.api.core.domain.dto;

import com.buymore.client.api.core.domain.entity.ClientEntity;
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
public class ClientDTO {

    private String clientId;
    private String clientName;
    private BigDecimal wallet;
    private String typeClient;
    private String document;
    private String email;

    public ClientDTO create(ClientEntity ent){
        setClientId(ent.getClientPk().getClientId().toString());
        setWallet(ent.getWalletBalance());
        setClientName(ent.getClientName());
        setTypeClient(ent.getClientType().getTypeClientName());
        setDocument(ent.getClientPk().getDocument());
        setEmail(ent.getClientPk().getEmail());
        return this;
    }

}
