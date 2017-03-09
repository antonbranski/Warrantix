package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsAddCustomerID;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsAddDeviceID;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Customer;

import java.util.ArrayList;

public class WalletBrandListWalletIDAdapter extends RecyclerView.Adapter<WalletBrandListWalletIDAdapter.ViewHolder> {

    // holder inner class
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View contentView = null;

        public ViewHolder(View v, int viewType, final Activity context) {
            super(v);
            contentView = v;
        }

        public void setActivity(Activity activity) {

        }
    }

    public static final int ADD_CUSTOMER_TO_WALLET = 0;
    public static final int ADD_DEVICE_TO_CUSTOMER = 1;

    private static final int HEADER = 0, FAMILY = 1, FOOTER = 2;

    private final Activity context;
    private ArrayList<Customer> familyCustomers = new ArrayList<Customer>();
    public int listViewType;

    public WalletBrandListWalletIDAdapter(Activity activity, ArrayList<Customer> familyCustomers, int listViewType) {
        this.context = activity;
        this.familyCustomers = familyCustomers;
        this.listViewType = listViewType;
    }

    @Override
    public int getItemCount() {
        if (listViewType == ADD_CUSTOMER_TO_WALLET) {
            if (familyCustomers != null)
                return 2 + familyCustomers.size();
            else
                return 2;
        } else if (listViewType == ADD_DEVICE_TO_CUSTOMER) {
            if (familyCustomers != null)
                return 1 + familyCustomers.size();
            else
                return 1;
        }

        return 0;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        View contentView = holder.contentView;

        if (familyCustomers == null)
            familyCustomers = new ArrayList<>();

        if (i == 0) {
            EditText txtMainWalletID = (EditText) contentView.findViewById(R.id.txtMainWalletID);

//            if (WarrantixPreference.warrantixConfig.hasMainCustomer() == true)
//                txtMainWalletID.setText(WarrantixPreference.warrantixConfig.getMainCustomer().getWalletID());
        }
        else if (i <= familyCustomers.size()) {
            // add device to ....
            LinearLayout addDeviceLayout = (LinearLayout) contentView.findViewById(R.id.addDeviceLayout);
            RelativeLayout btnAddDevice = (RelativeLayout) contentView.findViewById(R.id.btnAddDevice);

            final int index = i - 1;
            if (WalletBrandListWalletIDAdapter.this.listViewType == ADD_CUSTOMER_TO_WALLET)
                addDeviceLayout.setVisibility(View.GONE);
            else {
                addDeviceLayout.setVisibility(View.VISIBLE);
                btnAddDevice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WalletBrandListSettingsAddDeviceID.class);
                        intent.putExtra("familyID", index);
                        if (context instanceof BaseActivity)
                            ((BaseActivity) context).startActivity(intent, true);
                        else
                            context.startActivity(intent);
                    }
                });
            }

            updateFamilyView(contentView, i - 1);

        }
        else if (i > familyCustomers.size()) {
            // add customer to ...
            CardView customerAddLayout = (CardView) contentView.findViewById(R.id.customerAddLayout);
            Button addCustomerIDBTN = (Button) contentView.findViewById(R.id.addCustomerIDBTN);

            if (WalletBrandListWalletIDAdapter.this.listViewType != ADD_CUSTOMER_TO_WALLET)
                customerAddLayout.setVisibility(View.GONE);
            else {
                customerAddLayout.setVisibility(View.VISIBLE);
                addCustomerIDBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WalletBrandListSettingsAddCustomerID.class);
                        if (context instanceof BaseActivity)
                            ((BaseActivity) context).startActivity(intent, true);
                        else
                            context.startActivity(intent);
                    }
                });
            }
        }
    }

    private void updateFamilyView(View contentView, int familyIndex) {
        TextView txtFamilyID = (TextView) contentView.findViewById(R.id.txtFamilyID);
        TextView txtCustomerID = (TextView) contentView.findViewById(R.id.txtCustomerID);

        Customer family = familyCustomers.get(familyIndex);
        txtFamilyID.setText(Integer.toString(familyIndex + 1));
        txtCustomerID.setText(family.getId());

        LinearLayout phoneListLayout = (LinearLayout) contentView.findViewById(R.id.phoneListLayout);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get family device id list
        ArrayList<String> familyDeviceIDs = (ArrayList<String>) family.getDeviceID();
        for (int i = 0; i < familyDeviceIDs.size(); i++) {
            LinearLayout dynamicphoneLayout = (LinearLayout) inflater.inflate(R.layout.listview_phone_field, phoneListLayout, false);
            TextView dynamicMobileNumber = (TextView) dynamicphoneLayout.findViewById(R.id.txtMobileNumber);
            dynamicMobileNumber.setText(family.getDevice(familyDeviceIDs.get(i)).getTel());

            phoneListLayout.addView(dynamicphoneLayout);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else if (familyCustomers != null && position <= familyCustomers.size())
            return FAMILY;
        else
            return FOOTER;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == HEADER) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_wallet_brand_walletid_header, viewGroup, false);
            return new ViewHolder(itemView, viewType, context);
        } else if (viewType == FAMILY) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_wallet_brand_walletid_family, viewGroup, false);
            return new ViewHolder(itemView, viewType, context);
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_wallet_brand_walletid_footer, viewGroup, false);
            return new ViewHolder(itemView, viewType, context);
        }
    }

    //
    // Text watcher for text format
    //

//    private final TextWatcher walletWatcher = new TextWatcher() {
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            boolean flag = true;
//            String eachBlock[] = txtCustomerID.getText().toString().split("-");
//            for (int i = 0; i < eachBlock.length; i++) {
//                if (eachBlock[i].length() > 4) {
//                    flag = false;
//                }
//            }
//
//            if (flag) {
//                txtCustomerID.setOnKeyListener(new View.OnKeyListener() {
//
//                    @Override
//                    public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                        if (keyCode == KeyEvent.KEYCODE_DEL)
//                            keyDel = 1;
//                        return false;
//                    }
//                });
//
//                if (keyDel == 0) {
//                    if (((txtCustomerID.getText().length() + 1) % 5) == 0) {
//
//                        if (txtCustomerID.getText().toString().split("-").length <= 3) {
//                            txtCustomerID.setText(txtCustomerID.getText() + "-");
//                            txtCustomerID.setSelection(txtCustomerID.getText().length());
//                        }
//                    }
//                    strWalletID = txtCustomerID.getText().toString();
//                } else {
//                    strWalletID = txtCustomerID.getText().toString();
//                    keyDel = 0;
//                }
//
//            } else {
//                txtCustomerID.setText(strWalletID);
//            }
//
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count,
//                                      int after) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//        }
//    };


//    mobilephone1_check.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (isCheck1 == false)
//                isCheck1 = true;
//            else
//                isCheck1 = false;
//
//            if (isCheck1 == false)
//                mobilephone1_check.setBackgroundResource(R.drawable.square);
//            else
//                mobilephone1_check.setBackgroundResource(R.drawable.square_tick);
//        }
//    });


}