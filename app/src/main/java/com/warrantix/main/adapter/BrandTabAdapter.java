package com.warrantix.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.warrantix.main.R;
import com.warrantix.main.common.localdb.BrandObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 20/07/16.
 */
public class BrandTabAdapter extends RecyclerView.Adapter<BrandTabAdapter.BrandTabHolder> {


    private Context mContext;
    private List<BrandObject> brandObjectList = new ArrayList<>();

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public BrandTabAdapter(Context mContext, List<BrandObject> brandObjectList) {
        this.mContext = mContext;
        this.brandObjectList = brandObjectList;
    }

    @Override
    public BrandTabHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_tab_brands, parent, false);
        return new BrandTabHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BrandTabHolder holder, final int position) {

        BrandObject brandObject = brandObjectList.get(position);
        holder.viewUnderline.setVisibility(brandObject.isShowUnderline() ? View.VISIBLE : View.GONE);

        if (brandObject.isSelected())
            holder.imgIcon.setImageResource(brandObject.getBrandTextSelectedIcon());
        else holder.imgIcon.setImageResource(brandObject.getBrandTextIcon());


        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Test", "onClick: " + position);
                updateClickPosition(position);
                itemClickListener.itemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return brandObjectList.size();
    }

    static class BrandTabHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.view_underline)
        View viewUnderline;
        @BindView(R.id.ll_main)
        LinearLayout llMain;

        BrandTabHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface ItemClickListener {
        void itemClicked(int position);
    }

    private void updateClickPosition(int position) {
        for (int i = 0; i < brandObjectList.size(); i++) {
            brandObjectList.get(i).setSelected(false);
            brandObjectList.get(i).setShowUnderline(false);
        }
        brandObjectList.get(position).setSelected(true);
        brandObjectList.get(position).setShowUnderline(true);
        notifyDataSetChanged();
    }
}
