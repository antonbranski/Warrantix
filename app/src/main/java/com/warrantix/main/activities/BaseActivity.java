package com.warrantix.main.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    protected boolean isInitialized = false;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        finish(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        BusProvider.get().register(this);

        WarrantixApplication.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        WarrantixApplication.getInstance().setCurrentActivity(null);
    }

    @Override
    protected void onDestroy() {
        BusProvider.get().unregister(this);
        super.onDestroy();

        System.gc();
    }

    // ------------------------------------------------------------
    // Activity Transition Support functions
    // ------------------------------------------------------------
    public void startActivity(Intent intent, boolean animate) {
        super.startActivity(intent);
        if (animate)
            overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
    }

    public void startActivityForResult(Intent intent, int requestCode, boolean animate) {
        super.startActivityForResult(intent, requestCode);
        if (animate)
            overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
    }

    public void finish(boolean animate) {
        super.finish();
        if (animate)
            overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
    }

    //
    // reverse animation
    //
    public void finish(boolean animate, boolean reverseAnimate) {
        super.finish();

        if ((animate == true) && (reverseAnimate == true))
            overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
        else if ((animate == true) && (reverseAnimate == false))
            overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
    }

    public void startActivity(Intent intent, boolean animate, boolean reverseAnimate) {
        super.startActivity(intent);

        if ((animate == true) && (reverseAnimate == true))
            overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
        else if ((animate == true) && (reverseAnimate == false))
            overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
    }

    public void startActivityForResult(Intent intent, int requestCode, boolean animate, boolean reverseAnimate) {
        super.startActivityForResult(intent, requestCode);

        if ((animate == true) && (reverseAnimate == true))
            overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
        else if ((animate == true) && (reverseAnimate == false))
            overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
    }


    // ------------------------------------------------------------
    // Plugin Support functions
    // ------------------------------------------------------------
    public Intent urlMap(Intent intent) {
        return intent;
    }

    @Override
    public void startActivity(Intent intent) {
        intent = urlMap(intent);
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent = urlMap(intent);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        intent = urlMap(intent);
        super.startActivityFromFragment(fragment, intent, requestCode);
    }

    public void startActivity(String urlSchema) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlSchema)));
    }

    public void startActivityForResult(String urlSchema, int requestCode) {
        startActivityForResult(
                new Intent(Intent.ACTION_VIEW, Uri.parse(urlSchema)),
                requestCode);
    }

    /* To avoid multiple clicks on screen at a same time */
    public boolean isClickEnable() {

        int timeBetweenClick = 600; //in ns
        if (SystemClock.elapsedRealtime() - mLastClickTime < timeBetweenClick)
            return false;
        else {
            mLastClickTime = SystemClock.elapsedRealtime();
            return true;
        }
    }

    // ------------------------------------------------------------
    // support listview depended on items -- deprecated
    //
//    public static boolean setListViewHeightBasedOnItems(ListView listView) {
//
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter != null) {
//
//            int numberOfItems = listAdapter.getCount();
//
//            // Get total height of all items.
//            int totalItemsHeight = 0;
//            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
//                View item = listAdapter.getView(itemPos, null, listView);
//                item.measure(0, 0);
//                totalItemsHeight += item.getMeasuredHeight();
//            }
//
//            // Get total height of all item dividers.
//            int totalDividersHeight = listView.getDividerHeight() *
//                    (numberOfItems - 1);
//
//            // Set list height.
//            ViewGroup.LayoutParams params = listView.getLayoutParams();
//            params.height = totalItemsHeight + totalDividersHeight;
//            listView.setLayoutParams(params);
//            listView.requestLayout();
//
//            return true;
//
//        } else {
//            return false;
//        }
//
//    }
}