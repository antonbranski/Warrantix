package com.warrantix.main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.PDFViewActivity;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.PluginManager;
import com.warrantix.main.modules.b2c.AlarmReceiver;
import com.warrantix.main.modules.intercom.WarrantixCommManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class WarrantixApplication extends MultiDexApplication {
    private final static String TAG = WarrantixApplication.class.getSimpleName();

    public static final boolean DEBUG = true;
    public static final String PRIMARY_SCHEME = "app";
    public static final String LOG_APP_NAME = "WX:";

    private static WarrantixApplication instance = null;

    private WarrantixCommManager commManager = null;
    private PluginManager pluginManager = null;

    private Context appContext = null;
    private Activity currentActivity = null;

    private AlarmManager manager;
    private PendingIntent pendingIntent;

    public static WarrantixApplication getInstance() {
        if (instance == null)
            instance = new WarrantixApplication();

        return instance;
    }

    public static Context getAppContext() {
        return instance.appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        instance = this;
        appContext = getApplicationContext();

		if (WarrantixPreference.isFirstLaunched() == true)
			clearApplicationData();

		initManagers();
		initFontLibrary();
//		initSSLContext();

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
//		startAlarm(2*3600* b2cUtil.second);

//		// start the regIntentService to get the RegID(GCM Token of My Device).
//		Intent regIntent = new Intent(this, WarrantixRegIntentService.class);
//		startService(regIntent);
    }

    private void initSSLContext() {
        // SSL Handshake
        try {
            ProviderInstaller.installIfNeeded(appContext);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        // end SSl handshake
    }

    private void initManagers() {
        Log.d(TAG, "initManagers is called");
        commManager = WarrantixCommManager.getInstance();
        pluginManager = PluginManager.getInstance();
    }

    private static void initFontLibrary() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    //
    // Show PDF content
    //
    public void openPDFFiles(Context context, String name) {
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), name);
        try {
            in = assetManager.open(name);
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        Intent intent = new Intent(context, PDFViewActivity.class);
        intent.putExtra("path", name);

        if (context instanceof BaseActivity)
            ((BaseActivity) context).startActivity(intent, true);
        else
            context.startActivity(intent);
    }


    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    //
    // Confirm Dial dialog
    //
    public void showDial(final Activity activity, final String phoneNumber) {
        if (phoneNumber.equalsIgnoreCase("") == true) {
            MessageDialog dialog = new MessageDialog(activity);
            dialog.setTitle("Info");
            dialog.setMessage("No phone number");
            dialog.show();
            return;
        }

        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(activity);

        // request dial
        if (!marshMallowPermission.checkPermissionForDial()) {
            marshMallowPermission.requestPermissionForDial();
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Dial Now?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        intent.setPackage("com.android.phone");
                        intent.setData(Uri.parse("tel:" + phoneNumber));
                        if (activity instanceof BaseActivity)
                            ((BaseActivity) activity).startActivity(intent, true);
                        else
                            activity.startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    //
    // Store current Activated activity
    //
    public void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    public void showMessage(final String title, final String message) {
        if (currentActivity == null)
            return;

        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MessageDialog dialog = new MessageDialog(currentActivity);
                dialog.setTitle(title);
                dialog.setMessage(message);
                dialog.show();
            }
        });
    }

//	public void showMyLocation(Activity context, Location location) {
//		String uri = "";
//		if (location != null)
//			uri = String.format(Locale.ENGLISH, "geo:%f,%f", location.getLatitude(), location.getLongitude());
//
//		Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//		mapIntent.setPackage("com.google.android.apps.maps");
//		if (mapIntent.resolveActivity(getPackageManager()) != null) {
//			context.startActivity(mapIntent);
//		}
//	}

    public void startAlarm(int interval) {

        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//		Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void endAlarm() {

        manager.cancel(pendingIntent);
    }

    public boolean checkInstallation(String uri) {
        PackageManager pm = WarrantixApplication.getAppContext().getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s + " DELETED ");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
