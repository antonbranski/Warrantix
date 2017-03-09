package com.warrantix.main.manager;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.warrantix.main.R;
import com.warrantix.main.common.localdb.BrandObject;
import com.warrantix.main.common.localdb.WarrantixLocalDatabase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created on 19/07/16.
 */
public class BrandManager {
    private final static String TAG = BrandManager.class.getSimpleName();

    public static BrandManager instance = null;

    public static BrandManager getInstance() {
        if (instance == null)
            instance = new BrandManager();

        return instance;
    }

    public List<BrandObject> createBrandData() {

        List<BrandObject> brandObjects = new ArrayList<>();

        /**
         *
         * @param brandId
         * @param brandName
         * @param brandIcon
         * @param brandOrder
         * @param brandTextIcon
         * @param brandTextSelectedIcon
         * @param isLeadBrand
         * @param isDefault
         * @param showUnderline
         * @param isSelected
         */
        //Add Warrantix
        brandObjects.add(new BrandObject("0", "Warrantix", getImagePath(R.drawable.warrantix_logo), 0, (R.drawable.tab_warrantix_b), (R.drawable.tab_warrantix), true, true, true, true));
        //Add Hero
        brandObjects.add(new BrandObject("1", "Hero", getImagePath(R.drawable.hero_logo), 1, (R.drawable.tab_hero_b), (R.drawable.tab_hero), true, true, false, false));
        //Add Videocon
        brandObjects.add(new BrandObject("2", "Videocon", getImagePath(R.drawable.brand_videocon), 2, (R.drawable.tab_warrantix_b), (R.drawable.tab_warrantix), false, false, false, false));
        //Add Samsung
        brandObjects.add(new BrandObject("3", "Samsung", getImagePath(R.drawable.brand_samsung), 3, (R.drawable.tab_samsung_b), (R.drawable.tab_samsung), false, false, false, false));
        //Add Lg
        brandObjects.add(new BrandObject("4", "Lg", getImagePath(R.drawable.brand_lg), 4, (R.drawable.tab_lg_b), (R.drawable.tab_lg), false, false, false, false));
        //Add Suzuki
        brandObjects.add(new BrandObject("5", "Suzuki", getImagePath(R.drawable.brand_suzuki), 5, (R.drawable.tab_warrantix_b), (R.drawable.tab_warrantix), false, false, false, false));
        //Add Tata
        brandObjects.add(new BrandObject("6", "Tata", getImagePath(R.drawable.brand_tata), 6, (R.drawable.tab_warrantix_b), (R.drawable.tab_warrantix), false, false, false, false));
        //Add Godrej
        brandObjects.add(new BrandObject("7", "Godrej", getImagePath(R.drawable.brand_godrej), 7, (R.drawable.tab_godrej_b), (R.drawable.tab_godrej), false, false, false, false));

        Log.e(TAG, "createBrandData:" + brandObjects.size());

        return brandObjects;
    }


    public String getImagePath(int imageResId) {
        return Uri.parse("android.resource://com.warrantix.main/" + imageResId).toString();
    }

    public void addBrands(final List<BrandObject> brandObjects, Context mContext) {

        Log.e(TAG, "addBrands:" + brandObjects.size());

        WarrantixLocalDatabase.getBrandDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (!brandObjects.isEmpty()) {
                    realm.copyToRealmOrUpdate(brandObjects);
                }
            }
        });

//        WarrantixPreference.saveBooleanPreference(Constant.isBrandSaved, true);
    }

    public List<BrandObject> getBrands() {

        Log.e(TAG, "getBrands()--");
        List<BrandObject> brandObjects = new ArrayList<>();
        RealmResults<BrandObject> objects = WarrantixLocalDatabase.getBrandDatabase()
                .where(BrandObject.class)
                .findAllSorted("brandOrder");

        if (!objects.isEmpty()) {
            for (BrandObject brandObject : objects) {
                brandObjects.add(brandObject);
            }
        }

        Log.d(TAG, "getBrands()--" + brandObjects.size());
        return brandObjects;
    }

    public List<BrandObject> getBrandsWithoutLeadBrands() {

        Log.e(TAG, "getBrandsWithoutLeadBrands()--");
        List<BrandObject> brandObjects = new ArrayList<>();

        RealmResults<BrandObject> objects = WarrantixLocalDatabase.getBrandDatabase()
                .where(BrandObject.class).equalTo("isLeadBrand", false).equalTo("isLeadBrand", false)
                .findAllSorted("brandOrder");

        if (!objects.isEmpty()) {
            for (BrandObject brandObject : objects) {
                brandObjects.add(brandObject);
            }
        }

        Log.d(TAG, "getBrandsWithoutLeadBrands()--" + brandObjects.size());
        return brandObjects;
    }


    public void updateBrandPosition(final String brandId, final int newPosition) {

        Log.e(TAG, "updateBrandPosition()--");
        WarrantixLocalDatabase.getBrandDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                BrandObject brandObject = realm.where(BrandObject.class)
                        .equalTo("brandId", brandId)
                        .findFirst();
                brandObject.setBrandOrder(newPosition);
                Log.d(TAG, "updateBrandPosition()-Yes" + "--brandId--" + brandId + "--newPosition --" + newPosition);
            }
        });
    }
}
