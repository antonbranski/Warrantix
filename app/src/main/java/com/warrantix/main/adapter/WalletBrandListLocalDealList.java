package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.MapsActivity;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Deal;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Product;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandListLocalDealList extends BaseAdapter {

    private final Activity context;
    private List<Dealer> mDealers = new ArrayList<>();
    private List<String> mdistances = new ArrayList<>();
    private MarshMallowPermission marshMallowPermission;

    public WalletBrandListLocalDealList(Activity context, List<Dealer> dealers, List<String> distances) {
        this.context = context;
        mDealers = dealers;
        mdistances = distances;
        marshMallowPermission = new MarshMallowPermission(context);
    }

    @Override
    public int getCount() {
        return mDealers.size();
    }

    @Override
    public Object getItem(int position) {
        return mDealers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view != null)
            rowView = view;
        else {
            rowView = inflater.inflate(R.layout.activity_brand_list_localdeals_list, null, true);

            TextView nameText = (TextView) rowView.findViewById(R.id.name);
            TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
            TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
            TextView nameText3 = (TextView) rowView.findViewById(R.id.name3);

            TextView percentText = (TextView) rowView.findViewById(R.id.percent);
            TextView priceText = (TextView) rowView.findViewById(R.id.price);
            TextView distanceText = (TextView) rowView.findViewById(R.id.distance);
            final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

            Dealer selDealer = mDealers.get(position);

            Deal deal = selDealer.getDeals().get(0);
            Product product = WarrantixWebService.getInstance().getProduct(deal.getProductID(), null).getProduct();
            final Contact contact = selDealer.getContact();


            if (product != null) {

                nameText.setText(product.getName());
                nameText1.setText(product.getModel());
                nameText2.setText(selDealer.getName());
                nameText3.setText(selDealer.getContact().getAddress());
                if (mdistances.get(position) != null) {
                    distanceText.setText(mdistances.get(position) + " km");
                }
                percentText.setText(deal.getShortDescription());
                priceText.setText("Rs. " + product.getMsrp().toString());

                ImageButton brand_insurance_location = (ImageButton) rowView.findViewById(R.id.brand_insurance_location);
                brand_insurance_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                if (context instanceof WalletBrandListLocalDeals) {
//                    Location location = ((WalletBrandListLocalDeals)context).getMyLocation();
//                    WarrantixApplication.getInstance().showMyLocation(context, location);
//                }

//                        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(context);
//
//                        // request camera
//                        if (!marshMallowPermission.checkPermissionForFineLocation()) {
//                            marshMallowPermission.requestPermissionForGPS();
//                            return;
//                        }

                        startMapsActivity(contact);

                    }
                });

                ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
                brand_insurance_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WarrantixApplication.getInstance().showDial(context, "");
                    }
                });


                PicassoProvider.getInstance().get()
                        .load(product.getImageThmb())
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }
        }

        return rowView;
    }

    private String convertContactToJSON(Contact contact) {
        if (contact == null)
            return "";

        String json = new Gson().toJson(contact);
        return json;
    }


    private void startMapsActivity(Contact contact) {
        if (marshMallowPermission.checkPermissionForFineLocation()) {
            Intent i = new Intent(context, MapsActivity.class);
            i.putExtra("contact", convertContactToJSON(contact));
            context.startActivity(i);
        } else {
            marshMallowPermission.requestPermissionsForFineLocationNew();
        }
    }

}