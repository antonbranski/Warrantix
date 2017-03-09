package com.warrantix.main.activities.brandlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandAlertNotification;
import com.warrantix.main.modules.b2c.b2cUtil;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettings extends BaseActivity {

    private Intent mIntent;
    private TextView titleTEXT;
    private LinearLayout keepaliveTEXT;
    private TextView keepaliveEXTEXT;
    private TextView hoursTEXT;
    private LinearLayout adddeviceTEXT;
    private TextView adddeviceEXTEXT;
    private LinearLayout addcustomerTEXT;
    private TextView addcustomerEXTEXT;
    private LinearLayout alertsTEXT;
    private LinearLayout defaultTEXT;
    private LinearLayout aboutTEXT;

    private ImageButton addCutomerBtn;
    private ImageButton addDeviceBtn;
    private ImageButton defaultBtn;
    private ImageButton aboutBtn;

    private ListView listHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        String intervalType = sharedPreferences.getString(b2cUtil.INTERVAL_Type, "");
        if (intervalType == null || intervalType.equals("")) {
            intervalType = b2cUtil.DEFAULT_INTERVAL_TYPE;
        }

        hoursTEXT.setText(intervalType);
    }

    public void Initialize() {

        titleTEXT = (TextView) findViewById(R.id.title);
        keepaliveTEXT = (LinearLayout) findViewById(R.id.keepTXT);
        keepaliveEXTEXT = (TextView) findViewById(R.id.keepaliveEXTXT);
        hoursTEXT = (TextView) findViewById(R.id.hoursTXT);

        adddeviceTEXT = (LinearLayout) findViewById(R.id.addDeviceTXT);
        adddeviceEXTEXT = (TextView) findViewById(R.id.addDeviceExTXT);
        addcustomerTEXT = (LinearLayout) findViewById(R.id.addCustomerTXT);
        addcustomerEXTEXT = (TextView) findViewById(R.id.addCustomerExTXT);
        alertsTEXT = (LinearLayout) findViewById(R.id.alertTXT);
        defaultTEXT = (LinearLayout) findViewById(R.id.defaultTXT);
        aboutTEXT = (LinearLayout) findViewById(R.id.aboutTXT);

        adddeviceTEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandListSettings.this, WalletBrandListSettingsWalletID.class);
                mIntent.putExtra("customer", false);
                startActivity(mIntent, true);
            }
        });
        defaultTEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mIntent =  new Intent(WalletBrandListSettings.this, WalletBrandListSettingsDefaultMarketplace.class);
                mIntent = new Intent(WalletBrandListSettings.this, WalletBrandListSettingsDefaultMarketplaceNew.class);
                startActivity(mIntent, true);
            }
        });
        aboutTEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandListSettings.this, WalletBrandListSettingsAbout.class);
                startActivity(mIntent, true);
            }
        });
        addcustomerTEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandListSettings.this, WalletBrandListSettingsWalletID.class);
                startActivity(mIntent, true);
            }
        });
        alertsTEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandListSettings.this, WalletBrandAlertNotification.class);
                startActivity(mIntent, true);
            }
        });

        listHours = (ListView) findViewById(R.id.listHours);
        listHours.setVisibility(View.GONE);

        final String[] values = new String[]{"3 Hours", "2 Hours", "1 Hours", "30 mins", "15 mins", "10 mins"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_listview, android.R.id.text1, values);
        listHours.setAdapter(adapter);
        listHours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                int second = b2cUtil.second;
                int interval = 2 * 3600 * second;
                String intervalType = values[position];

                String itemValue = (String) listHours.getItemAtPosition(position);
                hoursTEXT.setText(itemValue);
                listHours.setVisibility(View.GONE);

//                WarrantixApplication.getInstance().endAlarm();

                switch (itemPosition) {
                    case 0:
                        interval = 3 * 3600 * second;
                        break;
                    case 1:
                        interval = 2 * 3600 * second;
                        break;
                    case 2:
                        interval = 1 * 3600 * second;
                        break;
                    case 3:
                        interval = 30 * 60 * second;
                        break;
                    case 4:
                        interval = 15 * 60 * second;
                        break;
                    case 5:
                        interval = 10 * 60 * second;
                        break;
                }

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
                sharedPreferences.edit().putString(b2cUtil.INTERVAL_Type, intervalType).apply();

                // start alarm service
                //WarrantixApplication.getInstance().startAlarm(interval);
            }
        });
        listHours.setDivider(null);
        listHours.setDividerHeight(0);

        ImageView dropdownButton = (ImageView) findViewById(R.id.dropdownButton);
        dropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listHours.getVisibility() == View.VISIBLE)
                    listHours.setVisibility(View.GONE);
                else
                    listHours.setVisibility(View.VISIBLE);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }
}
