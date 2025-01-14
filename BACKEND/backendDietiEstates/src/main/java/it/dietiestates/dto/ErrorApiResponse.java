package it.dietiestates.dto;

public class ErrorApiResponse extends ApiResponse {
    public ErrorApiResponse(String message) {
        super("error", message);
    }
}
