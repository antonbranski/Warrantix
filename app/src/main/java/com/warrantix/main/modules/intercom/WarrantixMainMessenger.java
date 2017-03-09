package com.warrantix.main.modules.intercom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.warrantix.framework.FrameworkApplication;
import com.warrantix.framework.FrameworkManager;
import com.warrantix.framework.common.convertor.ByteArrayToBase64TypeAdapter;
import com.warrantix.framework.common.receiver.BroadcastReceiverType;
import com.warrantix.framework.intercom.WarrantixIntercomMessenger;
import com.warrantix.framework.intercom.model.IntercomMessage;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;

public class WarrantixMainMessenger {

    private static final String TAG = WarrantixMainMessenger.class.getSimpleName();

    // default Warrantix Main app package name
    public static String TYPE_WARRANTIX_MAIN = "com.warrantix.main";

    // Command Type
    public static final int CMD_REGISTER_PLUGIN = 1;
    public static final int CMD_UNREGISTER_PLUGIN = 2;
    public static final int CMD_REQUEST_TO_PLUGIN = 3;
    public static final int CMD_RESPONSE_FROM_PLUGIN = 4;
    public static final int CMD_REQUEST_TO_MAIN = 5;
    public static final int CMD_RESPONSE_FROM_MAIN = 6;

    // Bundle Field Identifier
    public static final String BUNDLE_CMD_FIELD = "what";
    public static final String BUNDLE_BRANDNAME_FIELD = "brandname";
    public static final String BUNDLE_MESSAGE_FIELD = "message";
    public static final String BUNDLE_IMAGE_FIELD = "image";

    // ReceiverListener
    private ReceiveListener receiveListener = null;
    public void setReceiveListener(ReceiveListener listener) {
        receiveListener = listener;
    }

    // member variables
    protected String brandName = "";
    protected int brandIconResource = 0;

    // public static functions
    private static WarrantixMainMessenger instance = null;
    public static WarrantixMainMessenger getInstance() {
        if (instance == null) {
            instance = new WarrantixMainMessenger(R.mipmap.app_icon);
        }

        return instance;
    }

    // initialize/finalize member functions
    protected WarrantixMainMessenger(int brandIconResource) {
        this.brandName = TYPE_WARRANTIX_MAIN;
        this.brandIconResource = brandIconResource;
        this.initialize();
    }

    public void initialize() {

    }

    public void finalize() {

    }

    // Send / Receive Handlers
    protected void onRequestToMain(String fromBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "Received CMD_REQUEST_TO_MAIN");

        if (receiveListener != null)
            receiveListener.onRequestToMain(fromBrand, type, jsonMessage, bitmap);
    }

    protected void onResponseFromPlugin(String fromBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "Received CMD_RESPONSE_FROM_PLUGIN");

        if (receiveListener != null)
            receiveListener.onResponseFromPlugin(fromBrand, type, jsonMessage, bitmap);
    }

    public void sendRequestToPlugin(String toBrand, String type, String jsonMessage, Bitmap bitmap) {

        IntercomMessage message = createIntercomMessage(TYPE_WARRANTIX_MAIN, toBrand, type, jsonMessage);
        String intercomMessage = createMessage(message);
        sendMessage(CMD_REQUEST_TO_PLUGIN, intercomMessage, bitmap);

        Log.d(TAG, "Sent CMD_REQUEST_TO_PLUGIN");
    }

    public void sendResponseFromMain(String toBrand, String type, String jsonMessage, Bitmap bitmap) {

        IntercomMessage message = createIntercomMessage(TYPE_WARRANTIX_MAIN, toBrand, type, jsonMessage);
        String intercomMessage = createMessage(message);
        sendMessage(CMD_RESPONSE_FROM_MAIN, intercomMessage, bitmap);

        Log.d(TAG, "Sent CMD_RESPONSE_FROM_MAIN");
    }

    public void handleMessage(Intent b) {
        if (b == null)
            return;

        int what = b.getIntExtra(BUNDLE_CMD_FIELD, -1);
        if (what == -1)
            return;

        String jsonMessage = b.getStringExtra(BUNDLE_MESSAGE_FIELD);
        if ((jsonMessage == null) || (jsonMessage.equalsIgnoreCase("")))
            return;

        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
                new ByteArrayToBase64TypeAdapter()).create();
        Type type = new TypeToken<IntercomMessage>() {}.getType();
        IntercomMessage message = gson.fromJson(jsonMessage, type);
        if (message == null)
            return;

        Bitmap bmp = null;

        switch (what) {
            case CMD_REQUEST_TO_MAIN:
                onRequestToMain(
                        message.getFromWhere(),
                        message.getType(),
                        message.getMessage(),
                        bmp
                );
                break;

            case CMD_RESPONSE_FROM_PLUGIN:
                onResponseFromPlugin(
                        message.getFromWhere(),
                        message.getType(),
                        message.getMessage(),
                        bmp
                );
                break;
        }
    }

    // Get application context
    private Context getAppContext() {
        return WarrantixApplication.getAppContext();
    }

    // Atomic member functions
    private IntercomMessage createIntercomMessage(String fromWhere, String toWhere, String type, String jsonMessage) {
        IntercomMessage message = new IntercomMessage();
        message.setFromWhere(fromWhere);
        message.setToWhere(toWhere);
        message.setType(type);
        message.setMessage(jsonMessage);
        return message;
    }

    private String createMessage(IntercomMessage message) {
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
                new ByteArrayToBase64TypeAdapter()).create();
        Type typeClass = new TypeToken<IntercomMessage>() {}.getType();
        String intercomMessage = gson.toJson(message, typeClass);
        return intercomMessage;
    }

    private void sendMessage(int messageType, String intercomMessage, Bitmap bitmap) {
        Intent intent = new Intent();
        intent.setAction(BroadcastReceiverType.ACTION_TO_PLUGIN);
        intent.putExtra(BUNDLE_CMD_FIELD, messageType);
        intent.putExtra(BUNDLE_MESSAGE_FIELD, intercomMessage);
        WarrantixApplication.getAppContext().sendBroadcast(intent);
    }


    public interface ReceiveListener {
        public void onRequestToMain(String fromBrand, String type, String jsonMessage, Bitmap bitmap);
        public void onResponseFromPlugin(String fromBrand, String type, String jsonMessage, Bitmap bitmap);
    }

}