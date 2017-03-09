package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.RegisterProductResponse;

public class RegisterProductSuccessEvent
{
    private RegisterProductResponse mRegisterProductResponse = new RegisterProductResponse();

    public RegisterProductSuccessEvent(RegisterProductResponse registerProductResponse)
    {
        mRegisterProductResponse = registerProductResponse;
    }

    public RegisterProductResponse registerProductResponse()
    {
        return mRegisterProductResponse;
    }

}
