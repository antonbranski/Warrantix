package com.warrantix.main.activities;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.common.permission.MarshMallowPermission;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandAddTechnician extends BaseActivity {
    private static final String TAG = WalletBrandAddTechnician.class.getSimpleName();

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    private Uri imageUri;
    private ImageView photoImageView;
    private Bitmap photoImageBitmap;
    private CardView photoCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_add_technician);
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

        Button doneBTN = (Button) findViewById(R.id.doneBTN);
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        photoCardView = (CardView) findViewById(R.id.photoCardView);
        photoImageView = (ImageView) findViewById(R.id.photoImageView);
        photoImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (photoImageBitmap == null)
                    return false;

                PopupMenu popupMenu = new PopupMenu(WalletBrandAddTechnician.this, photoImageView) {
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
//        photoCardView.setVisibility(View.GONE);

        LinearLayout btnTakePhoto = (LinearLayout) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarshMallowPermission marshMallowPermission = new MarshMallowPermission(WalletBrandAddTechnician.this);

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

        LinearLayout btnFromGallery = (LinearLayout) findViewById(R.id.btnFromGallery);
        btnFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
            }
        });
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

                photoCardView.setVisibility(View.VISIBLE);
                Bitmap mInvoiceBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                if (mInvoiceBitmap.getWidth() < mInvoiceBitmap.getHeight())
                    mInvoiceBitmap = rotateBitmap(mInvoiceBitmap, 90);

                photoImageBitmap = scaleBitmapAspectRatio(1024, 768, mInvoiceBitmap);
                photoImageView.setImageBitmap(photoImageBitmap);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
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
}
