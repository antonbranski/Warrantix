package com.warrantix.main.activities.brandlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletMarketplaceEshopProducts;
import com.warrantix.main.adapter.WalletBrandSettingsShoppingCartList;
import com.warrantix.main.adapter.WalletBrandSettingsShoppingCartListNew;
import com.warrantix.main.common.event.CartSuccessEvent;
import com.warrantix.main.common.event.CitrusBillEvent;
import com.warrantix.main.common.event.DeleteOrderSuccessEvent;
import com.warrantix.main.common.event.OrdersSuccessEvent;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.rest.model.CartItem;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.request.AddCartRequest;
import com.warrantix.main.common.rest.response.CitrusBillResponse;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.DeleteOrderResponse;
import com.warrantix.main.common.rest.response.GetCartResponse;
import com.warrantix.main.common.rest.response.GetOrdersResponse;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.BackendManager;
import com.warrantix.main.manager.MyProductsManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsShoppingCart extends BaseActivity implements WalletBrandSettingsShoppingCartListNew.ItemClickListener {

    private static final String TAG = WalletBrandListSettingsShoppingCart.class.getSimpleName();
    private TextView titleTEXT;
    private TextView zeromotorText;
    private TextView youroderText;
    private TextView importantmessageText;
    private TextView lookupText;
    private TextView totalAmountText;

    private GetCartResponse getCartResponse;
    private int amount = 0;

    RecyclerView list;

    private String cartID = "";
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_shoppingcart);
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

        titleTEXT = (TextView) findViewById(R.id.title);

        zeromotorText = (TextView) findViewById(R.id.zeromotor_TEXT);
        totalAmountText = (TextView) findViewById(R.id.totalAmount_Text);
        youroderText = (TextView) findViewById(R.id.yourorder_TEXT);
        importantmessageText = (TextView) findViewById(R.id.important_message_TEXT);
        lookupText = (TextView) findViewById(R.id.lookup_TEXT);

        list = (RecyclerView) findViewById(R.id.list);

        list.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        cartID = "cart1";

//        //Add dummy data at first position
        productList = MyProductsManager.getInstance().getCartProducts();
        productList.add(0, new Product());
        Log.e("Cart Products -- ", "Products --" + productList.size());
        WalletBrandSettingsShoppingCartListNew adapter = new WalletBrandSettingsShoppingCartListNew(WalletBrandListSettingsShoppingCart.this, productList);
        adapter.setItemClicked(this);
        list.setAdapter(adapter);

//        BackendManager.getInstance().getCartResponse(cartID);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    @Subscribe
    public void onCartSuccessEvent(final CartSuccessEvent event) {
        WalletBrandListSettingsShoppingCart.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getCartResponse = event.getCartResponse();
                if (getCartResponse != null) {

                    BackendManager.getInstance().getOrdersResponse(cartID);
                    Contact deliveryAddress = getCartResponse.getCart().getDeliveryAddress();
                }
            }
        });
    }

    @Subscribe
    public void onOrdersSuccessEvent(final OrdersSuccessEvent event) {
        WalletBrandListSettingsShoppingCart.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetOrdersResponse getOrdersResponse = event.getOrdersResponse();
                if (getOrdersResponse != null) {
                    List<Order> orders = getOrdersResponse.getOrders();
                    if (orders.size() != 0) {
                        WalletBrandSettingsShoppingCartList adapter = new WalletBrandSettingsShoppingCartList(WalletBrandListSettingsShoppingCart.this, orders, event.getProducts(), getCartResponse.getCart());
                        list.setAdapter(adapter);
                    }
                }
            }
        });
    }

    @Subscribe
    public void onDeleteOrderSuccessEvent(final DeleteOrderSuccessEvent event) {
        WalletBrandListSettingsShoppingCart.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DeleteOrderResponse deleteOrderResponse = event.deleteOrderResponse();
                if (deleteOrderResponse != null) {
//                    Toast.makeText(WalletBrandListSettingsShoppingCart.this, "Order deleting is successed!", Toast.LENGTH_LONG).show();
                    BackendManager.getInstance().getCartResponse(cartID);
                }
            }
        });
    }

    @Subscribe
    public void onCitrusBillSuccessEvent(final CitrusBillEvent event) {
        WalletBrandListSettingsShoppingCart.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CitrusBillResponse citrusBillResponse = event.getCitrusBillResponse();
                if (citrusBillResponse != null) {
                    Log.d(TAG, "run: " + citrusBillResponse.getAmount());
                }
            }
        });
    }


    @Override
    public void proceedToCheckoutClicked() {

        if (productList.size() > 1) {
            BackendManager.getInstance().addCart(prepareCartRequest());
        } else {
            MessageDialog dialog = new MessageDialog(this);
            dialog.setTitle("Error !");
            dialog.setMessage("Please add item in cart");
            dialog.show();
        }
    }


    private void startWalletMarketplaceEshopProductsActivity() {
        WalletMarketplaceEshopProducts.eshop_productsSel = 1;
        startActivity(new Intent(WalletBrandListSettingsShoppingCart.this, WalletMarketplaceEshopProducts.class), true);
    }

    private AddCartRequest prepareCartRequest() {

        int count = 0;
        AddCartRequest addCartRequest = new AddCartRequest();
        List<CartItem> cartItems = new ArrayList<>();

        for (Product product : productList) {
            if (count != 0) {
                amount = amount + product.getMsrp() * product.getQuantity();

                cartItems.add(new CartItem("", product.getId(), product.getQuantity(), product.getMsrp(), product.getBrandID()));
            }
            count++;
        }

//        addCartRequest.setCustomerID(WarrantixPreference.warrantixConfig.getMainCustomer().getId());
//        addCartRequest.setDeliveryAddress(WarrantixPreference.warrantixConfig.getMainCustomer().getContact());
//        addCartRequest.setCurrency(WarrantixPreference.warrantixConfig.getMainWallet().getCurrency());

        CustomerResponse customerResponse = MockData.getCustomerByID("c0");

        addCartRequest.setCustomerID(customerResponse.getId());
        addCartRequest.setDeliveryAddress(customerResponse.getContact());
        addCartRequest.setCurrency("INR");
        addCartRequest.setCartItems(cartItems);


        return addCartRequest;

    }

}
