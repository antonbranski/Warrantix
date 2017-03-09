package com.warrantix.main.activities.registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.event.RegisterProductSuccessEvent;
import com.warrantix.main.common.rest.request.RegisterProductRequest;
import com.warrantix.main.common.rest.response.RegisterProductResponse;
import com.warrantix.main.customview.CurrencyTextView;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.BackendManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestDigitalWarrantyActivity extends BaseActivity {
    private static final String TAG = RequestDigitalWarrantyActivity.class.getSimpleName();

    private static final int PICKFILE_REQUEST_CODE = 11;
    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private Button btnRegister = null;
    private Context mContext;
    private CircleImageView productImageView = null;
    private TextView lblProductBrand = null;
    private EditText txtModelNumber = null;
    private EditText txtSerialNumber = null;
    private EditText txtPurchaseDate = null;
    private EditText txtDealerName = null;
    private CurrencyTextView txtPrice = null;
    private TextView txtAttachment = null;

    private Button lblAttachment = null;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    private String productName;
    private String brand;
    private String model;

    private DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_digital_warranty);
        mContext = RequestDigitalWarrantyActivity.this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            isInitialized = true;
        }
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);
        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnBack.setOnClickListener(btnBackClickListener);
        btnRegister = (Button) findViewById(R.id.btnRegisterInDigitalWarranty);
        btnRegister.setOnClickListener(btnRegisterClickListener);

//        productImageView = (CircleImageView) findViewById(R.id.productPhotoInDigitalWarranty);
//        lblProductBrand = (TextView) findViewById(R.id.lblProductBrandInDigitalWarranty);
        txtModelNumber = (EditText) findViewById(R.id.txtModelNumberInDigitalWarranty);
        txtSerialNumber = (EditText) findViewById(R.id.txtSerialNumberInDigitalWarranty);
        txtPurchaseDate = (EditText) findViewById(R.id.txtPurchaseDateInDigitalWarranty);
        txtDealerName = (EditText) findViewById(R.id.txtDealerNameInDigitalWarranty);
        txtPrice = (CurrencyTextView) findViewById(R.id.txtPriceInDigitalWarranty);
        txtAttachment = (TextView) findViewById(R.id.txtAttachmentInDigitalWarranty);

        lblAttachment = (Button) findViewById(R.id.lblAttachmentInDigitalWarranty);
        lblAttachment.setOnClickListener(btnAttachmentClickListener);

        txtPrice.createLeftDrawable("Rs.");

        model = getIntent().getStringExtra("model");
        txtModelNumber.setText(model);

        txtPurchaseDate.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        txtPurchaseDate.setLongClickable(false);
        txtPurchaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(txtPurchaseDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // if dialog is showed, ...
                if ((dialog != null) && (dialog.isShowing() == true))
                    return;

                dialog = new DatePickerDialog(RequestDigitalWarrantyActivity.this, purchaseDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            productName = getIntent().getStringExtra("productName");
            brand = getIntent().getStringExtra("brand");

            RegisterProductRequest registerProductRequest = new RegisterProductRequest();
            registerProductRequest.setModel(model);
            registerProductRequest.setName(productName);
            registerProductRequest.setBrandID(brand);
            registerProductRequest.setDealerName(txtDealerName.getText().toString());
            registerProductRequest.setPrice(txtPrice.getText().toString());
            registerProductRequest.setPurchaseDate(txtPurchaseDate.getText().toString());
            registerProductRequest.setSerial(txtSerialNumber.getText().toString());

            if ((productName != null) && (brand != null)) {
                if (!productName.equals("") && !brand.equals("")) {
                    BackendManager.getInstance().registerProduct(registerProductRequest);
                }
            }
            RegisterProductSuccessEvent successEvent = new RegisterProductSuccessEvent(new RegisterProductResponse());
            BusProvider.get().post(successEvent);

        }
    };

    private final View.OnClickListener btnAttachmentClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            WarrantixApplication.getInstance().openPDFFiles(RequestDigitalWarrantyActivity.this);
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("file/*");
            startActivityForResult(intent, PICKFILE_REQUEST_CODE);
        }
    };

    private final MessageDialog.OkButtonListener btnOkClickListener = new MessageDialog.OkButtonListener() {
        @Override
        public void callback() {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
        }
    };

    private final View.OnClickListener txtPurchaseDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar newCalendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(RequestDigitalWarrantyActivity.this, purchaseDatePickerListener,
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
            txtPurchaseDate.setText(dateFormatter.format(newDate.getTime()));
        }
    };

    @Subscribe
    public void onRegisterProductSuccessEvent(final RegisterProductSuccessEvent event) {
        RequestDigitalWarrantyActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RegisterProductResponse registerProductResponse = event.registerProductResponse();
                if (registerProductResponse != null) {
                    final MessageDialog dialog = new MessageDialog(RequestDigitalWarrantyActivity.this);
                    dialog.setTitle("CONGRATULATIONS!");
                    dialog.setMessage("Digital Warranty is being Processed. Will be delivered to you shortly");
                    dialog.setOkButtonListener(btnOkClickListener);
                    dialog.show();

                    // go to MainActivity when tap on back button of device
                    dialog.setOnKeyListener(new Dialog.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface arg0, int keyCode,
                                             KeyEvent event) {
                            // TODO Auto-generated method stub
                            if (keyCode == KeyEvent.KEYCODE_BACK) {

                                dialog.dismiss();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent, true, true);
                            }
                            return true;
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_REQUEST_CODE) {
            Log.d(TAG, "File Picked");
            String fileName = "";

            if (data == null)
                return;

//            Uri uri = data.getData();
//            String scheme = uri.getScheme();
//            if (scheme.equals("file")) {
//                fileName = uri.getLastPathSegment();
//            } else if (scheme.equals("content")) {
//                String[] proj = {MediaStore.Images.Media.TITLE};
//                Cursor cursor = mContext.getContentResolver().query(uri, proj, null, null, null);
//                if (cursor != null && cursor.getCount() != 0) {
//                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
//                    cursor.moveToFirst();
//                    fileName = cursor.getString(columnIndex);
//                }
//                if (cursor != null) {
//                    cursor.close();
//                }
//            }
//            Log.e("File Picked -- " + fileName);
            Toast.makeText(mContext, "File attached successfully.", Toast.LENGTH_SHORT).show();

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

}