package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.common.date.DateUtil;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.warrantix.main.R.string.Refer;
import static com.warrantix.main.R.string.message;

public class WalletBrandSocialReferList extends BaseAdapter {

    private final Activity context;

    List<Message> mMessages = new ArrayList<>();
    List<Product> mProducts = new ArrayList<>();
    List<CustomerResponse> mCustomerResponses = new ArrayList<>();

    public WalletBrandSocialReferList(Activity context, List<Message> messages, List<Product> products, List<CustomerResponse> customerResponses) {
        this.context = context;
        mMessages = messages;
        mCustomerResponses = customerResponses;
        mProducts = products;
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
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_brand_social_refer, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView addressText = (TextView) rowView.findViewById(R.id.address1);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        TextView explainText = (TextView) rowView.findViewById(R.id.explain);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

//        nameText.setText(mCustomerResponses.get(position).getUsername());
//        if (mCustomerResponses.get(position).getFacebookID() != null){
//            addressText.setText("via facebook");
//        } else {
//            addressText.setText("via email");
//        }

        nameText.setText(mProducts.get(position).getName());
        addressText.setText(mCustomerResponses.get(position).getUsername());

        try {
            JSONObject json = new JSONObject(mMessages.get(position).getContent());
            String explain = json.getString("content");
            explainText.setText(explain);
        }catch (JSONException e){

        }

        String date = DateUtil.dateFarmatWithString(mMessages.get(position).getUpdatedAt());
        daysText.setText(date);

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

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomerResponses != null) {
                    CustomerResponse resp = mCustomerResponses.get(position);
                    Contact contact = resp.getContact();
                    if (contact == null)
                        WarrantixApplication.getInstance().showDial(context, "");
                    else
                        WarrantixApplication.getInstance().showDial(context, contact.getTel());
                }
            }
        });

        ImageButton brand_insurance_chat = (ImageButton) rowView.findViewById(R.id.brand_insurance_chat);
        brand_insurance_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, WalletBrandSocialChatMessage.class);
                mIntent.putExtra("title", "NEW CHAT");
                mIntent.putExtra("imageThumb",mCustomerResponses.get(position).getImage());
                mIntent.putExtra("name",mCustomerResponses.get(position).getUsername());
                mIntent.putExtra("customerID",mCustomerResponses.get(position).getCustomerID());
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }




}