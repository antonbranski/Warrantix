package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandFinance0;
import com.warrantix.main.activities.WalletBrandFinance1;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandFinanceCompanyList extends BaseAdapter {

    private final Activity context;
    private List<ServiceCompany> mServiceCompanies = new ArrayList<>();
    private String mModel;
    private String mBrand;

    public WalletBrandFinanceCompanyList(Activity context, List<ServiceCompany> serviceCompanies, String model, String brand) {
        this.context = context;
        mServiceCompanies = serviceCompanies;
        mModel = model;
        mBrand = brand;
    }

    @Override
    public int getCount() {
        return mServiceCompanies.size();
    }

    @Override
    public Object getItem(int position) {
        return mServiceCompanies.get(position);
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
            rowView = inflater.inflate(R.layout.listview_wallet_brand_financecompany, null, true);
        else
            rowView = view;

        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        ServiceCompany serviceCompany = mServiceCompanies.get(position);

        PicassoProvider.getInstance().get()
                .load(serviceCompany.getImageUrl())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);


          imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
//             Intent intent = new Intent(getContext(),WalletHeroActivity.class);
//             getContext().startActivity(intent);
             //overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
          });

        Button shopButton = (Button) rowView.findViewById(R.id.financePolicyButton);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof BaseActivity) {
                    Intent i = new Intent(context, WalletBrandFinance1.class);
                    i.putExtra("brand",mBrand);
                    i.putExtra("model", mModel);
                    ((BaseActivity)context).startActivityForResult(i, Constant.backCode, true);
                }
                else {
                    Intent i = new Intent(context, WalletBrandFinance0.class);
                    context.startActivity(i);
                }
            }
        });

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mServiceCompanies != null) {
                    ServiceCompany company = mServiceCompanies.get(position);
                    Contact contact = company.getContact();
                    if (contact == null)
                        WarrantixApplication.getInstance().showDial(context, "");
                    else
                        WarrantixApplication.getInstance().showDial(context, contact.getTel());
                }
            }
        });

        ImageButton brand_insurance_chat = (ImageButton) rowView.findViewById(R.id.brand_insurance_chat);
        brand_insurance_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, WalletBrandSocialChatMessage.class);
                mIntent.putExtra("title", "NEW CHAT");
                mIntent.putExtra("imageThumb",mServiceCompanies.get(position).getImageUrl());
                mIntent.putExtra("name",mServiceCompanies.get(position).getName());
//                mIntent.putExtra("imageThumb",mCustomers.get(position).getImage());
//                mIntent.putExtra("name",mCustomers.get(position).getUsername());
//                mIntent.putExtra("customerID",mCustomers.get(position).getCustomerID());
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    context.startActivity(mIntent);
            }
        });
        return rowView;
    }




}