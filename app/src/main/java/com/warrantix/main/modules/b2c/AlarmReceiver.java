package com.warrantix.main.modules.b2c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.B2CGCMMessageContent;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.request.PullMessageRequest;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.response.PullMessageResponse;

import static com.warrantix.main.modules.b2c.b2cUtil.saveB2CURL;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
//        Toast.makeText(arg0, "Pulling the messages for B2C", Toast.LENGTH_SHORT).show();
        try {
            B2CManager.getInstance().getMessages();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}