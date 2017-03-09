package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandAccessoriesEshop;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.dialog.MessageDialog;

import java.util.ArrayList;
import java.util.List;

public class WarrantixMarketplaceSubCategoryProductAdapter extends BaseAdapter {

    private final Activity context;
    private List<Product> mProducts = new ArrayList<>();

    private Button serviceBTN;
    private Button shopBTN;

    public WarrantixMarketplaceSubCategoryProductAdapter(Activity context, List<Product> products) {
        this.context = context;
        mProducts = products;
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

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_marketplace_subcategory, null, true);
        else
            rowView = view;

        serviceBTN = (Button) rowView.findViewById(R.id.products_service);
        serviceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MessageDialog dialog = new MessageDialog(context);
                dialog.setTitle("Message");
                dialog.setMessage("Product Data Transferred to Registration Queue");
                dialog.show();

//                Intent i = new Intent(context, ScanCodeActivity.class);
//                if (context instanceof BaseActivity)
//                    ((BaseActivity)context).startActivity(i, true);
//                else
//                    context.startActivity(i);
            }
        });
        shopBTN = (Button) rowView.findViewById(R.id.shopBTN);
        shopBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, WalletBrandAccessoriesEshop.class);
                if (context instanceof WalletMarketplaceSubCategory) {
                    Gson gson = new Gson();
                    i.putExtra(Constant.productObject, gson.toJson(mProducts.get(position), Product.class));
                    i.putExtra("title", ((WalletMarketplaceSubCategory) context).getSubCategoryTitle());
                }
                i.putExtra("id",mProducts.get(position).getId());
                i.putExtra("productName",mProducts.get(position).getName());
                i.putExtra("productImage", mProducts.get(position).getImageThmb());
                ArrayList<String> imageUrls = (ArrayList<String>)mProducts.get(position).getImageUrls();
                i.putExtra("imageUrls", imageUrls);
                if (context instanceof BaseActivity)
                    ((BaseActivity)context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });

        final LinearLayout socialButton = (LinearLayout) rowView.findViewById(R.id.socialButton);
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] locationInWindow = new int[2];
                socialButton.getLocationInWindow(locationInWindow);

                if (context instanceof WalletMarketplaceSubCategory)
                    ((WalletMarketplaceSubCategory) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
            }
        });

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        Product product = mProducts.get(position);

        nameText.setText(product.getName());
        placeText.setText(product.getClass_());
        priceText.setText("Rs. "+product.getMsrp());

        PicassoProvider.getInstance().get()
                .load(product.getImageThmb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);

        return rowView;
    }



}