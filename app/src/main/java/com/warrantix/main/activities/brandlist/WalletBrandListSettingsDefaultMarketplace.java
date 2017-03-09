package com.warrantix.main.activities.brandlist;

import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.GlobalConfig;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.adapter.MarketPlaceGridAdapter;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.common.event.BrandImgsSuccessEvent;
import com.warrantix.main.common.event.PluginBackToScreenEvent;
import com.warrantix.main.common.rest.response.BrandImageUrlsResponse;
import com.warrantix.main.manager.BackendManager;

import java.util.List;


/**
 * deprecated : Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsDefaultMarketplace extends BaseActivity implements View.OnClickListener{
    private static final String TAG = WalletBrandListSettingsDefaultMarketplace.class.getSimpleName();

    private TextView titleTEXT;
    private Button doneBTN;
    private GridView gridView;

    private int selectedPosition = 0;
    private int selectedIndex = 0;
    private View selectedView = null;
    private LinearLayout mCategory = null;

    private  MarketPlaceGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_defaultmarketplace);
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

        selectedIndex = GlobalConfig.getInstance().getDefaultPluginIndex();

        doneBTN = (Button) findViewById(R.id.doneBTN);
        doneBTN.setOnClickListener(this);
        titleTEXT = (TextView) findViewById(R.id.title);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

//        final int[] resourceIds = new int[]
//                {
//                        R.drawable.warrantix0, R.drawable.croma0, R.drawable.big_bazaar0,
//                        R.drawable.videocon0, R.drawable.samsung0, R.drawable.lg0,
//                        R.drawable.suzuki0, R.drawable.tata0, R.drawable.brand_cromax,
//                        R.drawable.reliance0, R.drawable.flipkart0, R.drawable.zopper0
//                };

//        final int[] resourceIds = new int []
//                {
//                        R.drawable.tab_warrantix, R.drawable.tab_hero, R.drawable.tab_godrej,
//                        R.drawable.tab_samsung, R.drawable.tab_forbles, R.drawable.tab_lg,
//                        R.drawable.tab_mahindra, R.drawable.tab_micromax, R.drawable.tab_voltas,
//                        R.drawable.tab_hero, R.drawable.tab_godrej,
//                        R.drawable.tab_samsung, R.drawable.tab_forbles, R.drawable.tab_lg,
//                        R.drawable.tab_mahindra, R.drawable.tab_micromax, R.drawable.tab_voltas,
//                        R.drawable.tab_hero, R.drawable.tab_godrej,
//                        R.drawable.tab_samsung, R.drawable.tab_forbles, R.drawable.tab_lg,
//                        R.drawable.tab_mahindra, R.drawable.tab_micromax, R.drawable.tab_voltas
//                };

        BackendManager.getInstance().getBrandImageUrls("marketplace");

        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int row = position / 3;
                int index = ((row-1)/ 2) * 3 + position % 3;
                Log.d(TAG, "OnItemClick is called. index = " + index);


                if (selectedView != null) {
                    Log.d(TAG, "OnItemClick: selectedView != null");
                    // older selected view
                    RelativeLayout backLayout = (RelativeLayout) selectedView.findViewById(R.id.backLayout);
                    if (selectedPosition % 3 == 0) {
                        // left cell
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
                        layoutParams.setMargins((int) getResources().getDimension(R.dimen.control_space_margin_small), 0, 0, 0);
                        backLayout.setLayoutParams(layoutParams);
                    }
                    else if (selectedPosition % 3 == 2) {
                        // right cell
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
                        layoutParams.setMargins(0, 0, (int) getResources().getDimension(R.dimen.control_space_margin_small), 0);
                        backLayout.setLayoutParams(layoutParams);
                    }
                    else {
                        // center cell
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
                        layoutParams.setMargins(0, 0, 0, 0);
                        backLayout.setLayoutParams(layoutParams);
                    }

                    backLayout.setBackgroundResource(R.drawable.default_background);
                }

                // new view
                RelativeLayout backLayout = (RelativeLayout) view.findViewById(R.id.backLayout);
                if (position % 3 == 0) {
                    // left cell
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
                    layoutParams.setMargins((int) getResources().getDimension(R.dimen.control_space_margin_small), 0, 0, 0);
                    backLayout.setLayoutParams(layoutParams);
                }
                else if (position % 3 == 2) {
                    // right cell
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
                    layoutParams.setMargins(0, 0, (int) getResources().getDimension(R.dimen.control_space_margin_small), 0);
                    backLayout.setLayoutParams(layoutParams);
                }
                else {
                    // center cell
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
                    layoutParams.setMargins(0, 0, 0, 0);
                    backLayout.setLayoutParams(layoutParams);
                }

                backLayout.setBackgroundResource(R.drawable.grid_selected_background);

                selectedPosition = position;
                selectedIndex = index;
                selectedView = view;

                adapter.setDefaultSelected(selectedIndex);

                // show done animation
                if (mCategory.getVisibility() != View.VISIBLE) {
                    mCategory.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandListSettingsDefaultMarketplace.this, R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
        });
        gridView.setOnScrollListener(scrollChangeListener);

        mCategory = (LinearLayout) findViewById(R.id.category);

//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) gridView.getLayoutParams();
//        layoutParams.height = 9 / 3 * (int) getResources().getDimension(R.dimen._90sdp) + (int) getResources().getDimension(R.dimen._50sdp);
//        gridView.setLayoutParams(layoutParams);
    }

    public void setSelectedView(View selectedView, int index, int position) {
        this.selectedView = selectedView;
        this.selectedIndex = index;
        this.selectedPosition = position;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doneBTN){
            GlobalConfig.getInstance().setDefaultPluginIndex(selectedIndex);
            String toScreen = GlobalConfig.getInstance().getPluginName(selectedIndex);
            PluginBackToScreenEvent event = new PluginBackToScreenEvent(toScreen);
            BusProvider.get().post(event);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
        }
    }

    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, resources.getDisplayMetrics()
        );
    }

    private final AbsListView.OnScrollListener scrollChangeListener = new AbsListView.OnScrollListener() {
        public int mLastFirstVisibleItem = 0;
        public boolean mIsScrollingUp = true;

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub

        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
            if(scrollState != 0)
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

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandListSettingsDefaultMarketplace.this, R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
            else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandListSettingsDefaultMarketplace.this, R.anim.animation_slide_out_up);
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

    @Subscribe
    public void onBrandImsSuccessEvent(final BrandImgsSuccessEvent event)
    {

        WalletBrandListSettingsDefaultMarketplace.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BrandImageUrlsResponse brandImageUrlsResponse = event.getBrandImageUrlsResponse();

                if (brandImageUrlsResponse != null){
                    List<String> brandImgs  = brandImageUrlsResponse.getBrandImgUrls();
                    if (brandImgs.size() != 0) {
                        adapter = new MarketPlaceGridAdapter(WalletBrandListSettingsDefaultMarketplace.this, brandImgs, selectedIndex);
                        gridView.setAdapter(adapter);
                    }
                }
            }
        });
    }

}
