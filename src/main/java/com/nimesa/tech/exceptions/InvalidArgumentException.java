package com.nimesa.tech.exceptions;

public class InvalidArgumentException extends RuntimeException{

    public InvalidArgumentException(String message){
        super(message);
    }

    public InvalidArgumentException(String message, Throwable e){
        super(message,e);
    }

    public InvalidArgumentException(Throwable e){
        super(e);
    }
}
