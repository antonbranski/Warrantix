package com.warrantix.main.customview;

import android.view.View;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.DefaultTransformer;
import com.warrantix.main.WarrantixApplication;

public class AboutPageTransformer extends DefaultTransformer {

    public AboutPageTransformer() {
    }

    protected void onTransform(View view, float position) {
    }

    public boolean isPagingEnabled() {
        return true;
    }

    @Override
    protected void onPreTransform(View view, float position) {
        super.onPreTransform(view, position);

//        Toast.makeText(WarrantixApplication.getInstance().getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
    }


}
