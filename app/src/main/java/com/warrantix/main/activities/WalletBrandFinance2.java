package com.warrantix.main.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.warrantix.main.R;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.common.rest.model.PersonalDetail;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.customview.CurrencyTextView;
import com.warrantix.main.customview.TokenTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * obsoluted : The oldest screen
 */
public class WalletBrandFinance2 extends BaseActivity {
    private Intent mIntent;
    private Button nextBtn;

    private CurrencyTextView monthlyView;
    private TokenTextView bankView;
    private ListView bankListView;
    private EditText joinDateTxt;
    private String title;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private DatePickerDialog dialog;

    private EditText employerEditText;
    private EditText workExperienceTXT;
    private EditText yearsInCityTxt;
    private EditText durationInYearsTxt;

    private RadioButton mode_salaried;
    private RadioButton mode_selfemployed;

    private PersonalDetail mPersonalDetail = new PersonalDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance2);
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
        title = getIntent().getStringExtra("title");
        TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);



        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        employerEditText = (EditText) findViewById(R.id.employerEditText);
        workExperienceTXT = (EditText) findViewById(R.id.workExperienceTXT);
        yearsInCityTxt = (EditText) findViewById(R.id.yearsInCityTxt);
        durationInYearsTxt = (EditText) findViewById(R.id.durationInYearsTxt);

        mode_salaried = (RadioButton) findViewById(R.id.mode_salaried);
        mode_selfemployed = (RadioButton) findViewById(R.id.mode_selfemployed);


        // calendar text
        joinDateTxt = (EditText) findViewById(R.id.joiningDateTxt);
        joinDateTxt.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        joinDateTxt.setLongClickable(false);
        joinDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(joinDateTxt.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // if dialog is showed, ...
                if ((dialog != null) && (dialog.isShowing() == true))
                    return;

                dialog = new DatePickerDialog(WalletBrandFinance2.this, purchaseDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        // token list view
        String bank[] = {"Bank 1", "Bank 2", "Bank 3"};
        final ImageButton btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);

        TokenListAdapter productAdapter = new TokenListAdapter(WalletBrandFinance2.this, bank);
        bankListView = (ListView) findViewById(R.id.bankListview);
        bankView = (TokenTextView) findViewById(R.id.bankProductTXT);
        bankView.setTokenListView(bankListView, productAdapter, btnDrop1);

        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bankListView.getVisibility() == View.GONE) {
                    bankListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                } else {
                    bankListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        // Currency Text View
        monthlyView = (CurrencyTextView) findViewById(R.id.monthlySalaryTxt);
        monthlyView.createLeftDrawable("Rs.");

        nextBtn = (Button) findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPersonalDetailData();
                mIntent = new Intent(WalletBrandFinance2.this, WalletBrandFinance3.class);
                mIntent.putExtra("title", title);
                if (mPersonalDetail != null)
                    mIntent.putExtra("personalDetail", convertPersonalDetailToJSON(mPersonalDetail));
                startActivityForResult(mIntent, Constant.backCode, true);
            }
        });

    }

    private void sendPersonalDetailData(){
        mPersonalDetail = new PersonalDetail();
        mPersonalDetail.setEmployer(employerEditText.getText().toString());
        if (mode_salaried.isChecked()){
            mPersonalDetail.setSalaried(true);
        }else {
            mPersonalDetail.setSalaried(false);
        }
        mPersonalDetail.setMonthlySalary(monthlyView.getText().toString());
        mPersonalDetail.setBank(bankView.getText().toString());
        mPersonalDetail.setJoiningDate(joinDateTxt.getText().toString());
        mPersonalDetail.setWorkExperience(workExperienceTXT.getText().toString());
        mPersonalDetail.setYearsCity(yearsInCityTxt.getText().toString());
        mPersonalDetail.setYearsHome(durationInYearsTxt.getText().toString());

//        MockData.mockPersonalDetail.add(personalDetail0);
    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            joinDateTxt.setText(dateFormatter.format(newDate.getTime()));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.backCode && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish(true);
        }
    }

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

    private String convertPersonalDetailToJSON(PersonalDetail personalDetail) {
        String json = new Gson().toJson(personalDetail);
        return json;
    }


}
