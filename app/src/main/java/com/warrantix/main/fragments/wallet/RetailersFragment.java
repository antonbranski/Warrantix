package com.warrantix.main.fragments.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.PluginManager;

public class RetailersFragment extends BaseFragment {

    private ImageView retailImageView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_retailers, container, false);

        retailImageView = (ImageView) v.findViewById(R.id.retailLogoInBrandFragment);
        if (retailImageView != null)
            retailImageView.setOnClickListener(retailClickListener);
        return v;
    }

    private final View.OnClickListener retailClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pluginUri = "com.warrantix.hero";

            boolean installed = WarrantixApplication.getInstance().checkInstallation(pluginUri);
            if (installed) {
                Intent intent = PluginManager.getInstance().intentForRetailer(pluginUri);
                if (intent != null)
                    ((BaseActivity) mActivity).startActivityForResult(intent, MainActivity.PLUGIN_APP_LAUNCHED, true);
            } else {
                Intent marketIntent = PluginManager.getInstance().intentForInstallation(pluginUri);
                if (marketIntent != null)
                    startActivity(marketIntent);
            }
        }
    };
}