package com.iiitb.academic.exception;


import lombok.AllArgsConstructor;

public class AuthenticationException extends Exception{

    public AuthenticationException(String msg){
        super(msg);
    }

}
