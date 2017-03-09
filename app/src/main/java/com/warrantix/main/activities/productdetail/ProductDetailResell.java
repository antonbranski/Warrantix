package com.warrantix.main.activities.productdetail;

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
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.customview.CurrencyTextView;

import java.lang.reflect.Type;
import java.util.Calendar;

public class ProductDetailResell extends BaseActivity {

    private static final String TAG = ProductDetailResell.class.getSimpleName();

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    private ImageButton star1;
    private ImageButton star2;
    private ImageButton star3;
    private ImageButton star4;
    private ImageButton star5;

    private boolean isAccepted = false;

    private CurrencyTextView txtOfferPrice = null;

    private LinearLayout btnTakePhoto;
    private LinearLayout btnFromGallery;
    private Uri imageUri;

    private Bitmap invoiceImages[] = new Bitmap[5];
    private ImageView imageViews[] = new ImageView[5];
    private TextView indicatorViews[] = new TextView[5];
    private int step = 0;

    private RelativeLayout photo1;
    private RelativeLayout photo2;
    private RelativeLayout photo3;
    private RelativeLayout photo4;
    private RelativeLayout photo5;

    private int selectedItem = -1;

    private String name;
    private String model;
    private String brandID;
    private int rate;
    private String sku;

    private TextView txtProductName;
    private TextView txtModelNumber;
    private TextView txtBrandName;
    private TextView txtPurchaseYear;

    private Product myProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product_resell);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            initializePhotoViews();
            isInitialized = true;
        }
    }

    private void initializePhotoViews() {
        imageViews[0] = (ImageView) findViewById(R.id.firstImageView);
        imageViews[1] = (ImageView) findViewById(R.id.secondImageView);
        imageViews[2] = (ImageView) findViewById(R.id.thirdImageView);
        imageViews[3] = (ImageView) findViewById(R.id.fourthImageView);
        imageViews[4] = (ImageView) findViewById(R.id.fifthImageView);

        indicatorViews[0] = (TextView) findViewById(R.id.firstIndicatorView);
        indicatorViews[1] = (TextView) findViewById(R.id.secondIndicatorView);
        indicatorViews[2] = (TextView) findViewById(R.id.thirdIndicatorView);
        indicatorViews[3] = (TextView) findViewById(R.id.fourthIndicatorView);
        indicatorViews[4] = (TextView) findViewById(R.id.fifthIndicatorView);
    }

    private void set0(){
        star1.setBackgroundResource(R.drawable.star_black);
        star2.setBackgroundResource(R.drawable.star_black);
        star3.setBackgroundResource(R.drawable.star_black);
        star4.setBackgroundResource(R.drawable.star_black);
        star5.setBackgroundResource(R.drawable.star_black);
    }

    private void set1()
    {
        star1.setBackgroundResource(R.drawable.star);
        star2.setBackgroundResource(R.drawable.star_black);
        star3.setBackgroundResource(R.drawable.star_black);
        star4.setBackgroundResource(R.drawable.star_black);
        star5.setBackgroundResource(R.drawable.star_black);
    }

    private void set2(){
        star1.setBackgroundResource(R.drawable.star);
        star2.setBackgroundResource(R.drawable.star);
        star3.setBackgroundResource(R.drawable.star_black);
        star4.setBackgroundResource(R.drawable.star_black);
        star5.setBackgroundResource(R.drawable.star_black);
    }

    private void set3(){
        star1.setBackgroundResource(R.drawable.star);
        star2.setBackgroundResource(R.drawable.star);
        star3.setBackgroundResource(R.drawable.star);
        star4.setBackgroundResource(R.drawable.star_black);
        star5.setBackgroundResource(R.drawable.star_black);
    }

    private void set4(){
        star1.setBackgroundResource(R.drawable.star);
        star2.setBackgroundResource(R.drawable.star);
        star3.setBackgroundResource(R.drawable.star);
        star4.setBackgroundResource(R.drawable.star);
        star5.setBackgroundResource(R.drawable.star_black);
    }

    private void set5(){
        star1.setBackgroundResource(R.drawable.star);
        star2.setBackgroundResource(R.drawable.star);
        star3.setBackgroundResource(R.drawable.star);
        star4.setBackgroundResource(R.drawable.star);
        star5.setBackgroundResource(R.drawable.star);
    }

    public void initialize() {

        String jsonString = getIntent().getStringExtra("product");
        myProduct = convertJSONtoProduct(jsonString);

        name = myProduct.getName();
        model = myProduct.getModel();
        sku = myProduct.getSku();
        brandID = myProduct.getBrandID();

        int rate = 0;
        for (int i = 0; i < myProduct.getRate().size(); i++){
            rate = rate + myProduct.getRate().get(i).getRate();
        }
        rate = rate/myProduct.getRate().size();



        txtProductName = (TextView) findViewById(R.id.txtProductName);
        txtProductName.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        txtProductName.setLongClickable(false);

        txtModelNumber = (TextView) findViewById(R.id.txtModelNumber);
        txtModelNumber.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        txtModelNumber.setLongClickable(false);

        txtBrandName = (TextView) findViewById(R.id.txtBrandName);
        txtBrandName.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        txtBrandName.setLongClickable(false);

        txtPurchaseYear = (TextView) findViewById(R.id.txtPurchaseYear);
        txtPurchaseYear.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        txtPurchaseYear.setLongClickable(false);

        txtProductName.setText(name);
        txtModelNumber.setText(model);
        txtBrandName.setText(brandID);

        // calculate the year from days
        int offsetYear = Integer.parseInt(sku)/365;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int year = currentYear - offsetYear;

        txtPurchaseYear.setText(year+"");

        star1 = (ImageButton) findViewById(R.id.rating_star1);
        star2 = (ImageButton) findViewById(R.id.rating_star2);
        star3 = (ImageButton) findViewById(R.id.rating_star3);
        star4 = (ImageButton) findViewById(R.id.rating_star4);
        star5 = (ImageButton) findViewById(R.id.rating_star5);

        switch (rate){
            case 0: set0(); break;
            case 1: set1(); break;
            case 2: set2(); break;
            case 3: set3(); break;
            case 4: set4(); break;
            case 5: set5(); break;
        }


        final ImageView selectView = (ImageView) findViewById(R.id.accept_select);
        selectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAccepted == false)
                    isAccepted = true;
                else
                    isAccepted = false;

                if (isAccepted == false)
                    selectView.setBackgroundResource(R.drawable.square);
                else
                    selectView.setBackgroundResource(R.drawable.square_tick);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button btnDone = (Button) findViewById(R.id.doneBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        txtOfferPrice = (CurrencyTextView) findViewById(R.id.txtOfferPrice);
        txtOfferPrice.createLeftDrawable("Rs.");

        btnTakePhoto = (LinearLayout) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarshMallowPermission marshMallowPermission = new MarshMallowPermission(ProductDetailResell.this);

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

        photo1 = (RelativeLayout) findViewById(R.id.photo1);
        photo2 = (RelativeLayout) findViewById(R.id.photo2);
        photo3 = (RelativeLayout) findViewById(R.id.photo3);
        photo4 = (RelativeLayout) findViewById(R.id.photo4);
        photo5 = (RelativeLayout) findViewById(R.id.photo5);

        photo1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (invoiceImages[0] == null)
                    return false;

                selectedItem = 0;
                PopupMenu popupMenu = new PopupMenu(ProductDetailResell.this, photo1) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_remove:
                                removeImageBitmap(0);
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

        photo2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (invoiceImages[1] == null)
                    return false;

                selectedItem = 1;
                PopupMenu popupMenu = new PopupMenu(ProductDetailResell.this, photo2) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_remove:
                                removeImageBitmap(1);
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

        photo3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (invoiceImages[2] == null)
                    return false;

                selectedItem = 2;
                PopupMenu popupMenu = new PopupMenu(ProductDetailResell.this, photo3) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_remove:
                                removeImageBitmap(2);
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

        photo4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (invoiceImages[3] == null)
                    return false;

                selectedItem = 3;
                PopupMenu popupMenu = new PopupMenu(ProductDetailResell.this, photo4) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_remove:
                                removeImageBitmap(3);
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

        photo5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (invoiceImages[4] == null)
                    return false;

                selectedItem = 4;
                PopupMenu popupMenu = new PopupMenu(ProductDetailResell.this, photo5) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_remove:
                                removeImageBitmap(4);
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
    }

    private void removeImageBitmap(int index) {

        for (int i=index; i<step; i++) {
            try {
                invoiceImages[i] = invoiceImages[i + 1];
                imageViews[i].setImageBitmap(invoiceImages[i+1]);
            }
            catch (Exception e) {
                invoiceImages[i] = null;
                imageViews[i].setImageBitmap(null);
            }
        }

        step--;

        imageViews[step].setImageBitmap(null);
        imageViews[step].setBackgroundResource(R.drawable.default_selected_background);
        indicatorViews[step].setVisibility(View.VISIBLE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            try {
                if (requestCode == REQUEST_CAMERA) {
                    Log.d(TAG, "Image URL = " + imageUri.toString());
                }
                else if (requestCode == SELECT_FILE) {
                    imageUri = data.getData();
                }

                if (step > 4)
                    return;

                Bitmap mInvoiceBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                if (mInvoiceBitmap.getWidth() > mInvoiceBitmap.getHeight())
                    mInvoiceBitmap = rotateBitmap(mInvoiceBitmap, 90);

                mInvoiceBitmap = scaleBitmapAspectRatio(1024, 768, mInvoiceBitmap);

                indicatorViews[step].setVisibility(View.GONE);
                imageViews[step].setImageBitmap(mInvoiceBitmap);
                imageViews[step].setBackgroundResource(0);
                invoiceImages[step] = mInvoiceBitmap;

                step++;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
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
        }
        catch (OutOfMemoryError e) {
            return source;
        }
    }

    private Bitmap scaleBitmapAspectRatio(int width, int height, Bitmap bitmap){
        Bitmap background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        float originalWidth = bitmap.getWidth(), originalHeight = bitmap.getHeight();
        Canvas canvas = new Canvas(background);
        float scale, xTranslation = 0.0f, yTranslation = 0.0f;
        if (originalWidth > originalHeight) {
            scale = height/originalHeight;
            xTranslation = (width - originalWidth * scale)/2.0f;
        }
        else {
            scale = width / originalWidth;
            yTranslation = (height - originalHeight * scale)/2.0f;
        }
        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, transformation, paint);
        return background;
    }

    private Product convertJSONtoProduct(String jsonString) {
        Type type = new TypeToken<Product>(){}.getType();
        return new Gson().fromJson(jsonString, type);
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