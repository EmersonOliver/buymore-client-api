package com.buymore.client.api.core.api.exceptions;


import jakarta.ws.rs.core.Response;
import lombok.Getter;

import java.time.LocalDateTime;

public class BuymoreApiException extends RuntimeException {


    @Getter
    private Response.Status status;
    private String msg;
    private Throwable cause;
    private LocalDateTime timestamp = LocalDateTime.now();

    public BuymoreApiException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BuymoreApiException(String msg,Response.Status status) {
        super(msg);
        this.status = status;
    }

    public BuymoreApiException(String msg, Throwable cause, Response.Status status) {
        super(msg, cause);
        this.status = status;
    }

}
