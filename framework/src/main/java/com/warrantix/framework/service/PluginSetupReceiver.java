package com.warrantix.framework.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.warrantix.framework.FrameworkApplication;

public class PluginSetupReceiver extends BroadcastReceiver {
    private static final String TAG = PluginSetupReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getDataString();
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.d(TAG, packageName + " Package installed");
        }
        else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            Log.d(TAG, packageName + " Package removed");

            if (packageName.equalsIgnoreCase("package:com.warrantix.main") == true) {
                Log.d(TAG, "Wallet Package removed");
                Log.d(TAG, "Brand Package Name = " + FrameworkApplication.getAppContext().getPackageName());

//                FrameworkApplication.getAppContext().getPackageManager().setComponentEnabledSetting(
//                        new ComponentName("com.warrantix.hero", "com.warrantix.hero.BrandHero"),
//                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

                Intent deleteMe = new Intent(Intent.ACTION_DELETE);
                deleteMe.setData(Uri.parse("package:" + FrameworkApplication.getAppContext().getPackageName()));
                deleteMe.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(deleteMe);
            }
        }
    }
}

