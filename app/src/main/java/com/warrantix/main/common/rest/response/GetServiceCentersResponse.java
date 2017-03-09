package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.ServiceCenter;

import java.util.ArrayList;
import java.util.List;

public class GetServiceCentersResponse extends ErrorMessageResponse {

    List<ServiceCenter> mServiceCenters = new ArrayList<>();

    public void setServiceCenters(List<ServiceCenter> serviceCenters){
        mServiceCenters = serviceCenters;
    }

    public List<ServiceCenter> getServiceCenters(){
        return mServiceCenters;
    }

}