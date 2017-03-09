package com.warrantix.main.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandFinanceCompanyList;
import com.warrantix.main.common.event.ServiceCompaniesSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.manager.BackendManager;

import java.util.List;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandFinanceCompany extends BaseActivity {
    private ListView list = null;

    private String brand;
    private String model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_financecompany);

        brand = this.getIntent().getStringExtra("brand");
        model = this.getIntent().getStringExtra("model");
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

        BackendManager.getInstance().getServiceCompaniesResponse(WarrantixPreference.TYPE_FINANCE, WarrantixPreference.BRANDID_HEROID);


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
        WalletBrandFinanceCompany.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetServiceCompaniesResponse getServiceCompaniesResponse = event.getServiceCompaniesResponse();
                if (getServiceCompaniesResponse != null) {
                    List<ServiceCompany> serviceCompanies = getServiceCompaniesResponse.getServiceCompaniesServices();
                    if (serviceCompanies.size() != 0) {
                        WalletBrandFinanceCompanyList adapter = new WalletBrandFinanceCompanyList(WalletBrandFinanceCompany.this, serviceCompanies, brand, model);
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
