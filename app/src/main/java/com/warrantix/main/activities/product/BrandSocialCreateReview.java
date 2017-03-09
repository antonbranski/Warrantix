package com.warrantix.main.activities.product;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.common.event.AddReviewSuccessEvent;
import com.warrantix.main.common.rest.model.ReviewMessageContent;
import com.warrantix.main.common.rest.response.ReviewAddResponse;
import com.warrantix.main.customview.MultilineTextTouchListener;
import com.warrantix.main.customview.TokenTextView;
import com.warrantix.main.manager.BackendManager;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class BrandSocialCreateReview extends BaseActivity {

    private TextView titleTXT;
    private TextView salientFeaturedTEXT;
    private TextView txtErrorMessage;

    private TokenTextView selectproductTXT;
    private EditText messageTXT;

    private ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_social_review);
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
        titleTXT = (TextView) findViewById(R.id.title);
        salientFeaturedTEXT = (TextView) findViewById(R.id.salientfeatured);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        selectproductTXT = (TokenTextView) findViewById(R.id.selectproductTXT);
        messageTXT = (EditText) findViewById(R.id.messageTXT);
        messageTXT.setOnTouchListener(new MultilineTextTouchListener());

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button doneBTN = (Button) findViewById(R.id.doneBTN);
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String products = selectproductTXT.getText().toString();
                String content = messageTXT.getText().toString();

                if (products.equalsIgnoreCase("") == true) {
                    txtErrorMessage.setText("INFO : Please select the product.");
                    showErrorMessage();
                    return;
                }

                ReviewMessageContent reviewMessageContent = new ReviewMessageContent();
                reviewMessageContent.setContent(content);
                reviewMessageContent.setCustomerID("c1");

                BackendManager.getInstance().addReview(reviewMessageContent, products);

                txtErrorMessage.setVisibility(View.GONE);
                finish(true);
            }
        });

        productListView = (ListView) findViewById(R.id.productListView);

        final ImageButton btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productListView.getVisibility() == View.GONE) {
                    productListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    productListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String product[] = {"Product 1", "Product 2", "Product 3"};

        TokenListAdapter productAdapter = new TokenListAdapter(BrandSocialCreateReview.this, product);
        selectproductTXT.setTokenListView(productListView, productAdapter, btnDrop1);
    }

    private void showErrorMessage() {
        if (txtErrorMessage.getVisibility() != View.GONE)
            return;

        txtErrorMessage.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onAddReviewSuccessEvent(final AddReviewSuccessEvent event)
    {
        BrandSocialCreateReview.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ReviewAddResponse reviewAddResponse = event.getReviewAddResponse();
                if (reviewAddResponse != null){
                    //  please add needed code to show on this screen.

                }
            }
        });
    }
}
