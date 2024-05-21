package com.buymore.client.api.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ClientPK {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false, unique = true)
    private UUID clientId;

    @Column(name = "document_client", unique = true, nullable = false)
    private String document;

    @Column(name = "email", unique = true, nullable = false)
    private String email;


}
