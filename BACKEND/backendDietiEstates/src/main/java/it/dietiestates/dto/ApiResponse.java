package it.dietiestates.dto;

public class ApiResponse {
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

