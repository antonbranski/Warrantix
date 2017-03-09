package com.warrantix.main.modules.b2c;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.rest.model.B2CGCMMessageContent;
import com.warrantix.main.common.rest.model.B2CGCMMessageData;
import com.warrantix.main.common.rest.model.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class b2cUtil{

    private static final String TAG = b2cUtil.class.getSimpleName();

    public static final String B2C_IMAGE_URL = "b2cImageURL";
    public static final String INTERVAL_Type = "intervalType";
    public static final String B2C_WEBVIEW_URL = "b2cWebviewURL";
    public static final String DEFAULT_INTERVAL_TYPE = "2 Hours";
    public static final int second = 1000;

    public static void saveB2CURL(Message lastMessage){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());

        String imageUrl = "";
        String webviewUrl = "";

        try {
            String content = lastMessage.getContent();
            JSONObject jsonObject = new JSONObject(content);
            imageUrl = jsonObject.getString("imageUrl");
            webviewUrl = jsonObject.getString("webviewUrl");
        } catch (JSONException e){
            e.printStackTrace();
        }

        sharedPreferences.edit().putString(b2cUtil.B2C_IMAGE_URL, imageUrl).apply();
        sharedPreferences.edit().putString(b2cUtil.B2C_WEBVIEW_URL, webviewUrl).apply();

    }

    public static B2CGCMMessageContent parseFromJson(String message){
        B2CGCMMessageContent b2CGCMMessageContent = new B2CGCMMessageContent();

        if (message != null) {
            try {
                JSONObject json = new JSONObject(message);

                String command = json.getString("command");
                String type = json.getString("type");
                String apsUrl = json.getString("appServerUrl");
                String importance = json.getString("importance");

                B2CGCMMessageData b2CGCMMessageData = new B2CGCMMessageData();
                b2CGCMMessageData.setType(type);
                b2CGCMMessageData.setApsUrl(apsUrl);
                b2CGCMMessageData.setCommand(command);
                b2CGCMMessageData.setImportance(importance);

                b2CGCMMessageContent.setData(b2CGCMMessageData);
                b2CGCMMessageContent.setTo("");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Couldn't get any data from the url");
        }

        return b2CGCMMessageContent;
    }
}