package com.ridebooking.ride_booking_backend.common.exception;

public class NoDriverAvailableException extends RuntimeException{
    
    public NoDriverAvailableException(String message){
        super(message);
    }
}
