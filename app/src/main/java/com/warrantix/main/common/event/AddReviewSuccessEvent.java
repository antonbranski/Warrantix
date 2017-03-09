package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.ReviewAddResponse;

public class AddReviewSuccessEvent
{
    private ReviewAddResponse reviewAddResponse = new ReviewAddResponse();

    public AddReviewSuccessEvent(ReviewAddResponse reviewAddResponse)
    {
        this.reviewAddResponse = reviewAddResponse;
    }

    public ReviewAddResponse getReviewAddResponse()
    {
        return reviewAddResponse;
    }

}
