package com.warrantix.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.warrantix.main.R;
import com.warrantix.main.adapter.WarrantixMarketplaceAMCAdapter;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.manager.MyProductsManager;

import java.util.ArrayList;
import java.util.List;

public class WalletMarketplaceAMC extends BaseActivity {
    private static final String TAG = WalletMarketplaceAMC.class.getSimpleName();

    private LinearLayout mCategory = null;
    private RelativeLayout mPopupMenu = null;
    private ListView listView;
    private List<Product> myProducts = new ArrayList<>();
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace_amc);
        mIntent = getIntent();

        if (mIntent != null && mIntent.hasExtra("from") && mIntent.getStringExtra("from").equalsIgnoreCase(Constant.plugInPackName)) {
            WarrantixPreference.saveBooleanPreference(Constant.isFromPlugin, true);
        } else {
            WarrantixPreference.saveBooleanPreference(Constant.isFromPlugin, false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            initializeBannerBar();
            isInitialized = true;
        }

        if (listView != null) {
            // get the my products in local and show them on the screen.
            myProducts = MyProductsManager.getInstance().getAllMyProducts();
            WarrantixMarketplaceAMCAdapter adapter = new WarrantixMarketplaceAMCAdapter(this, myProducts);
            listView.setAdapter(adapter);
            listView.setOnScrollListener(scrollChangeListener);
            listView.setOnItemClickListener(listviewClickListener);
        }
    }

    private void initializeBannerBar() {
        final LinearLayout allButton = (LinearLayout) findViewById(R.id.products_all);
        final LinearLayout appliancesButton = (LinearLayout) findViewById(R.id.products_appliances);
        final LinearLayout vehicleButton = (LinearLayout) findViewById(R.id.wallet_vehicles);
        final LinearLayout electronicButton = (LinearLayout) findViewById(R.id.products_electronics);
        final LinearLayout assortedButton = (LinearLayout) findViewById(R.id.products_assorted);

        final TextView allTextView = (TextView) findViewById(R.id.allTextView);
        final TextView appliancesTextView = (TextView) findViewById(R.id.appliancesTextView);
        final TextView vehiclesTextView = (TextView) findViewById(R.id.vehiclesTextView);
        final TextView electronicTextView = (TextView) findViewById(R.id.electronicsTextView);
        final TextView personalTextView = (TextView) findViewById(R.id.personalTextView);

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        appliancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        vehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        electronicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        assortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
            }
        });
    }

    public void initialize() {
        mCategory = (LinearLayout) findViewById(R.id.category);
        mPopupMenu = (RelativeLayout) findViewById(R.id.product_service_layout);


        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(listviewClickListener);
    }

    private final AbsListView.OnScrollListener scrollChangeListener = new AbsListView.OnScrollListener() {
        public int mLastFirstVisibleItem = 0;
        public boolean mIsScrollingUp = true;

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub

        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
            if (scrollState != 0)
                return;

            final int currentFirstVisibleItem = view.getFirstVisiblePosition();
            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                mIsScrollingUp = false;
                Log.d(TAG, "scrolling down...");
            } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                mIsScrollingUp = true;
                Log.d(TAG, "scrolling up...");
            }

            mLastFirstVisibleItem = currentFirstVisibleItem;

            if (mIsScrollingUp == true) {
                if (mCategory.getVisibility() != View.VISIBLE) {
                    mCategory.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(WalletMarketplaceAMC.this, R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            } else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(WalletMarketplaceAMC.this, R.anim.animation_slide_out_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }


//            if(view.getFirstVisiblePosition() == 0) {
//                if (mCategory.getVisibility() != View.VISIBLE) {
//                    mCategory.setVisibility(View.VISIBLE);
//
//                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_in_up);
//                    animation.setDuration(400);
//                    mCategory.setAnimation(animation);
//                    mCategory.animate();
//                    animation.start();
//                }
//            }
//            else {
//                if (mCategory.getVisibility() != View.GONE) {
//                    mCategory.setVisibility(View.GONE);
//
//                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_out_up);
//                    animation.setDuration(400);
//                    mCategory.setAnimation(animation);
//                    mCategory.animate();
//                    animation.start();
//                }
//            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.backCode && resultCode == RESULT_OK) {
            finish(true);
        }
    }

    private String convertProductToJSON(Product product) {
        String json = new Gson().toJson(product);
        return json;
    }

    private final AdapterView.OnItemClickListener listviewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent mIntent = new Intent(WalletMarketplaceAMC.this, WalletBrandProductsDetail.class);
            if (myProducts != null)
                mIntent.putExtra("product", convertProductToJSON(myProducts.get(position)));
            startActivityForResult(mIntent, Constant.backCode, true);

            listView.setOnItemClickListener(null);
        }
    };
}