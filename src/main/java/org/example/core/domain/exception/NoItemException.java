package org.example.core.domain.exception;

public class NoItemException extends RuntimeException {
    public NoItemException (String menssage){
        super(menssage);
    }
}
