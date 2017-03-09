package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandAccessoriesEshop;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.activities.registration.ScanCodeActivity;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

public class WarrantixMarketplaceProductsPopularAdapter extends BaseAdapter {

    private final Activity context;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;
    private boolean isAccessory = false;
    List<Product> mProducts = new ArrayList<>();

    private Button serviceBTN;
    private Button shopBTN;

    public WarrantixMarketplaceProductsPopularAdapter(Activity context, List<Product> products) {
        this.context = context;
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view != null)
            rowView = view;
        else {
            rowView = inflater.inflate(R.layout.listview_marketplace_product_category, parent, false);

            TextView nameText = (TextView) rowView.findViewById(R.id.txt_name);
            final ImageView imageView = (ImageView) rowView.findViewById(R.id.img_thumb);

            Product product = mProducts.get(position);
            if (product != null){
                nameText.setText(product.getName());

                Picasso.with(this.context.getApplicationContext())
                        .load(product.getImageThmb())
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }

        }

        return rowView;
    }

}