package com.buymore.client.api.adapters.resources;

import com.buymore.client.api.core.domain.entity.ClientEntity;
import com.buymore.client.api.core.domain.records.ClientRecord;
import com.buymore.client.api.core.domain.service.ClientService;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;


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
            URI uri = new URI(srvRequest.uri()+"/load/"+clientId.toString());
            return Response.created(uri).build();
        } catch (URISyntaxException e) {
           return Response.serverError()
                   .entity(e.getMessage()).build();
        }
    }

    @Path("load/{clientId}")
    @GET
    public Response loadClient(@PathParam("clientId") String clientId){
        ClientEntity response =service.loadClientEntity(clientId).orElseThrow(IllegalAccessError::new);
        return Response.ok(response).build();
    }

    @GET
    @Path("listAll")
    public Response listAllClients(){
        List<ClientEntity> response = this.service.listAllClients();
        return Response.ok(response).build();
    }

}
