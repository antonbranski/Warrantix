package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.DeleteOrderResponse;
import com.warrantix.main.common.rest.response.SearchProductResponse;

public class DeleteOrderSuccessEvent
{
    private DeleteOrderResponse mDeleteOrderEvent= new DeleteOrderResponse();

    public DeleteOrderSuccessEvent(DeleteOrderResponse deleteOrderResponse)
    {
        mDeleteOrderEvent = deleteOrderResponse;
    }

    public DeleteOrderResponse deleteOrderResponse()
    {
        return mDeleteOrderEvent;
    }

}
