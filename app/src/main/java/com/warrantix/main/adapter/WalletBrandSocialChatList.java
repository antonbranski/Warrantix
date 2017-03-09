package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.date.DateUtil;
import com.warrantix.main.common.rest.model.LatestMessage;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandSocialChatList extends BaseAdapter {

    private final Activity context;
    List<LatestMessage> mMessages = new ArrayList<>();
    List<CustomerResponse> mCustomerResponses = new ArrayList<>();


    public WalletBrandSocialChatList(Activity context, List<LatestMessage> messages,List<CustomerResponse> customerResponses) {
        this.context = context;
        mMessages = messages;
        mCustomerResponses = customerResponses;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_brand_social_chat, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView typeText = (TextView) rowView.findViewById(R.id.type);
        TextView messageText = (TextView) rowView.findViewById(R.id.chatmessage);
        Button messageCountBTN = (Button) rowView.findViewById(R.id.messageCount);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

//        nameText.setText(mCustomerResponses.get(position).getUsername());
//        typeText.setText(mCustomerResponses.get(position).getLeadBrand());
        nameText.setText(mCustomerResponses.get(position).getLeadBrand());
        typeText.setText(mCustomerResponses.get(position).getUsername());
        messageText.setText(mMessages.get(position).getContent());
        String date = DateUtil.dateFarmatWithString(mMessages.get(position).getUpdatedAt());
        daysText.setText(date);
        if (mMessages.get(position).getUnreadNumber() != 0)
            messageCountBTN.setText(Integer.toString(mMessages.get(position).getUnreadNumber()));
        else
            messageCountBTN.setVisibility(View.GONE);

        PicassoProvider.getInstance().get()
                .load(mCustomerResponses.get(position).getImage())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);

          imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
    //             Intent intent = new Intent(getContext(),WalletHeroActivity.class);
    //             getContext().startActivity(intent);
             //overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
          });
        return rowView;
    }




}