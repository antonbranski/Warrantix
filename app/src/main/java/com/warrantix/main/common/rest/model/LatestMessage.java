package com.warrantix.main.common.rest.model;

public class LatestMessage extends Message{
    private int unreadNumber = 0;

    public void setUnreadNumber(int unreadNumber){
        this.unreadNumber = unreadNumber;
    }

    public int getUnreadNumber(){
        return this.unreadNumber;
    }
}