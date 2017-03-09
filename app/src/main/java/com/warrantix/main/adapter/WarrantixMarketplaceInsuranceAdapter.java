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
import com.warrantix.main.activities.WalletBrandInsurance0;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;

import java.util.List;

public class WarrantixMarketplaceInsuranceAdapter extends BaseAdapter {

    public static final int THEME_DEFAULT = 1;
    public static final int THEME_RED = 2;

    private final Activity context;
    List<Product> mProducts;
    private int theme = THEME_DEFAULT;

    public WarrantixMarketplaceInsuranceAdapter(Activity context, List<Product> products) {

        this.context = context;
        this.mProducts = products;

    }

    public WarrantixMarketplaceInsuranceAdapter(Activity context, List<Product> products, int theme) {
        this.context = context;
        this.mProducts = products;
        this.theme = theme;
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
            rowView = inflater.inflate(R.layout.listview_marketplace_insurance, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText0 = (TextView) rowView.findViewById(R.id.name0);
        TextView nameTextGone = (TextView) rowView.findViewById(R.id.name1GONE);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(mProducts.get(position).getName());
//        nameText0.setText(mProducts.get(position).getClass_());
        nameText1.setText(mProducts.get(position).getModel());
        daysText.setText("284 days to go");

        if (nameText0.getText().toString() != "") {
            nameText0.setVisibility(View.VISIBLE);
            nameTextGone.setVisibility(View.GONE);
        } else {
            nameText0.setVisibility(View.GONE);
            nameTextGone.setVisibility(View.VISIBLE);
        }

        if (Math.random() >= 0.5) {
            PicassoProvider.getInstance().get()
                    .load(R.drawable.wallet_product_1)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        } else {
            PicassoProvider.getInstance().get()
                    .load(R.drawable.wallet_product_2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        Button serviceBtn = (Button) rowView.findViewById(R.id.products_service_button);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
                Intent mIntent = new Intent(context, WalletBrandInsurance0.class);
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivityForResult(mIntent, Constant.backCode, true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }
}