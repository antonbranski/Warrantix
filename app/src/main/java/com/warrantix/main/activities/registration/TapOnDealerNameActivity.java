package com.warrantix.main.activities.registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.RegistrationManager;

public class TapOnDealerNameActivity extends BaseActivity {

    private static final String TAG = TapOnDealerNameActivity.class.getSimpleName();

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private ImageView photoView = null;
    private View invoiceView = null;

    private TextView titleView = null;
    private Bitmap mInvoiceBitmap = null;
    private View dragView = null;

    private int startX, startY, endX, endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_dealername);
    }

    @Override
    public void onResume() {
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

        titleView = (TextView) findViewById(R.id.titleViewInNavigationBar);
        titleView.setText("TAP ON DEALER NAME");

        photoView = (ImageView) findViewById(R.id.takenPhotoViewInTapDealerName);
        photoView.setOnTouchListener(btnImageDragListener);

        dragView = (View) findViewById(R.id.dragViewInDealerName);

        // draw photo
        mInvoiceBitmap = RegistrationManager.getInstance().getInvoiceBitmap();
        if (mInvoiceBitmap != null)
            photoView.setImageBitmap(mInvoiceBitmap);

        drawInvoiceView();

    }

    private void drawInvoiceView() {
        invoiceView = (View) findViewById(R.id.invoiceViewInDealerName);
        Rect invoiceRect = RegistrationManager.getInstance().getInvoiceRect();
        if (invoiceRect != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) invoiceView.getLayoutParams();
            layoutParams.leftMargin = invoiceRect.left;
            layoutParams.topMargin = invoiceRect.top;
            layoutParams.width = invoiceRect.width();
            layoutParams.height = invoiceRect.height();
            invoiceView.setLayoutParams(layoutParams);
        }
    }

    private void nextScreen() {
        int tempX, tempY;

        if (endX < startX)
            tempX = endX;
        else
            tempX = startX;

        if (endY < startY)
            tempY = endY;
        else
            tempY = startY;

        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        if (width < 10 || height < 10) {
            return;
        }

        // check rectangle area intersection
        boolean bCross = checkRectangleCross(tempX, tempY, width, height);
        if (bCross == true) {
            MessageDialog dialog = new MessageDialog(TapOnDealerNameActivity.this);
            dialog.setTitle("Info");
            dialog.setMessage("Don't make cross area");
            dialog.show();
            return;
        }

        logRectArea(tempX, tempY, Math.abs(endX - startX), Math.abs(endY - startY));
        RegistrationManager.getInstance().setDealerRect(tempX, tempY, Math.abs(endX - startX), Math.abs(endY - startY));

        Intent i = new Intent(TapOnDealerNameActivity.this, TapOnPriceActivity.class);
        startActivity(i, true);
    }

    private void logRectArea(int startx, int startY, int width, int height) {
        String logString = "";
        logString += "(" + startX + "," + startY + ")";
        logString += " - ";
        logString += "(" + width + "," + height + ")";
        Log.d(TAG, "RECT = " + logString);

        logString = "(" + photoView.getWidth() + "," + photoView.getHeight() + ")";
        Log.d(TAG, "ImageView (width, height) = " + logString);

        logString = "(" + mInvoiceBitmap.getWidth() + "," + mInvoiceBitmap.getHeight() + ")";
        Log.d(TAG, "Invoice Bitmap (width, height) = " + logString);
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnTouchListener btnImageDragListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getX();
            final int Y = (int) event.getY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    startX = X;
                    startY = Y;
                    break;
                case MotionEvent.ACTION_UP:
                    endX = X;
                    endY = Y;
                    nextScreen();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    endX = X;
                    endY = Y;
                    int tempX, tempY;

                    if (endX < startX)
                        tempX = endX;
                    else
                        tempX = startX;

                    if (endY < startY)
                        tempY = endY;
                    else
                        tempY = startY;

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) dragView.getLayoutParams();
                    layoutParams.leftMargin = tempX;
                    layoutParams.topMargin = tempY;
                    layoutParams.width = Math.abs(endX - startX);
                    layoutParams.height = Math.abs(endY - startY);
                    dragView.setLayoutParams(layoutParams);
                    break;
            }
            return true;
        }
    };

    private boolean checkRectangleCross(int startX, int startY, int width, int height) {
        Rect invoiceRect = RegistrationManager.getInstance().getInvoiceRect();
        Rect dealRect = new Rect(startX, startY, startX + width, startY + height);
        return invoiceRect.left < dealRect.left + dealRect.width()
                && invoiceRect.left + invoiceRect.width() > dealRect.left
                && invoiceRect.top < dealRect.top + dealRect.height()
                && invoiceRect.top + invoiceRect.height() > dealRect.top;
    }
}