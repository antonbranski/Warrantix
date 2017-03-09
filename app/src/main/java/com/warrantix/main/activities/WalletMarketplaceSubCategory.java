package com.warrantix.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.adapter.WarrantixMarketplaceSubCategoryAccessoriesAdapter;
import com.warrantix.main.adapter.WarrantixMarketplaceSubCategoryAdapter;
import com.warrantix.main.adapter.WarrantixMarketplaceSubCategoryProductAdapter;
import com.warrantix.main.common.event.ProductsSuccessEvent;
import com.warrantix.main.common.event.RelatedProductsSuccessEvent;
import com.warrantix.main.common.event.UsedProductsSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;
import com.warrantix.main.common.rest.response.GetUsedProductsResponse;
import com.warrantix.main.customview.PinterestView;
import com.warrantix.main.fragments.marketplace.HeroFragment;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletMarketplaceSubCategory extends BaseActivity {

    private static final String TAG = WalletMarketplaceSubCategory.class.getSimpleName();

    ListView list;

    private ImageButton BackArrowBtn;
    private PinterestView social2Bar = null;
    private String title;

    private boolean isAccessory = false;

    private List<UsedProduct> usedProducts = new ArrayList<>();
    private List<RelatedProduct> relatedProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace_subcategory);

        isAccessory = getIntent().getBooleanExtra("accessory", false);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            initSocialListeners();
            initSocial2Bar();
            isInitialized = true;
        }
    }

    private void initSocial2Bar() {
        social2Bar = (PinterestView) findViewById(R.id.social2Bar);
        social2Bar.addShowView(40, createChildView(R.drawable.long_press, "")
                , createChildView(R.drawable.popup_review, "Review")
                , createChildView(R.drawable.popup_refer, "Refer")
                , createChildView(R.drawable.popup_rank, "Rank")
                , createChildView(R.drawable.popup_reveal, "Reveal")
                , createChildView(R.drawable.popup_chat, "Chat"));
        /**
         * add pinterestview menu and Pre click view click
         */
        social2Bar.setPinClickListener(new PinterestView.PinMenuClickListener() {

            @Override
            public void onMenuItemClick(int childAt) {
                String tips = (String) social2Bar.getChildAt(childAt).getTag();
                int selected = 0;
                switch (childAt) {
                    case 1:
                        selected = 2;
                        break;
                    case 2:
                        selected = 1;
                        break;
                    case 3:
                        selected = 3;
                        break;
                    case 4:
                        selected = 0;
                        break;
                    case 5:
                        selected = 4;
                        break;
                    default:
                        return;
                }

                Intent i = new Intent(WalletMarketplaceSubCategory.this, WalletBrandSocial.class);
                i.putExtra("selected", selected);
                WalletMarketplaceSubCategory.this.startActivity(i, true);
            }

            @Override
            public void onPreViewClick() {
            }
        });
    }

    public boolean social2BarDispatchEvent(View v, MotionEvent event) {
        return social2Bar.dispatchTouchEvent(event);
    }

    public String getSubCategoryTitle() {
        return title;
    }

    public View createChildView(int imageId, String tip) {
        CircleImageView imageView = new CircleImageView(this);
        imageView.setBorderWidth(0);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setFillColor(getResources().getColor(R.color.colorAccent));
        imageView.setImageResource(imageId);
        imageView.setTag(tip);
        return imageView;
    }

    public void initialize() {

        title = getIntent().getStringExtra("title");
        TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);


        list = (ListView) findViewById(R.id.listView);
//        list.setAdapter(null);
        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return social2BarDispatchEvent(v, event);
            }
        });

        if (title != null) {
            if (title.equals(WarrantixPreference.SUBCATEGORY_TITLE_ESHOP_ACCESSORIES)) {
                BackendManager.getInstance().getRelatedProducts(WarrantixPreference.TYPE_ACCESSORIES, WarrantixPreference.BRANDID_HEROID);
            } else if (title.equals(WarrantixPreference.SUBCATEGORY_TITLE_USED_PRODUCTS)) {
                BackendManager.getInstance().getUsedProducts(WarrantixPreference.BRANDID_HEROID, null);
            } else {
                BackendManager.getInstance().getProductsResponse(WarrantixPreference.WARRANTIXID, title);
            }
        }

        BackArrowBtn = (ImageButton) findViewById(R.id.brand_arrow);
        BackArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

    }

    @Subscribe
    public void onUsedProductsSuccessEvent(final UsedProductsSuccessEvent event) {
        WalletMarketplaceSubCategory.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetUsedProductsResponse getUsedProductsResponse = event.getUsedProductsResponse();
                List<Product> products = event.getProducts();

                HeroFragment.printFullLog("UsedProducts--", new Gson().toJson(products));

                if (getUsedProductsResponse != null) {
                    usedProducts = getUsedProductsResponse.getUsedProducts();
                    if (usedProducts.size() != 0) {
                        WarrantixMarketplaceSubCategoryAdapter adapter = new WarrantixMarketplaceSubCategoryAdapter(WalletMarketplaceSubCategory.this, usedProducts, products);
                        list.setAdapter(adapter);
                        list.setDividerHeight(0);
                        list.setDivider(null);
                    }
                }
            }
        });
    }

    @Subscribe
    public void onRelatedProductsSuccessEvent(final RelatedProductsSuccessEvent event) {
        WalletMarketplaceSubCategory.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductsResponse getRelatedProductsResponse = event.getRelatedProductsResponse();
                List<Product> products = event.getProducts();

                HeroFragment.printFullLog("Related Products --", new Gson().toJson(products));

                if (getRelatedProductsResponse != null) {
                    relatedProducts = getRelatedProductsResponse.getRelatedProducts();
                    if (relatedProducts.size() != 0) {
                        WarrantixMarketplaceSubCategoryAccessoriesAdapter adapter = new WarrantixMarketplaceSubCategoryAccessoriesAdapter(WalletMarketplaceSubCategory.this, relatedProducts, products);
                        list.setAdapter(adapter);
                        list.setDividerHeight(0);
                        list.setDivider(null);
                    }
                }
            }
        });
    }

    @Subscribe
    public void onProductsSuccessEvent(final ProductsSuccessEvent event) {
        WalletMarketplaceSubCategory.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = event.getProductsResponse();

                if (getProductsResponse != null) {
                    List<Product> products = getProductsResponse.getProducts();

                    HeroFragment.printFullLog("Products--", new Gson().toJson(products));

                    if (products.size() != 0) {
                        WarrantixMarketplaceSubCategoryProductAdapter adapter = new WarrantixMarketplaceSubCategoryProductAdapter(WalletMarketplaceSubCategory.this, products);
                        list.setAdapter(adapter);
                        list.setDividerHeight(0);
                        list.setDivider(null);
                    }
                }
            }
        });
    }


    private void initSocialListeners() {
        LinearLayout social1Layout = (LinearLayout) findViewById(R.id.product_service1_layout);
        LinearLayout revealButton = (LinearLayout) social1Layout.findViewById(R.id.revealBTN);
        revealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletMarketplaceSubCategory.this, WalletBrandSocial.class);
                intent.putExtra("selected", 0);
                startActivity(intent, true);
            }
        });

        LinearLayout referButton = (LinearLayout) social1Layout.findViewById(R.id.referBTN);
        referButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletMarketplaceSubCategory.this, WalletBrandSocial.class);
                intent.putExtra("selected", 1);
                startActivity(intent, true);
            }
        });

        LinearLayout reviewButton = (LinearLayout) social1Layout.findViewById(R.id.reviewBTN);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletMarketplaceSubCategory.this, WalletBrandSocial.class);
                intent.putExtra("selected", 2);
                startActivity(intent, true);
            }
        });

        LinearLayout rankButton = (LinearLayout) social1Layout.findViewById(R.id.rankBTN);
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletMarketplaceSubCategory.this, WalletBrandSocial.class);
                intent.putExtra("selected", 3);
                startActivity(intent, true);
            }
        });

        LinearLayout chatButton = (LinearLayout) social1Layout.findViewById(R.id.chatBTN);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletMarketplaceSubCategory.this, WalletBrandSocial.class);
                intent.putExtra("selected", 4);
                startActivity(intent, true);
            }
        });

    }

    public void hideSocial1Bar() {
        View maskView = (View) findViewById(R.id.maskView);
        maskView.setVisibility(View.GONE);
        maskView.setOnClickListener(null);

        LinearLayout socialLayout = (LinearLayout) findViewById(R.id.product_service1_layout);
        socialLayout.setVisibility(View.GONE);
    }

    public void showSocial1Bar(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        View maskView = (View) findViewById(R.id.maskView);
        maskView.setVisibility(View.VISIBLE);
        maskView.setOnClickListener(maskViewClickListener);

        LinearLayout socialLayout = (LinearLayout) findViewById(R.id.product_service1_layout);
        socialLayout.setVisibility(View.VISIBLE);

        int topHeight = (int) getResources().getDimension(R.dimen._20sdp);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) socialLayout.getLayoutParams();
        int topMargin = buttonY - layoutParams.height - buttonWidth - 30;
        if (topMargin < topHeight)
            topMargin += buttonHeight + layoutParams.height + (int) getResources().getDimension(R.dimen._15sdp);
        layoutParams.setMargins(layoutParams.leftMargin, topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        socialLayout.setLayoutParams(layoutParams);
    }

    private final View.OnClickListener maskViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideSocial1Bar();
        }
    };

}
