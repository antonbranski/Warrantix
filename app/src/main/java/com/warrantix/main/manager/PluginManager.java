package com.warrantix.main.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.warrantix.framework.intercom.message.PluginInformation;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.command.WarrantixCommands;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.modules.intercom.WarrantixCommManager;

import java.lang.reflect.Type;
import java.util.HashMap;

public class PluginManager {

    private static final String TAG = PluginManager.class.getSimpleName();
    private static final String TYPE_PLUGIN_INFOS = "PluginManager.PluginInfos";

    private static PluginManager instance = null;
    public static PluginManager getInstance() {
        if (instance == null) {
            instance = new PluginManager();
            instance.initialize();
        }

        return instance;
    }

    public static boolean checkInstallation(String pluginUri) {
        return WarrantixApplication.getInstance().checkInstallation(pluginUri);
    }

    private HashMap<String, PluginInformation> pluginInfos = null;

    public PluginManager() {

    }

    protected void initialize() {

        // load plugins information
        String strPluginInfos = WarrantixPreference.loadPreference(TYPE_PLUGIN_INFOS);
        Type type = new TypeToken<HashMap<String, PluginInformation>>(){}.getType();
        pluginInfos = new Gson().fromJson(strPluginInfos, type);
        if (pluginInfos == null)
            pluginInfos = new HashMap<>();

    }

    public void syncPluginInfo(String fromBrand, PluginInformation pluginInfo) {
        pluginInfos.put(fromBrand, pluginInfo);

        // save configuration
        String content = new Gson().toJson(pluginInfos);
        WarrantixPreference.savePreference(TYPE_PLUGIN_INFOS, content);

        pluginInfo.setStatus(true);
        if (fromBrand.equalsIgnoreCase("com.warrantix.hero")) {
            pluginInfo.setShowIcon(true);
            WarrantixApplication.getAppContext().getPackageManager().setComponentEnabledSetting(
                    new ComponentName("com.warrantix.main", "com.warrantix.main.MainAlias"),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
        else {
            pluginInfo.setShowIcon(false);
        }

        WarrantixCommManager.getInstance().replySyncToPlugin(fromBrand, pluginInfo);
    }

    public Intent intentForBrand(String pluginUri) {
        PluginInformation pluginInfo = pluginInfos.get(pluginUri);
        if (pluginInfo == null)
            return null;

        // get brand screen url
        String brandScreen = pluginInfo.getBrandApp();
        if (brandScreen.equalsIgnoreCase(""))
            return null;

        // create intent for brand screen
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pluginUri, brandScreen));
        intent.putExtra(WarrantixCommands.PLUGIN_LAUNCHED, "yes");
        return intent;
    }

    public Intent intentForRetailer(String pluginUri) {
        PluginInformation pluginInfo = pluginInfos.get(pluginUri);
        if (pluginInfo == null)
            return null;

        // get retailer screen url
        String retailerScreen = pluginInfo.getRetailerApp();
        if (retailerScreen.equalsIgnoreCase(""))
            return null;

        // create intent for retailer screen
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pluginUri, retailerScreen));
        intent.putExtra(WarrantixCommands.PLUGIN_LAUNCHED, "yes");
        return intent;
    }

    public Intent intentForInstallation(String pluginUri) {
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pluginUri));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return marketIntent;
    }
}