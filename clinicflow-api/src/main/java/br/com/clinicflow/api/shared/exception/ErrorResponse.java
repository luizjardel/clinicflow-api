package br.com.clinicflow.api.shared.exception;

public record ErrorResponse(
        int status,
        String message
) {
}