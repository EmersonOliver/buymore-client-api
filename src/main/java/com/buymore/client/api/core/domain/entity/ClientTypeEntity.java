package com.buymore.client.api.core.domain.entity;

import io.quarkus.arc.All;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_type_table", schema = "buymore_client")
@SequenceGenerator(name = "sq_client_type_id", sequenceName = "seq_client_type_id", schema = "buymore_client", allocationSize = 1, initialValue = 1)
public class ClientTypeEntity {

    @Id
    @GeneratedValue(generator = "sq_client_type_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "client_type_id")
    private Long clientTypeId;

    @Column(name = "type_client_name", nullable = false)
    private String typeClientName;

    @Column(name = "client_shopkeeper", nullable = false)
    private Boolean clientShopkeeper;

}
