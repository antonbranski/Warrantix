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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.brandlist.WalletBrandListTransferWarranty1;
import com.warrantix.main.adapter.WalletProductList;
import com.warrantix.main.common.event.ProductsSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.manager.BackendManager;
import com.warrantix.main.manager.MyProductsManager;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandProducts extends BaseActivity {
    private static final String TAG = WalletBrandProducts.class.getSimpleName();

    private List<Product> myProducts = null;
    private LinearLayout mCategory = null;
    private ListView listView;

    private String forWhat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            initSocialListeners();
            initializeListView();
            isInitialized = true;
        }

        if (listView != null)
            listView.setOnItemClickListener(listviewClickListener);
    }

    private void Initialize() {
        initializeView();
        initializeBannerBar();

        ImageButton back = (ImageButton) findViewById(R.id.brand_arrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private void initializeListView() {
        forWhat = getIntent().getStringExtra("for");

        String fromWhere = getIntent().getStringExtra("from");
        myProducts = MyProductsManager.getInstance().getBrandMyProducts(fromWhere);
        WalletProductList adapter = new WalletProductList(WalletBrandProducts.this, myProducts, WalletProductList.THEME_RED);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(scrollChangeListener);
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

    public void initializeView() {
        mCategory = (LinearLayout) findViewById(R.id.category);

        listView = (ListView) findViewById(R.id.list);
        BackendManager.getInstance().getProductsResponse(WarrantixPreference.BRANDID_HEROID);

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

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandProducts.this, R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
            else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandProducts.this, R.anim.animation_slide_out_up);
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

    private void initSocialListeners() {
        LinearLayout social1Layout = (LinearLayout) findViewById(R.id.product_service1_layout);
        LinearLayout revealButton = (LinearLayout) social1Layout.findViewById(R.id.revealBTN);
        revealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProducts.this, WalletBrandSocial.class);
                intent.putExtra("selected", 0);
                startActivity(intent, true);
            }
        });

        LinearLayout referButton = (LinearLayout) social1Layout.findViewById(R.id.referBTN);
        referButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProducts.this, WalletBrandSocial.class);
                intent.putExtra("selected", 1);
                startActivity(intent, true);
            }
        });

        LinearLayout reviewButton = (LinearLayout) social1Layout.findViewById(R.id.reviewBTN);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProducts.this, WalletBrandSocial.class);
                intent.putExtra("selected", 2);
                startActivity(intent, true);
            }
        });

        LinearLayout rankButton = (LinearLayout) social1Layout.findViewById(R.id.rankBTN);
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProducts.this, WalletBrandSocial.class);
                intent.putExtra("selected", 3);
                startActivity(intent, true);
            }
        });

        LinearLayout chatButton = (LinearLayout) social1Layout.findViewById(R.id.chatBTN);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProducts.this, WalletBrandSocial.class);
                intent.putExtra("selected", 4);
                startActivity(intent, true);
            }
        });

    }

    public void showSocial1Bar(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        View maskView = (View) findViewById(R.id.maskView);
        maskView.setVisibility(View.VISIBLE);
        maskView.setOnClickListener(maskViewClickListener);

        LinearLayout socialLayout = (LinearLayout) findViewById(R.id.product_service1_layout);
        socialLayout.setVisibility(View.VISIBLE);

        int topHeight = (int) getResources().getDimension(R.dimen._60sdp);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) socialLayout.getLayoutParams();
        int topMargin = buttonY - layoutParams.height - buttonWidth - 30;
        if (topMargin < topHeight)
            topMargin += buttonHeight + layoutParams.height + (int) getResources().getDimension(R.dimen._15sdp);
        layoutParams.setMargins(layoutParams.leftMargin, topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        socialLayout.setLayoutParams(layoutParams);
    }


    public void hideSocial1Bar() {
        View maskView = (View) findViewById(R.id.maskView);
        maskView.setVisibility(View.GONE);
        maskView.setOnClickListener(null);

        LinearLayout socialLayout = (LinearLayout) findViewById(R.id.product_service1_layout);
        socialLayout.setVisibility(View.GONE);
    }

    private final View.OnClickListener maskViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideSocial1Bar();
        }
    };

    private String convertProductToJSON(Product product) {
        String json = new Gson().toJson(product);
        return json;
    }

    private final AdapterView.OnItemClickListener listviewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(myProducts != null) {
                if ((forWhat != null) && (forWhat.equalsIgnoreCase("TransferWarranty"))) {
                    Intent intent = new Intent(WalletBrandProducts.this, WalletBrandListTransferWarranty1.class);
                    intent.putExtra("product", convertProductToJSON(myProducts.get(position)));
                    startActivity(intent, true);
                }
                else {
                    Intent mIntent = new Intent(WalletBrandProducts.this, WalletBrandProductsDetail.class);
                    mIntent.putExtra("product", convertProductToJSON(myProducts.get(position)));
                    startActivity(mIntent, true);
                }
            }

            listView.setOnItemClickListener(null);
        }
    };
}