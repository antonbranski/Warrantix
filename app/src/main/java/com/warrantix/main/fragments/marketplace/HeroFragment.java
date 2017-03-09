package com.warrantix.main.fragments.marketplace;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.linearlistview.LinearListView;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletMarketplaceAMC;
import com.warrantix.main.activities.WalletMarketplaceInsurance;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.adapter.WarrantixMarketplaceProductsBestSellingAdapter;
import com.warrantix.main.adapter.WarrantixMarketplaceProductsPopularAdapter;
import com.warrantix.main.common.command.WarrantixCommands;
import com.warrantix.main.common.event.ProductsBestSellingSuccessEvent;
import com.warrantix.main.common.event.ProductsPopularSuccessEvent;
import com.warrantix.main.common.event.ProductsTopRateSuccessEvent;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

public class HeroFragment extends BaseFragment {

    private static final String TAG = HeroFragment.class.getSimpleName();
    private ImageButton hamburgerButton = null;
    private LinearListView popularListView;
    private LinearListView bestSellingListView;
    private LinearListView topRateListView;

    private List<Product> mPopularProducts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plugin_hero, container, false);
        initializeCategoryBar(v);
        initializeBrandAppButton(v);
        initializeScrollView(v);
        return v;
    }

    private void initializeScrollView(View v) {

    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {

        }
    }

    @Override
    public void onResume(){
        super.onResume();

        if (bestSellingListView != null)
            bestSellingListView.setOnItemClickListener(categoryListener);

        if (topRateListView != null)
            topRateListView.setOnItemClickListener(categoryListener);

        if (popularListView != null)
            popularListView.setOnItemClickListener(popularListViewItemClickListener);
    }

    @Subscribe
    public void onProductsPopularSuccessEvent(final ProductsPopularSuccessEvent event) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = event.getProductsResponse();
                if (getProductsResponse != null) {
                    List<Product> products = getProductsResponse.getProducts();
                    mPopularProducts = products;

                    printFullLog("Popular --",new Gson().toJson(products));

                    if (products != null) {
                        WarrantixMarketplaceProductsPopularAdapter adapter = new WarrantixMarketplaceProductsPopularAdapter(getActivity(), products);
                        popularListView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    @Subscribe
    public void onProductsTopRateSuccessEvent(final ProductsTopRateSuccessEvent event) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = event.getProductsResponse();
                if (getProductsResponse != null) {
                    List<Product> products = getProductsResponse.getProducts();
                    printFullLog("TopRate -- ",new Gson().toJson(products));

                    if (products != null) {
                        WarrantixMarketplaceProductsBestSellingAdapter adapter = new WarrantixMarketplaceProductsBestSellingAdapter(getActivity(), products);
                        topRateListView.setAdapter(adapter);
                    }
                }
            }
        });
    }


    @Subscribe
    public void onProductsBestSellingSuccessEvent(final ProductsBestSellingSuccessEvent event) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = event.getProductsResponse();
                if (getProductsResponse != null) {
                    List<Product> products = getProductsResponse.getProducts();

                    printFullLog("BestSelling --",new Gson().toJson(products));

                    if (products != null) {
                        WarrantixMarketplaceProductsBestSellingAdapter adapter = new WarrantixMarketplaceProductsBestSellingAdapter(getActivity(), products);
                        bestSellingListView.setAdapter(adapter);
                    }
                }
            }
        });
    }


    private void initializeBrandAppButton(View v) {
        ImageView brandeshopButton = (ImageView) v.findViewById(R.id.brandeshopButton);
        brandeshopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pluginUri = "com.warrantix.hero";
                boolean installed = WarrantixApplication.getInstance().checkInstallation(pluginUri);
                if (installed) {
                    try {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(pluginUri, "com.warrantix.hero.activity.WalletBrandApp"));
                        intent.putExtra(WarrantixCommands.PLUGIN_LAUNCHED, "yes");
                        ((BaseActivity) mActivity).startActivityForResult(intent, MainActivity.PLUGIN_APP_LAUNCHED, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(mActivity, "Please check the brand plugin installation", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pluginUri));
                        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivityForResult(marketIntent, MainActivity.PLUGIN_APP_INSTALLED);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(mActivity, "Please install google play store", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initializeCategoryBar(View v){

//            BackendManager.getInstance().getProductsResponse("heroID", false, true);
//            BackendManager.getInstance().getProductsResponse("heroID", true, false);
//            BackendManager.getInstance().getProductsResponse("heroID", true, true);

        BackendManager.getInstance().getPopularProductsResponse("heroID", false, true);
        BackendManager.getInstance().getBestSellingProductsResponse("heroID", true, true);
        BackendManager.getInstance().getTopRateProductsResponse("heroID", true, false);

        popularListView = (LinearListView) v.findViewById(R.id.horizontal_popular_list);
        bestSellingListView = (LinearListView) v.findViewById(R.id.horizontal_bestselling_list);
        topRateListView = (LinearListView) v.findViewById(R.id.horizontal_toprate_list);

        popularListView.setOnItemClickListener(popularListViewItemClickListener);
        bestSellingListView.setOnItemClickListener(categoryListener);
        topRateListView.setOnItemClickListener(categoryListener);

        hamburgerButton = (ImageButton) v.findViewById(R.id.popupMenu);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMorePopupMenu();
            }
        });

    }

    private final LinearListView.OnItemClickListener popularListViewItemClickListener = new LinearListView.OnItemClickListener() {
        @Override
        public void onItemClick(LinearListView parent, View view, int position, long id) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "Hero " + mPopularProducts.get(position).getName());
            if (mActivity instanceof BaseActivity)
                ((BaseActivity) mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);

            popularListView.setOnItemClickListener(null);
        }
    };

    private final LinearListView.OnItemClickListener categoryListener = new LinearListView.OnItemClickListener() {
        @Override
        public void onItemClick(LinearListView parent, View view, int position, long id) {
            //            Intent i = new Intent(mActivity, WalletBrandAccessoriesEshop.class);
//            ((BaseActivity)mActivity).startActivity(i, true);

            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "HERO MOTORCYCLES");
            if (mActivity instanceof BaseActivity)
                ((BaseActivity) mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);

            bestSellingListView.setOnItemClickListener(null);
            topRateListView.setOnItemClickListener(null);
        }
    };

    public void loadMorePopupMenu() {
        PopupMenu popupMenu = new PopupMenu(mActivity, hamburgerButton) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.menu_plugin_accessories: {
                        Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
                        i.putExtra("title", "eSHOP ACCESSORIES");
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                    return true;

                    case R.id.menu_plugin_amc: {
//                        Intent i = new Intent(mActivity, WalletBrandAMC0.class);
//                        if (mActivity instanceof BaseActivity)
//                            ((BaseActivity) mActivity).startActivity(i, true);
//                        else
//                            mActivity.startActivity(i);

                        Intent i = new Intent(mActivity, WalletMarketplaceAMC.class);
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);

                    }
                    return true;

                    case R.id.menu_plugin_usedproducts: {
                        Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
                        i.putExtra("title", "USED PRODUCTS");
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                    return true;

                    case R.id.menu_plugin_insurance: {
//                        Intent i = new Intent(mActivity, WalletBrandInsurance0.class);
//                        if (mActivity instanceof BaseActivity)
//                            ((BaseActivity)mActivity).startActivity(i, true);
//                        else
//                            mActivity.startActivity(i);

                        Intent i = new Intent(mActivity, WalletMarketplaceInsurance.class);
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                    return true;

                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };

        popupMenu.inflate(R.menu.menu_popup_plugin);
        popupMenu.show();
    }

    public static void printFullLog(String tag, String sb) {
        if (sb.length() > 4000) {
            Log.e(tag, "sb.length = " + sb.length());
            int chunkCount = sb.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    Log.e(tag, "chunk " + i + " of " + chunkCount + ":" + sb.substring(4000 * i));
                } else {
                    Log.e(tag, "chunk " + i + " of " + chunkCount + ":" + sb.substring(4000 * i, max));
                }
            }
        } else {
            Log.e(tag, sb.toString());
        }

    }

}