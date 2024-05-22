package com.buymore.client.api.core.domain.entity;

import com.buymore.client.api.core.domain.records.ClientRecord;
import com.buymore.client.api.core.utils.CryptUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client_table", schema = "buymore_client")
public class ClientEntity  {

    @EmbeddedId
    private ClientPK clientPk;

    @Column(name = "clientName" , nullable = false, length = 40)
    private String clientName;

    @Column(name = "client_pass", nullable = false, columnDefinition = "TEXT")
    private String clientPass;

    @Column(name = "client_salt", nullable = false, columnDefinition = "TEXT")
    private String clientSalt;

    @Column(name = "wallet_balance", nullable = false)
    private BigDecimal walletBalance;

    @Column(name = "fk_client_type")
    private Long fkClientType;

    @ManyToOne
    @JoinColumn(name = "fk_client_type", referencedColumnName = "client_type_id", insertable = false, updatable = false)
    private ClientTypeEntity clientType;

    public ClientEntity create(ClientRecord record){
        ClientPK pk = new ClientPK();
        pk.setDocument(record.document());
        pk.setEmail(record.email());
        this.clientPk = pk;
        this.clientName = record.clientName();
        this.clientSalt = CryptUtils.salt();
        this.clientPass = CryptUtils.passwordCreated(record.password(), this.clientSalt);
        this.fkClientType = record.typeClient();
        this.walletBalance = record.balance();
        return this;
    }
}
