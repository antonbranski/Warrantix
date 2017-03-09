package com.warrantix.main.modules.intercom;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.warrantix.framework.common.convertor.ByteArrayToBase64TypeAdapter;
import com.warrantix.framework.intercom.WarrantixIntercomMessenger;
import com.warrantix.framework.intercom.model.IntercomMessage;
import com.warrantix.framework.intercom.model.IntercomMessageType;
import com.warrantix.main.modules.intercom.model.BrandInfo;
import com.warrantix.main.modules.intercom.model.BrandTypeData;

/**
 * Reference : https://github.com/android/platform_development/blob/master/samples/ApiDemos/src/com/example/android/apis/app/MessengerService.java
 *
 */

//
// This class is for main side intercommunication service
//

public class WarrantixIntercomService extends Service
{
    private static final String TAG = WarrantixIntercomService.class.getSimpleName();

    //
    // Registered Plugins Info Container
    //
    ArrayList<BrandInfo> mClients = new ArrayList<BrandInfo>();

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            Log.d(TAG, "WarrantixIntercomService handleMessage is called. msg.what = " + msg.what);

            // Parse bundle content
            String brandName = "";
            byte[] brandIconByteArray = null;
            Bundle b = msg.getData();
            if (b != null) {
                brandName = b.getString(WarrantixIntercomMessenger.BUNDLE_BRANDNAME_FIELD);
                brandIconByteArray = b.getByteArray(WarrantixIntercomMessenger.BUNDLE_IMAGE_FIELD);
            }

            // check command type
            switch (msg.what)
            {
                case WarrantixIntercomMessenger.CMD_REGISTER_PLUGIN: {
                    Log.d(TAG, "WarrantixIntercomService handleMessage : CMD_REGISTER_PLUGIN is called");

                    // add new brand data instance
                    BrandTypeData brandTypeData = new BrandTypeData();
                    brandTypeData.setBrandName(brandName);
                    brandTypeData.setBrandIconByteArray(brandIconByteArray);

                    // remove the existing brand data instance
                    for (int i = 0; i < mClients.size(); i++) {
                        BrandTypeData orgTypeData = mClients.get(i).getBrandTypeData();
                        if (orgTypeData.getBrandName().equalsIgnoreCase(brandName)) {
                            Log.d(TAG, "Remove existing channel");
                            mClients.remove(i);
                        }
                    }

                    mClients.add(new BrandInfo(msg.replyTo, brandTypeData));
                }
                    break;

                case WarrantixIntercomMessenger.CMD_UNREGISTER_PLUGIN: {
                    Log.d(TAG, "WarrantixIntercomService handleMessage : CMD_UNREGISTER_PLUGIN is called");

                    // remove brand data instance
                    for (int i = 0; i < mClients.size(); i++) {
                        BrandTypeData brandTypeData = mClients.get(i).getBrandTypeData();
                        if (brandTypeData.getBrandName().equalsIgnoreCase(brandName))
                            mClients.remove(i);
                    }
                }
                    break;

                default:
                    processCommand(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {
        Log.d(TAG, "WarrantixIntercomService is created");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "WarrantixIntercomService is destroyed");
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    //
    // Process command from Plugins
    //
    private void processCommand(Message msg) {

        // check bundle
        Bundle b = msg.getData();
        if (b == null)
            return;

        // Parse Intercom Message
        String jsonMessage = b.getString(WarrantixIntercomMessenger.BUNDLE_MESSAGE_FIELD);
        if ((jsonMessage == null) || (jsonMessage.equalsIgnoreCase("")))
            return;

        Log.d(TAG, "JSON Message = " + jsonMessage);

        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
                new ByteArrayToBase64TypeAdapter()).create();
        Type type = new TypeToken<IntercomMessage>() {}.getType();
        IntercomMessage message = gson.fromJson(jsonMessage, type);
        if (message == null)
            return;

        // Extract additional bitmap data
//        byte[] byteArray = b.getByteArray(WarrantixIntercomMessenger.BUNDLE_IMAGE_FIELD);
//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        boolean bReceiverFound = false;
        String errorMessage = "";

        // check command type
        switch (msg.what) {
            case WarrantixIntercomMessenger.CMD_REQUEST_TO_MAIN: {

                for (int i = 0; i < mClients.size(); i++) {
                    BrandTypeData brandTypeData = mClients.get(i).getBrandTypeData();

                    // try to find out the receiver brand plugin
                    if (brandTypeData.getBrandName().equalsIgnoreCase(WarrantixIntercomMessenger.TYPE_WARRANTIX_MAIN) == true) {
                        Log.d(TAG, "CMD_RESPONSE_FROM_MAIN: Found Target");
                        sendMessage(mClients.get(i).getMessenger(), WarrantixIntercomMessenger.CMD_REQUEST_TO_MAIN, jsonMessage);
                        bReceiverFound = true;
                        //break;
                    }
                }

                // send back the error message when receiver not found
                if (bReceiverFound == false)
                    sendBackErrorMessage(msg.replyTo, WarrantixIntercomMessenger.CMD_RESPONSE_FROM_MAIN,
                            message.getToWhere(), message.getFromWhere(),
                            IntercomMessageType.TYPE_BRAND_NOT_FOUND, errorMessage);
            }
                break;

            case WarrantixIntercomMessenger.CMD_RESPONSE_FROM_MAIN: {

                for (int i = 0; i < mClients.size(); i++) {
                    BrandTypeData brandTypeData = mClients.get(i).getBrandTypeData();

                    // try to find out the receiver brand plugin
                    if (brandTypeData.getBrandName().equalsIgnoreCase(message.getToWhere()) == true) {
                        Log.d(TAG, "CMD_RESPONSE_FROM_MAIN: Found Target");
                        sendMessage(mClients.get(i).getMessenger(), WarrantixIntercomMessenger.CMD_RESPONSE_FROM_MAIN, jsonMessage);
                        bReceiverFound = true;
                        //break;
                    }
                }

                // send back the error message when receiver not found
                if (bReceiverFound == false)
                    sendBackErrorMessage(msg.replyTo, WarrantixIntercomMessenger.CMD_RESPONSE_FROM_PLUGIN,
                            message.getToWhere(), message.getFromWhere(),
                            IntercomMessageType.TYPE_BRAND_NOT_FOUND, errorMessage);
            }
                break;

            case WarrantixIntercomMessenger.CMD_REQUEST_TO_PLUGIN: {

                for (int i = 0; i < mClients.size(); i++) {
                    BrandTypeData brandTypeData = mClients.get(i).getBrandTypeData();

                    // try to find out the receiver brand plugin
                    if (brandTypeData.getBrandName().equalsIgnoreCase(message.getToWhere()) == true) {
                        sendMessage(mClients.get(i).getMessenger(), WarrantixIntercomMessenger.CMD_REQUEST_TO_PLUGIN, jsonMessage);
                        bReceiverFound = true;
                        break;
                    }
                }

                // send back the error message when receiver not found
                if (bReceiverFound == false) {
                    sendBackErrorMessage(msg.replyTo, WarrantixIntercomMessenger.CMD_RESPONSE_FROM_PLUGIN,
                            message.getToWhere(), message.getFromWhere(),
                            IntercomMessageType.TYPE_BRAND_NOT_FOUND, errorMessage);
                }
            }
                break;

            case WarrantixIntercomMessenger.CMD_RESPONSE_FROM_PLUGIN: {

                for (int i = 0; i < mClients.size(); i++) {
                    BrandTypeData brandTypeData = mClients.get(i).getBrandTypeData();

                    // try to find out the receiver brand plugin
                    if (brandTypeData.getBrandName().equalsIgnoreCase(WarrantixIntercomMessenger.TYPE_WARRANTIX_MAIN) == true) {
                        sendMessage(mClients.get(i).getMessenger(), WarrantixIntercomMessenger.CMD_RESPONSE_FROM_PLUGIN, jsonMessage);
                        bReceiverFound = true;
                        break;
                    }
                }

                // send back the error message when receiver not found
                if (bReceiverFound == false) {
                    sendBackErrorMessage(msg.replyTo, WarrantixIntercomMessenger.CMD_RESPONSE_FROM_MAIN,
                            message.getToWhere(), message.getFromWhere(),
                            IntercomMessageType.TYPE_BRAND_NOT_FOUND, errorMessage);
                }
            }
                break;

//            case WarrantixIntercomMessenger.MSG_REQUEST_TO_SERVICE: {
//                processRequestToService(msg.replyTo, message.getFromWhere(), message.getType());
//            }
//                break;

            default:

        }
    }

    //
    // Reply to sender with ERROR message
    //
    private void sendBackErrorMessage(Messenger replyTo, int messageType,
                                      String fromWhere, String toWhere,
                                      String type, String errorMessage) {
        // send error message
        IntercomMessage message = createIntercomMessage(fromWhere, toWhere, type, errorMessage);
        String intercomMessage = createMessage(message);
        sendMessage(replyTo, messageType, intercomMessage);
    }

    //
    // process MSG_REQUEST_TO_SERVICE
    //
    private void processRequestToService(Messenger messenger, String fromWhere, String type) {
        if (type.equalsIgnoreCase(IntercomMessageType.TYPE_GET_REGISTERED_PLUGINS)) {

            // create content message
            Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
                    new ByteArrayToBase64TypeAdapter()).create();

            ArrayList<BrandTypeData> brandArray = new ArrayList<>();
            for (int i = 0; i < mClients.size(); i++) {
                brandArray.add(mClients.get(i).getBrandTypeData());
            }

            Type typeClass = new TypeToken<ArrayList<BrandTypeData>>(){}.getType();
            String contentMessage = gson.toJson(brandArray, typeClass);

            // send message
            IntercomMessage message = createIntercomMessage(fromWhere, WarrantixIntercomMessenger.TYPE_WARRANTIX_MAIN, type, contentMessage);
            String intercomMessage = createMessage(message);
            sendMessage(messenger, WarrantixIntercomMessenger.CMD_RESPONSE_FROM_MAIN, intercomMessage);

        }
        else {

        }
    }

    //
    // Basic operation functions
    //

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

    private void sendMessage(Messenger messenger, int messageType, String intercomMessage) {

        Message msg = Message.obtain(null, messageType);

        Bundle b = new Bundle();
        b.putString(WarrantixIntercomMessenger.BUNDLE_MESSAGE_FIELD, intercomMessage);

        msg.setData(b);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

//END_INCLUDE(service)