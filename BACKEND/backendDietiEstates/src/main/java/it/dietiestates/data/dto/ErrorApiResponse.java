package it.dietiestates.data.dto;

public class ErrorApiResponse extends ApiResponse {
    private static final long serialVersionUID = 1L;
    public ErrorApiResponse(String message) {
        super("error", message);
    }
}
