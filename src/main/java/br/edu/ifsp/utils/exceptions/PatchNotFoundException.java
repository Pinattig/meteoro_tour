package br.edu.ifsp.utils.exceptions;

public class PatchNotFoundException extends RuntimeException{

    public PatchNotFoundException(String message) {
        super(message);
    }
}