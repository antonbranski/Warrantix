package com.warrantix.main.common.event;

public class SignupFailureEvent
{
    String message;

    public SignupFailureEvent(String errorMessage)
    {
        this.message = errorMessage;
    }

    public String getMessage() {
        return this.message;
    }
}
