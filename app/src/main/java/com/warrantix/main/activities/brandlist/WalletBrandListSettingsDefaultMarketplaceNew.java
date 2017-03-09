package com.warrantix.main.activities.brandlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.squareup.otto.Subscribe;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.adapter.MarketPlaceGridAdapterNew;
import com.warrantix.main.common.event.BrandImgsSuccessEvent;
import com.warrantix.main.common.event.PluginBackToScreenEvent;
import com.warrantix.main.common.rest.response.BrandImageUrlsResponse;
import com.warrantix.main.manager.BackendManager;
import com.warrantix.main.manager.BrandManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 17/07/16.
 */
public class WalletBrandListSettingsDefaultMarketplaceNew extends BaseActivity {

    private static final String TAG = WalletBrandListSettingsDefaultMarketplaceNew.class.getSimpleName();
    @BindView(R.id.brand_arrow)
    ImageButton brandArrow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.defaultmarketplace_bar)
    LinearLayout defaultmarketplaceBar;
    @BindView(R.id.rv_drag)
    RecyclerView rvDrag;
    @BindView(R.id.doneBTN)
    Button doneBTN;
    @BindView(R.id.category)
    LinearLayout category;

    private RecyclerView.LayoutManager mLayoutManager;
    private MarketPlaceGridAdapterNew mAdapter;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;

    private Context mContext;
    private List<String> imagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_defaultmarketplace_new);
        ButterKnife.bind(this);
        Init();
    }

    private void Init() {
        mContext = WalletBrandListSettingsDefaultMarketplaceNew.this;


        mLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);


        // drag & drop manager
        mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();
        mRecyclerViewDragDropManager.setDraggingItemShadowDrawable(
                (NinePatchDrawable) ContextCompat.getDrawable(mContext, R.drawable.material_shadow_z3));

        // Start dragging after long press
        mRecyclerViewDragDropManager.setItemMoveMode(RecyclerViewDragDropManager.ITEM_MOVE_MODE_SWAP);
        mRecyclerViewDragDropManager.setInitiateOnLongPress(true);
        mRecyclerViewDragDropManager.setInitiateOnMove(false);
        mRecyclerViewDragDropManager.setLongPressTimeout(750);

//      adapter
        final MarketPlaceGridAdapterNew myItemAdapter = new MarketPlaceGridAdapterNew(mContext, BrandManager.getInstance().getBrands());

        // Marton's code for CRUD
//        final MarketPlaceGridAdapterNew myItemAdapter = new MarketPlaceGridAdapterNew(mContext, setData());
//        mAdapter = myItemAdapter;

        mWrappedAdapter = mRecyclerViewDragDropManager.createWrappedAdapter(myItemAdapter);// wrap for dragging
        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();

        rvDrag.setLayoutManager(mLayoutManager);
        rvDrag.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        rvDrag.setItemAnimator(animator);

        mRecyclerViewDragDropManager.attachRecyclerView(rvDrag);

        BackendManager.getInstance().getBrandImageUrls("marketplace");

        rvDrag.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy <= 0) {
                    if (category.getVisibility() != View.VISIBLE) {
                        category.setVisibility(View.VISIBLE);

                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.animation_slide_in_up);
                        animation.setDuration(400);
                        category.setAnimation(animation);
                        category.animate();
                        animation.start();
                    }
                } else {
                    if (category.getVisibility() != View.GONE) {
                        category.setVisibility(View.GONE);

                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.animation_slide_out_up);
                        animation.setDuration(400);
                        category.setAnimation(animation);
                        category.animate();
                        animation.start();
                    }
                }
            }
        });


    }

    @OnClick({R.id.doneBTN, R.id.brand_arrow})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.doneBTN:
                PluginBackToScreenEvent event = new PluginBackToScreenEvent("settings");
                BusProvider.get().post(event);

//                GlobalConfig.getInstance().setDefaultPluginIndex(selectedIndex);
//                String toScreen = GlobalConfig.getInstance().getPluginName(selectedIndex);
//                PluginBackToScreenEvent event = new PluginBackToScreenEvent(toScreen);
//                BusProvider.get().post(event);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent, true, true);
                break;
            case R.id.brand_arrow:
                finish(true);
                break;
        }
    }

    @Subscribe
    public void onBrandImsSuccessEvent(final BrandImgsSuccessEvent event) {

        WalletBrandListSettingsDefaultMarketplaceNew.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BrandImageUrlsResponse brandImageUrlsResponse = event.getBrandImageUrlsResponse();

                if (brandImageUrlsResponse != null) {

                    Log.e(TAG, "run 1: " + brandImageUrlsResponse.getBrandImgUrls().size());
                    imagesList = new ArrayList<>(brandImageUrlsResponse.getBrandImgUrls().size());
                    imagesList.addAll(brandImageUrlsResponse.getBrandImgUrls());

                    if (imagesList.size() != 0) {
                        // ??

                    }
                }
            }
        });
    }

    private List<String> setData() {
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.warrantix_logo).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.croma0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.big_bazaar0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.videocon0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.samsung0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.lg0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.suzuki0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tata0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.brand_cromax).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.reliance0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.flipkart0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.zopper0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.croma0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.big_bazaar0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.videocon0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.samsung0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.lg0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.suzuki0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tata0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.brand_cromax).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.reliance0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.flipkart0).toString());
        imagesList.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.zopper0).toString());

        Log.e(TAG, "run 2: " + imagesList.size());
        return imagesList;
    }


}
