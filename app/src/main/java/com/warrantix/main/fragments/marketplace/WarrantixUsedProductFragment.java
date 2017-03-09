package com.warrantix.main.fragments.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.linearlistview.LinearListView;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.adapter.WarrantixMarketplaceCategoryUsedProductAdapter;
import com.warrantix.main.common.event.UsedProductAppliancesSuccessEvent;
import com.warrantix.main.common.event.UsedProductElectronicsSuccessEvent;
import com.warrantix.main.common.event.UsedProductVehiclesSuccessEvent;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.response.GetUsedProductsResponse;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

public class WarrantixUsedProductFragment extends BaseFragment {

    private MarketPlaceFragment parentFragment = null;

    private List<UsedProduct> mAppliance = new ArrayList<>();
    private LinearListView eletronicsLinearListView;
    private LinearListView appliancesLinearListView;
    private LinearListView vehiclesLinearListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_warrantix_usedproduct, container, false);

        initialize(v);
        return v;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            BackendManager.getInstance().getUsedProducts("warrantixID", "electronics");
            BackendManager.getInstance().getUsedProducts("warrantixID", "appliances");
            BackendManager.getInstance().getUsedProducts("warrantixID", "vehicles");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        if (eletronicsLinearListView != null)
            eletronicsLinearListView.setOnItemClickListener(productItemClickListener);

        if (appliancesLinearListView != null)
            appliancesLinearListView.setOnItemClickListener(productItemClickListener);

        if (vehiclesLinearListView != null)
            vehiclesLinearListView.setOnItemClickListener(productItemClickListener);
    }

    private void initialize(View v) {
        ImageButton hamburgerButton = (ImageButton) v.findViewById(R.id.popupMenu);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog dialog = new MessageDialog(mActivity);
                dialog.setTitle("Releasing Shortly");
                dialog.setMessage("");
                dialog.show();
            }
        });

        eletronicsLinearListView = (LinearListView) v.findViewById(R.id.horizontal_eletronics_list);
        appliancesLinearListView = (LinearListView) v.findViewById(R.id.horizontal_applicance_list);
        vehiclesLinearListView = (LinearListView) v.findViewById(R.id.horizontal_vehicle_list);

        LinearLayout products_appliances = (LinearLayout) v.findViewById(R.id.products_appliances);
        LinearLayout products_vehicles = (LinearLayout) v.findViewById(R.id.products_vehicles);
        LinearLayout products_electronics = (LinearLayout) v.findViewById(R.id.products_electronics);
        LinearLayout products_assorted = (LinearLayout) v.findViewById(R.id.products_assorted);

        products_appliances.setOnClickListener(productClickListener);
        products_vehicles.setOnClickListener(productClickListener);
        products_electronics.setOnClickListener(productClickListener);
        products_assorted.setOnClickListener(productClickListener);

        ImageButton backButton = (ImageButton) v.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity instanceof MainActivity)
                    ((MainActivity)mActivity).setMarketplaceFragment();
            }
        });

        eletronicsLinearListView.setOnItemClickListener(productItemClickListener);
        appliancesLinearListView.setOnItemClickListener(productItemClickListener);
        vehiclesLinearListView.setOnItemClickListener(productItemClickListener);
    }

    private final View.OnClickListener productClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "USED PRODUCT LIST");
            if (mActivity instanceof BaseActivity)
                ((BaseActivity) mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);
        }
    };

    private final LinearListView.OnItemClickListener productItemClickListener = new LinearListView.OnItemClickListener() {
        @Override
        public void onItemClick(LinearListView parent, View view, int position, long id) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "USED PRODUCT LIST");
            if (mActivity instanceof BaseActivity)
                ((BaseActivity) mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);

            eletronicsLinearListView.setOnItemClickListener(null);
            appliancesLinearListView.setOnItemClickListener(null);
            vehiclesLinearListView.setOnItemClickListener(null);
        }
    };

    @Subscribe
    public void onUsedProductElectronicsSuccessEvent(final UsedProductElectronicsSuccessEvent event)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetUsedProductsResponse getUsedProductsResponse = event.getUsedProductsResponse();

                if (getUsedProductsResponse != null){
                    List<UsedProduct> usedProducts = getUsedProductsResponse.getUsedProducts();
                    if (usedProducts != null) {
                        WarrantixMarketplaceCategoryUsedProductAdapter adapter = new WarrantixMarketplaceCategoryUsedProductAdapter(getActivity(), usedProducts, event.getProducts());
                        eletronicsLinearListView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    @Subscribe
    public void onUsedProductAppliancesSuccessEvent(final UsedProductAppliancesSuccessEvent event)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetUsedProductsResponse getUsedProductsResponse = event.getUsedProductsResponse();

                if (getUsedProductsResponse != null){
                    List<UsedProduct> usedProducts = getUsedProductsResponse.getUsedProducts();
                    if (usedProducts != null) {
                        WarrantixMarketplaceCategoryUsedProductAdapter adapter = new WarrantixMarketplaceCategoryUsedProductAdapter(getActivity(), usedProducts, event.getProducts());
                        appliancesLinearListView.setAdapter(adapter);
                    }
                }
            }
        });
    }


    @Subscribe
    public void onUsedProductVehiclesSuccessEvent(final UsedProductVehiclesSuccessEvent event)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetUsedProductsResponse getUsedProductsResponse = event.getUsedProductsResponse();

                if (getUsedProductsResponse != null){
                    List<UsedProduct> usedProducts = getUsedProductsResponse.getUsedProducts();
                    if (usedProducts != null) {
                        WarrantixMarketplaceCategoryUsedProductAdapter adapter = new WarrantixMarketplaceCategoryUsedProductAdapter(getActivity(), usedProducts, event.getProducts());
                        vehiclesLinearListView.setAdapter(adapter);
                    }
                }
            }
        });
    }


}