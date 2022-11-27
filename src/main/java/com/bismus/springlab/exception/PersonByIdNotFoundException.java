package com.bismus.springlab.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonByIdNotFoundException extends RuntimeException {
    private final int personId;

    @Override
    public String getMessage() {
        return "Profile with id = " + personId + " not found";
    }
}
