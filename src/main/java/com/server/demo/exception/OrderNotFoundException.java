package com.server.demo.exception;

public class OrderNotFoundException extends  Exception{
    public OrderNotFoundException(String message){
        super(message);
    }
}
