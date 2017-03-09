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
import com.warrantix.main.activities.WalletBrandFinanceCompany;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WarrantixMarketplaceFinanceAdapter extends BaseAdapter {

    private final Activity context;
    private List<Product> mProducts = new ArrayList<>();

    public WarrantixMarketplaceFinanceAdapter(Activity context, List<Product> products) {
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
    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_marketplace_finance, null, true);
        else
            rowView = view;


        holder = new ViewHolder(rowView);


        final Product product = mProducts.get(position);
        holder.txtModel.setText(product.getName());
        holder.txtBrand.setText(product.getModel());
        holder.txtPrice.setText(String.format("Rs. %s", product.getMsrp().toString()));


        PicassoProvider.getInstance().get()
                .load(mProducts.get(position).getImageThmb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(holder.productsImage);


        holder.productsServiceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent mIntent = new Intent(context, WalletBrandFinanceCompany.class);
                mIntent.putExtra("brand", product.getName());
                mIntent.putExtra("model", product.getModel());
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivityForResult(mIntent, Constant.backCode, true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }

    static class ViewHolder {
        @BindView(R.id.products_image)
        ImageView productsImage;
        @BindView(R.id.txt_model)
        TextView txtModel;
        @BindView(R.id.txt_brand)
        TextView txtBrand;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.products_service_button)
        Button productsServiceButton;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}