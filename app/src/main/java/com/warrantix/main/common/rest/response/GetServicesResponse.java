package com.warrantix.main.common.rest.response;

        import com.warrantix.main.common.rest.model.Message;
        import com.warrantix.main.common.rest.model.Product;
        import com.warrantix.main.common.rest.model.Service;

        import java.util.ArrayList;
        import java.util.List;

public class GetServicesResponse extends ErrorMessageResponse {

    List<Service> mServices = new ArrayList<>();

    public void setServices(List<Service> services){
        mServices = services;
    }

    public List<Service> getServices(){
        return mServices;
    }

}