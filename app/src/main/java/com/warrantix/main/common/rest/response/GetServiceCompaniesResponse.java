package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCompany;

import java.util.ArrayList;
import java.util.List;

public class GetServiceCompaniesResponse extends ErrorMessageResponse {

    List<ServiceCompany> mServiceCompanies= new ArrayList<>();

    public void setServiceCompanies(List<ServiceCompany> services){
        mServiceCompanies = services;
    }

    public List<ServiceCompany> getServiceCompaniesServices(){
        return mServiceCompanies;
    }

}