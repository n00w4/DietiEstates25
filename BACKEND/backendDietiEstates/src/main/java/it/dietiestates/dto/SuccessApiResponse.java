package it.dietiestates.dto;

public class SuccessApiResponse extends ApiResponse {
    public SuccessApiResponse(String message) {
        super("success", message);
    }
}
