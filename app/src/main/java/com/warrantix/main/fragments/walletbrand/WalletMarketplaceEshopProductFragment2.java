package com.warrantix.main.fragments.walletbrand;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.WalletMarketplaceEshopProducts;
import com.warrantix.main.fragments.BaseFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.tooltip.Tooltip;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletMarketplaceEshopProductFragment2 extends BaseFragment {

    @BindView(R.id.amountTXT)
    TextView amountTXT;
    @BindView(R.id.amountValueTXT)
    TextView amountValueTXT;
    @BindView(R.id.salientfeatured)
    TextView salientfeatured;
    @BindView(R.id.mode_creditcard)
    RadioButton modeCreditcard;
    @BindView(R.id.mode_debitcard)
    RadioButton modeDebitcard;
    @BindView(R.id.mode_netbanking)
    RadioButton modeNetbanking;
    @BindView(R.id.mode_americancard)
    RadioButton modeAmericancard;
    @BindView(R.id.mode_mobiquikwallet)
    RadioButton modeMobiquikwallet;
    @BindView(R.id.mode_paytmwallet)
    RadioButton modePaytmwallet;
    @BindView(R.id.entercarddetails)
    TextView entercarddetails;
    @BindView(R.id.cardnumberTXT)
    EditText cardnumberTXT;
    @BindView(R.id.numberoncardTXT)
    EditText numberoncardTXT;
    @BindView(R.id.empiryTXT)
    EditText empiryTXT;
    @BindView(R.id.CVVTXT)
    EditText CVVTXT;
    @BindView(R.id.cvvInfoBTN)
    ImageButton cvvInfoBTN;
    @BindView(R.id.nextBTN)
    Button nextBTN;
    @BindView(R.id.vd_sc)
    ScrollView vdSc;


    private Context mContext;
    private Intent mIntent;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/yyyy", Locale.US);
    private DatePickerDialog dialog;

    /**
     * 1- Credit card
     * 2- Debit card
     * 3- NetBanking
     * 4- American Express
     * 5- Mobikwik
     * 6- payTM
     */
    private int paymentType = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_brand_product2, container, false);
        InitializeView();
        ButterKnife.bind(this, mView);

        empiryTXT.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        empiryTXT.setLongClickable(false);
        return mView;
    }

    public void InitializeView() {
        this.mContext = getActivity();
    }


    @OnClick({R.id.empiryTXT, R.id.cvvInfoBTN,
            R.id.mode_creditcard, R.id.mode_debitcard, R.id.mode_netbanking, R.id.mode_americancard, R.id.mode_mobiquikwallet, R.id.mode_paytmwallet,
            R.id.nextBTN})
    void handleClick(View view) {
        switch (view.getId()) {

            case R.id.empiryTXT:
                showDatePickerDialog();
                break;
            case R.id.cvvInfoBTN:
                if (!isClickEnableForCVV())
                    return;

                showCVVToolTip();
                break;

            //Radio button clicks
            case R.id.mode_creditcard:
                updateRadioButtonUI(1);
                break;

            case R.id.mode_debitcard:
                updateRadioButtonUI(2);
                break;

            case R.id.mode_netbanking:
                updateRadioButtonUI(3);
                break;

            case R.id.mode_americancard:
                updateRadioButtonUI(4);
                break;

            case R.id.mode_mobiquikwallet:
                updateRadioButtonUI(5);
                break;

            case R.id.mode_paytmwallet:
                updateRadioButtonUI(6);
                break;

            //next button click
            case R.id.nextBTN:
                if ((mContext != null) && (mContext instanceof WalletMarketplaceEshopProducts)) {
                    ((WalletMarketplaceEshopProducts) mContext).loadProduct3Fragment();
                }
                break;


        }
    }

    private void showDatePickerDialog() {
        Calendar newCalendar = Calendar.getInstance();
        try {
            newCalendar.setTime(dateFormatter.parse(empiryTXT.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // if dialog is showed, ...
        if ((dialog != null) && (dialog.isShowing() == true))
            return;

        dialog = new DatePickerDialog(getActivity(), purchaseDatePickerListener,
                newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    private void showCVVToolTip() {
        Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/Montserrat-Regular.ttf");
        Tooltip.make(mActivity,
                new Tooltip.Builder(101)
                        .anchor(CVVTXT, Tooltip.Gravity.TOP)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 2000)
                        .activateDelay(400)
                        .showDelay(300)
                        .text("This is dummy information")
                        .maxWidth(500)
                        .withArrow(true)
                        .withOverlay(true)
                        .typeface(font)
                        .withStyleId(R.style.ToolTipLayoutCustomStyle)
                        .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                        .build()
        ).show();
    }


    private void updateRadioButtonUI(int type) {
        paymentType = type;
        modeCreditcard.setChecked(type == 1);
        modeDebitcard.setChecked(type == 2);
        modeNetbanking.setChecked(type == 3);
        modeAmericancard.setChecked(type == 4);
        modeMobiquikwallet.setChecked(type == 5);
        modePaytmwallet.setChecked(type == 6);
    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            empiryTXT.setText(dateFormatter.format(newDate.getTime()));
        }
    };

    private class ActionModeCallbackInterceptor implements android.view.ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

        }
    }

}
