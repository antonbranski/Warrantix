package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.SearchProductResponse;

public class SigninFailureEvent
{
    String message;

    public SigninFailureEvent(String errorMessage)
    {
        this.message = errorMessage;
    }

    public String getMessage() {
        return this.message;
    }
}
