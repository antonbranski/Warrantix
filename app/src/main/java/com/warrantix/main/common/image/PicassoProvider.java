package com.warrantix.main.common.image;

import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.Utils;

public class PicassoProvider {
    private static PicassoProvider instance = null;
    public static PicassoProvider getInstance() {
        if (instance == null) {
            instance = new PicassoProvider();
            instance.initialize();
        }
        return instance;
    }

    private Picasso mPicasso;
    private LruCache mCache;

    private void initialize() {
        mCache = new LruCache(Utils.calculateMemoryCacheSize(WarrantixApplication.getAppContext()));
        mPicasso = new Picasso.Builder(WarrantixApplication.getAppContext())
                .memoryCache(mCache)
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.d("PicassoListener", "Image load for Uri = " + uri.toString());
                    }
                })
                .build();
    }

    public Picasso get() {
        return mPicasso;
    }
}