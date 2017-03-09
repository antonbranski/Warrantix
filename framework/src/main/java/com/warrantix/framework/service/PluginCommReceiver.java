package com.warrantix.framework.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.warrantix.framework.intercom.WarrantixIntercomMessenger;

public class PluginCommReceiver extends BroadcastReceiver {
    private static final String TAG = PluginCommReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive is called.");

        WarrantixIntercomMessenger globalMessenger = WarrantixIntercomMessenger.getInstance();
        if (globalMessenger != null)
            globalMessenger.handleMessage(intent);
    }
}