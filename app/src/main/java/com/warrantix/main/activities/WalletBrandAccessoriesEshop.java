package com.warrantix.main.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.common.event.AddProductToCartSuccessEvent;
import com.warrantix.main.common.event.ProductSuccessEvent;
import com.warrantix.main.common.event.RelatedProductSuccessEvent;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.response.AddProductToCartResponse;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.MyProductsManager;

import java.util.ArrayList;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandAccessoriesEshop extends BaseActivity implements View.OnClickListener{

    private TextView applyforTEXT;
    private TextView detailsTEXT;

    private TextView titleTEXT;
    private TextView flipcoverTEXT;
    private TextView colorTEXT;
    private TextView whiteTEXT;
    private TextView proceTEXT;

    private TextView deliveryTEXT;
    private TextView financeOptionTEXT;
    private TextView enter_pincodeTEXT;
    private TextView delivery_pincodeTEXT;
    private TextView quantityTEXT;
    private TextView aboutthisitemTEXT;
    private TextView descriptionTEXT;
    private TextView description_explainTEXT;
    private TextView featured_detailsTEXT;
    private TextView featured_details_explainTEXT;

    private ImageButton marker1;
    private ImageButton marker2;
    private ImageButton marker3;
    private ImageButton marker4;

    private Button addToCartBTN;
    private Intent mIntent;

    private ImageButton up_arrowBtn;
    private ImageButton down_arrowBtn;
    private TextView numberTEXT;
    private Spinner spinnerNum;

    private Button buynowBTN;

    private LinearLayout revealBTN;
    private LinearLayout referBTN;
    private LinearLayout reviewBTN;
    private LinearLayout rankBTN;
    private LinearLayout chatBTN;

    private ImageButton BackArrowBtn;

    private String name = "";
    private String price = "";
    private String description = "";
    private String imageURL = "";
    private String color = "";
    private String msrpSymbol = "";
    private String featureAndDetail = "";

    private Product product = null;
    private ViewPager imageViewPager;
    private CustomPagerAdapter mImageSlideAdapter;

    ArrayList<String> mImageUrls = new ArrayList<>();

    MessageDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_accessories_shop);
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

        dialog = new MessageDialog(WalletBrandAccessoriesEshop.this);

        mIntent = getIntent();
        Gson gson = new Gson();

        if (mIntent != null && mIntent.hasExtra(Constant.productObject)) {
            product = gson.fromJson(mIntent.getStringExtra(Constant.productObject), Product.class);
            Log.e("P - NAME -1-", "" + product.getName());
        }

        applyforTEXT = (TextView) findViewById(R.id.applyTXT);
        applyforTEXT.setPaintFlags(applyforTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        detailsTEXT = (TextView) findViewById(R.id.detailsTXT);
//        detailsTEXT.setPaintFlags(detailsTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        marker1 = (ImageButton) findViewById(R.id.marker1);
        marker2 = (ImageButton) findViewById(R.id.marker2);
        marker3 = (ImageButton) findViewById(R.id.marker3);
        marker4 = (ImageButton) findViewById(R.id.marker4);

        marker1.setImageResource(R.drawable.red_circle);
        marker2.setImageResource(R.drawable.red_circle_line);
        marker3.setImageResource(R.drawable.red_circle_line);
        marker4.setImageResource(R.drawable.red_circle_line);

        String title = "";
        if (product != null) {
            mImageUrls = (ArrayList<String>) product.getImageUrls();
            title = product.getName();
        }

        titleTEXT = (TextView) findViewById(R.id.title);
        if (title != null)
            titleTEXT.setText(title);

        imageViewPager = (ViewPager) findViewById(R.id.image_pager);

        if (mImageUrls != null) {
            if (mImageUrls.size() != 0) {
                mImageSlideAdapter = new CustomPagerAdapter(this);
                imageViewPager.setAdapter(mImageSlideAdapter);
            }
        }

        imageViewPager.setOnPageChangeListener(onPageChangeListener);


        flipcoverTEXT = (TextView) findViewById(R.id.Flip_CoverTXT);
        colorTEXT = (TextView) findViewById(R.id.colorTXT);
        whiteTEXT = (TextView) findViewById(R.id.whiteTXT);
        proceTEXT = (TextView) findViewById(R.id.proceTXT);
        deliveryTEXT = (TextView) findViewById(R.id.delivaryTXT);
        financeOptionTEXT = (TextView) findViewById(R.id.financeTXT);
        enter_pincodeTEXT = (TextView) findViewById(R.id.enter_pincodeTXT);
        delivery_pincodeTEXT = (TextView) findViewById(R.id.delivaryTXT);
        quantityTEXT = (TextView) findViewById(R.id.QuantityTXT);
        aboutthisitemTEXT = (TextView) findViewById(R.id.ABOUT_THIS_ITEMTXT);
        descriptionTEXT = (TextView) findViewById(R.id.DescriptionTXT);
        description_explainTEXT = (TextView) findViewById(R.id.Features_Details_ExplainTXT);
        featured_detailsTEXT = (TextView) findViewById(R.id.Features_DetailsTXT);
        featured_details_explainTEXT = (TextView) findViewById(R.id.Features_Details_Explain1TXT);

        LinearLayout applyFinanceBTN = (LinearLayout) findViewById(R.id.applyFinanceBTN);
        applyFinanceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandFinanceCompany.class);
                i.putExtra("brand", product.getName());
                i.putExtra("model", product.getModel());
//                i.putExtra("title", "GET FINANCE");
                startActivity(i, true);
            }
        });

//         get product with id
//        if (product != null) {
//            String _Id = product.getId();
//
////            if ((title != null) && (title.equals(WarrantixPreference.SUBCATEGORY_TITLE_ESHOP_ACCESSORIES))) {
////                BackendManager.getInstance().getProductsResponse(_Id);
////            } else if ((title != null) && (title.equals(WarrantixPreference.SUBCATEGORY_TITLE_USED_PRODUCTS))) {
////                BackendManager.getInstance().getProductResponse(_Id);
////            } else {
////                BackendManager.getInstance().getProductResponse(_Id);
////            }
//
//            BackendManager.getInstance().getProductsResponse(_Id);
//        }


        addToCartBTN = (Button) findViewById(R.id.addtocartBTN);
        addToCartBTN.setOnClickListener(this);

        up_arrowBtn = (ImageButton) findViewById(R.id.up_arrowBTN);
        up_arrowBtn.setOnClickListener(this);

        down_arrowBtn = (ImageButton) findViewById(R.id.down_arrowBTN);
        down_arrowBtn.setOnClickListener(this);

        numberTEXT = (TextView) findViewById(R.id.numberTXT);
        spinnerNum = (Spinner) findViewById(R.id.spinner);

        final int[] number = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        spinnerNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spinnerNum.getSelectedItemPosition();
                numberTEXT.setText(String.valueOf(number[item]));

            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        buynowBTN = (Button) findViewById(R.id.buynowBTN);
        buynowBTN.setOnClickListener(this);

        revealBTN = (LinearLayout) findViewById(R.id.revealBTN);
        referBTN = (LinearLayout) findViewById(R.id.referBTN);
        reviewBTN = (LinearLayout) findViewById(R.id.reviewBTN);
        rankBTN = (LinearLayout) findViewById(R.id.rankBTN);
        chatBTN = (LinearLayout) findViewById(R.id.chatBTN);

        revealBTN.setOnClickListener(this);
        referBTN.setOnClickListener(this);
        reviewBTN.setOnClickListener(this);
        rankBTN.setOnClickListener(this);
        chatBTN.setOnClickListener(this);

        BackArrowBtn = (ImageButton) findViewById(R.id.brand_arrow);
        BackArrowBtn.setOnClickListener(this);

        if (product != null) {
            name = product.getName();
            if (product.getMsrp() != null)
                price = product.getMsrp().toString();
            description = product.getDescription();
            imageURL = product.getImageThmb();
            msrpSymbol = product.getMsrpCurrencySymb();
            featureAndDetail = product.getFeatureAndDetails();
        }

        flipcoverTEXT.setText(name);
        proceTEXT.setText("Price: Rs. " + price);
        description_explainTEXT.setText(description);
        featured_details_explainTEXT.setText(featureAndDetail);

    }

    @Subscribe
    public void onRelatedProductSuccessEvent(final RelatedProductSuccessEvent event) {
        WalletBrandAccessoriesEshop.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductResponse getRelatedProductResponse = event.getRelatedProductsResponse();
                if (getRelatedProductResponse != null) {
                    RelatedProduct relatedProduct = getRelatedProductResponse.getRelatedProduct();
                    name = relatedProduct.getName();
                    price = String.valueOf(relatedProduct.getMsrp());
                    description = relatedProduct.getDescription();
                    imageURL = relatedProduct.getImageThmb();
                    msrpSymbol = relatedProduct.getMsrpCurrencySymb();
                    featureAndDetail = relatedProduct.getFeatureAndDetails();

                    flipcoverTEXT.setText(name);
                    proceTEXT.setText("price: Rs. " + price);
                    description_explainTEXT.setText(description);
                    featured_details_explainTEXT.setText(featureAndDetail);

//                    ImageView productImageView = (ImageView) findViewById(R.id.productImageView);
//                    if (!imageURL.equals("") && imageURL != null) {
//                        PicassoProvider.getInstance().get()
//                                .load(imageURL)
//                                .error(R.drawable.image_holder)
//                                .placeholder(R.drawable.image_holder)
//                                .into(productImageView);
//                    }
                }
            }
        });
    }

    @Subscribe
    public void onProductSuccessEvent(final ProductSuccessEvent event) {
        WalletBrandAccessoriesEshop.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductResponse getProductResponse = event.getProductResponse();
                if (getProductResponse != null) {
                    Product product = getProductResponse.getProduct();
                    if (product != null) {
                        name = product.getName();
                        if (product.getMsrp() != null)
                            price = product.getMsrp().toString();
                        description = product.getDescription();
                        imageURL = product.getImageThmb();
                        msrpSymbol = product.getMsrpCurrencySymb();
                        featureAndDetail = product.getFeatureAndDetails();
                    }

                    flipcoverTEXT.setText(name);
                    proceTEXT.setText("price: Rs. " + price);
                    description_explainTEXT.setText(description);
                    featured_details_explainTEXT.setText(featureAndDetail);

//                    ImageView productImageView = (ImageView) findViewById(R.id.productImageView);
//                    if (!TextUtils.isEmpty(imageURL)) {
//                        PicassoProvider.getInstance().get()
//                                .load(imageURL)
//                                .error(R.drawable.image_holder)
//                                .placeholder(R.drawable.image_holder)
//                                .into(productImageView);
//                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addtocartBTN) {
            if (product != null) {
                //Set quantity
                product.setQuantity(Integer.valueOf(numberTEXT.getText().toString().trim()));
                //Save in local database
                MyProductsManager.getInstance().addCartProduct(product);
//                AddProductToCartRequest addProductToCartRequest = new AddProductToCartRequest();
//                addProductToCartRequest.setCartID("cart1");
//                addProductToCartRequest.setCustomerID("c1");
//                addProductToCartRequest.setProductID(product.getId());
//                addProductToCartRequest.setQuantity(Integer.parseInt(numberTEXT.getText().toString()));
//                BackendManager.getInstance().addProductToCart(addProductToCartRequest);
            }

            dialog.setOnKeyListener(new Dialog.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        dialog.dismiss();
                        finish(true);
                    }
                    return true;
                }
            });
        }
        if (v.getId() == R.id.up_arrowBTN) {
            if (Integer.parseInt(numberTEXT.getText().toString()) <= 100) {
                numberTEXT.setText(String.valueOf(Integer.parseInt(numberTEXT.getText().toString()) + 1));
            }
        }
        if (v.getId() == R.id.down_arrowBTN) {
            if (Integer.parseInt(numberTEXT.getText().toString()) > 1) {
                numberTEXT.setText(String.valueOf(Integer.parseInt(numberTEXT.getText().toString()) - 1));
            }
        }
        if (v.getId() == R.id.buynowBTN) {
//            PaymentManager.addShoppingCart(WalletBrandAccessoriesEshop.this, "", "");

//            WalletMarketplaceEshopProducts.eshop_productsSel=1;
//            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletMarketplaceEshopProducts.class);
//            startActivity(mIntent, true);

            WalletMarketplaceEshopProducts.eshop_productsSel = 1;
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletMarketplaceEshopProducts.class);
            startActivity(mIntent, true);
        }

        if (v.getId() == R.id.revealBTN) {
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 0);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.referBTN) {
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 1);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.reviewBTN) {
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 2);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.rankBTN) {
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 3);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.chatBTN) {
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 4);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.brand_arrow) {
            finish(true);
        }
    }



    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mImageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_image, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            PicassoProvider.getInstance().get()
                    .load(mImageUrls.get(position))
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch(position)
            {
                case 0: {
                    marker1.setImageResource(R.drawable.red_circle);
                    marker2.setImageResource(R.drawable.red_circle_line);
                    marker3.setImageResource(R.drawable.red_circle_line);
                    marker4.setImageResource(R.drawable.red_circle_line);
                    break;
                }
                case 1: {
                    marker1.setImageResource(R.drawable.red_circle_line);
                    marker2.setImageResource(R.drawable.red_circle);
                    marker3.setImageResource(R.drawable.red_circle_line);
                    marker4.setImageResource(R.drawable.red_circle_line);
                    break;
                }
                case 2:{
                    marker1.setImageResource(R.drawable.red_circle_line);
                    marker2.setImageResource(R.drawable.red_circle_line);
                    marker3.setImageResource(R.drawable.red_circle);
                    marker4.setImageResource(R.drawable.red_circle_line);
                    break;
                }
                default:
                    marker1.setImageResource(R.drawable.red_circle_line);
                    marker2.setImageResource(R.drawable.red_circle_line);
                    marker3.setImageResource(R.drawable.red_circle_line);
                    marker4.setImageResource(R.drawable.red_circle);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Subscribe
    public void onAddProductToCartSuccessEvent(final AddProductToCartSuccessEvent event) {
        WalletBrandAccessoriesEshop.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
               AddProductToCartResponse addProductToCartResponse = event.getAddProductToCartResponse();
                if (addProductToCartResponse != null) {

                    dialog.setTitle(addProductToCartResponse.getMessage());
                    dialog.setMessage("");
                    dialog.show();
                    dialog.setOkButtonListener(new MessageDialog.OkButtonListener() {
                        @Override
                        public void callback() {
                            WalletBrandAccessoriesEshop.this.finish(true);
                        }
                    });


                }
            }
        });
    }
}
