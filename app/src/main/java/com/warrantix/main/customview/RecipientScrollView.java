package com.warrantix.main.customview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.RecipientListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipientScrollView extends HorizontalScrollView implements RecipientListAdapter.OnRecipientItemSelectListener{

    private final static String TAG = RecipientScrollView.class.getSimpleName();
    private ListView relatedListView = null;
    private RecipientListAdapter relatedListAdapter = null;
    private LinearLayout imageHolder;

    private HashMap<String, RecipientListAdapter.Recipient> recipients = new HashMap<String, RecipientListAdapter.Recipient>();
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    public RecipientScrollView(Context context) {
        super(context);
    }

    public RecipientScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecipientScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RecipientScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setRecipientListView(ListView listView, RecipientListAdapter adapter) {
        if (imageHolder == null) {
            imageHolder = new LinearLayout(getContext());
            imageHolder.setOrientation(LinearLayout.HORIZONTAL);
            this.addView(imageHolder);
        }

        this.relatedListView = listView;
        this.relatedListAdapter = adapter;

        if ((listView != null) && (adapter != null)) {
            relatedListView.setAdapter(relatedListAdapter);
            relatedListView.setOnItemClickListener(relatedListAdapter);
            relatedListView.setDividerHeight(0);
            relatedListView.setDivider(null);

            relatedListAdapter.setRecipientItemSelectListener(this);
        }
    }

    /*This function has whole logic for chips generate*/
    public void addImageView(RecipientListAdapter.Recipient recipient)
    {
        final float SCALE_FACTOR = 1.0f; 	// calculated from textview_token_item's padding and font size

        // inflate chips_edittext layout
        LayoutInflater lf = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) lf.inflate(R.layout.textview_recipient_item, null);
        textView.setText(recipient.name); // set text

        // capture bitmapt of genreated textview
        int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        textView.measure(spec, spec);
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());

        Bitmap b = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        canvas.translate(-textView.getScrollX(), -textView.getScrollY());
        textView.draw(canvas);
        textView.setDrawingCacheEnabled(true);

        Bitmap cacheBmp = textView.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        textView.destroyDrawingCache();  // destory drawable

        // create bitmap drawable for imagespan
        BitmapDrawable bmpDrawable = new BitmapDrawable(viewBmp);
        int lineHeight = this.getHeight();
        bmpDrawable.setBounds(0, 0, (int) (bmpDrawable.getIntrinsicWidth() * lineHeight / bmpDrawable.getIntrinsicHeight() * SCALE_FACTOR),
                (int)(lineHeight * SCALE_FACTOR));

        ImageView newImageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newImageView.setImageDrawable(bmpDrawable);
        imageHolder.addView(newImageView);
    }

    public HashMap<String, RecipientListAdapter.Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public void OnRecipientItemSelected(RecipientListAdapter.Recipient recipient) {
        recipients.put(recipient.name, recipient);
        addImageView(recipient);
    }

}