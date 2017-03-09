package com.warrantix.main.activities.brandlist;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.manager.BackendManager;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsAddCustomerID extends BaseActivity implements Validator.ValidationListener {

    @Required(order = 1, message = "Please input the customer name")
    private EditText txtCustomerName;

    @Required(order = 2, message = "Please input the customer mobile number")
    @NumberRule(order = 3, type = NumberRule.NumberType.LONG, message = "Enter Phone Number in Numeric")
    private EditText txtMobileNumber;

    @Required(order = 4, message = "Please input the customer email address")
    @Email(order = 5, message = "Please Check and Enter a valid Email Address")
    private EditText txtEmailAddress;

//    @Required(order = 6, message = "Please input the customer password")
    @Password(order = 7)
    @TextRule(order = 8, minLength = 6, maxLength = 15, message = "Enter at least 6 characters in password.")
    private EditText txtPassword;
    private TextView txtErrorMessage;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_addcustomerid);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            isInitialized = true;
        }
    }

    public void initialize(){

        txtCustomerName = (EditText) findViewById(R.id.nameTxt);
        txtMobileNumber = (EditText) findViewById(R.id.mobileNumberTxt);
        txtEmailAddress = (EditText) findViewById(R.id.mailAddressTxt);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        ImageButton backButton = (ImageButton) findViewById(R.id.brand_arrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button doneButton = (Button) findViewById(R.id.doneBTN);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        EditText walletIDTxt = (EditText) findViewById(R.id.walletIDTxt);
        walletIDTxt.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        walletIDTxt.setLongClickable(false);
        if (WarrantixPreference.warrantixConfig.hasMainWallet() == true)
            walletIDTxt.setText(WarrantixPreference.warrantixConfig.getMainWallet().getId());

        EditText mainCustomerTXT = (EditText) findViewById(R.id.mainCustomerTXT);
        mainCustomerTXT.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        mainCustomerTXT.setLongClickable(false);
        if (WarrantixPreference.warrantixConfig.hasMainCustomer() == true)
            mainCustomerTXT.setText(WarrantixPreference.warrantixConfig.getMainCustomer().getId());

        validator = new Validator(this);
        validator.setValidationListener(this);

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
        txtPassword.setFilters(new InputFilter[] { filter });
    }

    private void showErrorMessage(String errorMessage) {
        txtErrorMessage.setText(errorMessage);
        txtErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onValidationSucceeded() {
        String customerName = txtCustomerName.getText().toString();
        String mobileNumber = txtMobileNumber.getText().toString();
        String email = txtEmailAddress.getText().toString();

        BackendManager.getInstance().addFamilyCustomer(customerName, mobileNumber, email);

        Intent intent = new Intent(getApplicationContext(), WalletBrandListSettings.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent, true, true);
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        showErrorMessage(failureMessage);
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
}
