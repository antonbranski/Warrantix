package com.warrantix.main.activities.brandlist;


import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Bundle;
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
import com.mobsandgeeks.saripaar.annotation.Required;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.manager.BackendManager;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsAddDeviceID extends BaseActivity implements Validator.ValidationListener {

    private EditText txtCustomerName;
    private EditText txtEmailAddress;

    @Required(order = 2, message = "Please input the customer mobile number")
    @NumberRule(order = 3, type = NumberRule.NumberType.LONG, message = "Enter Phone Number in Numeric")
    private EditText txtMobileNumber;

    private Validator validator;
    private TextView txtErrorMessage;
    private Customer family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_adddeviceid);
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

        int familyID = getIntent().getIntExtra("familyID", -1);

        txtCustomerName = (EditText) findViewById(R.id.nameTxt);
        txtMobileNumber = (EditText) findViewById(R.id.mobileNumberTxt);
        txtEmailAddress = (EditText) findViewById(R.id.mailAddressTxt);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        if (familyID != -1) {
            family = (Customer) WarrantixPreference.warrantixConfig.getFamilyCustomers().get(familyID);
            if (family.getContact() != null)
                txtCustomerName.setText(family.getContact().getFirstName());
            txtEmailAddress.setText(family.getUsername());
            txtCustomerName.setText(family.getUsername());
        }

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

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {

        if (family != null) {
            String telNumber = txtMobileNumber.getText().toString();
            BackendManager.getInstance().addDeviceToCustomer(telNumber, family);
        }

        Intent intent = new Intent(getApplicationContext(), WalletBrandListSettings.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent, true, true);
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        showErrorMessage(failureMessage);
    }

    private void showErrorMessage(String errorMessage) {
        txtErrorMessage.setText(errorMessage);
        txtErrorMessage.setVisibility(View.VISIBLE);
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
