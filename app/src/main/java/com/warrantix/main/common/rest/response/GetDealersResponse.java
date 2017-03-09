package com.warrantix.main.common.rest.response;

import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Product;

import java.util.List;

public class GetDealersResponse extends ErrorMessageResponse {

    List<Dealer> mDealers;

    public void setDealers(List<Dealer> dealers){
        mDealers = dealers;
    }

    public List<Dealer> getDealers(){
        return mDealers;
    }

}