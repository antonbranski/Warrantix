package com.warrantix.main.fragments;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.framework.common.bus.BusProvider;

public class BaseFragment extends Fragment {

    protected Activity mActivity = null;
    protected ImageView categoryImageView;
    protected TextView titleText;
    protected RelativeLayout titleBackground;
    private long mLastClickTime = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;

        BusProvider.get().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BusProvider.get().unregister(this);

        mActivity = null;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
//        if (mActivity != null) {
//            InputMethodManager im = (InputMethodManager) mActivity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }

        super.setMenuVisibility(visible);
    }

    /* To avoid multiple clicks on screen at a same time */
    public boolean isClickEnableForCVV() {

        int timeBetweenClick = 2000; //in ns
        if (SystemClock.elapsedRealtime() - mLastClickTime < timeBetweenClick)
            return false;
        else {
            mLastClickTime = SystemClock.elapsedRealtime();
            return true;
        }
    }
}