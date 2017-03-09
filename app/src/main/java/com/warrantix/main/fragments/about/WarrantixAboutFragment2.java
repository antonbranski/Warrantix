package com.warrantix.main.fragments.about;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.customview.framevideoview.FrameVideoView;
import com.warrantix.main.customview.framevideoview.FrameVideoViewListener;
import com.warrantix.main.fragments.BaseFragment;

import java.util.Random;

public class WarrantixAboutFragment2 extends BaseFragment {

    private final static String TAG = WarrantixAboutFragment2.class.getSimpleName();

    private TextView txtView1;
    private TextView txtView2;
    private TextView txtView3;
    private TextView txtView4;
    private TextView txtView5;
    private TextView txtView6;

    private WalletBrandListSettingsAbout mActivity;
    private boolean isFragmentVisiable = false;
    private FrameVideoView tutorial2;
    private MediaPlayer mediaPlayer;

    @Override
    public void onStart() {
        super.onStart();
        isFragmentVisiable = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        isFragmentVisiable = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about2, container, false);
        mActivity = (WalletBrandListSettingsAbout) getActivity();

        tutorial2 = (FrameVideoView) v.findViewById(R.id.tutorial2);

        txtView1 = (TextView) v.findViewById(R.id.txtView1);
        txtView2 = (TextView) v.findViewById(R.id.txtView2);
        txtView3 = (TextView) v.findViewById(R.id.txtView3);
        txtView4 = (TextView) v.findViewById(R.id.txtView4);
        txtView5 = (TextView) v.findViewById(R.id.txtView5);
        txtView6 = (TextView) v.findViewById(R.id.txtView6);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume is called");
//        animStart();
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);

        if (visible) {
            Log.e(TAG, "setMenuVisibility is called (TRUE)");

            if (tutorial2 != null) {
                txtView1.setVisibility(View.INVISIBLE);
                txtView2.setVisibility(View.INVISIBLE);
                txtView3.setVisibility(View.INVISIBLE);
                txtView4.setVisibility(View.INVISIBLE);
                txtView5.setVisibility(View.INVISIBLE);
                txtView6.setVisibility(View.INVISIBLE);

                animStart();
            }
        } else {
            Log.e(TAG, "setMenuVisibility is called (FALSE)");

            if (tutorial2 != null) {
                tutorial2.setVisibility(View.INVISIBLE);

                txtView1.setVisibility(View.INVISIBLE);
                txtView2.setVisibility(View.INVISIBLE);
                txtView3.setVisibility(View.INVISIBLE);
                txtView4.setVisibility(View.INVISIBLE);
                txtView5.setVisibility(View.INVISIBLE);
                txtView6.setVisibility(View.INVISIBLE);

                clearDrawable();
            }
        }
    }

    public void animStart() {
        if (tutorial2 != null) {
            try {
                Uri video = Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.tutorial2);
                tutorial2.setVisibility(View.VISIBLE);
                tutorial2.setup(video, getResources().getColor(R.color.wx_primary_color));
                tutorial2.setFrameVideoViewListener(new FrameVideoViewListener() {
                    @Override
                    public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                        WarrantixAboutFragment2.this.mediaPlayer = mediaPlayer;

                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                WarrantixAboutFragment2.this.mediaPlayer.stop();
                            }
                        });
                    }

                    @Override
                    public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {
                        tutorial2.setVisibility(View.INVISIBLE);
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
                tutorial2.setVisibility(View.INVISIBLE);
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

    public void animLast(final NonSwipeableViewPager viewPager, final int toPage) {
        if (tutorial2 != null) {

        }
    }

    public void animLast() {
        if (tutorial2 != null && isFragmentVisiable) {

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
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

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

    private AnimationSet createMoveToCenterAnimation(final View targetView, int delay) {
        int centerPos[] = new int[2];
        tutorial2.getLocationOnScreen(centerPos);
        centerPos[0] += tutorial2.getWidth() / 2;
        centerPos[1] += tutorial2.getHeight() / 2;

        int originalPos[] = new int[2];
        targetView.getLocationOnScreen(originalPos);
        originalPos[0] += targetView.getWidth() / 2;
        originalPos[1] += targetView.getHeight() / 2;

        AnimationSet set = new AnimationSet(true);
        float density = getResources().getDisplayMetrics().density;
        TranslateAnimation anim = new TranslateAnimation(0, (centerPos[0] - originalPos[0]),
                0, (centerPos[1] - originalPos[1]));
        anim.setDuration(300);
        set.addAnimation(anim);

        Animation fadeOut = FadeOut(100);
        set.addAnimation(fadeOut);
        set.setStartOffset(delay);
        set.setFillAfter(true);
        set.setFillEnabled(true);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.setInterpolator(new AccelerateInterpolator());

        return set;
    }

    private AnimationSet createMoveAnimation(final View targetView, int delay) {
        int centerPos[] = new int[2];
        tutorial2.getLocationOnScreen(centerPos);
        centerPos[0] += tutorial2.getWidth() / 2;
        centerPos[1] += tutorial2.getHeight() / 2;

        int originalPos[] = new int[2];
        targetView.getLocationOnScreen(originalPos);
        originalPos[0] += targetView.getWidth() / 2;
        originalPos[1] += targetView.getHeight() / 2;

        AnimationSet set = new AnimationSet(true);
        float density = getResources().getDisplayMetrics().density;
        TranslateAnimation anim = new TranslateAnimation((centerPos[0] - originalPos[0]), 0,
                (centerPos[1] - originalPos[1]), 0);
        anim.setDuration(300);
        set.addAnimation(anim);

        Animation fadeIn = FadeIn(100);
        set.addAnimation(fadeIn);
        set.setStartOffset(delay);
        set.setFillAfter(true);
        set.setFillEnabled(true);
        set.getAnimations().get(1).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.setInterpolator(new AccelerateInterpolator());

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

    private AnimatorSet createRandomXY(View targetView, Random random) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(targetView, "translationX", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationX());
        ObjectAnimator animY = ObjectAnimator.ofFloat(targetView, "translationY", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationY());
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.setDuration(random.nextInt(100) + 1000);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.playTogether(animX, animY);
        return animSetXY;
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

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    animLast();
//                }
//            }, 2000);
//        }
//    }

    public void clearDrawable() {
//        Utils.clearAnimationDrawable(drawable);
//        drawable = null;
    }
}