package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletMarketplaceEshopProducts;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Cart;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.manager.BackendManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WalletBrandSettingsShoppingCartList extends RecyclerView.Adapter<WalletBrandSettingsShoppingCartList.ViewHolder> {
    private static final String TAG = WalletBrandSettingsShoppingCartList.class.getSimpleName();

    // holder inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View contentView = null;

        public ViewHolder(View v, int viewType, final Activity context) {
            super(v);
            contentView = v;

            if (viewType == WalletBrandSettingsShoppingCartList.HEADER)
            {
                Button proceedForCheckoutBtn = (Button) v.findViewById(R.id.proceedForCheckoutBTN);
                proceedForCheckoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WalletMarketplaceEshopProducts.eshop_productsSel=1;
                        Intent i = new Intent(context, WalletMarketplaceEshopProducts.class);
                        i.putExtra("title", "SHOPPING CART");
                        i.putExtra("from", "ShoppingCart");

                        if (context instanceof BaseActivity)
                            ((BaseActivity)context).startActivity(i, true);
                        else
                            context.startActivity(i);
                    }
                });
            }
        }

        public void setActivity(Activity activity) {
        }
    }

    private final Activity context;
    private TextView numberView[];

    private int itemCount = 0;
    public static final int HEADER = 0, CONTENT = 1;

    private List<Order> mOrders = new ArrayList<>();
    private List<Product> mProducts = new ArrayList<>();
    private Cart mCart = new Cart();

    public WalletBrandSettingsShoppingCartList(Activity activity, List<Order> orders, List<Product> products, Cart cart) {
        this.context = activity;
        mOrders = orders;
        mProducts = products;
        mCart = cart;
        this.numberView = new TextView[mOrders.size()];
    }

    @Override
    public int getItemCount() {
        if (mOrders == null) {
            itemCount = 1;
        }
        else {
            itemCount = mOrders.size()+1;
        }

        return itemCount;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        if (i != 0) {
            int pos = i - 1;
            View rowView = holder.contentView;
            TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
            TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
            TextView priceText1 = (TextView) rowView.findViewById(R.id.price1);
            TextView priceText2 = (TextView) rowView.findViewById(R.id.price2);
            TextView number = (TextView) rowView.findViewById(R.id.number);
            ImageButton btnDeleteOrder = (ImageButton) rowView.findViewById(R.id.btnDeleteOrder);
            final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

            Order order = mOrders.get(i-1);
            Product product = mProducts.get(i-1);

            nameText1.setText(product.getName());
            nameText2.setText(product.getModel());
            priceText1.setText("Rs. "+product.getMsrp().toString());
            priceText2.setText("You saved Rs. "+order.getUnitPrice().toString());
            number.setText(order.getQuantity().toString());

            numberView[pos] = (TextView) rowView.findViewById(R.id.number);
            Log.d(TAG, "pos = " + pos + " numberView =" + numberView[pos]);

            ImageButton upButton = (ImageButton) rowView.findViewById(R.id.up_arrowBTN);
            ImageButton downButton = (ImageButton) rowView.findViewById(R.id.down_arrowBTN);
            upButton.setTag(pos);
            downButton.setTag(pos);
            upButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();

                    int number = Integer.parseInt(numberView[pos].getText().toString());
                    if (number < 1000)
                        number++;
                    numberView[pos].setText(Integer.toString(number));

                    Log.d(TAG, "Pos = " + pos + " number = " + number);
                }
            });

            downButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();

                    int number = Integer.parseInt(numberView[pos].getText().toString());
                    if (number > 1)
                        number--;
                    numberView[pos].setText(Integer.toString(number));

                    Log.d(TAG, "Pos = " + pos + " number = " + number);
                }
            });

            btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BackendManager.getInstance().deleteOrder(mOrders.get(i-1).getId());
                }
            });



            PicassoProvider.getInstance().get()
                    .load(product.getImageThmb())
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else
            return CONTENT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == CONTENT) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_brand_list_settings_shoppingcart_list, viewGroup, false);
            return new ViewHolder(itemView, viewType, context);
        }
        else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_shoppingcart_header, viewGroup, false);
            TextView totalAmountText = (TextView) itemView.findViewById(R.id.totalAmount_Text);

            DecimalFormat format = new DecimalFormat("00,000");
            String price = format.format(mCart.getTotalAmount());

            totalAmountText.setText(" Rs. "+price);
            return new ViewHolder(itemView, viewType, context);
        }
    }

}