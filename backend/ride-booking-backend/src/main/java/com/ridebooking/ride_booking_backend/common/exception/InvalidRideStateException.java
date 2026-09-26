package com.ridebooking.ride_booking_backend.common.exception;

public class InvalidRideStateException extends RuntimeException{
    public InvalidRideStateException(String message){
        super(message);
    }
}
