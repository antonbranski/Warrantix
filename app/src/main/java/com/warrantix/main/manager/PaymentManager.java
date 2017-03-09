package com.warrantix.main.manager;


import android.app.Activity;

public class PaymentManager {
    private static final String TAG = PaymentManager.class.getSimpleName();

    public static final String CITRUS_ACCESS_KEY = "T6UTG8KTVTY6TCCZNACX";
    public static final String CITRUS_SIGNUP_ID = "2y0voyc9j0-signup";
    public static final String CITRUS_SIGNUP_SECRET = "34fb067e7a2fcd36ce589a5f11c6fd2c";
    public static final String CITRUS_SIGNIN_ID = "2y0voyc9j0-signin";
    public static final String CITRUS_SIGNIN_SECRET = "ae7a693a99a50061453152ea339af8e8";
    public static final String CITRUS_VANITY = "2y0voyc9j0";

//    T6UTG8KTVTY6TCCZNACX
//    871f019a65e8ab42fada5a43ddd6a1a5c071eb95

    private static PaymentManager instance = null;

//    private CitrusClient citrusClient;

    public static PaymentManager getInstance() {
        if (instance == null)
            instance = new PaymentManager();

        return instance;
    }

    public PaymentManager() {
//        citrusClient = CitrusClient.getInstance(WarrantixApplication.getInstance().getApplicationContext());
//        citrusClient.init(
//                CITRUS_SIGNUP_ID,
//                CITRUS_SIGNUP_SECRET,
//                CITRUS_SIGNIN_ID,
//                CITRUS_SIGNIN_SECRET,
//                CITRUS_VANITY,
//                Environment.SANDBOX );
//        citrusClient.enableLog(true);

//        citrusClient.isUserSignedIn(new com.citrus.sdk.Callback<Boolean>() {
//            @Override
//            public void success(Boolean loggedIn) {
//                Log.v(TAG, "Citrus User Login Succeeded");
//            }
//
//            @Override
//            public void error(CitrusError error) {
//                Log.v(TAG, "Citrus User Login Failed");
//            }
//        });
    }

    public static void addShoppingCart(Activity activity, String billGeneratorURL, String returnURL) {
        if (activity == null)
            return;

//        CitrusFlowManager.initCitrusConfig(CITRUS_SIGNUP_ID,
//                CITRUS_SIGNUP_SECRET, CITRUS_SIGNIN_ID,
//                CITRUS_SIGNIN_SECRET,
//                activity.getResources().getColor(R.color.wx_primary_color),
//                activity,
//                Environment.SANDBOX, CITRUS_VANITY, billGeneratorURL , returnURL);
//        CitrusFlowManager.startShoppingFlow(activity,"developercitrus@mailinator.com", "8424019644", "5");
    }
}