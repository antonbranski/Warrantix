package com.warrantix.main.activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;


import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.modules.b2c.b2cUtil;

public class MediaWebviewActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_webview);

        webView = (WebView) findViewById(R.id.mediaWebview);

        webView.getSettings().setJavaScriptEnabled(true);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        String webviewURL = sharedPreferences.getString(b2cUtil.B2C_WEBVIEW_URL, "");
        webView.loadUrl(webviewURL);
    }
}
