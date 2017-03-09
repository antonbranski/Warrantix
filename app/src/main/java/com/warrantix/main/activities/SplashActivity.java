package com.warrantix.main.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.squareup.okhttp.internal.Util;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsAbout;
import com.warrantix.main.common.Utils;
import com.warrantix.main.common.event.SigninFailureEvent;
import com.warrantix.main.common.event.SigninSuccessEvent;
import com.warrantix.main.common.event.SignupFailureEvent;
import com.warrantix.main.common.event.SignupSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.customview.MoveAnimation;
import com.warrantix.main.customview.ResizeAnimation;
import com.warrantix.main.customview.framevideoview.FrameVideoView;
import com.warrantix.main.customview.framevideoview.FrameVideoViewListener;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.BackendManager;
import com.warrantix.main.manager.BrandManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash Activity is the start-up activity that appears until a delay is expired
 * or the user taps the screen.  When the splash activity starts, various app
 * initialization operations are performed.
 */
public class SplashActivity extends BaseActivity implements Validator.ValidationListener/*, LoaderManager.LoaderCallbacks<Cursor> */{

    private final static String LOG_TAG = SplashActivity.class.getSimpleName();

    private FrameVideoView vidHolder;
    private FrameVideoView logoVideo;

    private ImageView splashBG;
    private ImageView logoImageView;
    private ImageView backToMain;
    private TextView txtTitle;

    private LinearLayout mainLayout;
    private LinearLayout signLayout;
    private RelativeLayout titleLayout;

    private TextView txtCaption1;
    private TextView txtCaption2;
    private Button btnSignup;
    private Button btnSignIn;

    private ImageView customerImageView;
    private TextView customerTextView;
    private TextView forgotTextView;


    @Required(order = 1, message = "Please input the customer name")
    private EditText customerEditText;

    private ImageView passwordImageView;
    private TextView passwordTextView;

    @Required(order = 6, message = "Please input the customer password")
    @Password(order = 7)
    @TextRule(order = 8, minLength = 6, maxLength = 15, message = "Enter at least 6 characters in password.")
    private EditText passwordEditText;

    private ImageView emailImageView;
    private TextView emailTextView;

    @Required(order = 4, message = "Please input the customer email address")
    @Email(order = 5, message = "Please Check and Enter a valid Email Address")
    private EditText emailEditText;

    private ImageView phoneImageView;
    private TextView phoneTextView;

    @Required(order = 2, message = "Please input the customer mobile number")
    @NumberRule(order = 3, type = NumberRule.NumberType.LONG, message = "Enter Phone Number in Numeric")
    private EditText phoneEditText;

    private View dividerLine1;
    private View dividerLine2;
    private View dividerLine3;
    private View dividerLine4;

    private TextView txtErrorMessage;
    private Validator validator;

    Timer timer;
    TimerTask myTimerTask;

    private final int CAPTIONS_SIZE = 6;
    private final String captions[] = {
            "To Organize & Upkeep products",
            "To Organize & Manage Brand Apps",
            "To Manage Post-Purchase tasks",
            "To Buy Products, Accessories, AMC, Insurance",
            "To Consult close friends before any purchase",
            "To Organize, Upkeep, Manage & Buy Products"
    };

    static int mCaptionIndex = 0;

    static final int STATUS_MAIN_SCREEN = 0;
    static final int STATUS_SIGNUP_SCREEN = 1;
    static final int STATUS_SIGNIN_SCREEN = 2;

    private int mCurrentStatus = STATUS_MAIN_SCREEN;

    private MediaPlayer player;
    //    private AnimationDrawable animLogo1;
//    private AnimationDrawable animLogo2;
//    private AnimationDrawable animLogo3;
    private boolean bFinished = false;
    private boolean bAnimating = false;

    @Override
    public void onBackPressed() {
        if (bAnimating == true)
            return;

        if (mCurrentStatus == STATUS_MAIN_SCREEN) {
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            backToMain.setEnabled(false);
            hideSignScreen();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // To save 'Brands' in local db
//        if (WarrantixPreference.isFirstLaunched())
            BrandManager.getInstance().addBrands(BrandManager.getInstance().createBrandData(), SplashActivity.this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
//            animLogo1 = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_logo1_animatein);
//            animLogo2 = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_logo2_downscale);
//            animLogo3 = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_logo3_downscale);
            showLogoAnimation();
        }

        if (!isInitialized)
            return;

        // Reset last frame of animation
//        if (mCurrentStatus == STATUS_MAIN_SCREEN)
//            logoImageView.setBackgroundResource(R.drawable.logo1_animatein_00105);
//        else if (mCurrentStatus == STATUS_SIGNUP_SCREEN || mCurrentStatus == STATUS_SIGNIN_SCREEN)
//            logoImageView.setBackgroundResource(R.drawable.logo2_downscale_00089);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initialize() {

        setContentView(R.layout.activity_splash);

        WarrantixPreference.loadConfig();

        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        txtCaption1 = (TextView) findViewById(R.id.txtCaption1);
        txtCaption2 = (TextView) findViewById(R.id.txtCaption2);
        btnSignup = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        splashBG = (ImageView) findViewById(R.id.splashBGView);
        backToMain = (ImageView) findViewById(R.id.backToMain);
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        signLayout = (LinearLayout) findViewById(R.id.signLayout);
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);

        logoImageView.setVisibility(View.GONE);
        txtCaption1.setVisibility(View.GONE);
        txtCaption2.setVisibility(View.GONE);
        btnSignIn.setVisibility(View.GONE);
        btnSignup.setVisibility(View.GONE);
        splashBG.setVisibility(View.GONE);

        mainLayout.setVisibility(View.GONE);
        signLayout.setVisibility(View.GONE);
        titleLayout.setVisibility(View.GONE);

        passwordImageView = (ImageView) findViewById(R.id.passwordImageView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        emailImageView = (ImageView) findViewById(R.id.emailImageView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        customerImageView = (ImageView) findViewById(R.id.customerImageView);
        forgotTextView = (TextView) findViewById(R.id.txt_forgot);
        customerTextView = (TextView) findViewById(R.id.customerTextView);
        customerEditText = (EditText) findViewById(R.id.customerEditText);
        phoneImageView = (ImageView) findViewById(R.id.phoneImageview);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        dividerLine1 = (View) findViewById(R.id.dividerLine1);
        dividerLine2 = (View) findViewById(R.id.dividerLine2);
        dividerLine3 = (View) findViewById(R.id.dividerLine3);
        dividerLine4 = (View) findViewById(R.id.dividerLine4);

        passwordImageView.setVisibility(View.GONE);
        passwordTextView.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.GONE);
        emailImageView.setVisibility(View.GONE);
        emailTextView.setVisibility(View.GONE);
        emailEditText.setVisibility(View.GONE);
        customerImageView.setVisibility(View.GONE);
        customerTextView.setVisibility(View.GONE);
        customerEditText.setVisibility(View.GONE);
        phoneImageView.setVisibility(View.GONE);
        phoneTextView.setVisibility(View.GONE);
        phoneEditText.setVisibility(View.GONE);

        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        dividerLine1.setVisibility(View.GONE);
        dividerLine2.setVisibility(View.GONE);
        dividerLine3.setVisibility(View.GONE);
        dividerLine4.setVisibility(View.GONE);

        validator = new Validator(this);
        validator.setValidationListener(this);

        btnSignup.setOnClickListener(btnSignUpClickListener);
        btnSignIn.setOnClickListener(btnSignInClickListener);

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        passwordEditText.setFilters(new InputFilter[]{filter});

        customerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(customerEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        emailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(emailEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        passwordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(passwordEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        phoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(phoneEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        customerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(customerEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(emailEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(passwordEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        phoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(phoneEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        vidHolder = (FrameVideoView) findViewById(R.id.myvideo);
        logoVideo = (FrameVideoView) findViewById(R.id.logoVideo);

        backToMain.setEnabled(false);
//        this.getSupportLoaderManager().initLoader(0, null, this);
    }

    private void showLogoAnimation() {
        try {
            vidHolder = (FrameVideoView) findViewById(R.id.myvideo);

            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashvideo);
            vidHolder.setVisibility(View.VISIBLE);
            vidHolder.setup(video, getResources().getColor(R.color.wx_primary_color));
            vidHolder.setFrameVideoViewListener(new FrameVideoViewListener() {
                @Override
                public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                    SplashActivity.this.player = mediaPlayer;

                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            vidHolder.setVisibility(View.GONE);
                            SplashActivity.this.player.stop();
                            showMainScreen(true);
                        }
                    });
                }

                @Override
                public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {
                    vidHolder.setVisibility(View.GONE);
                    showMainScreen(true);
                }
            });
        } catch (Exception ex) {
            vidHolder.setVisibility(View.GONE);
            showMainScreen(true);
        }
    }

    private void showLogo1Animation() {
        try {
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo1);
            logoVideo.setVisibility(View.VISIBLE);
            logoVideo.setup(video, getResources().getColor(R.color.wx_primary_color));
            logoVideo.setFrameVideoViewListener(new FrameVideoViewListener() {
                @Override
                public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                    SplashActivity.this.player = mediaPlayer;

                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            SplashActivity.this.player.stop();
                        }
                    });
                }

                @Override
                public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showLogo2Animation() {
        try {
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo2);
            logoVideo.setVisibility(View.VISIBLE);
            logoVideo.setup(video, getResources().getColor(R.color.wx_primary_color));
            logoVideo.setFrameVideoViewListener(new FrameVideoViewListener() {
                @Override
                public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                    SplashActivity.this.player = mediaPlayer;

                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            SplashActivity.this.player.stop();
                        }
                    });
                }

                @Override
                public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showLogo3Animation() {
        try {
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo3);
            logoVideo.setVisibility(View.VISIBLE);
            logoVideo.setup(video, getResources().getColor(R.color.wx_primary_color));
            logoVideo.setFrameVideoViewListener(new FrameVideoViewListener() {
                @Override
                public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                    SplashActivity.this.player = mediaPlayer;

                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            SplashActivity.this.player.stop();
                        }
                    });
                }

                @Override
                public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void showMainScreen(final boolean bInit) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if ((isInitialized == true) && (bInit == true))
                    return;

                if (bInit == true)
                    isInitialized = true;

                mCurrentStatus = STATUS_MAIN_SCREEN;

                btnSignup.setEnabled(true);
                btnSignIn.setEnabled(true);

                mainLayout.setVisibility(View.VISIBLE);
                logoImageView.setVisibility(View.VISIBLE);
                forgotTextView.setVisibility(View.GONE);

                if (bInit == true) {
//                    logoImageView.setBackgroundDrawable(animLogo1);
//                    animLogo1.stop();
//                    animLogo1.start();
                    showLogo1Animation();
                } else {
//                    logoImageView.setBackgroundDrawable(animLogo3);
//                    animLogo3.stop();
//                    animLogo3.start();
                    showLogo3Animation();
                }

                if (bInit == true) {
                    Animation animSplashBG = createShowAnimation(splashBG, 0, R.anim.animation_splash_background_show);
                    splashBG.clearAnimation();
                    splashBG.startAnimation(animSplashBG);
                }

                Animation animCaption1 = createShowAnimation(txtCaption1, 100, R.anim.animation_splash_slide_in);
                txtCaption1.clearAnimation();
                txtCaption1.startAnimation(animCaption1);

                Animation animCaption2 = createShowAnimation(txtCaption2, 200, R.anim.animation_splash_slide_in);
                txtCaption2.clearAnimation();
                txtCaption2.startAnimation(animCaption2);

                if (bInit == true) {
                    Animation animSignUp = createShowAnimation(btnSignup, 350, R.anim.animation_splash_slide_in);
                    btnSignup.clearAnimation();
                    btnSignup.startAnimation(animSignUp);

                    Animation animSignIn = createShowAnimation(btnSignIn, 450, R.anim.animation_splash_slide_in);
                    btnSignIn.clearAnimation();
                    btnSignIn.startAnimation(animSignIn);
                }

                timer = new Timer();
                myTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mCaptionIndex++;
                                if (mCaptionIndex >= CAPTIONS_SIZE)
                                    mCaptionIndex = 0;

                                if (mCurrentStatus == STATUS_MAIN_SCREEN) {
//                                    Animation animCaption1 = createShowAnimation(txtCaption1, 0, R.anim.animation_splash_bounce);
//                                    txtCaption1.clearAnimation();
//                                    txtCaption1.startAnimation(animCaption1);

                                    txtCaption2.setText(captions[mCaptionIndex]);
                                    Animation animCaption2 = createShowAnimation(txtCaption2, 100, R.anim.animation_splash_slide_miniin);
                                    animCaption2.setDuration(200);
                                    txtCaption2.clearAnimation();
                                    txtCaption2.startAnimation(animCaption2);
                                } else {
                                    if (txtCaption1.getVisibility() == View.VISIBLE) {
                                        Animation animCaption1 = createHideAnimation(txtCaption1, 0, R.anim.animation_splash_fade_out);
                                        txtCaption1.clearAnimation();
                                        txtCaption1.startAnimation(animCaption1);
                                    }

                                    if (txtCaption2.getVisibility() == View.VISIBLE) {
                                        Animation animCaption2 = createHideAnimation(txtCaption2, 0, R.anim.animation_splash_fade_out);
                                        txtCaption2.clearAnimation();
                                        txtCaption2.startAnimation(animCaption2);
                                    }
                                }
                            }
                        });
                    }
                };
                timer.schedule(myTimerTask, 3000, 3000);
            }
        });
    }

    private void showSignupScreen() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mCurrentStatus = STATUS_SIGNUP_SCREEN;

                myTimerTask.cancel();

                customerEditText.setText("");
//                emailEditText.setText(Utils.getRegisteredEmail(SplashActivity.this));
                passwordEditText.setText("");
                phoneEditText.setText(Utils.getPhoneNumber(SplashActivity.this));
                forgotTextView.setVisibility(View.GONE);

                Animation animCaption1 = createHideAnimation(txtCaption1, 0, R.anim.animation_splash_fade_out);
                txtCaption1.clearAnimation();
                txtCaption1.startAnimation(animCaption1);

                Animation animCaption2 = createHideAnimation(txtCaption2, 0, R.anim.animation_splash_fade_out);
                txtCaption2.clearAnimation();
                txtCaption2.startAnimation(animCaption2);

                Animation animSignIn = createSigninDownAnimation(btnSignIn, 0);
                btnSignIn.clearAnimation();
                btnSignIn.startAnimation(animSignIn);

                AnimationSet downAnimSet = createSignUpDownAnimation(btnSignup, 0);
                btnSignup.startAnimation(downAnimSet);

                showLogo2Animation();
//                logoImageView.setBackgroundDrawable(animLogo2);
//                animLogo2.stop();
//                animLogo2.start();

                txtTitle.setText("SIGN UP");
                Animation animTitle = createShowAnimation(txtTitle, 600, R.anim.animation_splash_fade_in);
                txtTitle.startAnimation(animTitle);

                Animation animBackToMain = createShowAnimation(backToMain, 600, R.anim.animation_splash_slide_minifromright);
                backToMain.startAnimation(animBackToMain);
                backToMain.setOnClickListener(btnBackToMainClickListener);

                customerImageView.setVisibility(View.VISIBLE);
                customerTextView.setVisibility(View.VISIBLE);
                customerEditText.setVisibility(View.VISIBLE);
                dividerLine1.setVisibility(View.VISIBLE);

                emailImageView.setVisibility(View.VISIBLE);
                emailTextView.setVisibility(View.VISIBLE);
                emailEditText.setVisibility(View.VISIBLE);
                dividerLine2.setVisibility(View.VISIBLE);

                passwordImageView.setVisibility(View.VISIBLE);
                passwordTextView.setVisibility(View.VISIBLE);
                passwordEditText.setVisibility(View.VISIBLE);
                dividerLine3.setVisibility(View.VISIBLE);

                phoneImageView.setVisibility(View.VISIBLE);
                phoneTextView.setVisibility(View.VISIBLE);
                phoneEditText.setVisibility(View.VISIBLE);
                dividerLine4.setVisibility(View.VISIBLE);

                titleLayout.setVisibility(View.VISIBLE);
                signLayout.setVisibility(View.VISIBLE);

                Animation animCustomerImageView = createShowAnimation(customerImageView, 200 + 700, R.anim.animation_splash_slide_minifromright);
                customerImageView.clearAnimation();
                customerImageView.startAnimation(animCustomerImageView);

                Animation animCustomerTextView = createShowAnimation(customerTextView, 250 + 700, R.anim.animation_splash_slide_fromright);
                customerTextView.clearAnimation();
                customerTextView.startAnimation(animCustomerTextView);

                Animation animCustomEditText = createShowAnimation(customerEditText, 300 + 700, R.anim.animation_splash_slide_fromright);
                customerEditText.clearAnimation();
                customerEditText.startAnimation(animCustomEditText);

                Animation animDivider1 = createShowAnimation(dividerLine1, 350 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine1.clearAnimation();
                dividerLine1.startAnimation(animDivider1);

                Animation animEmailImageView = createShowAnimation(emailImageView, 400 + 700, R.anim.animation_splash_show);
                emailImageView.clearAnimation();
                emailImageView.startAnimation(animEmailImageView);

                Animation animEmailTextView = createShowAnimation(emailTextView, 450 + 700, R.anim.animation_splash_show);
                emailTextView.clearAnimation();
                emailTextView.startAnimation(animEmailTextView);

                Animation animEmailEditText = createShowAnimation(emailEditText, 500 + 700, R.anim.animation_splash_slide_fromright);
                emailEditText.clearAnimation();
                emailEditText.startAnimation(animEmailEditText);

                Animation animDivider2 = createShowAnimation(dividerLine2, 550 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine2.clearAnimation();
                dividerLine2.startAnimation(animDivider2);

                Animation animPasswordImageView = createShowAnimation(passwordImageView, 600 + 700, R.anim.animation_splash_show);
                passwordImageView.clearAnimation();
                passwordImageView.startAnimation(animPasswordImageView);

                Animation animPasswordTextView = createShowAnimation(passwordTextView, 650 + 700, R.anim.animation_splash_slide_fromright);
                passwordTextView.clearAnimation();
                passwordTextView.startAnimation(animPasswordTextView);

                Animation animPasswordEditText = createShowAnimation(passwordEditText, 700 + 700, R.anim.animation_splash_slide_fromright);
                passwordEditText.clearAnimation();
                passwordEditText.startAnimation(animPasswordEditText);

                Animation animDivider3 = createShowAnimation(dividerLine3, 750 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine3.clearAnimation();
                dividerLine3.startAnimation(animDivider3);

                Animation animPhoneImageView = createShowAnimation(phoneImageView, 800 + 700, R.anim.animation_splash_show);
                phoneImageView.clearAnimation();
                phoneImageView.startAnimation(animPhoneImageView);

                Animation animPhoneTextView = createShowAnimation(phoneTextView, 850 + 700, R.anim.animation_splash_slide_fromright);
                phoneTextView.clearAnimation();
                phoneTextView.startAnimation(animPhoneTextView);

                Animation animPhoneEditText = createShowAnimation(phoneEditText, 900 + 700, R.anim.animation_splash_slide_fromright);
                phoneEditText.clearAnimation();
                phoneEditText.startAnimation(animPhoneEditText);

                Animation animDivider4 = createShowAnimation(dividerLine4, 950 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine4.clearAnimation();
                dividerLine4.startAnimation(animDivider4);

                btnSignup.setEnabled(true);
            }
        });
    }

    private void showSigninScreen() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCurrentStatus = STATUS_SIGNIN_SCREEN;

                myTimerTask.cancel();

                customerEditText.setText("");
//                emailEditText.setText(Utils.getRegisteredEmail(SplashActivity.this));
                passwordEditText.setText("");
                phoneEditText.setText(Utils.getPhoneNumber(SplashActivity.this));

                Animation animCaption1 = createHideAnimation(txtCaption1, 0, R.anim.animation_splash_fade_out);
                txtCaption1.clearAnimation();
                txtCaption1.startAnimation(animCaption1);

                Animation animCaption2 = createHideAnimation(txtCaption2, 0, R.anim.animation_splash_fade_out);
                txtCaption2.clearAnimation();
                txtCaption2.startAnimation(animCaption2);

                AnimationSet animSignin = createSigninScaleDownAnimation(btnSignIn, 0);
                btnSignIn.clearAnimation();
                btnSignIn.startAnimation(animSignin);
                btnSignIn.setText("Log In");

                Animation animSignup = createHideAnimation(btnSignup, 0, R.anim.animation_splash_fade_out);
                btnSignup.clearAnimation();
                btnSignup.startAnimation(animSignup);

                showLogo2Animation();
//                logoImageView.setBackgroundDrawable(animLogo2);
//                animLogo2.stop();
//                animLogo2.start();

                txtTitle.setText("LOG IN");
                Animation animTitle = createShowAnimation(txtTitle, 100, R.anim.animation_splash_fade_in);
                txtTitle.startAnimation(animTitle);

                Animation animBackToMain = createShowAnimation(backToMain, 100, R.anim.animation_splash_slide_minifromright);
                backToMain.clearAnimation();
                backToMain.startAnimation(animBackToMain);
                backToMain.setOnClickListener(btnBackToMainClickListener);

                emailImageView.setVisibility(View.VISIBLE);
                emailTextView.setVisibility(View.VISIBLE);
                emailEditText.setVisibility(View.VISIBLE);
                passwordImageView.setVisibility(View.VISIBLE);
                passwordTextView.setVisibility(View.VISIBLE);
                passwordEditText.setVisibility(View.VISIBLE);
                customerImageView.setVisibility(View.INVISIBLE);
                customerTextView.setVisibility(View.INVISIBLE);
                customerEditText.setVisibility(View.INVISIBLE);
                phoneImageView.setVisibility(View.GONE);
                phoneTextView.setVisibility(View.GONE);
                phoneEditText.setVisibility(View.GONE);

                dividerLine1.setVisibility(View.INVISIBLE);
                dividerLine2.setVisibility(View.VISIBLE);
                dividerLine3.setVisibility(View.VISIBLE);
                dividerLine4.setVisibility(View.INVISIBLE);

                titleLayout.setVisibility(View.VISIBLE);
                signLayout.setVisibility(View.VISIBLE);

                Animation animEmailImageView = createShowAnimation(emailImageView, 200 + 500, R.anim.animation_splash_show);
                emailImageView.clearAnimation();
                emailImageView.startAnimation(animEmailImageView);

                Animation animEmailTextView = createShowAnimation(emailTextView, 300 + 500, R.anim.animation_splash_slide_fromright);
                emailTextView.clearAnimation();
                emailTextView.startAnimation(animEmailTextView);

                Animation animEmailEditText = createShowAnimation(emailEditText, 400 + 500, R.anim.animation_splash_slide_fromright);
                emailEditText.clearAnimation();
                emailEditText.startAnimation(animEmailEditText);

                Animation animDivider2 = createShowAnimation(dividerLine2, 500 + 500, R.anim.animation_splash_slide_fromright);
                dividerLine2.clearAnimation();
                dividerLine2.startAnimation(animDivider2);

                Animation animPasswordImageView = createShowAnimation(passwordImageView, 600 + 500, R.anim.animation_splash_show);
                passwordImageView.clearAnimation();
                passwordImageView.startAnimation(animPasswordImageView);

                Animation animPasswordTextView = createShowAnimation(passwordTextView, 700 + 500, R.anim.animation_splash_slide_fromright);
                passwordTextView.clearAnimation();
                passwordTextView.startAnimation(animPasswordTextView);
                animPasswordTextView.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        forgotTextView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                Animation animPasswordEditText = createShowAnimation(passwordEditText, 800 + 500, R.anim.animation_splash_slide_fromright);
                passwordEditText.clearAnimation();
                passwordEditText.startAnimation(animPasswordEditText);

                Animation animDivider3 = createShowAnimation(dividerLine3, 900 + 500, R.anim.animation_splash_slide_fromright);
                dividerLine3.clearAnimation();
                dividerLine3.startAnimation(animDivider3);

                btnSignIn.setEnabled(true);

//                emailEditText.setEnabled(false);
//                passwordEditText.setEnabled(false);
                if (WarrantixPreference.warrantixConfig.hasMainCustomer() == true) {
                    emailEditText.setText(WarrantixPreference.warrantixConfig.getMainCustomer().getUsername());
                    passwordEditText.setText(WarrantixPreference.warrantixConfig.getMainCustomer().getPassword());
                }
            }
        });
    }

    private void hideSignScreen() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                hideKeyboard(SplashActivity.this);

//                emailEditText.setEnabled(true);
//                passwordEditText.setEnabled(true);
//                emailEditText.setText(Utils.getRegisteredEmail(SplashActivity.this));
                passwordEditText.setText("");

                Animation animBackToMain = createHideAnimation(backToMain, 0, R.anim.animation_splash_fade_out);
                backToMain.clearAnimation();
                backToMain.startAnimation(animBackToMain);

                Animation animTitle = createHideAnimation(txtTitle, 0, R.anim.animation_splash_fade_out);
                txtTitle.clearAnimation();
                txtTitle.startAnimation(animTitle);

                if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                    AnimationSet upAnimSet = createSignUpUpAnimation(btnSignup, 900);
                    btnSignup.startAnimation(upAnimSet);

                    Animation animSignIn = createSigninUpAnimation(btnSignIn, 900);
                    btnSignIn.clearAnimation();
                    btnSignIn.startAnimation(animSignIn);
                } else {
                    Animation animSignUp = createShowAnimation(btnSignup, 700, R.anim.animation_splash_show);
                    btnSignup.startAnimation(animSignUp);

                    Animation animSignIn = createSigninScaleUpAnimation(btnSignIn, 700);
                    btnSignIn.clearAnimation();
                    btnSignIn.startAnimation(animSignIn);
                    btnSignIn.setText("Existing Customer? Log In");
                }

                if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                    Animation animCustomerImageView = createHideAnimation(customerImageView, 100, R.anim.animation_splash_slide_toright);
                    customerImageView.clearAnimation();
                    customerImageView.startAnimation(animCustomerImageView);

                    Animation animCustomerTextView = createHideAnimation(customerTextView, 100, R.anim.animation_splash_slide_toright);
                    customerTextView.clearAnimation();
                    customerTextView.startAnimation(animCustomerTextView);

                    Animation animCustomerEditText = createHideAnimation(customerEditText, 100, R.anim.animation_splash_slide_toright);
                    customerEditText.clearAnimation();
                    customerEditText.startAnimation(animCustomerEditText);

                    Animation animDivider1 = createHideAnimation(dividerLine1, 100, R.anim.animation_splash_slide_toright);
                    dividerLine1.clearAnimation();
                    dividerLine1.startAnimation(animDivider1);
                }

                Animation animEmailImageView = createHideAnimation(emailImageView, 200, R.anim.animation_splash_slide_toright);
                emailImageView.clearAnimation();
                emailImageView.startAnimation(animEmailImageView);

                Animation animEmailTextView = createHideAnimation(emailTextView, 200, R.anim.animation_splash_slide_toright);
                emailTextView.clearAnimation();
                emailTextView.startAnimation(animEmailTextView);

                Animation animEditText = createHideAnimation(emailEditText, 200, R.anim.animation_splash_slide_toright);
                emailEditText.clearAnimation();
                emailEditText.startAnimation(animEditText);

                Animation animDivider2 = createHideAnimation(dividerLine2, 200, R.anim.animation_splash_slide_toright);
                dividerLine2.clearAnimation();
                dividerLine2.startAnimation(animDivider2);

                Animation animPasswordImageView = createHideAnimation(passwordImageView, 300, R.anim.animation_splash_slide_toright);
                passwordImageView.clearAnimation();
                passwordImageView.startAnimation(animPasswordImageView);

                Animation animPasswordTextView = createHideAnimation(passwordTextView, 300, R.anim.animation_splash_slide_toright);
                passwordTextView.clearAnimation();
                passwordTextView.startAnimation(animPasswordTextView);

                Animation animPasswordEditText = createHideAnimation(passwordEditText, 300, R.anim.animation_splash_slide_toright);
                passwordEditText.clearAnimation();
                passwordEditText.startAnimation(animPasswordEditText);

                forgotTextView.setVisibility(View.GONE);
                Animation animDivider3 = createHideAnimation(dividerLine3, 300, R.anim.animation_splash_slide_toright);
                dividerLine3.clearAnimation();
                dividerLine3.startAnimation(animDivider3);

                if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                    Animation animPhoneImageView = createHideAnimation(phoneImageView, 400, R.anim.animation_splash_slide_toright);
                    phoneImageView.clearAnimation();
                    phoneImageView.startAnimation(animPhoneImageView);

                    Animation animPhoneTextView = createHideAnimation(phoneTextView, 400, R.anim.animation_splash_slide_toright);
                    phoneTextView.clearAnimation();
                    phoneTextView.startAnimation(animPhoneTextView);

                    Animation animPhoneEditText = createHideAnimation(phoneEditText, 400, R.anim.animation_splash_slide_toright);
                    phoneEditText.clearAnimation();
                    phoneEditText.startAnimation(animPhoneEditText);

                    Animation animDivider4 = createHideAnimation(dividerLine4, 400, R.anim.animation_splash_slide_toright);
                    dividerLine4.clearAnimation();
                    dividerLine4.startAnimation(animDivider4);
                }

                txtErrorMessage.setVisibility(View.GONE);
                txtErrorMessage.setText("");

                // showMainScreen(false);
            }
        });
    }

    private void goMainScreen() {
        bFinished = true;

//        Utils.clearAnimationDrawable(animLogo1);
//        Utils.clearAnimationDrawable(animLogo2);
//        Utils.clearAnimationDrawable(animLogo3);
//        System.gc();

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent, true);
        finish();
        overridePendingTransition(0,0);
    }

    private void goAboutScreen() {
        bFinished = true;

//        Utils.clearAnimationDrawable(animLogo1);
//        Utils.clearAnimationDrawable(animLogo2);
//        Utils.clearAnimationDrawable(animLogo3);
//        System.gc();

        Intent intent = new Intent(SplashActivity.this, WalletBrandListSettingsAbout.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("Main", "Main");
        startActivity(intent, true);
        finish();
        overridePendingTransition(0,0);
    }

    private void signup() {
        String customerName = customerEditText.getText().toString();
        String customerEmail = emailEditText.getText().toString();
        String customerPassword = passwordEditText.getText().toString();
        String customerMobile = phoneEditText.getText().toString();

        BackendManager.getInstance().customerSignup(customerName, customerMobile, customerEmail, customerPassword);
    }

    private void signin() {
        String customerEmail = emailEditText.getText().toString();
        String customerPassword = passwordEditText.getText().toString();

        BackendManager.getInstance().customerSignin(customerEmail, customerPassword);
    }

    private final View.OnClickListener btnSignUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnSignup.setEnabled(false);

            if (mCurrentStatus == STATUS_MAIN_SCREEN)
                showSignupScreen();
            else if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                Animation animBounce = createShowAnimation(btnSignup, 0, R.anim.animation_splash_bounce);
                btnSignup.clearAnimation();
                btnSignup.startAnimation(animBounce);

//                validator.validate();
                goAboutScreen();
            }
        }
    };

    private final View.OnClickListener btnSignInClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnSignIn.setEnabled(false);

            if (mCurrentStatus == STATUS_MAIN_SCREEN)
                showSigninScreen();
            else if (mCurrentStatus == STATUS_SIGNIN_SCREEN) {
                Animation animBounce = createShowAnimation(btnSignIn, 0, R.anim.animation_splash_bounce);
                btnSignIn.clearAnimation();
                btnSignIn.startAnimation(animBounce);

//                validator.validate();
                goMainScreen();
            }
        }
    };

    private final View.OnClickListener btnBackToMainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            backToMain.setEnabled(false);

            hideSignScreen();
        }
    };

    @Override
    public void onValidationSucceeded() {
        txtErrorMessage.setText("");
        txtErrorMessage.setVisibility(View.INVISIBLE);
        if (mCurrentStatus == STATUS_SIGNUP_SCREEN)
            signup();
        else if (mCurrentStatus == STATUS_SIGNIN_SCREEN)
            signin();
    }

    private void showErrorMessage(String errorMessage) {
        txtErrorMessage.setText(errorMessage);
        txtErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        showErrorMessage(failureMessage);

        btnSignup.setEnabled(true);
        btnSignIn.setEnabled(true);
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
        return anim;
    }

    //
    // General View hide animation
    //
    private Animation createHideAnimation(final View targetView, final int delay, int animID) {
        Animation anim = AnimationUtils.loadAnimation(WarrantixApplication.getInstance().getApplicationContext(), animID);
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.INVISIBLE);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim.setStartOffset(delay);
        anim.setAnimationListener(listener);
        return anim;
    }

    //
    // Signup Button up / down animation
    //
    private AnimationSet createSignUpDownAnimation(final View targetView, final int delay) {
        Animation animBounce = createShowAnimation(targetView, 0, R.anim.animation_splash_bounce);
        animBounce.setStartOffset(0);
        Animation animResize = new ResizeAnimation(targetView, 80, 200);
        animResize.setStartOffset(100);

        float moveY = 70 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int) moveY, 250);
        animDown.setStartOffset(300);
        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animBounce);
        downAnimSet.addAnimation(animResize);
        downAnimSet.addAnimation(animDown);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                bAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

                backToMain.setEnabled(true);
                bAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    private AnimationSet createSignUpUpAnimation(final View targetView, final int delay) {

        float moveY = -70 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int) moveY, 300);
        animDown.setStartOffset(0);

        Animation animResize = new ResizeAnimation(targetView, 125, 200);
        animResize.setStartOffset(300);

        Animation animEnd = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.0f);
        animEnd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMainScreen(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animEnd.setStartOffset(500);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animDown);
        downAnimSet.addAnimation(animResize);
        downAnimSet.addAnimation(animEnd);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                targetView.setEnabled(false);

                bAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

                bAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    //
    // Sign in button up animation
    //
    private AnimationSet createSigninUpAnimation(final View targetView, final int delay) {
        Animation animFadeIn = createShowAnimation(targetView, 0, R.anim.animation_splash_fade_in);
        animFadeIn.setStartOffset(0);

        float moveY = -50 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int) moveY, 500);
        animDown.setStartOffset(0);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animDown);
        downAnimSet.addAnimation(animFadeIn);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                bAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

                bAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    private AnimationSet createSigninDownAnimation(final View targetView, final int delay) {
        Animation animFadeout = createHideAnimation(targetView, 0, R.anim.animation_splash_fade_out);
        animFadeout.setStartOffset(0);

        float moveY = 50 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int) moveY, 500);
        animDown.setStartOffset(0);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animFadeout);
        downAnimSet.addAnimation(animDown);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                bAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

                bAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    private AnimationSet createSigninScaleDownAnimation(final View targetView, final int delay) {
        Animation animBounce = createShowAnimation(targetView, 0, R.anim.animation_splash_bounce);
        animBounce.setStartOffset(0);
        Animation animResize = new ResizeAnimation(targetView, 80, 200);
        animResize.setStartOffset(100);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animBounce);
        downAnimSet.addAnimation(animResize);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                bAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

                backToMain.setEnabled(true);
                bAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return downAnimSet;
    }

    private AnimationSet createSigninScaleUpAnimation(final View targetView, final int delay) {
        Animation animResize = new ResizeAnimation(targetView, 125, 200);
        animResize.setStartOffset(0);
        animResize.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMainScreen(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animResize);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
                targetView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                bAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
                targetView.setLayerType(View.LAYER_TYPE_NONE, null);

                bAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    @Subscribe
    public void onSignupSuccessEvent(SignupSuccessEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                goAboutScreen();
            }
        });
    }

    @Subscribe
    public void onSignupFailureEvent(final SignupFailureEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MessageDialog dialog = new MessageDialog(SplashActivity.this);
                dialog.setTitle("Error");
                dialog.setMessage(event.getMessage());
                dialog.show();

                btnSignup.setEnabled(true);
            }
        });
    }

    @Subscribe
    public void onSigninSuccessEvent(SigninSuccessEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                goMainScreen();
            }
        });
    }

    @Subscribe
    public void onSigninFailureEvent(final SigninFailureEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MessageDialog dialog = new MessageDialog(SplashActivity.this);
                dialog.setTitle("Error");
                dialog.setMessage(event.getMessage());
                dialog.show();

                btnSignIn.setEnabled(true);
            }
        });
    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new CursorLoader(this,
//                // Retrieve data rows for the device user's 'profile' contact.
//                Uri.withAppendedPath(
//                        ContactsContract.Profile.CONTENT_URI,
//                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
//                ProfileQuery.PROJECTION,
//
//                // Select only email addresses.
//                ContactsContract.Contacts.Data.MIMETYPE + " = ?",
//                new String[]{ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},
//
//                // Show primary email addresses first. Note that there won't be
//                // a primary email address if the user hasn't specified one.
//                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        List<String> emails = new ArrayList<String>();
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            if (emailEditText != null)
//                emailEditText.setText(cursor.getString(ProfileQuery.ADDRESS));
//
//            emails.add(cursor.getString(ProfileQuery.ADDRESS));
//            // Potentially filter on ProfileQuery.IS_PRIMARY
//            cursor.moveToNext();
//        }
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
//
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

//    private void checkIfAnimationDone(AnimationDrawable anim){
//        final AnimationDrawable a = anim;
//        int timeBetweenChecks = 300;
//        Handler h = new Handler();
//        h.postDelayed(new Runnable(){
//            public void run(){
//                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
//                    checkIfAnimationDone(a);
//                } else{
//                    clearAnimationDrawable(anim);
//                }
//            }
//        }, timeBetweenChecks);
//    };


}
