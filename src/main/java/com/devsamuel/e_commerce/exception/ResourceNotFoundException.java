package com.devsamuel.e_commerce.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String messagem){
        super(messagem);
    }
}
