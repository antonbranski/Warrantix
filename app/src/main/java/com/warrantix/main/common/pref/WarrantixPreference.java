package com.warrantix.main.common.pref;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Device;
import com.warrantix.main.common.rest.model.Wallet;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WarrantixPreference {

    public static String TAG = WarrantixPreference.class.getSimpleName();
    private static boolean TEST = false;

    public static final String WARRANTIX_CONFIG = "warrantixConfig";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    public static final String TYPE_AMC = "AMC";
    public static final String TYPE_ACCESSORIES = "accessories";
    public static final String TYPE_FINANCE = "finance";
    public static final String TYPE_INSURANCE = "insurance";
    public static final String BRANDID_HEROID = "heroID";
    public static final String SUBCATEGORY_TITLE_USED_PRODUCTS = "USED PRODUCTS";
    public static final String SUBCATEGORY_TITLE_ESHOP_ACCESSORIES = "eSHOP ACCESSORIES";
    public static final String WARRANTIXID = "warrantixID";

    //
    // Warrantix Main App Global Configuration
    //
    public static WarrantixConfig warrantixConfig;

    //
    // Warrantix Global Config ACCESS Functions
    //
    public static void loadConfig() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        String json = sharedPreferences.getString(WARRANTIX_CONFIG, "");
        Type type = new TypeToken<WarrantixConfig>() {
        }.getType();

        warrantixConfig = new Gson().fromJson(json, type);
        Log.v(TAG, "Loaded Warrantix configuration information.");

        if (warrantixConfig == null) {
            warrantixConfig = new WarrantixConfig();

            if (WarrantixPreference.TEST == true)
                warrantixConfig = createMockupConfig();
        }
    }

    public static void writeConfig() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        String json = new Gson().toJson(warrantixConfig);

        Boolean bCommit = sharedPreferences.edit().putString(WARRANTIX_CONFIG, json).commit();
        Log.v(TAG, "Wrote Warrantix configuration information. status = " + bCommit);
    }

    //
    // Warrantix Other Module Preference ACCESS Functions
    //
    public static String loadPreference(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        String content = sharedPreferences.getString(key, "");
        Log.d(TAG, "Key = " + key + " Content = " + content);
        return content;
    }

    public static boolean savePreference(String key, String content) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        Boolean bCommit = sharedPreferences.edit().putString(key, content).commit();
        Log.d(TAG, "Key = " + key + " Content = " + content + " success = " + bCommit);
        return bCommit;
    }

    public static boolean saveBooleanPreference(String key, boolean b) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        Boolean bCommit = sharedPreferences.edit().putBoolean(key, b).commit();
        Log.d(TAG, "Key = " + key + " Content = " + b + " success = " + bCommit);
        return bCommit;
    }


    public static boolean loadBooleanPreference(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        Log.d(TAG, "Key = " + key + " Content = " + sharedPreferences.getBoolean(key, false));
        return sharedPreferences.getBoolean(key, false);
    }

    public static boolean isFirstLaunched() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        boolean bLaunched = sharedPreferences.getBoolean("firstlaunch", true);

        saveBooleanPreference("firstlaunch", false);

        Log.d(TAG, "Key = firstlaunch Content = " + bLaunched);
        return bLaunched;
    }


    //
    // TEST Purpose : Mockup Config Function
    //
    private static WarrantixConfig createMockupConfig() {
        WarrantixConfig mockupConfig = new WarrantixConfig();

        // main customer
        Customer mainCustomer = new Customer();
        mainCustomer.setId("TestMainCustomer");
        mainCustomer.setUsername("Test@gmail.com");
        mainCustomer.setPassword("11111111");
        mainCustomer.setWalletID("TestMainWallet");

        Contact mainContact = new Contact();
        mainContact.setTel("123456789");
        mainCustomer.setContact(mainContact);

        mockupConfig.setMainCustomer(mainCustomer);

        // main device
        Device mainDevice = new Device();
        mainDevice.setId("TestMainDevice");
        mainDevice.setTel("123456789");

        mockupConfig.setMainDevice(mainDevice);

        // main wallet
        Wallet mainWallet = new Wallet();
        mainWallet.setPrimaryID("TestMainWallet");
        mainWallet.setId("TestMainWallet");

        mockupConfig.setMainWallet(mainWallet);

        // family customers
        Customer family1 = new Customer();
        family1.setUsername("children1@gmail.com");
        family1.setPassword("22222222");
        family1.setId("Family1");

        Device family1Device1 = new Device();
        family1Device1.setId("family1device1");
        family1Device1.setTel("1111111111");

        Device family1Device2 = new Device();
        family1Device2.setId("family1device2");
        family1Device2.setTel("1111111112");

        ArrayList<String> family1DeviceIDs = new ArrayList<>();
        family1DeviceIDs.add("family1device1");
        family1DeviceIDs.add("family1device2");

        family1.addDevice("family1device1", family1Device1);
        family1.addDevice("family1device2", family1Device2);
        family1.setDeviceID(family1DeviceIDs);


        Customer family2 = new Customer();
        family2.setUsername("children2@gmail.com");
        family2.setPassword("33333333");
        family2.setId("Family2");

        Device family2Device1 = new Device();
        family2Device1.setId("family2device1");
        family2Device1.setTel("444444444");

        Device family2Device2 = new Device();
        family2Device2.setId("family2device2");
        family2Device2.setTel("4444444442");

        ArrayList<String> family2DeviceIDs = new ArrayList<>();
        family2DeviceIDs.add("family2device1");
        family2DeviceIDs.add("family2device2");

        family2.addDevice("family2device1", family2Device1);
        family2.addDevice("family2device2", family2Device2);
        family2.setDeviceID(family2DeviceIDs);

//        mockupConfig.addFamilyCustomer(family1);
//        mockupConfig.addFamilyCustomer(family2);


        return mockupConfig;
    }

}