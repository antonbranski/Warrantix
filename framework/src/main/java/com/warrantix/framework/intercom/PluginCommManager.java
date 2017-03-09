package com.warrantix.framework.intercom;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.warrantix.framework.FrameworkManager;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.framework.common.event.SyncPluginInfoSuccessEvent;
import com.warrantix.framework.intercom.message.PluginInformation;
import com.warrantix.framework.intercom.model.IntercomMessageType;

import java.lang.reflect.Type;


public class PluginCommManager implements WarrantixIntercomMessenger.ReceiveListener{
    private static final String TAG = PluginCommManager.class.getSimpleName();

    private WarrantixIntercomMessenger messenger;

    protected PluginCommManager(int brandIconResource) {
        super();

        messenger = WarrantixIntercomMessenger.getInstance(brandIconResource);
        messenger.setReceiveListener(this);
    }

    //
    // call back functions
    //
    @Override
    public void onRequestToPlugin(String fromBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "onRequestToPlugin is called");

    }

    @Override
    public void onResponseFromMain(String toBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "onRespnseFromMain is called");

        if (type.equalsIgnoreCase(IntercomMessageType.TYPE_SYNC_PLUGIN_INFORMATION))
            onSyncPluginInfo(jsonMessage);
    }

    //
    // synchronize configuration handlers
    //
    public void syncPluginInfo(final PluginInformation pluginInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "syncPluginInfo is called");
                if (pluginInformation == null)
                    return;

                Gson gson = new Gson();
                Type type = new TypeToken<PluginInformation>() {}.getType();
                String jsonMessage = gson.toJson(pluginInformation, type);

                messenger.sendRequestToMain(IntercomMessageType.TYPE_SYNC_PLUGIN_INFORMATION, jsonMessage, null);
            }
        }).start();
    }

    public void onSyncPluginInfo(final String jsonMessage) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onSyncPluginInfo is called");

                Gson gson = new Gson();
                Type type = new TypeToken<PluginInformation>() {}.getType();
                PluginInformation pluginInfo = gson.fromJson(jsonMessage, type);

                if (pluginInfo.getShowIcon() == true)
                    FrameworkManager.showAppIcon();
                else
                    FrameworkManager.hideAppIcon();

                BusProvider.get().post(new SyncPluginInfoSuccessEvent());

            }
        }).start();
    }

}