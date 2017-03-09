package com.warrantix.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FrameworkApplication extends Application {

    private FrameworkManager frameworkManager;

    protected static FrameworkApplication instance = null;
    public static Context getAppContext() {
        if (instance == null)
            return null;

        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeFrameManager();
        instance = this;
    }

    private void initializeFrameManager() {
        frameworkManager = FrameworkManager.getInstance();
        frameworkManager.initialize(getApplicationContext());
    }

    public FrameworkManager getFrameworkManager() {
        return frameworkManager;
    }
}