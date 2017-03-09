package com.warrantix.framework.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.warrantix.framework.R;
import com.warrantix.framework.common.bus.BusProvider;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PluginBaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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

    // ------------------------------------------------------------
    // Activity Transition Support functions
    // ------------------------------------------------------------
    public void startActivity(Intent intent, boolean animate) {
        super.startActivity(intent);
        if (animate)
            overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
    }

    public void startActivityForResult(Intent intent, int requestCode, boolean animate) {
        super.startActivityForResult(intent, requestCode);
        if (animate)
            overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
    }

    public void finish(boolean animate) {
        super.finish();
        if (animate)
            overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
    }
}