package com.warrantix.main.fragments.about;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsAbout;
import com.warrantix.main.common.Utils;
import com.warrantix.main.customview.framevideoview.FrameVideoView;
import com.warrantix.main.customview.framevideoview.FrameVideoViewListener;
import com.warrantix.main.fragments.BaseFragment;

import java.util.Random;

public class WarrantixAboutFragment1 extends BaseFragment {

    private final static String TAG = WarrantixAboutFragment1.class.getSimpleName();

    private TextView txtView1;
    private TextView txtView2;
    private TextView txtView3;
    private TextView txtView4;
    private TextView txtView5;
    private TextView txtView6;

    private WalletBrandListSettingsAbout mActivity;
    private FrameVideoView tutorial1;
    private MediaPlayer mediaPlayer;

    private int curVideoPos = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about1, container, false);
        mActivity = (WalletBrandListSettingsAbout) getActivity();

        tutorial1 = (FrameVideoView) v.findViewById(R.id.tutorial1);
        txtView1 = (TextView) v.findViewById(R.id.txtView1);
        txtView2 = (TextView) v.findViewById(R.id.txtView2);
        txtView3 = (TextView) v.findViewById(R.id.txtView3);
        txtView4 = (TextView) v.findViewById(R.id.txtView4);
        txtView5 = (TextView) v.findViewById(R.id.txtView5);
        txtView6 = (TextView) v.findViewById(R.id.txtView6);

        animStart();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e(TAG, "onResume is called");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e(TAG, "onPause is called");
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);

        if (visible) {
            Log.e(TAG, "setMenuVisibility is called (TRUE)");

            if (tutorial1 != null) {
                txtView1.setVisibility(View.INVISIBLE);
                txtView2.setVisibility(View.INVISIBLE);
                txtView3.setVisibility(View.INVISIBLE);
                txtView4.setVisibility(View.INVISIBLE);
                txtView5.setVisibility(View.INVISIBLE);
                txtView6.setVisibility(View.INVISIBLE);

                animStart();
            }
        }
        else {
            Log.e(TAG, "setMenuVisibility is called (FALSE)");

            if (tutorial1 != null) {
                tutorial1.setVisibility(View.INVISIBLE);

                txtView1.setVisibility(View.INVISIBLE);
                txtView2.setVisibility(View.INVISIBLE);
                txtView3.setVisibility(View.INVISIBLE);
                txtView4.setVisibility(View.INVISIBLE);
                txtView5.setVisibility(View.INVISIBLE);
                txtView6.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void animStart() {
        if (tutorial1 != null) {
            try {
                Uri video = Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.tutorial1);
                tutorial1.setVisibility(View.VISIBLE);
                tutorial1.setup(video, getResources().getColor(R.color.wx_primary_color));
                tutorial1.setFrameVideoViewListener(new FrameVideoViewListener() {
                    @Override
                    public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                        WarrantixAboutFragment1.this.mediaPlayer = mediaPlayer;

                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                WarrantixAboutFragment1.this.mediaPlayer.stop();
                            }
                        });
                    }

                    @Override
                    public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {
                        tutorial1.setVisibility(View.INVISIBLE);
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
                tutorial1.setVisibility(View.INVISIBLE);
            }

            Animation animTxt1 = createShowAnimation(txtView1, 700 - 200, R.anim.animation_splash_slide_in);
            animTxt1.setDuration(200);
            txtView1.startAnimation(animTxt1);

            Animation animTxt2 = createShowAnimation(txtView2, 750 - 200, R.anim.animation_splash_slide_in);
            animTxt2.setDuration(200);
            txtView2.startAnimation(animTxt2);

            Animation animTxt3 = createShowAnimation(txtView3, 800 - 200, R.anim.animation_splash_slide_in);
            animTxt3.setDuration(200);
            txtView3.startAnimation(animTxt3);

            Animation animTxt4 = createShowAnimation(txtView4, 850 - 200, R.anim.animation_splash_slide_in);
            animTxt4.setDuration(200);
            txtView4.startAnimation(animTxt4);

            Animation animTxt5 = createShowAnimation(txtView5, 900 - 200, R.anim.animation_splash_slide_in);
            animTxt5.setDuration(200);
            txtView5.startAnimation(animTxt5);

            Animation animTxt6 = createShowAnimation(txtView6, 950 - 200, R.anim.animation_splash_slide_in);
            animTxt6.setDuration(200);
            txtView6.startAnimation(animTxt6);
        }
    }

    //
    // General View show animation
    //
    private Animation createShowAnimation(final View targetView, final int delay, int animID) {
        Animation anim = AnimationUtils.loadAnimation(WarrantixApplication.getInstance().getApplicationContext(), animID);
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim.setStartOffset(delay);
        anim.setAnimationListener(listener);
        anim.setInterpolator(new AccelerateInterpolator());

        return anim;
    }

    private AnimationSet createMoveAnimation(final View targetView, int delay) {
        int centerPos[] = new int[2];
        tutorial1.getLocationOnScreen(centerPos);
        centerPos[0] += tutorial1.getWidth() / 2;
        centerPos[1] += tutorial1.getHeight() / 2;

        int originalPos[] = new int[2];
        targetView.getLocationOnScreen(originalPos);
        originalPos[0] += targetView.getWidth() / 2;
        originalPos[1] += targetView.getHeight() / 2;

        AnimationSet set = new AnimationSet(true);
        float density = getResources().getDisplayMetrics().density;
        final TranslateAnimation anim = new TranslateAnimation((centerPos[0] - originalPos[0]), 0, (centerPos[1] - originalPos[1]), 0);
        anim.setDuration(300);

        Animation fadeIn = FadeIn(100);
        set.addAnimation(anim);
        set.addAnimation(fadeIn);

        set.setStartOffset(delay);
        set.setFillAfter(true);
        set.setFillEnabled(true);

        set.getAnimations().get(1).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        set.setInterpolator(new AccelerateInterpolator());

        return set;
    }

    private AnimatorSet createRandomXY(View targetView, Random random) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(targetView, "translationX", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationX());
        ObjectAnimator animY = ObjectAnimator.ofFloat(targetView, "translationY", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationY());
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.setDuration(random.nextInt(100) + 1000);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.playTogether(animX, animY);
        return animSetXY;
    }

    private Animator createRandomX(View targetView, Random random) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(targetView, "translationX", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationX());
        animX.setDuration(random.nextInt(100) + 1000);
        animX.setInterpolator(new LinearInterpolator());
        return animX;
    }

    private Animator createRandomY(View targetView, Random random) {
        ObjectAnimator animY = ObjectAnimator.ofFloat(targetView, "translationY", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationX());
        animY.setDuration(random.nextInt(100) + 1000);
        animY.setInterpolator(new LinearInterpolator());
        return animY;
    }

    public AnimatorSet createRandomFloatingAnimation(View targetView) {
        AnimatorSet set = new AnimatorSet();

        Random random = new Random();

//        AnimatorSet anim1 = createRandomXY(targetView, random);
//        AnimatorSet anim2 = createRandomXY(targetView, random);
//        AnimatorSet anim3 = createRandomXY(targetView, random);
//        AnimatorSet anim4 = createRandomXY(targetView, random);
//
//        set.playSequentially(anim1/*, anim2, anim3, anim4*/);

        Animator animX1 = createRandomX(targetView, random);
        Animator animY1 = createRandomY(targetView, random);
        set.play(animX1).with(animY1);

        Animator animX2 = createRandomX(targetView, random);
        Animator animY2 = createRandomY(targetView, random);
        set.play(animX2).with(animY2);
        set.play(animX2).after(animX1);

        Animator animX3 = createRandomX(targetView, random);
        Animator animY3 = createRandomY(targetView, random);
        set.play(animX3).with(animY3);
        set.play(animX3).after(animX2);

        Animator animX4 = createRandomX(targetView, random);
        Animator animY4 = createRandomY(targetView, random);
        set.play(animX4).with(animY4);
        set.play(animX4).after(animX3);
        return set;
    }

    public Animation createFloatingRandomAnimations(View targetView) {
        Random random = new Random();

        int originalPos[] = new int[2];
        targetView.getLocationOnScreen(originalPos);
        originalPos[0] += targetView.getWidth() / 2;
        originalPos[1] += targetView.getHeight() / 2;

        float deltaX = (random.nextFloat() - 0.5f) * 30;
        float deltaY = (random.nextFloat() - 0.5f) * 30;
        TranslateAnimation anim1 = new TranslateAnimation(0, deltaX, 0, deltaY);
        anim1.setDuration(1000);
        anim1.setRepeatMode(Animation.REVERSE);
        anim1.setRepeatCount(Animation.INFINITE);
        return anim1;
    }

    private Animation FadeIn(int t) {
        Animation fade;
        fade = new AlphaAnimation(0.0f, 1.0f);
        fade.setDuration(t);
        fade.setInterpolator(new AccelerateInterpolator());
        return fade;
    }

    private Animation FadeOut(int t) {
        Animation fade;
        fade = new AlphaAnimation(1.0f, 0.1f);
        fade.setDuration(t);
        fade.setInterpolator(new AccelerateInterpolator());
        return fade;
    }

    /**
     * @param targetView - Animation view
     * @param random     - random object
     */
    private void CreateRandomAnimation(final View targetView, final Random random) {

        ObjectAnimator animX = ObjectAnimator.ofFloat(targetView, "translationX", random.nextBoolean() ? mActivity.generateRandomFromRange() : -mActivity.generateRandomFromRange());
        ObjectAnimator animY = ObjectAnimator.ofFloat(targetView, "translationY", random.nextBoolean() ? mActivity.generateRandomFromRange() : -mActivity.generateRandomFromRange());
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.setDuration(500);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.playTogether(animX, animY);
        animSetXY.start();

        animSetXY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                 CreateRandomAnimation(targetView, random);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void clearDrawable() {
//        Utils.clearAnimationDrawable(drawable);
//        drawable = null;
    }

}