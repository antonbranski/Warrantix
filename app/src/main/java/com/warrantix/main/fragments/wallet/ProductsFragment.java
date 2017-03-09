package com.warrantix.main.fragments.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandProductsDetail;
import com.warrantix.main.adapter.WalletProductList;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;
import com.warrantix.main.manager.MyProductsManager;

import java.util.List;

public class ProductsFragment extends BaseFragment {
    private static final String TAG = ProductsFragment.class.getSimpleName();

    private List<Product> myProducts = null;
    private LinearLayout mCategory = null;
    private ListView listView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_products, container, false);
        initializeView(v);
        initializeBannerBar(v);
        return v;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
//        if (visible) {
//            WalletProductList adapter = new WalletProductList(getActivity(), MyProductsManager.getInstance().getAllProducts());
//            listView.setAdapter(adapter);
//            listView.setOnScrollListener(scrollChangeListener);
//        }
    }

    @Override
    public void onResume(){
        super.onResume();

        if (listView != null)
            listView.setOnItemClickListener(listviewClickListener);
    }

    private void initializeBannerBar(View v) {
        final LinearLayout allButton = (LinearLayout) v.findViewById(R.id.products_all);
        final LinearLayout appliancesButton = (LinearLayout) v.findViewById(R.id.products_appliances);
        final LinearLayout vehicleButton = (LinearLayout) v.findViewById(R.id.wallet_vehicles);
        final LinearLayout electronicButton = (LinearLayout) v.findViewById(R.id.products_electronics);
        final LinearLayout assortedButton = (LinearLayout) v.findViewById(R.id.products_assorted);

        final TextView allTextView = (TextView) v.findViewById(R.id.allTextView);
        final TextView appliancesTextView = (TextView) v.findViewById(R.id.appliancesTextView);
        final TextView vehiclesTextView = (TextView) v.findViewById(R.id.vehiclesTextView);
        final TextView electronicTextView = (TextView) v.findViewById(R.id.electronicsTextView);
        final TextView personalTextView = (TextView) v.findViewById(R.id.personalTextView);

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

    public void initializeView(View v) {
        mCategory = (LinearLayout) v.findViewById(R.id.category);
        myProducts = MyProductsManager.getInstance().getAllMyProducts();

        listView = (ListView) v.findViewById(R.id.list);
        listView.setOnItemClickListener(listviewClickListener);

        WalletProductList adapter = new WalletProductList(getActivity(), myProducts);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(scrollChangeListener);
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

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
            else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_out_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
        }
    };

    private String convertProductToJSON(Product product) {
        String json = new Gson().toJson(product);
        return json;
    }

    private final AdapterView.OnItemClickListener listviewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent mIntent = new Intent(mActivity, WalletBrandProductsDetail.class);
            if(myProducts != null)
                mIntent.putExtra("product", convertProductToJSON(myProducts.get(position)));

            if (mActivity instanceof BaseActivity)
                ((BaseActivity) mActivity).startActivityForResult(mIntent, Constant.backCode, true);
            else
                startActivityForResult(mIntent, Constant.backCode);

            listView.setOnItemClickListener(null);
        }
    };
}