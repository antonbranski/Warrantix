package com.warrantix.main.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.common.event.SearchProductSuccessEvent;
import com.warrantix.main.common.rest.request.SearchProductRequest;
import com.warrantix.main.common.rest.response.SearchProductResponse;
import com.warrantix.main.manager.BackendManager;

public class ProductDetailActivity extends BaseActivity {

    private static final String TAG = ProductDetailActivity.class.getSimpleName();

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private ImageView btnSearchProduct = null;

    private Button btnNext = null;
    private RelativeLayout btnScanProductName = null;
    private RelativeLayout btnScanSerialNumber = null;

    private EditText txtProductName = null;
    private EditText txtBrand = null;
    private EditText txtModelNumber = null;
    private EditText txtSerialNumber = null;

    private String scanCodeResult = "";
    private String scanCodeType = "";


    private SearchProductRequest searchProductRequest = new SearchProductRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            updateWithProductDetail();
            isInitialized = true;
        }
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);

        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnNext = (Button) findViewById(R.id.btnNextInProductDetail);
        btnScanProductName = (RelativeLayout) findViewById(R.id.btnScanProductInProductDetail);
        btnScanSerialNumber = (RelativeLayout) findViewById(R.id.btnScanSerialInProductDetail);
        btnSearchProduct = (ImageView) findViewById(R.id.imgSearchProduct);

        btnBack.setOnClickListener(btnBackClickListener);
        btnNext.setOnClickListener(btnNextClickListener);
        btnScanProductName.setOnClickListener(btnScanProductNameClickListener);
        btnScanSerialNumber.setOnClickListener(btnScanSerialNumberClickListener);

        txtProductName = (EditText) findViewById(R.id.txtProductNameInProductDetail);
        txtBrand = (EditText) findViewById(R.id.txtBrandInProductDetail);
        txtModelNumber = (EditText) findViewById(R.id.txtModelNumberInProductDetail);
        txtSerialNumber = (EditText) findViewById(R.id.txtSerialNumberInProductDetail);

    }

    private void updateWithProductDetail()
    {
        scanCodeResult = getIntent().getStringExtra("SCANCODE_RESULT");
        scanCodeType = getIntent().getStringExtra("SCANCODE_TYPE");

        // debug message
        Log.d(TAG, "Result:" + scanCodeResult + " Type:" + scanCodeType);
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnNextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ProductDetailActivity.this, ProofPurchaseActivity.class);
            i.putExtra("productName", txtProductName.getText().toString());
            i.putExtra("brand", txtBrand.getText().toString());
            i.putExtra("model", txtModelNumber.getText().toString());
            startActivity(i, true);
        }
    };

    private final View.OnClickListener btnScanProductNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // call searchProduct api.
            if (!txtProductName.getText().toString().equals("") && !txtBrand.getText().toString().equals("")&& !txtModelNumber.getText().toString().equals(""))
            {
                searchProductRequest.setName(txtProductName.getText().toString());
                searchProductRequest.setBrandID(txtBrand.getText().toString());
                searchProductRequest.setModel(txtModelNumber.getText().toString());

                BackendManager.getInstance().searchProduct(searchProductRequest);
            }

            finish(true);
        }
    };

    private final View.OnClickListener btnScanSerialNumberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };


    @Subscribe
    public void onSearchProductSuccessEvent(final SearchProductSuccessEvent event)
    {
        ProductDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SearchProductResponse searchProductResponse = event.searchProductResponse();
                if (searchProductResponse != null){
                    //  please add needed code to show on this screen.

                }
            }
        });
    }
}