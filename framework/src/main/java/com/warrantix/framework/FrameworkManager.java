package com.warrantix.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.warrantix.framework.activity.PluginBaseActivity;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.framework.intercom.PluginCommManager;
import com.warrantix.framework.intercom.message.PluginInformation;

public class FrameworkManager {
    private static final String TAG = FrameworkManager.class.getSimpleName();
    private static FrameworkManager instance = null;

    private Context applicationContext = null;

    private String aliasName = "";
    private String brandScreen = "";
    private String retailerScreen = "";

    public static FrameworkManager getInstance() {
        if (instance == null)
            instance = new FrameworkManager();

        return instance;
    }

    public FrameworkManager() {
        BusProvider.get().register(this);
    }

    //
    // static exported functions
    //
    public static void registerAliasName(String aliasName) {
        if (instance == null)
            return;

        instance._registerAliasName(aliasName);
    }

    public static void registerBrand(Class clz) {
        if (instance == null)
            return;

        instance._registerBrand(clz);
    }

    public static void registerRetailer(Class clz) {
        if (instance == null)
            return;

        instance._registerRetailer(clz);
    }

    public static void showAppIcon() {
        if (instance == null)
            return;

        instance._showAppIcon();
    }

    public static void hideAppIcon() {
        if (instance == null)
            return;

        instance._hideAppIcon();
    }

    public static void launchProducts(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchProducts(ctx, i);
    }

    public static void launchSocial(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchSocial(ctx, i);
    }

    public static void launchAMC(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchAMC(ctx, i);
    }

    public static void launchInsurance(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchInsurance(ctx, i);
    }

    public static void launchFinance(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchFinance(ctx, i);
    }

    public static void launchAccessories(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchAccessories(ctx, i);
    }

    public static void launchMap(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchMap(ctx, i);
    }

    public static void launchWalletMain(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchWalletMain(ctx, i);
    }

    public static void launchSocialReveal(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchSocialReveal(ctx, i);
    }

    public static void launchSocialRefer(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchSocialRefer(ctx, i);
    }

    public static void launchSocialReview(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchSocialReview(ctx, i);
    }

    public static void launchSocialRank(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchSocialRank(ctx, i);
    }

    public static void launchSocialChat(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchSocialChat(ctx, i);
    }

    public static void launchProductsDetailScheduleService(Context ctx, Intent i) {
        if (instance == null)
            return;

        instance._launchProductsDetailScheduleService(ctx, i);
    }

    public static void sync(PluginCommManager commManager) {
        if (instance == null)
            return;

        instance._sync(commManager);
    }

    //
    // member functions
    //

    private void _registerAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    private void _sync(PluginCommManager commManager) {
        Log.d(TAG, "sync is called");

        // check the validation
        if (commManager == null)
            return;

        // create PluginInformation sturcture & send it
        PluginInformation pluginInfo = new PluginInformation();
        pluginInfo.setBrandApp(brandScreen);
        pluginInfo.setRetailerApp(retailerScreen);
        commManager.syncPluginInfo(pluginInfo);
    }

    private void _launchSocialReveal(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandSocial"));
        intent.putExtra("selected", 0);
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchSocialRefer(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandSocial"));
        intent.putExtra("selected", 1);
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchSocialReview(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandSocial"));
        intent.putExtra("selected", 2);
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchSocialRank(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandSocial"));
        intent.putExtra("selected", 3);
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchSocialChat(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandSocial"));
        intent.putExtra("selected", 4);
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchMap(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.MapsActivity"));
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchWalletMain(Context ctx, Intent intent) {
        String mainURI = "com.warrantix.main";
        intent.setComponent(new ComponentName(mainURI, mainURI + ".activities.SplashActivity"));
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchProducts(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandProducts"));
        intent.putExtra("title", "Products");
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchSocial(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletBrandSocial"));
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchAMC(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletMarketplaceAMC"));
        intent.putExtra("from", ctx.getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchInsurance(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletMarketplaceInsurance"));
        intent.putExtra("from", ctx.getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchFinance(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletMarketplaceFinance"));
        intent.putExtra("from", ctx.getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    private void _launchAccessories(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.WalletMarketplaceSubCategory"));
        intent.putExtra("title", "eSHOP ACCESSORIES");
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }


    private void _launchProductsDetailScheduleService(Context ctx, Intent intent) {
        intent.setComponent(new ComponentName("com.warrantix.main", "com.warrantix.main.activities.productdetail.ProductsDetailScheduleService"));
        intent.putExtra("from", getApplicationContext().getPackageName());

        if (ctx instanceof PluginBaseActivity)
            ((PluginBaseActivity) ctx).startActivity(intent, true);
        else
            ctx.startActivity(intent);
    }

    public void _showAppIcon() {
        Log.d(TAG, "showAppIcon is called");

        if (aliasName.equalsIgnoreCase("") == false) {
            FrameworkApplication.getAppContext().getPackageManager().setComponentEnabledSetting(
                    new ComponentName(FrameworkApplication.getAppContext().getPackageName(),
                            FrameworkApplication.getAppContext().getPackageName() + "." + aliasName),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        }
    }

    public void _hideAppIcon() {
        Log.d(TAG, "hideAppIcon is called");

        if (aliasName.equalsIgnoreCase("") == false) {
            FrameworkApplication.getAppContext().getPackageManager().setComponentEnabledSetting(
                    new ComponentName(FrameworkApplication.getAppContext().getPackageName(),
                            FrameworkApplication.getAppContext().getPackageName() + "." + aliasName),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        }
    }

    public void initialize(Context appContext) {
        this.applicationContext = appContext;
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

    private void _registerBrand(Class clz) {
        this.brandScreen = clz.getName();
    }

    private void _registerRetailer(Class clz) {
        this.retailerScreen = clz.getName();
    }

}