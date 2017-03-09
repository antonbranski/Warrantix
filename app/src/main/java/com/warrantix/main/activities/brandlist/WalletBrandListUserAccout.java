package com.warrantix.main.activities.brandlist;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.squareup.okhttp.internal.Util;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.common.Utils;
import com.warrantix.main.common.event.UserAccountFailedEvent;
import com.warrantix.main.common.event.UserAccountSuccessEvent;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.BackendManager;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListUserAccout extends BaseActivity implements Validator.ValidationListener {
    private static final String TAG = WalletBrandListUserAccout.class.getSimpleName();

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    private TextView titleTEXT;
    private TextView walletIDTEXT;
    private TextView customerIDTEXT;
    private TextView nameTEXT;
    private TextView addressTEXT;
    private TextView cityTEXT;
    private TextView mobilenoTEXT;
    private TextView emailaddressTEXT;
    private TextView passwordTEXT;
    private TextView debitcardTEXT;

    private EditText walletIDEdit;
    private EditText customerIDEdit;

    private EditText nameEdit;
    private EditText addressEdit;
    private EditText cityEdit;

    @NumberRule(order = 3, type = NumberRule.NumberType.LONG, message = "Enter Phone Number in Numeric")
    private EditText mobilenoEdit;

    @Email(order = 5, message = "Please Check and Enter a valid Email Address")
    private EditText emailaddressEdit;
    private EditText passwordEdit;
    private EditText debitcardEdit;
    private TextView txtErrorMessage;


    private LinearLayout btnTakePhoto;
    private LinearLayout btnFromGallery;

    private Uri imageUri;
    private ImageView photoImageView;
    private Bitmap photoImageBitmap;
    private CardView photoCardView;

    private Validator validator;

    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_useraccout);
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

        photoCardView = (CardView) findViewById(R.id.photoCardView);
        photoImageView = (ImageView) findViewById(R.id.photoImageView);
        photoImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (photoImageBitmap == null)
                    return false;

                PopupMenu popupMenu = new PopupMenu(WalletBrandListUserAccout.this, photoImageView) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_remove:
                                photoImageBitmap = null;
                                photoImageView.setImageBitmap(null);
                                photoCardView.setVisibility(View.GONE);
                                return true;

                            default:
                                return super.onMenuItemSelected(menu, item);
                        }
                    }
                };

                popupMenu.inflate(R.menu.menu_popup_remove);
                popupMenu.show();
                return false;
            }
        });

        titleTEXT = (TextView) findViewById(R.id.title);
        walletIDTEXT = (TextView) findViewById(R.id.walletIDTXT);
        customerIDTEXT = (TextView) findViewById(R.id.customerIDTXT);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        nameTEXT = (TextView) findViewById(R.id.nameTXT);
        addressTEXT = (TextView) findViewById(R.id.addressTXT);
        cityTEXT = (TextView) findViewById(R.id.cityTXT);
        mobilenoTEXT = (TextView) findViewById(R.id.mobilenoTXT);
        emailaddressTEXT = (TextView) findViewById(R.id.emailaddressTXT);
        passwordTEXT = (TextView) findViewById(R.id.passwordTXT);
        debitcardTEXT = (TextView) findViewById(R.id.debitcardTXT);

        walletIDEdit = (EditText) findViewById(R.id.walletID_edit);
        walletIDEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        walletIDEdit.setLongClickable(false);

        customerIDEdit = (EditText) findViewById(R.id.customerID_edit);
        customerIDEdit.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        customerIDEdit.setLongClickable(false);

        nameEdit = (EditText) findViewById(R.id.name_edit);
        addressEdit = (EditText) findViewById(R.id.address_edit);
        cityEdit = (EditText) findViewById(R.id.city_edit);
        mobilenoEdit = (EditText) findViewById(R.id.mobileNo_edit);
        emailaddressEdit = (EditText) findViewById(R.id.emailaddress_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        debitcardEdit = (EditText) findViewById(R.id.debitcard_edit);

        emailaddressEdit.setText(Utils.getRegisteredEmail(WalletBrandListUserAccout.this));
        mobilenoEdit.setText(Utils.getPhoneNumber(WalletBrandListUserAccout.this));

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
        passwordEdit.setFilters(new InputFilter[]{filter});

        validator = new Validator(this);
        validator.setValidationListener(this);


        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        btnDone = (Button) findViewById(R.id.editBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
//                btnDone.setEnabled(false);
            }
        });

        btnTakePhoto = (LinearLayout) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarshMallowPermission marshMallowPermission = new MarshMallowPermission(WalletBrandListUserAccout.this);

                // request camera
                if (!marshMallowPermission.checkPermissionForCamera()) {
                    marshMallowPermission.requestPermissionForCamera();
                    return;
                }

                // request external storage
                if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                    marshMallowPermission.requestPermissionForExternalStorage();
                    return;
                }

                // Take photo from camera
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CAMERA);

            }
        });

        btnFromGallery = (LinearLayout) findViewById(R.id.btnFromGallery);
        btnFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
            }
        });

        // update screen
        if (WarrantixPreference.warrantixConfig.hasMainCustomer() == true) {
            Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();

            walletIDEdit.setText(mainCustomer.getWalletID());
            customerIDEdit.setText(mainCustomer.getId());

            Contact contact = mainCustomer.getContact();
            if (contact != null) {
                nameEdit.setText(contact.getFirstName() + " " + contact.getLastName());
                addressEdit.setText(contact.getAddress());
                cityEdit.setText(contact.getCity());
                mobilenoEdit.setText(contact.getTel());
                emailaddressEdit.setText(contact.getContactEmail());
                passwordEdit.setText(mainCustomer.getPassword());
                debitcardEdit.setText(contact.getSsn());
            }
        }
    }

    private void saveContact() {

        Contact contact = null;
        Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();

        if (mainCustomer != null)
            contact = mainCustomer.getContact();

        if (contact == null)
            contact = new Contact();

        if (!nameEdit.getText().toString().equalsIgnoreCase(""))
            contact.setFirstName(nameEdit.getText().toString());

        if (!addressEdit.getText().toString().equalsIgnoreCase(""))
            contact.setAddress(addressEdit.getText().toString());

        if (!cityEdit.getText().toString().equalsIgnoreCase(""))
            contact.setCity(cityEdit.getText().toString());

        if (!mobilenoEdit.getText().toString().equalsIgnoreCase(""))
            contact.setTel(mobilenoEdit.getText().toString());

        if (!emailaddressEdit.getText().toString().equalsIgnoreCase(""))
            contact.setContactEmail(emailaddressEdit.getText().toString());

        if (!passwordEdit.getText().toString().equalsIgnoreCase("") && (mainCustomer != null))
            mainCustomer.setPassword(passwordEdit.getText().toString());

        if (!debitcardEdit.getText().toString().equalsIgnoreCase(""))
            contact.setSsn(debitcardEdit.getText().toString());

        if (mainCustomer != null)
            mainCustomer.setContact(contact);

        BackendManager.getInstance().updateMainCustomer();
    }

    @Subscribe
    public void onUserAccountSuccesEvent(UserAccountSuccessEvent successEvent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finish(true);
            }
        });
    }

    @Subscribe
    public void onUserAccountFailedEvent(final UserAccountFailedEvent failedEvent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MessageDialog dialog = new MessageDialog(WalletBrandListUserAccout.this);
                dialog.setTitle("Error");
                dialog.setMessage(failedEvent.getMessage());
                dialog.show();

                btnDone.setEnabled(true);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                if (requestCode == REQUEST_CAMERA) {
                    Log.d(TAG, "Image URL = " + imageUri.toString());
                } else if (requestCode == SELECT_FILE) {
                    imageUri = data.getData();
                }

                photoCardView.setVisibility(View.VISIBLE);
                Bitmap mInvoiceBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                if (mInvoiceBitmap.getWidth() < mInvoiceBitmap.getHeight())
                    mInvoiceBitmap = rotateBitmap(mInvoiceBitmap, 90);

                photoImageBitmap = scaleBitmapAspectRatio(1024, 768, mInvoiceBitmap);
                photoImageView.setImageBitmap(photoImageBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (photoImageBitmap == null)
                photoCardView.setVisibility(View.GONE);
        }
    }

    private Bitmap rotateBitmap(Bitmap source, int angle) {
        try {
            Bitmap bitmap = null;

            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
            source.recycle();
            source = null;
            return bitmap;
        } catch (OutOfMemoryError e) {
            return source;
        }
    }

    private void showErrorMessage(String errorMessage) {
        txtErrorMessage.setText(errorMessage);
        txtErrorMessage.setVisibility(View.VISIBLE);
    }

    private void hideErrorMessage() {
        txtErrorMessage.setText("");
        txtErrorMessage.setVisibility(View.GONE);
    }

    @Override
    public void onValidationSucceeded() {
        hideErrorMessage();
        saveContact();
        btnDone.setEnabled(true);
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        System.out.println("Fail Message " + failureMessage);
        showErrorMessage(failureMessage);
        btnDone.setEnabled(true);
    }

    private Bitmap scaleBitmapAspectRatio(int width, int height, Bitmap bitmap) {
        Bitmap background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        float originalWidth = bitmap.getWidth(), originalHeight = bitmap.getHeight();
        Canvas canvas = new Canvas(background);
        float scale, xTranslation = 0.0f, yTranslation = 0.0f;
        if (originalWidth > originalHeight) {
            scale = height / originalHeight;
            xTranslation = (width - originalWidth * scale) / 2.0f;
        } else {
            scale = width / originalWidth;
            yTranslation = (height - originalHeight * scale) / 2.0f;
        }
        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, transformation, paint);
        return background;
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
