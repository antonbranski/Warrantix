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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.WalletBrandAMC;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.rest.model.Cart;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.fragments.BaseFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import it.sephiroth.android.library.tooltip.Tooltip;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandAMCFragment2 extends BaseFragment implements View.OnClickListener {
    private Context mContext;
    private Intent mIntent;
    private View mView;

    private TextView amounttxt;
    private TextView amountValuetxt;
    private TextView salientfeaturedtxt;

    private RadioButton creditcardRB;
    private RadioButton debitcardRB;
    private RadioButton netbankingRB;
    private RadioButton americanexpresscardRB;
    private RadioButton mobiquikwalletRB;
    private RadioButton payTMRB;

    private TextView entercarddetails;

    private EditText cardnumberTXT;
    private EditText numberoncardTXT;
    private EditText empiryTXT;
    private EditText CVVTXT;

    List<Cart> mockCarts = new ArrayList<>();

    private Button nextBtn;
    private ImageView wallet_hero;
    private PopupWindow mPopupWindow;
    private LayoutInflater inflater;

    private Boolean isTooltipClosed = false;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/yyyy", Locale.US);
    private DatePickerDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        this.inflater = inflater;

        mView = inflater.inflate(R.layout.fragment_brand_amc2, container, false);
        InitializeView();
        return mView;
    }

    public void InitializeView() {
        amounttxt = (TextView) mView.findViewById(R.id.amountTXT);
        amountValuetxt = (TextView) mView.findViewById(R.id.amountValueTXT);
        salientfeaturedtxt = (TextView) mView.findViewById(R.id.salientfeatured);
        entercarddetails = (TextView) mView.findViewById(R.id.entercarddetails);

        creditcardRB = (RadioButton) mView.findViewById(R.id.mode_creditcard);
        debitcardRB = (RadioButton) mView.findViewById(R.id.mode_debitcard);
        netbankingRB = (RadioButton) mView.findViewById(R.id.mode_netbanking);
        americanexpresscardRB = (RadioButton) mView.findViewById(R.id.mode_americancard);
        mobiquikwalletRB = (RadioButton) mView.findViewById(R.id.mode_mobiquikwallet);
        payTMRB = (RadioButton) mView.findViewById(R.id.mode_paytmwallet);


        cardnumberTXT = (EditText) mView.findViewById(R.id.cardnumberTXT);
        numberoncardTXT = (EditText) mView.findViewById(R.id.numberoncardTXT);
        empiryTXT = (EditText) mView.findViewById(R.id.empiryTXT);
        CVVTXT = (EditText) mView.findViewById(R.id.CVVTXT);

        cardnumberTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        numberoncardTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        empiryTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        CVVTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));

        empiryTXT.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        empiryTXT.setLongClickable(false);
        empiryTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        creditcardRB.setOnClickListener(this);
        debitcardRB.setOnClickListener(this);
        netbankingRB.setOnClickListener(this);
        americanexpresscardRB.setOnClickListener(this);
        mobiquikwalletRB.setOnClickListener(this);
        payTMRB.setOnClickListener(this);

        final LinearLayout cvvLayout = (LinearLayout) mView.findViewById(R.id.cvvLayout);

        nextBtn = (Button) mView.findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(this);

        Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/Montserrat-Regular.ttf");

        final Tooltip.Builder tooltip = new Tooltip.Builder(101)
                .anchor(CVVTXT, Tooltip.Gravity.TOP)
                .closePolicy(new Tooltip.ClosePolicy()
                        .insidePolicy(true, false)
                        .outsidePolicy(true, false), 3000)
                .activateDelay(400)
                .showDelay(300)
                .text("This is dummy information")
                .maxWidth(500)
                .withArrow(true)
                .withOverlay(true)
                .typeface(font)
                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                .build();

        final ImageButton cvvInfoBTN = (ImageButton) mView.findViewById(R.id.cvvInfoBTN);
        cvvInfoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isClickEnableForCVV())
                    return;

                Tooltip.make(mActivity, tooltip).show();
            }
        });


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextBTN) {
            if ((mContext != null) && (mContext instanceof WalletBrandAMC)) {

                CartSendData();
                ((WalletBrandAMC) mContext).loadAMCFragment3();
            }
        }

    }

    private void CartSendData(){
        MockData.createContacts();
        Cart cart0 = new Cart();
        cart0.setId(numberoncardTXT.getText().toString());
        cart0.setCreatedAt("2016-06-10");
        cart0.setUpdatedAt(empiryTXT.getText().toString());
        cart0.setCustomerID(cardnumberTXT.getText().toString());
        cart0.setDeliveryAddress(MockData.mockContacts.get(0));
        cart0.setTotalAmount(123000);
        cart0.setCurrency(CVVTXT.getText().toString());
    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
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
