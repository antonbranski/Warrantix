package com.warrantix.main.modules.intercom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.warrantix.framework.intercom.WarrantixIntercomMessenger;

public class WarrantixCommReceiver extends BroadcastReceiver {
    private static final String TAG = WarrantixCommReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive is called.");

        WarrantixMainMessenger globalMessenger = WarrantixMainMessenger.getInstance();
        if (globalMessenger != null)
            globalMessenger.handleMessage(intent);
    }
}