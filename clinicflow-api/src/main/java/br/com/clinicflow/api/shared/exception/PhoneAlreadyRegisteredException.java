package br.com.clinicflow.api.shared.exception;

public class PhoneAlreadyRegisteredException extends RuntimeException {
    public PhoneAlreadyRegisteredException(String message) {
        super(message);
    }
}
