package com.buymore.client.api.core.api.exceptions;

import com.buymore.client.api.core.utils.DateBuyMoreUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class BuyMoreExceptionHandler implements ExceptionMapper<Exception> {

    private Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

    @Override
    public Response toResponse(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(DateBuyMoreUtils.convertLocalDateTimeToString(LocalDateTime.now()));
        errorMessage.setMessage(exception.getMessage());
        if(exception instanceof  BuymoreApiException){
            if(((BuymoreApiException) exception).getStatus() != null){
                this.status = ((BuymoreApiException) exception).getStatus();
                errorMessage.setMessage(exception.getMessage());
                errorMessage.setError("X05");
            }
        }
        return Response.status(status.getStatusCode()).entity(errorMessage).build();
    }
}
