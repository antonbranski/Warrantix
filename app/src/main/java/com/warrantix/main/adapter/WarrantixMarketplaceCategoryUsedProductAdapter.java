package com.warrantix.main.adapter;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.UsedProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WarrantixMarketplaceCategoryUsedProductAdapter extends BaseAdapter {

    private final Activity context;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;
    private boolean isAccessory = false;
    List<UsedProduct> mUsedProducts = new ArrayList<>();
    List<Product> mProducts = new ArrayList<>();

    private Button serviceBTN;
    private Button shopBTN;

    public WarrantixMarketplaceCategoryUsedProductAdapter(Activity context, List<UsedProduct> usedProducts, List<Product> products) {
        this.context = context;
        this.mUsedProducts = usedProducts;
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mUsedProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsedProducts.get(position);
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
            rowView = inflater.inflate(R.layout.listview_horizontal_warrantix_catusedproduct, parent, false);

            TextView nameText = (TextView) rowView.findViewById(R.id.txt_name);
            TextView priceText = (TextView) rowView.findViewById(R.id.price);
            final ImageView imageView = (ImageView) rowView.findViewById(R.id.product_img);

            UsedProduct usedProduct = mUsedProducts.get(position);
            Product product = mProducts.get(position);
            if (product != null){
                nameText.setText(product.getName());

                DecimalFormat format = new DecimalFormat("00,000");
                String price = format.format(usedProduct.getMsrp());

                priceText.setText("Rs. " + price);

                PicassoProvider.getInstance().get()
                        .load(product.getImageThmb())
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }

        }

        return rowView;
    }

}