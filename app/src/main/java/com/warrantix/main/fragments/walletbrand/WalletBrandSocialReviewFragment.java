package com.warrantix.main.fragments.walletbrand;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.product.BrandSocialCreateRank;
import com.warrantix.main.activities.product.BrandSocialCreateReview;
import com.warrantix.main.adapter.WalletBrandSocialReferList;
import com.warrantix.main.adapter.WalletBrandSocialRevealList;
import com.warrantix.main.adapter.WalletBrandSocialReviewList;
import com.warrantix.main.common.event.ProductSuccessEvent;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Review;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandSocialReviewFragment extends BaseFragment {
    private Context mContext;
    private View mView;
    private TextView revealtxt;
    private TextView reveal1txt;

    private ImageButton mFabButton;

    private ListView list = null;
    private Intent mIntent;
    private int status=0;
    private boolean touched = true;
    private LinearLayout frameLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_social_review, container, false);
        InitializeView();
        return mView;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            BackendManager.getInstance().getProductResponse("575a963055b8439f41aac0ff");
        }
    }

    public void InitializeView(){
        revealtxt = (TextView) mView.findViewById(R.id.revealTXT);
        reveal1txt = (TextView) mView.findViewById(R.id.revealTXT1);

        mFabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BrandSocialCreateReview.class);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity)mContext).startActivity(i, true);
                else
                    mContext.startActivity(i);
            }
        });
        frameLayout = (LinearLayout)mView.findViewById(R.id.framelayout);


        list=(ListView)mView.findViewById(R.id.list);
        list.setAdapter(null);

    }


    @Subscribe
    public void onProductSuccessEvent(final ProductSuccessEvent event)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductResponse getProductResponse = event.getProductResponse();
                if (getProductResponse != null){
                    List<Customer> customersByReview = event.getCustomersByReview();
                    if (customersByReview != null) {
                        WalletBrandSocialReviewList adapter = new WalletBrandSocialReviewList(getActivity(), event.getProductResponse(), customersByReview);
                        list.setAdapter(adapter);
                    }
                }
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.fabButton){
//            list.setAdapter(null);
//            name.add("Srinivasan M.K");
//            address.add("via eamil ");
//            days.add("1 days");
//            explain.add("");
//            imageID.add("person1");
//            String [] name1 = name.toArray(new String[name.size()]);
//            String [] address1 = address.toArray(new String[address.size()]);
//            String [] days1 = days.toArray(new String[days.size()]);
//            String [] explain1 = days.toArray(new String[explain.size()]);
//            String [] imageID1 = imageID.toArray(new String[imageID.size()]);
//            WalletBrandSocialReferList adapter = new WalletBrandSocialReferList(getActivity(), name1,address1,days1,explain1,imageID1);
//            list.setAdapter(adapter);
//        }
//
//    }



}
