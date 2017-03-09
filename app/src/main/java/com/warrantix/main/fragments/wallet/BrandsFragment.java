package com.warrantix.main.fragments.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.adapter.BrandAdapter;
import com.warrantix.main.common.localdb.BrandObject;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BrandManager;
import com.warrantix.main.manager.PluginManager;
import com.warrantix.main.modules.b2c.b2cUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandsFragment extends BaseFragment implements BrandAdapter.ItemClickListener {

    private static final String TAG = BrandsFragment.class.getSimpleName();
    @BindView(R.id.lblTitleInBrandFragment)
    TextView lblTitleInBrandFragment;
    @BindView(R.id.brandLogoInBrandFragment)
    ImageView brandLogoInBrandFragment;
    @BindView(R.id.advertiseVideoInBrandFragment)
    ImageView advertiseVideoInBrandFragment;
    @BindView(R.id.advertiseVideoInRetailerFragment)
    ImageView advertiseVideoInRetailerFragment;
    @BindView(R.id.lblAssociatedBrandInBrandFragment)
    TextView lblAssociatedBrandInBrandFragment;
    @BindView(R.id.rv_grid)
    RecyclerView rvGrid;

    private Context mContext;
    BrandAdapter mAdapter;

    private BrandManager brandManager;
    private List<BrandObject> brandObjectList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_brands, container, false);
        ButterKnife.bind(this, v);
        Init();
        return v;
    }


    private void Init() {

        mContext = getActivity();



        brandLogoInBrandFragment.setOnClickListener(brandLogoClickListener);
        advertiseVideoInBrandFragment.setOnClickListener(advertiseVideoClickListener);


    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WarrantixApplication.getAppContext());
        String imageURL = sharedPreferences.getString(b2cUtil.B2C_IMAGE_URL, "");

//        if (!imageURL.equals("")) {
//            Picasso.with(getActivity()).load(imageURL).into(advertiseVideoView);
//        }

        getBrandDataAndSetAdapter();
    }

    private final View.OnClickListener brandLogoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pluginUri = "com.warrantix.hero";
            boolean installed = WarrantixApplication.getInstance().checkInstallation(pluginUri);
            if (installed) {
                try {
                    Intent intent = PluginManager.getInstance().intentForBrand(pluginUri);
                    if (intent != null)
                        ((BaseActivity) mActivity).startActivityForResult(intent, MainActivity.PLUGIN_APP_LAUNCHED, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "Please check the brand plugin installation", Toast.LENGTH_SHORT).show();
                }
            } else {
                // download apk from play store
                try {
                    Intent marketIntent = PluginManager.getInstance().intentForInstallation(pluginUri);
                    if (marketIntent != null)
                        startActivityForResult(marketIntent, MainActivity.PLUGIN_APP_INSTALLED);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "Please install google play store", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private final View.OnClickListener advertiseVideoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    private void getBrandDataAndSetAdapter() {

        Log.e(TAG, "getBrandDataAndSetAdapter: ");

        brandManager = BrandManager.getInstance();
        brandObjectList = brandManager.getBrandsWithoutLeadBrands();

        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.HORIZONTAL, false);
        rvGrid.setLayoutManager(mLayoutManager);
        rvGrid.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BrandAdapter(mContext, brandObjectList);
        mAdapter.setItemClickListener(this);
        rvGrid.setAdapter(mAdapter);
    }

    @Override
    public void itemClicked(int position) {

    }
}