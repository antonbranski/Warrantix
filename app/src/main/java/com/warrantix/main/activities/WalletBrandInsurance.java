package com.warrantix.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.fragments.walletbrand.WalletBrandInsuranceFragment1;
import com.warrantix.main.fragments.walletbrand.WalletBrandInsuranceFragment2;
import com.warrantix.main.fragments.walletbrand.WalletBrandInsuranceFragment3;

public class WalletBrandInsurance extends BaseActivity implements View.OnClickListener {
    private ImageView insurance1;
    private ImageView insurance2;
    private ImageView insurance3;

    private LinearLayout insurance1Lyout;
    private LinearLayout insurance2Lyout;
    private LinearLayout insurance3Lyout;
    private Intent mIntent;

    private TextView titleTXT;
    public static int curStep=0;

    public boolean isAcceptedSelect = false;
    public boolean isAccepted3Select = false;

    private NonSwipeableViewPager contentPager = null;
    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new WalletBrandInsuranceFragment1();
            else if (position == 1)
                return new WalletBrandInsuranceFragment2();
            else if (position == 2)
                return new WalletBrandInsuranceFragment3();
            else
                return new Fragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_insurance);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    private void Initialize() {

        isAcceptedSelect = false;
        isAccepted3Select = false;
        curStep = 0;

        contentPager = (NonSwipeableViewPager) findViewById(R.id.fragment_container);
        contentPager.setAdapter(contentPageAdapter);

        titleTXT = (TextView) findViewById(R.id.title);
        titleTXT.setText("SELECT INSURANCE");

        insurance1 = (ImageView) findViewById(R.id.insurance1BTN);
        insurance2 = (ImageView) findViewById(R.id.insurance2BTN);
        insurance3 = (ImageView) findViewById(R.id.insurance3BTN);

        insurance1Lyout = (LinearLayout) findViewById(R.id.insurance1Lyout);
        insurance2Lyout = (LinearLayout) findViewById(R.id.insurance2Lyout);
        insurance3Lyout = (LinearLayout) findViewById(R.id.insurance3Lyout);

        insurance1.setVisibility(View.VISIBLE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.GONE);

        insurance1Lyout.setOnClickListener(this);
        insurance2Lyout.setOnClickListener(this);
        insurance3Lyout.setOnClickListener(this);

        if (curStep==0){
            contentPager.setCurrentItem(0);
        }
        else if(curStep==1){
            contentPager.setCurrentItem(1);
        }
        else if (curStep == 2) {
            contentPager.setCurrentItem(2);
        }
        else {
            contentPager.setCurrentItem(0);
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentPager.getCurrentItem() == 0)
                    finish(true);
                else {
                    contentPager.setCurrentItem(contentPager.getCurrentItem() - 1);
                    updateSelectionUi();
                }
            }
        });
    }

    public void loadInsuranceFragment1() {
        contentPager.setCurrentItem(0);

        titleTXT.setText("SELECT INSURANCE");

        insurance1.setVisibility(View.VISIBLE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.GONE);

        updateCurrentStep(0);
    }

    public void loadInsuranceFragment3() {
        contentPager.setCurrentItem(2);

        titleTXT.setText("SHOPPING CART");

        insurance1.setVisibility(View.GONE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.VISIBLE);

        updateCurrentStep(2);
    }

    public void loadInsuranceFragment2() {
        contentPager.setCurrentItem(1);

        titleTXT.setText("SHOPPING CART");

        insurance1.setVisibility(View.GONE);
        insurance2.setVisibility(View.VISIBLE);
        insurance3.setVisibility(View.GONE);

        updateCurrentStep(1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insurance1Lyout) {
            if (curStep >= 0)
                loadInsuranceFragment1();
        }
        if (v.getId() == R.id.insurance2Lyout) {
            if (curStep >= 1)
                loadInsuranceFragment2();
        }
        if (v.getId() == R.id.insurance3Lyout){
            if (curStep >= 2)
                loadInsuranceFragment3();
        }
    }

    private void updateCurrentStep(int sel) {
        if (curStep < sel)
            curStep = sel;
    }

    private void updateSelectionUi() {
        insurance1.setVisibility(contentPager.getCurrentItem() == 0 ? View.VISIBLE : View.GONE);
        insurance2.setVisibility(contentPager.getCurrentItem() == 1 ? View.VISIBLE : View.GONE);
        insurance3.setVisibility(contentPager.getCurrentItem() == 2 ? View.VISIBLE : View.GONE);
    }
}
