package com.warrantix.main.modules.b2c;

import com.warrantix.main.WarrantixApplication;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.response.PullMessageResponse;

import static com.warrantix.main.modules.b2c.b2cUtil.saveB2CURL;


public class B2CManager {

    private static final String TAG = B2CManager.class.getSimpleName();

    private static B2CManager instance = null;

    public static B2CManager getInstance() {
        if (instance == null)
            instance = new B2CManager();

        return instance;
    }

    public B2CManager() {
        BusProvider.get().register(this);
    }

    public synchronized PullMessageResponse getMessages() {

        // get messages
        PullMessageResponse response = WarrantixWebService.getInstance().getMessages("b2c");
        if ((response == null) || ( response.getCode() != 0))
            WarrantixApplication.getInstance().showMessage("GetMessages", "Failed to get the messages");

        else if (response.getMessages() == null){
            WarrantixApplication.getInstance().showMessage("GetMessages", "Failed to get the messages");

        }
        else {
            Message lastMessage = response.getMessages().get(0);
            saveB2CURL(lastMessage);
        }

        return response;
    }

}