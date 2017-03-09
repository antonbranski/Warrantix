package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandSocial;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandListOrderList extends BaseAdapter {

    private final Activity context;

    private  List<Order> mOrders = new ArrayList<>();
    private List<CustomerResponse> mCustomers = new ArrayList<>();

    public WalletBrandListOrderList(Activity context, List<Order> orders, List<CustomerResponse> customerResponses) {

        this.context = context;
        mOrders = orders;
        mCustomers = customerResponses;

    }

    @Override
    public int getCount() {
        return mOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrders.get(position);
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
            rowView = inflater.inflate(R.layout.activity_brand_list_order_list, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        Order order = mOrders.get(position);
        Product product = WarrantixWebService.getInstance().getProduct(mOrders.get(position).getProductID(),null).getProduct();

        nameText.setText(product.getName());
        nameText1.setText(product.getModel());
        daysText.setText(order.getStatus());

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOrders != null) {
                    Order order = mOrders.get(position);
                    WarrantixApplication.getInstance().showDial(context, order.getContact().getTel());
                }
            }
        });

        ImageButton brand_insurance_shop = (ImageButton) rowView.findViewById(R.id.brand_insurance_shop);
        brand_insurance_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, WalletBrandSocialChatMessage.class);
                i.putExtra("title", "NEW CHAT");
                i.putExtra("imageThumb",mCustomers.get(position).getImage());
                i.putExtra("name",mCustomers.get(position).getUsername());
                i.putExtra("customerID",mCustomers.get(position).getCustomerID());

//                Intent i = new Intent(context, WalletBrandSocial.class);
//                i.putExtra("selected", 4);

                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });



        PicassoProvider.getInstance().get()
                .load(product.getImageThmb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);


        return rowView;
    }
}