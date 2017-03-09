package com.warrantix.main.modules.intercom;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.warrantix.framework.intercom.PluginCommManager;
import com.warrantix.framework.intercom.WarrantixIntercomMessenger;
import com.warrantix.framework.intercom.message.PluginInformation;
import com.warrantix.framework.intercom.model.IntercomMessage;
import com.warrantix.framework.intercom.model.IntercomMessageType;
import com.warrantix.main.R;
import com.warrantix.main.manager.PluginManager;

import java.lang.reflect.Type;


public class WarrantixCommManager implements WarrantixMainMessenger.ReceiveListener {

    private static final String TAG = WarrantixCommManager.class.getSimpleName();
    private static WarrantixCommManager instance;

    public static WarrantixCommManager getInstance() {
        if (instance == null)
            instance = new WarrantixCommManager(R.mipmap.app_icon);

        return instance;
    }


    private WarrantixMainMessenger messenger;
    protected WarrantixCommManager(int brandIconResource) {
        super();

        messenger = WarrantixMainMessenger.getInstance();
        messenger.setReceiveListener(this);
    }

    @Override
    public void onRequestToMain(String fromBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "onRequestToMain is called : type = " + type + " Message = " + jsonMessage);

        if (type.equalsIgnoreCase(IntercomMessageType.TYPE_SYNC_PLUGIN_INFORMATION))
            onSyncPluginInformation(fromBrand, jsonMessage);
    }

    @Override
    public void onResponseFromPlugin(String fromBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "onResponseFromPlugin is called");
    }

    //
    // protected member functions
    //
    protected void onSyncPluginInformation(final String fromBrand, final String jsonMessage) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onSyncPluginInformation is called : JSON = " + jsonMessage);

                Gson gson = new Gson();
                Type type = new TypeToken<PluginInformation>() {}.getType();
                PluginInformation pluginInfo = gson.fromJson(jsonMessage, type);

                PluginManager.getInstance().syncPluginInfo(fromBrand, pluginInfo);

            }
        }).start();
    }

    public void replySyncToPlugin(final String toBrand, final PluginInformation replyInfo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                String jsonMessage = gson.toJson(replyInfo);
                Log.d(TAG, "replySyncToPlugin is called : JSON = " + jsonMessage);

                messenger.sendResponseFromMain(toBrand, IntercomMessageType.TYPE_SYNC_PLUGIN_INFORMATION, jsonMessage, null);

            }
        }).start();
    }
}