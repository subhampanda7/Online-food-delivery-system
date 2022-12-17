package com.masai.exceptions;

public class RestaurantException extends RuntimeException{
    public RestaurantException() {
    }

    public RestaurantException(String message){

        super(message);

    }
}
