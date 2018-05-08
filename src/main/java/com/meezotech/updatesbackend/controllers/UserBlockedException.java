package com.meezotech.updatesbackend.controllers;

public class UserBlockedException extends RuntimeException {

    public UserBlockedException(String message) {
        super(message);
    }

}
