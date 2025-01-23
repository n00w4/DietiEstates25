package it.dietiestates.data.dto;

public class SuccessApiResponse extends ApiResponse {
    private static final long serialVersionUID = 1L;
    public SuccessApiResponse(String message) {
        super("success", message);
    }
}
