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
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.GetProductResponse;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandSocialReviewList extends BaseAdapter {

    private final Activity context;
    private List<Customer> mCustomers = new ArrayList<>();
    private GetProductResponse mGetProductResponse = new GetProductResponse();

    public WalletBrandSocialReviewList(Activity context,GetProductResponse getProductResponse, List<Customer> customers) {
        this.context = context;
        this.mCustomers = customers;
        mGetProductResponse = getProductResponse;
    }

    @Override
    public int getCount() {
        return mCustomers.size();
    }

    @Override
    public Object getItem(int position) {
        return mCustomers.get(position);
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
            rowView = inflater.inflate(R.layout.listview_wallet_brand_social_review, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView addressText = (TextView) rowView.findViewById(R.id.address1);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        TextView explainText = (TextView) rowView.findViewById(R.id.explain);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        Customer customer = mCustomers.get(position);

//        nameText.setText(customer.getUsername());
//        addressText.setText("for "+mGetProductResponse.getProduct().getName());
        nameText.setText(mGetProductResponse.getProduct().getName());
        addressText.setText(customer.getUsername()  );

        daysText.setText(customer.getUpdatedAt());

        PicassoProvider.getInstance().get()
                .load(customer.getImage())
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
                if (mCustomers != null) {
                    Customer resp = mCustomers.get(position);
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
                mIntent.putExtra("imageThumb",mCustomers.get(position).getImage());
                mIntent.putExtra("name",mCustomers.get(position).getUsername());
                mIntent.putExtra("customerID",mCustomers.get(position).getCustomerID());
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }




}