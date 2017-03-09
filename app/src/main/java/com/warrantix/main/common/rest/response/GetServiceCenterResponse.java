package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.ServiceCenter;

public class GetServiceCenterResponse extends ErrorMessageResponse {

    ServiceCenter mServiceCenter = new ServiceCenter();

    public void setServiceCenter (ServiceCenter serviceCenter){
        mServiceCenter = serviceCenter;
    }

    public ServiceCenter getServiceCenter(){
        return mServiceCenter;
    }

}