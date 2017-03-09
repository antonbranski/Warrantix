package com.warrantix.main.customview;

import android.view.MotionEvent;
import android.view.View;

public class MultilineTextTouchListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                v.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return false;
    }
}