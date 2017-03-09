package com.warrantix.main.customview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MoveAnimation extends Animation {
    final float startY;
    final float targetY;
    View view;

    public MoveAnimation(View view, int yOffset, int duration) {
        this.view = view;
        this.startY = view.getY();
        this.targetY = startY + yOffset ;
        setDuration(duration);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newY = (int) (startY + (targetY - startY) * interpolatedTime);
        view.setY(newY);
        view.requestLayout();
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