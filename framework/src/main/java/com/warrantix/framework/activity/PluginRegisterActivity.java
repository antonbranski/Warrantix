package com.warrantix.framework.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.squareup.otto.Subscribe;
import com.warrantix.framework.R;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.framework.common.event.SyncPluginInfoSuccessEvent;
import com.warrantix.framework.intercom.WarrantixIntercomMessenger;

public class PluginRegisterActivity extends Activity {

    private static final String TAG = PluginRegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_register);
    }

    @Override
    protected void onResume() {
        super.onResume();

        BusProvider.get().register(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        BusProvider.get().unregister(this);
    }
}