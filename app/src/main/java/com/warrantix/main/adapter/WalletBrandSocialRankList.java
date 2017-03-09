package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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

import java.util.ArrayList;
import java.util.List;

public class WalletBrandSocialRankList extends BaseAdapter {

    private final Activity context;
    private List<Customer> mCustomers = new ArrayList<>();
    private Product mProduct = new Product();

    public WalletBrandSocialRankList(Activity context, Product product, List<Customer> customers) {
        this.context = context;
        mCustomers = customers;
        mProduct = product;
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
            rowView = inflater.inflate(R.layout.listview_wallet_brand_social_rank, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        Customer customer = mCustomers.get(position);

        nameText.setText(customer.getUsername());
        placeText.setText(mProduct.getName());
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
                    Customer customer = mCustomers.get(position);
                    Contact contact = customer.getContact();
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