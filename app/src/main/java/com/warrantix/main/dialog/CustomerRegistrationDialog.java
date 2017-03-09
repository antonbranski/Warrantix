package com.warrantix.main.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;

public class CustomerRegistrationDialog extends Dialog implements Validator.ValidationListener {

    private OkButtonListener okButtonListener = null;

    @Required(order = 1, message = "Please input the customer name")
    private EditText txtCustomerName;

    @Required(order = 2, message = "Please input the customer mobile number")
    @NumberRule(order = 3, type = NumberRule.NumberType.LONG, message = "Enter Phone Number in Numeric")
    private EditText txtMobileNumber;

    @Required(order = 4, message = "Please input the customer email address")
    @Email(order = 5, message = "Please Check and Enter a valid Email Address")
    private EditText txtEmailAddress;

    @Required(order = 6, message = "Please input the customer password")
    @Password(order = 7)
    @TextRule(order = 8, minLength = 6, maxLength = 15, message = "Enter at least 6 characters in password.")
    private EditText txtPassword;
    private TextView txtErrorMessage;

    private Validator validator;
    private Button btnOk;

    public CustomerRegistrationDialog(Activity a) {
        super(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_customer_registration);

        initialize();
    }

    private void initialize() {

        txtCustomerName = (EditText) findViewById(R.id.txtCustomerName);
        txtMobileNumber = (EditText) findViewById(R.id.txtMobileNumber);
        txtEmailAddress = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        btnOk = (Button) findViewById(R.id.btnOkInMessageDialog);
        btnOk.setOnClickListener(btnOkClickListener);

        setCanceledOnTouchOutside(false);

        validator = new Validator(this);
        validator.setValidationListener(this);

        // password input filter
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

    public void setOkButtonListener(OkButtonListener listener) {
        okButtonListener = listener;
    }

    @Override
    public void onBackPressed() {
        dismiss();
    }

    private final View.OnClickListener btnOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validator.validate();
        }
    };

    @Override
    public void onValidationSucceeded() {
//        String customerName = txtCustomerName.getText().toString();
        String mobileNumber = txtMobileNumber.getText().toString();
        String email = txtEmailAddress.getText().toString();
        String password = txtPassword.getText().toString();

        btnOk.setEnabled(false);

        if (okButtonListener != null)
            okButtonListener.callback("", mobileNumber, email, password);

        dismiss();
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        showErrorMessage(failureMessage);
    }

    public interface OkButtonListener {
        public void callback(String customerName, String mobileNumber, String eMail, String password);
    }
}