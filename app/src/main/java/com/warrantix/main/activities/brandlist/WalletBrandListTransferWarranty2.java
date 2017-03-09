package com.warrantix.main.activities.brandlist;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.rest.model.Warranty;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.customview.MultilineTextTouchListener;
import com.warrantix.main.dialog.MessageDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListTransferWarranty2 extends BaseActivity {

    private static final String TAG = WalletBrandListTransferWarranty2.class.getSimpleName();

    private TextView titleTEXT;
    private TextView transfereeTEXT;
    private TextView walletIDTEXT;
    private TextView transferdateTEXT;
    private TextView messageTEXT;
    private TextView authorizedTEXT;
    private TextView releaseTEXT;

    private EditText transfereeEdit;
    private EditText walletIDEdit;
    private EditText transferdateEdit;
    private EditText messageEdit;
    private EditText customerID_edit;
    private EditText email_edit;
    private EditText mobileNO_edit;

    private boolean isWarrantyAuthorized = false;
    private boolean isWarrantyPaymentReceipt = false;

    private ImageView chkWarrantyAuthrized = null;
    private ImageView chkPaymentReceipt = null;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private DatePickerDialog dialog;
    private MessageDialog messageDialog;
    private boolean bIsShowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_transferwarranty2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){
        Intent intent = getIntent();
        final String product_json = intent.getStringExtra("product");
        Log.e("product===???   ",product_json);

        titleTEXT = (TextView) findViewById(R.id.title);
        transfereeTEXT = (TextView) findViewById(R.id.transfereeTXT);

        walletIDTEXT = (TextView) findViewById(R.id.walletIDTXT);
        transferdateTEXT = (TextView) findViewById(R.id.transferDateTXT);
        messageTEXT = (TextView) findViewById(R.id.messageTXT);
        authorizedTEXT = (TextView) findViewById(R.id.authorizedTXT);
        releaseTEXT = (TextView) findViewById(R.id.releaseTXT);

        customerID_edit = (EditText) findViewById(R.id.customerID_edit);
        email_edit = (EditText) findViewById(R.id.email_edit);
        mobileNO_edit = (EditText) findViewById(R.id.mobileNO_edit);
        transfereeEdit = (EditText) findViewById(R.id.transferee_edit);
        walletIDEdit = (EditText) findViewById(R.id.walletID_edit);
        transferdateEdit = (EditText) findViewById(R.id.transferDate_edit);
        transferdateEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        transferdateEdit.setLongClickable(false);
        transferdateEdit.setOnClickListener(txtTransferDateListener);

        messageEdit = (EditText) findViewById(R.id.message_edit);
        messageEdit.setOnTouchListener(new MultilineTextTouchListener());

        chkWarrantyAuthrized = (ImageView) findViewById(R.id.isWarrantyAuthorized);
        chkPaymentReceipt = (ImageView) findViewById(R.id.isWarrantyPaymentReceipt);
        chkWarrantyAuthrized.setOnClickListener(isAuthorizedListener);
        chkPaymentReceipt.setOnClickListener(isPaymentReceiptListener);

        final Button transferBTN = (Button) findViewById(R.id.transferBTN);
        transferBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageDialog = new MessageDialog(WalletBrandListTransferWarranty2.this);
                messageDialog.setTitle("Warranty Transferred");
                messageDialog.setMessage("");
                messageDialog.setOkButtonListener(btnOkClickListener);
                messageDialog.show();
                // go to MainActivity when tap on back button of device
                messageDialog.setOnKeyListener(new Dialog.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface arg0, int keyCode,
                                         KeyEvent event) {
                        // TODO Auto-generated method stub
                        if (keyCode == KeyEvent.KEYCODE_BACK) {

                            messageDialog.dismiss();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent, true, true);
                        }
                        return true;
                    }
                });

                bIsShowed = true;
                sendWarrantyData();


            }
        });


        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private void sendWarrantyData(){

        Warranty warranty0 = new Warranty();
        warranty0.setCustomerID(customerID_edit.getText().toString());
        warranty0.setEmailAddress(email_edit.getText().toString());
        warranty0.setMobileNumber(mobileNO_edit.getText().toString());
        warranty0.setTransferDate(transferdateEdit.getText().toString());
        warranty0.setTransferMessage(messageEdit.getText().toString());
        warranty0.setWalletID(walletIDEdit.getText().toString());
        if (isWarrantyAuthorized){
            warranty0.setWarrantyStyle(true);
        }else {
            warranty0.setWarrantyStyle(false);
        }

        MockData.mockWarranty.add(warranty0);

    }

    private final MessageDialog.OkButtonListener btnOkClickListener = new MessageDialog.OkButtonListener() {
        @Override
        public void callback() {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
        }
    };


    private final View.OnClickListener isAuthorizedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isWarrantyAuthorized == false)
                isWarrantyAuthorized = true;
            else
                isWarrantyAuthorized = false;

            if (isWarrantyAuthorized == true)
                chkWarrantyAuthrized.setBackgroundResource(R.drawable.square_tick);
            else
                chkWarrantyAuthrized.setBackgroundResource(R.drawable.square);
        }
    };

    private final View.OnClickListener isPaymentReceiptListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isWarrantyPaymentReceipt == false)
                isWarrantyPaymentReceipt = true;
            else
                isWarrantyPaymentReceipt = false;

            if (isWarrantyPaymentReceipt == true)
                chkPaymentReceipt.setBackgroundResource(R.drawable.square_tick);
            else
                chkPaymentReceipt.setBackgroundResource(R.drawable.square);
        }
    };

    private final View.OnClickListener txtTransferDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "txtTransferDateListener() is called");

            Calendar newCalendar = Calendar.getInstance();
            try {
                newCalendar.setTime(dateFormatter.parse(transferdateEdit.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // if dialog is showed, ...
            if ((dialog != null) && (dialog.isShowing() == true))
                return;

            dialog = new DatePickerDialog(WalletBrandListTransferWarranty2.this, purchaseDatePickerListener,
                    newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    };

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            transferdateEdit.setText(dateFormatter.format(newDate.getTime()));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.backCode && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
