package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Message;

import java.util.List;

public class PullMessageResponse extends ErrorMessageResponse {

    List<Message> mMessages;

    public void setMessages(List<Message> messages){
        mMessages = messages;
    }

    public List<Message> getMessages(){
        return mMessages;
    }

}