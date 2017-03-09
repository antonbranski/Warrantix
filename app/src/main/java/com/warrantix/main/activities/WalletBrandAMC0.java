package com.warrantix.main.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandAMCList;
import com.warrantix.main.common.event.ServiceCompaniesSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandAMC0 extends BaseActivity {
    private ListView list = null;
    private List<ServiceCompany> serviceCompanies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_amc0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            InitializeView();
            isInitialized = true;
        }
    }

    public void InitializeView() {

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(null);
        BackendManager.getInstance().getServiceCompaniesResponse(WarrantixPreference.TYPE_AMC, WarrantixPreference.BRANDID_HEROID);
        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    @Subscribe
    public void onServiceCompaniesSuccessEvent(final ServiceCompaniesSuccessEvent event) {
        WalletBrandAMC0.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetServiceCompaniesResponse getServiceCompaniesResponse = event.getServiceCompaniesResponse();
                if (getServiceCompaniesResponse != null) {
                    serviceCompanies = getServiceCompaniesResponse.getServiceCompaniesServices();
                    if (serviceCompanies.size() != 0) {
                        WalletBrandAMCList adapter = new WalletBrandAMCList(WalletBrandAMC0.this, serviceCompanies);
                        list.setAdapter(adapter);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.backCode && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish(true);
        }
    }
}
