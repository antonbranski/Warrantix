package com.warrantix.main.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.warrantix.main.R;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.localdb.BrandObject;
import com.warrantix.main.manager.BrandManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 18/07/16.
 */
// Comment Marton's code for CRUD

public class MarketPlaceGridAdapterNew extends RecyclerView.Adapter<MarketPlaceGridAdapterNew.DragHolder>
        implements DraggableItemAdapter<MarketPlaceGridAdapterNew.DragHolder> {


    private static final String TAG = "MarketPlaceGrid";

    private Context mContext;
    private List<BrandObject> brandList = new ArrayList<>();
    private BrandManager brandManager;

//    private List<String> imageList = new ArrayList<>();

    public MarketPlaceGridAdapterNew(Context context, List<BrandObject> brandList) {
//    public MarketPlaceGridAdapterNew(Context context, List<String> images) {
        setHasStableIds(true);
        this.mContext = context;
        this.brandList = brandList;
        brandManager = BrandManager.getInstance();
//        this.imageList = images;
    }

    @Override
    public DragHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.gridcell_marketplace_plugin_new, parent, false);
        return new DragHolder(v);
    }

    @Override
    public void onBindViewHolder(DragHolder holder, int position) {

        BrandObject brandObject = brandList.get(position);

        // set text
        holder.txtDefault.setVisibility(position == 0 ? View.VISIBLE : View.GONE);

        holder.txtNumber.setVisibility(position == 0 || position > 7 ? View.GONE : View.VISIBLE);
        holder.txtNumber.setText(String.valueOf(position));


        PicassoProvider.getInstance().get()
                .load(brandObject.getBrandIcon())
//                .load(imageList.get(position))
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(holder.image);

        // set background resource (target view ID: container)
        int dragState = holder.getDragStateFlags();

        if (((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_UPDATED) != 0)) {
            int bgResId;

            if ((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.color.wx_fourth_color;
                holder.llMain.setBackgroundResource(bgResId);
            } else if ((dragState & RecyclerViewDragDropManager.STATE_FLAG_DRAGGING) != 0) {
                bgResId = R.color.wx_secondary_color;
                holder.llMain.setBackgroundResource(bgResId);
            } else {
                bgResId = R.color.wx_secondary_color;
                holder.llMain.setBackgroundResource(bgResId);
            }
        }
    }

    @Override
    public int getItemCount() {
        return brandList.size();
//        return imageList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        Log.d(TAG, "onMoveItem(fromPosition = " + fromPosition + ", toPosition = " + toPosition + ")");

        if (fromPosition == toPosition || fromPosition == 0 || toPosition == 0) {
            return;
        }

        BrandObject fromBrandObject = brandList.get(fromPosition);
        BrandObject toBrandObject = brandList.get(toPosition);

        //Update position in local DB
        brandManager.updateBrandPosition(brandList.get(fromPosition).getBrandId(), toPosition);
        brandManager.updateBrandPosition(brandList.get(toPosition).getBrandId(), fromPosition);

        //Update list
        brandList.remove(toPosition);
        brandList.add(toPosition, fromBrandObject);

        brandList.remove(fromPosition);
        brandList.add(fromPosition, toBrandObject);

//        String s = imageList.get(fromPosition);
//        imageList.remove(fromPosition);
//        imageList.add(toPosition, s);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("onMoveItem", " -- notifyDataSetChanged -- ");
                notifyDataSetChanged();
            }
        }, 200);
    }

    @Override
    public boolean onCheckCanStartDrag(DragHolder holder, int position, int x, int y) {
        return position != 0;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(DragHolder holder, int position) {
        return new ItemDraggableRange(1, Math.max(0, getItemCount() - 1));
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return dropPosition != 0;
    }

    static class DragHolder extends AbstractDraggableItemViewHolder {
        @BindView(R.id.txt_number)
        TextView txtNumber;
        @BindView(R.id.txt_default)
        TextView txtDefault;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.ll_main)
        LinearLayout llMain;

        DragHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}