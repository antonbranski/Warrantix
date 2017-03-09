package com.warrantix.main.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.common.image.PicassoProvider;

import java.util.List;

public class SearchMarketPlaceGridAdapter extends BaseAdapter {

    private Context context;
    private final List<String> pluginResourceIDs;

    public SearchMarketPlaceGridAdapter(Context context, List<String> pluginResourceIDs) {
        this.context = context;
        this.pluginResourceIDs = pluginResourceIDs;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView != null) {
            gridView = convertView;
        }
        else {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.gridcell_marketplace_plugin, null);
        }

        int row = position / 3;

        // set value into textview
        TextView textNumberView = (TextView) gridView.findViewById(R.id.textNumberView);
        TextView defaultTextView = (TextView) gridView.findViewById(R.id.defaultTextView);
        ImageView pluginImageView = (ImageView) gridView.findViewById(R.id.pluginImageView);

        pluginImageView.setVisibility(View.VISIBLE);
        textNumberView.setVisibility(View.GONE);
        defaultTextView.setVisibility(View.GONE);

        PicassoProvider.getInstance().get()
                .load(pluginResourceIDs.get(position))
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(pluginImageView);

        gridView.setBackgroundResource(R.drawable.grid_layout_selector);

        return gridView;
    }

    @Override
    public int getCount() {
        return pluginResourceIDs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}