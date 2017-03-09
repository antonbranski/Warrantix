package com.warrantix.main.fragments.walletbrand;


import android.content.Context;
import android.content.Intent;
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
import com.warrantix.main.activities.product.BrandSocialCreateReveal;
import com.warrantix.main.adapter.WalletBrandSocialRankList;
import com.warrantix.main.adapter.WalletBrandSocialRevealList;
import com.warrantix.main.common.event.MessagesSuccessEvent;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.ReminderMessageContent;
import com.warrantix.main.common.rest.model.RevealMessageContent;
import com.warrantix.main.common.rest.response.PullMessageResponse;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandSocialRevealFragment extends BaseFragment {
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
        mView = inflater.inflate(R.layout.fragment_brand_social_reveal, container, false);
        InitializeView();
        return mView;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            BackendManager.getInstance().getMessagesResponse("reveal");
        }
    }


    public void InitializeView(){
        revealtxt = (TextView) mView.findViewById(R.id.revealTXT);
        reveal1txt = (TextView) mView.findViewById(R.id.revealTXT1);

        mFabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BrandSocialCreateReveal.class);
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
    public void onMessagesSuccessEvent(final MessagesSuccessEvent event)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PullMessageResponse pullMessageResponse = event.getPullMessageResponse();
                if (pullMessageResponse != null){
                    List<Message> messages = pullMessageResponse.getMessages();
                    if (messages != null) {
                        WalletBrandSocialRevealList adapter = new WalletBrandSocialRevealList(getActivity(), messages, event.getProducts(), event.getCustomerResponses());
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
//            place.add("owns a Hero Karizma ZMR");
//            days.add("1 days");
//            imageID.add("person1");
//            String [] name1 = name.toArray(new String[name.size()]);
//            String [] place1 = place.toArray(new String[place.size()]);
//            String [] days1 = days.toArray(new String[days.size()]);
//            String [] imageID1 = imageID.toArray(new String[imageID.size()]);
//            WalletBrandSocialRankList adapter = new WalletBrandSocialRankList(getActivity(), name1,place1,days1,imageID1);
//            list.setAdapter(adapter);
//        }
//
//
//    }





}
