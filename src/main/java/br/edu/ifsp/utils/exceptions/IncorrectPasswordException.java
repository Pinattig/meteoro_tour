package br.edu.ifsp.utils.exceptions;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
