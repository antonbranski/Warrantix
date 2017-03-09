package com.warrantix.framework.intercom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.warrantix.framework.FrameworkApplication;
import com.warrantix.framework.FrameworkManager;
import com.warrantix.framework.common.convertor.ByteArrayToBase64TypeAdapter;
import com.warrantix.framework.common.receiver.BroadcastReceiverType;
import com.warrantix.framework.intercom.model.IntercomMessage;
import java.lang.reflect.Type;

//
// This class is for plugin side intercommunication manager
//
public class WarrantixIntercomMessenger
{
    private static final String TAG = WarrantixIntercomMessenger.class.getSimpleName();

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
    public static final String BUNDLE_CMD_TYPE = "what";
    public static final String BUNDLE_BRANDNAME_FIELD = "brandname";
    public static final String BUNDLE_MESSAGE_FIELD = "message";
    public static final String BUNDLE_IMAGE_FIELD = "image";

    // Receiver & Connection Listener
    private ReceiveListener receiveListener = null;
    public void setReceiveListener(ReceiveListener listener) {
        receiveListener = listener;
    }

    // member variables
    protected String brandName = "";
    protected int brandIconResource = 0;

    // public static functions
    private static WarrantixIntercomMessenger instance = null;
    public static WarrantixIntercomMessenger getInstance(int brandIconResource) {
        if (instance == null) {
            instance = new WarrantixIntercomMessenger(brandIconResource);
        }

        return instance;
    }

    public static WarrantixIntercomMessenger getInstance() {
        return instance;
    }

    // initialize/finalize member functions
    protected WarrantixIntercomMessenger(int brandIconResource) {
        this.brandName = getAppContext().getPackageName();
        this.brandIconResource = brandIconResource;
        this.initialize();
    }

    public void initialize() {
    }
    public void finalize() {

    }

    // Send / Receive Handlers
    protected void onRequestToPlugin(String fromBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "Received CMD_REQUEST_TO_PLUGIN");

        if (receiveListener != null)
            receiveListener.onRequestToPlugin(fromBrand, type, jsonMessage, bitmap);
    }

    protected void onResponseFromMain(String toBrand, String type, String jsonMessage, Bitmap bitmap) {
        Log.d(TAG, "Received CMD_RESPONSE_FROM_MAIN");

        if (receiveListener != null)
            receiveListener.onResponseFromMain(toBrand, type, jsonMessage, bitmap);
    }

    public void sendRequestToMain(String type, String jsonMessage, Bitmap bitmap) {

        IntercomMessage message = createIntercomMessage(brandName, TYPE_WARRANTIX_MAIN, type, jsonMessage);
        String intercomMessage = createMessage(message);
        sendMessage(CMD_REQUEST_TO_MAIN, intercomMessage, bitmap);

        Log.d(TAG, "Sent CMD_REQUEST_TO_MAIN");
    }

    public void sendResponseToMain(String type, String jsonMessage, Bitmap bitmap) {

        IntercomMessage message = createIntercomMessage(brandName, TYPE_WARRANTIX_MAIN, type, jsonMessage);
        String intercomMessage = createMessage(message);
        sendMessage(CMD_RESPONSE_FROM_PLUGIN, intercomMessage, bitmap);

        Log.d(TAG, "Sent CMD_RESPONSE_TO_MAIN");
    }


    public void handleMessage(Intent b) {
        if (b == null)
            return;

        int what = b.getIntExtra(BUNDLE_CMD_TYPE, -1);
        if (what == -1)
            return;

        Log.d(TAG, "handleMessage is called : what = " + what);

        String jsonMessage = b.getStringExtra(BUNDLE_MESSAGE_FIELD);
        if ((jsonMessage == null) || (jsonMessage.equalsIgnoreCase("")))
            return;

        Log.d(TAG, "handleMessage is called : JSON = " + jsonMessage);

        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
                new ByteArrayToBase64TypeAdapter()).create();
        Type type = new TypeToken<IntercomMessage>() {}.getType();
        IntercomMessage message = gson.fromJson(jsonMessage, type);
        if (message == null)
            return;

        Log.d(TAG, "Parse Message is OK");
        Bitmap bmp = null;
//            byte[] byteArray = b.getByteArray(BUNDLE_IMAGE_FIELD);
//            if ((byteArray != null) && (byteArray.length > 0))
//                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        switch (what) {
            case CMD_RESPONSE_FROM_MAIN:
                onResponseFromMain(
                        message.getToWhere(),
                        message.getType(),
                        message.getMessage(),
                        bmp
                );
                break;

            case CMD_REQUEST_TO_PLUGIN:
                onRequestToPlugin(
                        message.getToWhere(),
                        message.getType(),
                        message.getMessage(),
                        bmp
                );
                break;
        }
    }

    // Get application context
    private Context getAppContext() {
        return FrameworkManager.getInstance().getApplicationContext();
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
        intent.setAction(BroadcastReceiverType.ACTION_TO_WALLET);
        intent.putExtra(BUNDLE_CMD_TYPE, messageType);
        intent.putExtra(BUNDLE_MESSAGE_FIELD, intercomMessage);
        FrameworkApplication.getAppContext().sendBroadcast(intent);
    }

    //
    // Listener interface declarations
    //
    public interface ReceiveListener {
        public void onRequestToPlugin(String fromBrand, String type, String jsonMessage, Bitmap bitmap);
        public void onResponseFromMain(String toBrand, String type, String jsonMessage, Bitmap bitmap);
    }
}