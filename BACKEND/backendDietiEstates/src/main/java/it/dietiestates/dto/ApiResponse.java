package it.dietiestates.dto;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String status;
    private final String message;

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

