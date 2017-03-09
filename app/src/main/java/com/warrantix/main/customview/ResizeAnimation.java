package com.warrantix.main.customview;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {
    final int startWidth;
    final float targetWidth;
    View view;

    public ResizeAnimation(View view, int percentage, int duration) {
        this.view = view;
        startWidth = view.getWidth();
        this.targetWidth = (float) startWidth * (float)percentage / (float)100;
        setDuration(duration);

//        Log.v("ResizeAnimation", "StartWidth = " + startWidth + " targetWidth = " + targetWidth);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
        view.getLayoutParams().width = newWidth;
        view.requestLayout();

//        Log.v("ResizeAnimation", "newWidth = " + newWidth + " interpolatedTime = " + interpolatedTime);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}