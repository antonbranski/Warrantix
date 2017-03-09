package com.warrantix.main.fragments.marketplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsDefaultMarketplaceNew;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsSearchMarketplace;
import com.warrantix.main.adapter.BrandTabAdapter;
import com.warrantix.main.common.localdb.BrandObject;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BrandManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketPlaceFragment extends BaseFragment implements BrandTabAdapter.ItemClickListener {


    private static final String TAG = MarketPlaceFragment.class.getSimpleName();
    @BindView(R.id.rv_tabBrands)
    RecyclerView rvTabBrands;
    @BindView(R.id.pluginPager)
    NonSwipeableViewPager pluginPager;

    private Context mContext;
    private FragmentStatePagerAdapter pluginPageAdapter = null;
    private BrandManager brandManager;
    private List<BrandObject> brandObjectList = new ArrayList<>();
    private BrandTabAdapter brandTabAdapter;

    //Default
    private final String searchID = "1356";
    private final String settingID = "1357";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_marketplace, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void Init() {
        mContext = getActivity();
        getBrandDataAndSetAdapter();

    }

    public void getBrandDataAndSetAdapter() {

        Log.e(TAG, "getBrandDataAndSetAdapter: ");
        brandManager = BrandManager.getInstance();
        brandObjectList = brandManager.getBrands();
        //Add search
        brandObjectList.add(new BrandObject(searchID, "Search", "", brandObjectList.size() + 1, (R.drawable.tab_search), (R.drawable.tab_search_b), false, false, false, false));
        //Add setting
        brandObjectList.add(new BrandObject(settingID, "Setting", "", brandObjectList.size() + 2, (R.drawable.tab_setting), (R.drawable.tab_setting_b), false, false, false, false));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rvTabBrands.setLayoutManager(mLayoutManager);
        rvTabBrands.setItemAnimator(new DefaultItemAnimator());
        brandTabAdapter = new BrandTabAdapter(mContext, brandObjectList);
        brandTabAdapter.setItemClickListener(this);
        rvTabBrands.setAdapter(brandTabAdapter);

        initializePluginFragments();
    }

    private void initializePluginFragments() {
        pluginPageAdapter = new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
//                if (position == 0) {
//                    WarrantixMainFragment fragment = new WarrantixMainFragment();
//                    fragment.setParentFragment(MarketPlaceFragment.this);
//                    return fragment;
//                } else if (position == 1)
//                    return new HeroFragment();
//                else
//                    return new Fragment();

                Log.e(TAG, "getItem: ?? --- " + brandObjectList.get(position).getBrandId());

                if (brandObjectList.isEmpty()) {
                    return new Fragment();
                } else {
                    switch (brandObjectList.get(position).getBrandId()) {

                        case "0":
                            WarrantixMainFragment fragment = new WarrantixMainFragment();
                            fragment.setParentFragment(MarketPlaceFragment.this);
                            return fragment;

                        case "1":
                            return new HeroFragment();

                        default:
                            return new Fragment();

                    }
                }
            }


            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public int getCount() {
                return 9;
            }
        };

        pluginPager.setOffscreenPageLimit(pluginPageAdapter.getCount() + 1);
        pluginPager.setAdapter(pluginPageAdapter);

        int heroPos = getPositionByName("hero");
        //Default
        brandObjectList.get(heroPos).setSelected(true);
        brandObjectList.get(heroPos).setShowUnderline(true);
        pluginPager.setCurrentItem(heroPos);
    }

//    public void manualSelectBrandIcon(String brandName) {
//        if (brandName.equalsIgnoreCase("warrantix")) {
//            warrantixLayout.callOnClick();
//            autoScroll(0);
//        } else if (brandName.equalsIgnoreCase("hero")) {
//            heroLayout.callOnClick();
//            autoScroll(1);
//        } else if (brandName.equalsIgnoreCase("goorej")) {
//            goorejLayout.callOnClick();
//            autoScroll(2);
//        } else if (brandName.equalsIgnoreCase("samsung")) {
//            samsungLayout.callOnClick();
//            autoScroll(3);
//        } else if (brandName.equalsIgnoreCase("eureka")) {
//            forbesLayout.callOnClick();
//            autoScroll(4);
//        } else if (brandName.equalsIgnoreCase("lg")) {
//            lgLayout.callOnClick();
//            autoScroll(5);
//        } else if (brandName.equalsIgnoreCase("mahindra")) {
//            mahindraLayout.callOnClick();
//            autoScroll(6);
//        } else if (brandName.equalsIgnoreCase("micromax")) {
//            micromaxLayout.callOnClick();
//            autoScroll(7);
//        } else if (brandName.equalsIgnoreCase("voltas")) {
//            voltasLayout.callOnClick();
//            autoScroll(8);
//        }
//    }
//
//    private void autoScroll(int i) {
//        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
//        int widthScreen = metrics.widthPixels;
//
//        int margin = (int) getResources().getDimension(R.dimen._5sdp);
//        int positionLeftWidth = (warrantixLayout.getWidth() + margin) * i - margin;
//        positionLeftWidth = positionLeftWidth - widthScreen / 2;
//        if (positionLeftWidth < 0)
//            positionLeftWidth = 0;
//
//        brandScrollBar.smoothScrollTo(positionLeftWidth, 0);
//    }
//
//
//    private void initializeBrandBar(View v) {
//
//        brandScrollBar = (HorizontalScrollView) v.findViewById(R.id.brandScrollBar);
//
//        warrantixLayout = (LinearLayout) v.findViewById(R.id.tab_warrantix);
//        heroLayout = (LinearLayout) v.findViewById(R.id.tab_hero);
//        goorejLayout = (LinearLayout) v.findViewById(R.id.tab_goorej);
//        samsungLayout = (LinearLayout) v.findViewById(R.id.tab_samsung);
//        forbesLayout = (LinearLayout) v.findViewById(R.id.tab_forbes);
//        lgLayout = (LinearLayout) v.findViewById(R.id.tab_lg);
//        mahindraLayout = (LinearLayout) v.findViewById(R.id.tab_mahindra);
//        micromaxLayout = (LinearLayout) v.findViewById(R.id.tab_micromax);
//        voltasLayout = (LinearLayout) v.findViewById(R.id.tab_voltas);
//        searchLayout = (LinearLayout) v.findViewById(R.id.tab_search);
//        settingLayout = (LinearLayout) v.findViewById(R.id.tab_setting);
//
//        warrantixImageView = (ImageView) v.findViewById(R.id.tab_warrantix_imageview);
//        heroImageView = (ImageView) v.findViewById(R.id.tab_hero_imageview);
//        goorejImageView = (ImageView) v.findViewById(R.id.tab_goorej_imageview);
//        samsungImageView = (ImageView) v.findViewById(R.id.tab_samsung_imageview);
//        forbesImageView = (ImageView) v.findViewById(R.id.tab_forbes_imageview);
//        lgImageView = (ImageView) v.findViewById(R.id.tab_lg_imageview);
//        mahindraImageView = (ImageView) v.findViewById(R.id.tab_mahindra_imageview);
//        micromaxImageView = (ImageView) v.findViewById(R.id.tab_micromax_imageview);
//        voltasImageView = (ImageView) v.findViewById(R.id.tab_voltas_imageview);
//        searchImageView = (ImageView) v.findViewById(R.id.tab_search_imageview);
//        settingImageView = (ImageView) v.findViewById(R.id.tab_setting_imageview);
//
//        warrantixUnderline = (View) v.findViewById(R.id.warrantix_underline);
//        heroUnderline = (View) v.findViewById(R.id.hero_underline);
//        goorejUnderline = (View) v.findViewById(R.id.goorey_underline);
//        samsungUnderline = (View) v.findViewById(R.id.samsung_underline);
//        forbesUnderline = (View) v.findViewById(R.id.forbles_underline);
//        lgUnderline = (View) v.findViewById(R.id.lg_underline);
//        mahindraUnderline = (View) v.findViewById(R.id.mahindra_underline);
//        micromaxUnderline = (View) v.findViewById(R.id.micromax_underline);
//        voltasUnderline = (View) v.findViewById(R.id.voltas_underline);
//
//        prevImageView = heroImageView;
//        prevUnderline = heroUnderline;

//        warrantixDrawable1 = getResources().getDrawable(R.drawable.tab_warrantix);
//        warrantixDrawable2 = getResources().getDrawable(R.drawable.tab_warrantix_b);
//        heroDrawable1 = getResources().getDrawable(R.drawable.tab_hero);
//        heroDrawable2 = getResources().getDrawable(R.drawable.tab_hero_b);
//        goorejDrawable1 = getResources().getDrawable(R.drawable.tab_godrej);
//        goorejDrawable2 = getResources().getDrawable(R.drawable.tab_godrej_b);
//        samsungDrawable1 = getResources().getDrawable(R.drawable.tab_samsung);
//        samsungDrawable2 = getResources().getDrawable(R.drawable.tab_samsung_b);
//        forbesDrawable1 = getResources().getDrawable(R.drawable.tab_forbles);
//        forbesDrawable2 = getResources().getDrawable(R.drawable.tab_forbles_b);
//        lgDrawable1 = getResources().getDrawable(R.drawable.tab_lg);
//        lgDrawable2 = getResources().getDrawable(R.drawable.tab_lg_b);
//        mahindraDrawable1 = getResources().getDrawable(R.drawable.tab_mahindra);
//        mahindraDrawable2 = getResources().getDrawable(R.drawable.tab_mahindra_b);
//        micromaxDrawable1 = getResources().getDrawable(R.drawable.tab_micromax);
//        micromaxDrawable2 = getResources().getDrawable(R.drawable.tab_micromax_b);
//        voltasDrawable1 = getResources().getDrawable(R.drawable.tab_voltas);
//        voltasDrawable2 = getResources().getDrawable(R.drawable.tab_voltas_b);
//        searchDrawable1 = getResources().getDrawable(R.drawable.tab_search);
//        searchDrawable2 = getResources().getDrawable(R.drawable.tab_search_b);
//        settingDrawable1 = getResources().getDrawable(R.drawable.tab_setting);
//        settingDrawable2 = getResources().getDrawable(R.drawable.tab_setting_b);

//        warrantixImageView.setImageDrawable(warrantixDrawable2);
//        heroImageView.setImageDrawable(heroDrawable1);
//        goorejImageView.setImageDrawable(goorejDrawable2);
//        samsungImageView.setImageDrawable(samsungDrawable2);
//        forbesImageView.setImageDrawable(forbesDrawable2);
//        lgImageView.setImageDrawable(lgDrawable2);
//        mahindraImageView.setImageDrawable(mahindraDrawable2);
//        micromaxImageView.setImageDrawable(micromaxDrawable2);
//        voltasImageView.setImageDrawable(voltasDrawable2);
//        searchImageView.setImageDrawable(searchDrawable2);
//        settingImageView.setImageDrawable(settingDrawable2);

//        warrantixLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//
//                warrantixImageView.setImageDrawable(warrantixDrawable1);
//                warrantixUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = warrantixImageView;
//                prevUnderline = warrantixUnderline;
//
//                pluginPager.setCurrentItem(0);
//            }
//        });
//
//        heroLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                heroImageView.setImageDrawable(heroDrawable1);
//                heroUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = heroImageView;
//                prevUnderline = heroUnderline;
//
//                pluginPager.setCurrentItem(1);
//            }
//        });
//
//        goorejLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                goorejImageView.setImageDrawable(goorejDrawable1);
//                goorejUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = goorejImageView;
//                prevUnderline = goorejUnderline;
//
//                pluginPager.setCurrentItem(2);
//            }
//        });
//
//        samsungLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                samsungImageView.setImageDrawable(samsungDrawable1);
//                samsungUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = samsungImageView;
//                prevUnderline = samsungUnderline;
//
//                pluginPager.setCurrentItem(3);
//            }
//        });
//
//        forbesLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                forbesImageView.setImageDrawable(forbesDrawable1);
//                forbesUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = forbesImageView;
//                prevUnderline = forbesUnderline;
//
//                pluginPager.setCurrentItem(4);
//            }
//        });
//
//        lgLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                lgImageView.setImageDrawable(lgDrawable1);
//                lgUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = lgImageView;
//                prevUnderline = lgUnderline;
//
//                pluginPager.setCurrentItem(5);
//            }
//        });
//
//        mahindraLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                mahindraImageView.setImageDrawable(mahindraDrawable1);
//                mahindraUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = mahindraImageView;
//                prevUnderline = mahindraUnderline;
//
//                pluginPager.setCurrentItem(6);
//            }
//        });
//
//        micromaxLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                micromaxImageView.setImageDrawable(micromaxDrawable1);
//                micromaxUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = micromaxImageView;
//                prevUnderline = micromaxUnderline;
//
//                pluginPager.setCurrentItem(7);
//            }
//        });
//
//        voltasLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearPreviousView();
//                voltasImageView.setImageDrawable(voltasDrawable1);
//                voltasUnderline.setVisibility(View.VISIBLE);
//
//                prevImageView = voltasImageView;
//                prevUnderline = voltasUnderline;
//
//                pluginPager.setCurrentItem(8);
//            }
//        });

//        searchLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                clearPreviousView();
////                searchImageView.setImageDrawable(searchDrawable1);
////
////                prevImageView = searchImageView;
////                prevUnderline = null;
//                Intent i = new Intent(mActivity, WalletBrandListSettingsSearchMarketplace.class);
//                ((BaseActivity)mActivity).startActivity(i, true);
//            }
//        });
//
//        settingLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                clearPreviousView();
////                settingImageView.setImageDrawable(settingDrawable1);
////
////                prevImageView = settingImageView;
////                prevUnderline = null;
//
////                Intent i = new Intent(mActivity, WalletBrandListSettingsDefaultMarketplace.class);
//                Intent i = new Intent(mActivity, WalletBrandListSettingsDefaultMarketplaceNew.class);
//                ((BaseActivity)mActivity).startActivity(i, true);
//            }
//        });
//}

    public void setWarrantixUsedProductFragment() {
        if (mActivity instanceof MainActivity)
            ((MainActivity) mActivity).setWarrantixUsedProductFragment();
    }

    public void setWarrantixCategoryFragment(int index) {
        if (mActivity instanceof MainActivity)
            ((MainActivity) mActivity).setWarrantixCategoryFragment(index);
    }

    @Override
    public void itemClicked(int position) {


        Log.e(TAG, "itemClicked: " + position);
        switch (brandObjectList.get(position).getBrandId()) {

            case searchID:
                ((BaseActivity) mActivity).startActivity(new Intent(mActivity, WalletBrandListSettingsSearchMarketplace.class), true);
                break;

            case settingID:
                ((BaseActivity) mActivity).startActivity(new Intent(mActivity, WalletBrandListSettingsDefaultMarketplaceNew.class), true);
                break;

            default:
                pluginPager.setCurrentItem(position);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "onActivityResult: INSIDE :) ");

//        if (requestCode == Constant.brandUpdated) {
//            getBrandDataAndSetAdapter();
//        }
    }


    public void manualSelectBrandIcon(String brandName) {
//        if (brandName.equalsIgnoreCase("warrantix")) {
//            pluginPager.setCurrentItem(0);
//        } else if (brandName.equalsIgnoreCase("hero")) {
//            pluginPager.setCurrentItem(0);
//        } else if (brandName.equalsIgnoreCase("goorej")) {
//
//        }
        Log.e(TAG, "manualSelectBrandIcon--?: " + brandName);
        int pos = getPositionByName(brandName);

        if (!brandObjectList.isEmpty()) {
            updateClickPosition(pos);
        }


        pluginPager.setCurrentItem(getPositionByName(brandName));
    }


    private int getPositionByName(String brandName) {
        if (brandObjectList.isEmpty())
            return 0;
        int pos = 0;
        for (int i = 0; i < brandObjectList.size(); i++) {
            if (brandObjectList.get(i).getBrandName().toLowerCase().equalsIgnoreCase(brandName)) {
                pos = i;
            }

        }

        Log.e(TAG, "manualSelectBrandIcon: " + pos);
        return pos;
    }

    private int getPositionByID(String ID) {
        if (brandObjectList.isEmpty())
            return 0;

        int pos = 0;

        for (int i = 0; i < brandObjectList.size(); i++) {
            if (brandObjectList.get(i).getBrandId().toLowerCase().equalsIgnoreCase(ID)) {
                pos = i;
            }

        }

        Log.e(TAG, "getPositionByID: " + pos);
        return pos;
    }

    private void updateClickPosition(int position) {
        for (int i = 0; i < brandObjectList.size(); i++) {
            brandObjectList.get(i).setSelected(false);
            brandObjectList.get(i).setShowUnderline(false);
        }
        brandObjectList.get(position).setSelected(true);
        brandObjectList.get(position).setShowUnderline(true);

        if (brandTabAdapter != null)
            brandTabAdapter.notifyDataSetChanged();
    }
}