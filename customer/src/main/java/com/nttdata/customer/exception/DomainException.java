package com.nttdata.customer.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}