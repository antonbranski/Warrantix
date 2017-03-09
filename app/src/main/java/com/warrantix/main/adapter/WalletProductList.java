package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandProducts;
import com.warrantix.main.activities.WalletBrandProductsDetail;
import com.warrantix.main.activities.productdetail.ProductsDetailScheduleService;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.Constant;

import java.util.List;

public class WalletProductList extends BaseAdapter {
    private final static String TAG = WalletProductList.class.getSimpleName();

    public static final int THEME_DEFAULT = 1;
    public static final int THEME_RED = 2;

    private final Activity context;

    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;
    private int theme = THEME_DEFAULT;

    List<Product> mProducts;

    public WalletProductList(Activity context, List<Product> products) {

        this.context = context;
        this.mProducts = products;
        this.theme = THEME_DEFAULT;
    }

    public WalletProductList(Activity context, List<Product> products, int theme) {
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
    public View getView(final int position, View view, ViewGroup parent) {

        Log.d(TAG, "getView: ProductList = " + WalletProductList.this + "  position = " + position + " View = " + view + "parent = " + parent);

        if (mProducts == null)
            return null;

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_product, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText0 = (TextView) rowView.findViewById(R.id.name0);
        TextView nameTextGone = (TextView) rowView.findViewById(R.id.name1GONE);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);
        ImageView activeLine = (ImageView) rowView.findViewById(R.id.activeLine);
        ImageView socialImage = (ImageView) rowView.findViewById(R.id.socialImage);

        if (theme == THEME_RED) {
            activeLine.setBackgroundResource(R.drawable.process_red);
            socialImage.setImageResource(R.drawable.red_vertical_menu);
        }

        nameText.setText(mProducts.get(position).getName());
//        nameText0.setText(mProducts.get(position).getClass_());
        nameText1.setText(mProducts.get(position).getModel());
        daysText.setText("284 days to go");

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

        final RelativeLayout socialButton = (RelativeLayout) rowView.findViewById(R.id.products_social_button);
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] locationInWindow = new int[2];
                socialButton.getLocationInWindow(locationInWindow);

                if (context instanceof WalletBrandProducts)
                    ((WalletBrandProducts) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
                else if (context instanceof MainActivity)
                    ((MainActivity) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
            }
        });

        Button serviceBtn =  (Button) rowView.findViewById(R.id.products_service_button);
        if (theme == THEME_RED) {
            serviceBtn.setVisibility(View.GONE);
            serviceBtn = (Button) rowView.findViewById(R.id.products_red_service_button);
        }
        else {
            Button redServiceBTN = (Button) rowView.findViewById(R.id.products_red_service_button);
            redServiceBTN.setVisibility(View.GONE);
        }

        serviceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
                if (mProducts == null)
                    return;

                Product myProduct = mProducts.get(position);
                Intent mIntent = new Intent(context,ProductsDetailScheduleService.class);
                if(myProduct != null)
                    mIntent.putExtra("product", convertProductToJSON(myProduct));

                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent,true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }

    private String convertProductToJSON(Product product) {
        String json = new Gson().toJson(product);
        return json;
    }
}