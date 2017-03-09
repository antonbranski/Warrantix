package com.warrantix.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.localdb.BrandObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 20/07/16.
 */
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandHolder> {


    private Context mContext;
    private List<BrandObject> brandObjectList = new ArrayList<>();
    int size = 0;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public BrandAdapter(Context mContext, List<BrandObject> brandObjectList) {
        this.mContext = mContext;
        this.brandObjectList = brandObjectList;
        size = brandObjectList.size();
    }

    @Override
    public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_brands, parent, false);
        return new BrandHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BrandHolder holder, final int position) {

        BrandObject brandObject = brandObjectList.get(position);

//        if (size > 1)
//            holder.viewLine.setVisibility(position == size - 1 || position == size - 2 ? View.GONE : View.VISIBLE);

        holder.txtBrandName.setText(brandObject.getBrandName());

        PicassoProvider.getInstance().get()
                .load(brandObject.getBrandIcon())
                .into(holder.img);


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

    static class BrandHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.txt_brandName)
        TextView txtBrandName;
        @BindView(R.id.view_line)
        View viewLine;
        @BindView(R.id.ll_main)
        LinearLayout llMain;

        BrandHolder(View view) {
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
