package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.ErrorMessageResponse;

/**
 *
 * Deprecated
 *
 */
public class UserAccountFailedEvent {

    String mErrorMessage;

    public UserAccountFailedEvent(String errorMessage){
        mErrorMessage = errorMessage;
    }

    public String getMessage() {
        return mErrorMessage;
    }
}
