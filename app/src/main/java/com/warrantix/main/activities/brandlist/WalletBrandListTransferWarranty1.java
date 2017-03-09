package com.warrantix.main.activities.brandlist;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListTransferWarranty1 extends BaseActivity {

    private TextView titleTEXT;
    private TextView brandTEXT;
    private TextView productnameTEXT;
    private TextView modelnoTEXT;
    private TextView purchasedateTEXT;
    private TextView warrantyvalidityTEXT;

    private TextView brandEdit;
    private TextView productnameEdit;
    private TextView modelnoEdit;
    private TextView purchasedateEdit;
    private TextView warrantyvalidityEdit;

    private Button nextBTN;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_transferwarranty1);
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

        nextBTN = (Button) findViewById(R.id.nextBTN);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(WalletBrandListTransferWarranty1.this, WalletBrandListTransferWarranty2.class);
                mIntent.putExtra("product", product_json);
                startActivity(mIntent, true);
            }
        });
        titleTEXT = (TextView) findViewById(R.id.title);
        brandTEXT = (TextView) findViewById(R.id.brandTXT);
        productnameTEXT = (TextView) findViewById(R.id.productnameTXT);
        modelnoTEXT = (TextView) findViewById(R.id.modelnoTXT);
        purchasedateTEXT = (TextView) findViewById(R.id.purchasedateTXT);
        warrantyvalidityTEXT = (TextView) findViewById(R.id.warrantyvalidityTXT);

        brandEdit = (EditText) findViewById(R.id.brand_edit);
        brandEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        brandEdit.setLongClickable(false);
        productnameEdit = (EditText) findViewById(R.id.productname_edit);
        productnameEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        productnameEdit.setLongClickable(false);
        modelnoEdit = (EditText) findViewById(R.id.model_edit);
        modelnoEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        modelnoEdit.setLongClickable(false);
        purchasedateEdit = (EditText) findViewById(R.id.purchasedate_edit);
        purchasedateEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        purchasedateEdit.setLongClickable(false);

//        purchasedateEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar newCalendar = Calendar.getInstance();
//                DatePickerDialog dialog = new DatePickerDialog(WalletBrandListTransferWarranty1.this, purchaseDatePickerListener,
//                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//                dialog.show();
//            }
//        });

        warrantyvalidityEdit = (EditText) findViewById(R.id.warrantyvalidity_edit);
        warrantyvalidityEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        warrantyvalidityEdit.setLongClickable(false);

        try {
            JSONObject json = new JSONObject(product_json);
            brandEdit.setText(json.getString("model"));
            productnameEdit.setText(json.getString("name"));
            modelnoEdit.setText(json.getString("sku"));
            purchasedateEdit.setText(json.getString("updatedAt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            purchasedateEdit.setText(dateFormatter.format(newDate.getTime()));
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
