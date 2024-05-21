package com.buymore.client.api.core.domain.repository;

import com.buymore.client.api.core.domain.entity.ClientEntity;
import com.buymore.client.api.core.domain.entity.ClientPK;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepositoryBase<ClientEntity, ClientPK> {


}
