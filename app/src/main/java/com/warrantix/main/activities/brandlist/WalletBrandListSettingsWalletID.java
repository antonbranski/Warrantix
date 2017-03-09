package com.warrantix.main.activities.brandlist;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandListWalletIDAdapter;
import com.warrantix.main.adapter.WalletBrandSettingsShoppingCartList;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Customer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsWalletID extends BaseActivity {

    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_walletid);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){
        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        boolean bCustomer = getIntent().getBooleanExtra("customer", true);
        int listViewType = 0;
        if (bCustomer == false) {
            listViewType = WalletBrandListWalletIDAdapter.ADD_DEVICE_TO_CUSTOMER;
        }
        else {
            listViewType = WalletBrandListWalletIDAdapter.ADD_CUSTOMER_TO_WALLET;
        }

        list = (RecyclerView) findViewById(R.id.list);
        list.setHasFixedSize(false);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        ArrayList<Customer> familycustomers = WarrantixPreference.warrantixConfig.getFamilyCustomers();

        WalletBrandListWalletIDAdapter adapter = new WalletBrandListWalletIDAdapter(this, familycustomers, listViewType);
        list.setAdapter(adapter);
    }

}
