package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.common.image.PicassoProvider;

public class WalletBrandProductsDetailServiceContactsList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1, name, name2, distance, price;
    private final String[] imageId;

    public WalletBrandProductsDetailServiceContactsList(Activity context, String[] name, String[] name1, String[] name2, String[] distance, String[] price, String[] imageId) {
        super(context, R.layout.listview_brand_products_detail_servicecontacts, name);
        this.context = context;
        this.name = name;
        this.name1 = name1;
        this.name2 = name2;
        this.distance = distance;
        this.price = price;
        this.imageId = imageId;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_brand_products_detail_servicecontacts, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
        TextView distanceText = (TextView) rowView.findViewById(R.id.distance);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(name[position]);
        nameText1.setText(name1[position]);
        nameText2.setText(name2[position]);
        distanceText.setText(distance[position]);
        priceText.setText(price[position]);

        if (position == 0) {
            PicassoProvider.getInstance().get()
                    .load(R.drawable.person1_1)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        } else if (position == 1) {
            PicassoProvider.getInstance().get()
                    .load(R.drawable.person2_2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        } else {
            PicassoProvider.getInstance().get()
                    .load(R.drawable.person3_2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().showDial(context, "");
            }
        });

        ImageButton brand_insurance_location = (ImageButton) rowView.findViewById(R.id.brand_insurance_location);
        brand_insurance_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (context instanceof ProductsDetailServiceContacts) {
//                    Location location = ((ProductsDetailServiceContacts)context).getMyLocation();
//                    WarrantixApplication.getInstance().showMyLocation(context, location);
//                }
                Intent mIntent = new Intent(context, WalletBrandSocialChatMessage.class);
                mIntent.putExtra("title", "NEW CHAT");

                if (position == 0)
                    mIntent.putExtra("imageThumb", Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person1).toString());
                else if (position == 1)
                    mIntent.putExtra("imageThumb", Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person2).toString());
                else
                    mIntent.putExtra("imageThumb", Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person3).toString());

                mIntent.putExtra("name", name[position]);
                mIntent.putExtra("customerID", "");
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    context.startActivity(mIntent);

            }
        });

        return rowView;
    }


}