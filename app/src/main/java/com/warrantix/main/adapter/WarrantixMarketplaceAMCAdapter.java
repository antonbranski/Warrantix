package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandAMC0;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class WarrantixMarketplaceAMCAdapter extends BaseAdapter{

    private final Activity context;
    private List<Product> mProducts = new ArrayList<>();

    public WarrantixMarketplaceAMCAdapter(Activity context, List<Product> products) {
        this.context = context;
         mProducts = products;
    }

    @Override
    public int getCount() {
        if (mProducts == null)
            return 0;
        else
            return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        if (mProducts == null)
            return null;
        else
            return mProducts.get(position);
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
            rowView = inflater.inflate(R.layout.listview_marketplace_amc, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText0 = (TextView) rowView.findViewById(R.id.name0);
        TextView nameTextGone = (TextView) rowView.findViewById(R.id.name1GONE);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        Product product = mProducts.get(position);

        nameText.setText(product.getName());
        nameText1.setText(product.getModel());
        daysText.setText(product.getSku() + " days to go");

        if (nameText0.getText().toString()!=""){
            nameText0.setVisibility(View.VISIBLE);
            nameTextGone.setVisibility(View.GONE);
        }else {
            nameText0.setVisibility(View.GONE);
            nameTextGone.setVisibility(View.VISIBLE);
        }


        PicassoProvider.getInstance().get()
                .load(mProducts.get(position).getImageThmb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);


        Button serviceBtn =  (Button) rowView.findViewById(R.id.products_service_button);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
                Intent mIntent = new Intent(context,WalletBrandAMC0.class);
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivityForResult(mIntent, Constant.backCode,true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }
}