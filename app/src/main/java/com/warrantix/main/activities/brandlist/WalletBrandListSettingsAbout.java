package com.warrantix.main.activities.brandlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.customview.AboutPageTransformer;
import com.warrantix.main.customview.FixedSpeedScroller;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.fragments.about.WarrantixAboutFragment1;
import com.warrantix.main.fragments.about.WarrantixAboutFragment2;
import com.warrantix.main.fragments.about.WarrantixAboutFragment3;
import com.warrantix.main.fragments.about.WarrantixAboutFragment4;
import com.warrantix.main.fragments.about.WarrantixAboutFragment5;

import java.lang.reflect.Field;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsAbout extends BaseActivity {

    private String toWhere = "";
    private NonSwipeableViewPager contentPager = null;

    private WarrantixAboutFragment1 fragment1 = new WarrantixAboutFragment1();
    private WarrantixAboutFragment2 fragment2 = new WarrantixAboutFragment2();
    private WarrantixAboutFragment3 fragment3 = new WarrantixAboutFragment3();
    private WarrantixAboutFragment4 fragment4 = new WarrantixAboutFragment4();
    private WarrantixAboutFragment5 fragment5 = new WarrantixAboutFragment5();
    private CircleIndicator indicator;
    private TextView txtNext;
    private ImageView btnBack;

    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return fragment1;
            else if (position == 1)
                return fragment2;
            else if (position == 2)
                return fragment3;
            else if (position == 3)
                return fragment4;
            else if (position == 4)
                return fragment5;

            return new Fragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    int pageStep = 0;


    @Override
    public void onBackPressed() {
        backPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_about);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isInitialized) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize() {

        //  toWhere = "Main" if its from SplashActivity else ""
        toWhere = getIntent().getStringExtra("Main");

        contentPager = (NonSwipeableViewPager) findViewById(R.id.viewPager);
        btnBack = (ImageView) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed();
            }
        });


        if (contentPager != null) {
            contentPager.setAdapter(contentPageAdapter);
        }
        contentPager.setPageTransformer(true, new AboutPageTransformer());
        contentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageStep = position;
                txtNext.setText(pageStep >= 4 ? "FINISH" : "NEXT");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        contentPager.setCurrentItem(0);

        indicator = (CircleIndicator) findViewById(R.id.indicator);
        if (indicator != null) {
            indicator.setViewPager(contentPager);
        }

        txtNext = (TextView) findViewById(R.id.txtNext);
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (contentPager.getCurrentItem() < 4) {
                    pageStep++;
                    contentPager.setCurrentItem(contentPager.getCurrentItem() + 1);
                } else {

                    clearUnusedMemory();

                    if (TextUtils.isEmpty(toWhere)) {
                        Intent intent = new Intent(getApplicationContext(), WalletBrandListSettings.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent, true, true);
                        finish();
                        overridePendingTransition(0,0);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent, true, true);
                        finish();
                        overridePendingTransition(0,0);
                    }
                }

            }
        });

    }

    public void setFixedScroller() {
        try {
            Interpolator sInterpolator = new DecelerateInterpolator();

            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(contentPager.getContext(), sInterpolator);
            mScroller.set(contentPager, scroller);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {
        }
    }

    public float generateRandomFromRange() {
        int min = 0;
        int max = 20;
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private void openMainActivity() {
        clearUnusedMemory();

        //Navigate to main screen
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent, true, true);
        finish(true);
        overridePendingTransition(0,0);
    }

    private void backPressed() {
        if (contentPager.getCurrentItem() > 0) {
            pageStep--;
            contentPager.setCurrentItem(contentPager.getCurrentItem() - 1);
            return;
        }

        if (TextUtils.isEmpty(toWhere)) {
            Intent intent = new Intent(getApplicationContext(), WalletBrandListSettings.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
            finish();
            overridePendingTransition(0,0);
        }
        else
            openMainActivity();
    }

    private void clearUnusedMemory() {
        fragment1.clearDrawable();
        fragment2.clearDrawable();
        fragment3.clearDrawable();
        fragment4.clearDrawable();
        fragment5.clearDrawable();
        System.gc();
    }
}
