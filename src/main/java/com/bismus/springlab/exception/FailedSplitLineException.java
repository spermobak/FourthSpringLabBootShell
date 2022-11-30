package com.bismus.springlab.exception;

public class FailedSplitLineException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Failed to split the line into two parts";
    }
}
