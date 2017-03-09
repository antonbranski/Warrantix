package com.warrantix.main.fragments.walletbrand;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.product.BrandSocialCreateRank;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.activities.social.WalletBrandSocialSelectContact;
import com.warrantix.main.adapter.WalletBrandSocialChatList;
import com.warrantix.main.common.event.ChatRoomSuccessEvent;
import com.warrantix.main.common.rest.model.LatestMessage;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandSocialChatFragment extends BaseFragment {
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

    private List<CustomerResponse> mCustomers = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_social_chat, container, false);
        InitializeView();
        return mView;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        BackendManager.getInstance().getMessagesResponse("chat");

        if (list != null)
            list.setOnItemClickListener(listviewClickListener);
    }

    public void InitializeView() {
        revealtxt = (TextView) mView.findViewById(R.id.revealTXT);
        reveal1txt = (TextView) mView.findViewById(R.id.revealTXT1);

        mFabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BrandSocialCreateRank.class);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity) mContext).startActivity(i, true);
                else
                    mContext.startActivity(i);
            }
        });
        frameLayout = (LinearLayout) mView.findViewById(R.id.framelayout);

        list = (ListView) mView.findViewById(R.id.list);
        list.setAdapter(null);

        list.setOnItemClickListener(listviewClickListener);

        ImageButton fabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, WalletBrandSocialSelectContact.class);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity)mContext).startActivity(i, true);
                else
                    mContext.startActivity(i);
            }
        });
    }

    @Subscribe
    public void onChatRoomSuccessEvent(final ChatRoomSuccessEvent event)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<LatestMessage> latestMessages = event.getLatestMessages();
                if (latestMessages != null){
                    mCustomers = event.getCustomerResponses();
                    if (latestMessages.size() != 0) {
                        WalletBrandSocialChatList adapter = new WalletBrandSocialChatList(getActivity(), latestMessages,  event.getCustomerResponses());
                        list.setAdapter(adapter);
                    }
                }
            }
        });
    }

    private final AdapterView.OnItemClickListener listviewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent mIntent = new Intent(mContext, WalletBrandSocialChatMessage.class);
            mIntent.putExtra("imageThumb",mCustomers.get(position).getImage());
            mIntent.putExtra("name",mCustomers.get(position).getUsername());
            mIntent.putExtra("customerID",mCustomers.get(position).getCustomerID());
            mIntent.putExtra("loadMessage", true);
            if (mContext instanceof BaseActivity)
                ((BaseActivity) mContext).startActivity(mIntent, true);
            else
                mContext.startActivity(mIntent);

            list.setOnItemClickListener(null);
        }
    };

}
