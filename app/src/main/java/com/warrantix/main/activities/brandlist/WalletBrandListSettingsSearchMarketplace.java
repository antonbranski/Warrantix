package com.warrantix.main.activities.brandlist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.otto.Subscribe;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.adapter.MarketPlaceGridAdapter;
import com.warrantix.main.common.event.BrandImgsSuccessEvent;
import com.warrantix.main.common.event.PluginBackToScreenEvent;
import com.warrantix.main.common.rest.response.BrandImageUrlsResponse;
import com.warrantix.main.manager.BackendManager;

import java.util.List;


/**
 * deprecated : Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsSearchMarketplace extends BaseActivity implements View.OnClickListener{

    private static final String TAG = WalletBrandListSettingsDefaultMarketplace.class.getSimpleName();

    private Button doneBTN;
    private GridView gridView;

    private int selectedPosition = 0;
    private int selectedIndex = 0;
    private View selectedView = null;
    private LinearLayout mCategory = null;

    private MarketPlaceGridAdapter adapter;
    private EditText searchField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_searchmarketplace);
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

//        final int[] resouceIds = new int[]
//                {
//                        R.drawable.warrantix0, R.drawable.croma0, R.drawable.big_bazaar0,
//                        R.drawable.videocon0, R.drawable.samsung0, R.drawable.lg0,
//                        R.drawable.suzuki0, R.drawable.tata0, R.drawable.brand_cromax,
//                        R.drawable.reliance0, R.drawable.flipkart0, R.drawable.zopper0
//                };

        BackendManager.getInstance().getBrandImageUrls(null);

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

            }
        });

        searchField = (EditText) findViewById(R.id.searchField);
        searchField.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                String str = s.toString().trim();
                switch (str){
                    case "warrantix":
                        searchFieldValue(3);
                        break;
                    case "croma":
                        searchFieldValue(4);
                        break;
                    case "big bazzar":
                        searchFieldValue(5);
                        break;
                    case "videocon":
                        searchFieldValue(9);
                        break;
                    case "samsung":
                        searchFieldValue(10);
                        break;
                    case "lg":
                        searchFieldValue(11);
                        break;
                    case "suzuki":
                        searchFieldValue(15);
                        break;
                    case "tata":
                        searchFieldValue(16);
                        break;
                    case "cromax":
                        searchFieldValue(17);
                        break;
                    case "reliancedigital":
                        searchFieldValue(21);
                        break;
                    case "flipkart":
                        searchFieldValue(22);
                        break;
                    case "zopper":
                        searchFieldValue(23);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) gridView.getLayoutParams();
//        layoutParams.height = 12 / 3 * (int) getResources().getDimension(R.dimen._50sdp) + (int) getResources().getDimension(R.dimen._50sdp);
//        gridView.setLayoutParams(layoutParams);
    }

    private void searchFieldValue(int position){
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
        RelativeLayout backLayout = (RelativeLayout) gridView.findViewById(R.id.backLayout);
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
        selectedView = gridView;

        adapter.setDefaultSelected(selectedIndex);
    }
    public void setSelectedView(View selectedView, int index, int position) {
        this.selectedView = selectedView;
        this.selectedIndex = index;
        this.selectedPosition = position;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doneBTN){
            String toScreen = "Warrantix";
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

    @Subscribe
    public void onBrandImsSuccessEvent(final BrandImgsSuccessEvent event)
    {
        WalletBrandListSettingsSearchMarketplace.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BrandImageUrlsResponse brandImageUrlsResponse = event.getBrandImageUrlsResponse();

                if (brandImageUrlsResponse != null){
                     List<String> brandImgs  = brandImageUrlsResponse.getBrandImgUrls();
                    if (brandImgs.size() != 0) {
                        adapter = new MarketPlaceGridAdapter(WalletBrandListSettingsSearchMarketplace.this, brandImgs, selectedIndex);
                        gridView.setAdapter(adapter);
                    }
                }
            }
        });
    }

}
