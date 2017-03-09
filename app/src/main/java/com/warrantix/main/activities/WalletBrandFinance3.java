package com.warrantix.main.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.warrantix.main.R;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.rest.model.PersonalDetail;
import com.warrantix.main.customview.CurrencyTextView;
import com.warrantix.main.customview.TokenTextView;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * obsoluted : The oldest screen
 */
public class WalletBrandFinance3 extends BaseActivity {

    private Button DoneBtn;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private EditText birthDateTxt;
    private EditText totalEmisTxt;
    private EditText incentiveTxt;
    private String title = null;

    private TokenTextView residenceTypeTxt;

    private DatePickerDialog dialog;
    private RadioButton rbtnWorkingYes;
    private RadioButton rbtnWorkingNo;

    private RadioButton rbtnByEmail;
    private RadioButton rbtnByCall;

    private RadioButton rbtnMorning;
    private RadioButton rbtnAfternoon;
    private RadioButton rbtnEvening;

    private CurrencyTextView spouseTxt;

    private String oldSpouse = "";
    private PersonalDetail mPersonalDetail = new PersonalDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize() {

        String jsonString = getIntent().getStringExtra("personalDetail");
        mPersonalDetail = convertJSONtoPersonal(jsonString);

        totalEmisTxt = (EditText) findViewById(R.id.totalEmisTxt);
        incentiveTxt = (EditText) findViewById(R.id.monthlyIncentivesTxt);

        title = getIntent().getStringExtra("title");
        final TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);

        rbtnMorning = (RadioButton) findViewById(R.id.morning);
        rbtnAfternoon = (RadioButton) findViewById(R.id.afternoon);
        rbtnEvening = (RadioButton) findViewById(R.id.evening);

        rbtnByEmail = (RadioButton) findViewById(R.id.financeProposal);
        rbtnByCall = (RadioButton) findViewById(R.id.financeCompany);

        rbtnWorkingNo = (RadioButton) findViewById(R.id.no);
        rbtnWorkingYes = (RadioButton) findViewById(R.id.yes);

        rbtnWorkingNo.setOnClickListener(rbtnWorkingNoClickListener);
        rbtnWorkingYes.setOnClickListener(rbtnWorkingYesClickListener);

        DoneBtn = (Button) findViewById(R.id.doneBTN);
        DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isClickEnable())
                    return;

//                if ((title != null) && (title.equalsIgnoreCase("GET FINANCE") == true)) {
//                    Intent intent = new Intent(getApplicationContext(), WalletBrandAccessoriesEshop.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    startActivity(intent, true, true);
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), WalletBrandFinance0.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    startActivity(intent, true, true);

//                    if (WarrantixPreference.loadBooleanPreference(Constant.isFromPlugin))
                     sendPersonalDetailData();
                        setResult(RESULT_OK);
                    finish();
//                }
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        // calendar text
        birthDateTxt = (EditText) findViewById(R.id.birthDateTxt);
        birthDateTxt.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        birthDateTxt.setLongClickable(false);
        birthDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isClickEnable())
                    return;


                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(birthDateTxt.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // if dialog is showed, ...
                if ((dialog != null) && (dialog.isShowing() == true))
                    return;

                dialog = new DatePickerDialog(WalletBrandFinance3.this, birthDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        // token list view

        final ImageButton btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        String residences[] = {"Residence 1", "Residence 2", "Residence 3"};
        TokenListAdapter residenceAdapter = new TokenListAdapter(WalletBrandFinance3.this, residences);
        final ListView residenceTypeListView = (ListView) findViewById(R.id.residenceTypeListView);
        residenceTypeTxt = (TokenTextView) findViewById(R.id.residenceTypeTxt);
        residenceTypeTxt.setTokenListView(residenceTypeListView, residenceAdapter, btnDrop1);


        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (residenceTypeListView.getVisibility() == View.GONE) {
                    residenceTypeListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                } else {
                    residenceTypeListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        // Currency Text View
         spouseTxt = (CurrencyTextView) findViewById(R.id.spouseTxt);
        spouseTxt.createLeftDrawable("Rs.");
    }

    private DatePickerDialog.OnDateSetListener birthDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            birthDateTxt.setText(dateFormatter.format(newDate.getTime()));
        }
    };

    private void sendPersonalDetailData(){

        mPersonalDetail.setResidenceType(residenceTypeTxt.getText().toString());
        mPersonalDetail.setDob(birthDateTxt.getText().toString());
        mPersonalDetail.setEMIs(totalEmisTxt.getText().toString());
        mPersonalDetail.setIncentives(residenceTypeTxt.getText().toString());

        if (rbtnWorkingYes.isChecked()){
            mPersonalDetail.setIsSpouseWorking(true);
        }else {
            mPersonalDetail.setIsSpouseWorking(false);
        }

        mPersonalDetail.setSalaryOfSpouse(spouseTxt.getText().toString());

        if (rbtnByEmail.isChecked()){
            mPersonalDetail.setWantFinance(rbtnByEmail.getText().toString());
        }else {
            mPersonalDetail.setWantFinance(rbtnByCall.getText().toString());
        }

        if (rbtnMorning.isChecked()){
            mPersonalDetail.setWantFinance(rbtnMorning.getText().toString());
        } else if (rbtnAfternoon.isChecked()){
            mPersonalDetail.setWantFinance(rbtnAfternoon.getText().toString());
        } else {
            mPersonalDetail.setCallMeTime(rbtnEvening.getText().toString());
        }


        MockData.mockPersonalDetail.add(mPersonalDetail);
    }

    private View.OnClickListener rbtnWorkingYesClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            spouseTxt.setFocusableInTouchMode(true);
            spouseTxt.setText(oldSpouse);
        }
    };

    private View.OnClickListener rbtnWorkingNoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            oldSpouse = spouseTxt.getText().toString();

            spouseTxt.setText("");
            spouseTxt.setFocusable(false);

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
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


    private PersonalDetail convertJSONtoPersonal(String jsonString) {
        Type type = new TypeToken<PersonalDetail>() {
        }.getType();
        return new Gson().fromJson(jsonString, type);
    }

}
