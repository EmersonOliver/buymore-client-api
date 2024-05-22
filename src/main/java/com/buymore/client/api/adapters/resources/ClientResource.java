package com.buymore.client.api.adapters.resources;

import com.buymore.client.api.core.domain.dto.ClientDTO;
import com.buymore.client.api.core.domain.entity.ClientEntity;
import com.buymore.client.api.core.domain.entity.ClientPK;
import com.buymore.client.api.core.domain.records.ClientRecord;
import com.buymore.client.api.core.domain.service.ClientService;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Path("client")
public class ClientResource {

    @Inject
    ClientService service;

    @Context
    HttpServerRequest srvRequest;

    @POST
    public Response createClient(ClientRecord request){
        try {
            UUID clientId = this.service.saveClient(request);
            URI uri = new URI("/client/load/"+clientId.toString());
            return Response.created(uri).build();
        } catch (URISyntaxException e) {
           return Response.serverError()
                   .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("load/{clientId}")
    public Response loadClient(@PathParam("clientId") String clientId){
        ClientEntity result =service.loadClientEntity(clientId).orElseThrow(IllegalAccessError::new);
        ClientDTO response = new ClientDTO().create(result);
        return Response.ok(response).build();
    }

    @GET
    @Path("listAll")
    public Response listAllClients(){
        List<ClientDTO> response = this.service.listAllClients().stream()
                .map(x-> new ClientDTO().create(x))
                .collect(Collectors.toList());
        return Response.ok(response).build();
    }

}
