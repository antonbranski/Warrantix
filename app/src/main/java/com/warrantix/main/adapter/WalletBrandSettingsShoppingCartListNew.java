package com.warrantix.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsShoppingCart;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.manager.MyProductsManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 10/07/16.
 */
public class WalletBrandSettingsShoppingCartListNew extends RecyclerView.Adapter<WalletBrandSettingsShoppingCartListNew.MainViewHolder> {


    private static final String TAG = WalletBrandSettingsShoppingCartListNew.class.getSimpleName();
    private final int HEADER = 0, CONTENT = 1;
    private List<Product> products = new ArrayList<>();
    private Picasso picasso;
    private ItemClickListener itemClicked;
    private MyProductsManager myProductsManager;


    public void setItemClicked(ItemClickListener itemClicked) {
        this.itemClicked = itemClicked;
    }

    public WalletBrandSettingsShoppingCartListNew(WalletBrandListSettingsShoppingCart walletBrandListSettingsShoppingCart, List<Product> productList) {
        this.products = productList;
        picasso = PicassoProvider.getInstance().get();
        myProductsManager = MyProductsManager.getInstance();

    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == CONTENT) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_brand_list_settings_shoppingcart_list, viewGroup, false);
            return new ItemViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_shoppingcart_header, viewGroup, false);
            return new HeaderViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {


        if (holder.getItemViewType() == HEADER) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;


            //OnClickListener
            headerViewHolder.proceedForCheckoutBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClicked.proceedToCheckoutClicked();


                }
            });


        } else if (holder.getItemViewType() == CONTENT) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            final Product product = products.get(position);

            itemViewHolder.price1.setText("Rs." + product.getMsrp().toString());
            itemViewHolder.name1.setText(product.getName());
            itemViewHolder.name2.setText(product.getModel());
            itemViewHolder.number.setText(String.valueOf(product.getQuantity()));

            //Set Image
            if (!TextUtils.isEmpty(product.getImageThmb())) {
                picasso.load(product.getImageThmb()).error(R.drawable.image_holder).placeholder(R.drawable.image_holder).into(itemViewHolder.productsImage);
            } else {
                picasso.load(R.drawable.image_holder).error(R.drawable.image_holder).placeholder(R.drawable.image_holder).into(itemViewHolder.productsImage);
            }


            //OnClickListener
            itemViewHolder.upArrowBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int number = Integer.parseInt(itemViewHolder.number.getText().toString());
                    if (number < 1000)
                        number++;

                    itemViewHolder.number.setText(String.valueOf(number));

                    product.setQuantity(number);
                    myProductsManager.updateProductQuantity(product.getId(), number);
                    Log.d(TAG, "Pos = " + position + " number = " + number);
                }
            });

            itemViewHolder.downArrowBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(itemViewHolder.number.getText().toString());
                    if (number > 1)
                        number--;

                    itemViewHolder.number.setText(String.valueOf(number));

                    product.setQuantity(number);
                    myProductsManager.updateProductQuantity(product.getId(), number);
                    Log.d(TAG, "Pos = " + position + " number = " + number);
                }
            });

            itemViewHolder.btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myProductsManager.deleteProductFromCart(product.getId());
                    products.remove(position);
                    notifyDataSetChanged();

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else
            return CONTENT;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View view) {
            super(view);

        }
    }


    public class HeaderViewHolder extends MainViewHolder {

        @BindView(R.id.zeromotor_TEXT)
        TextView zeromotorTEXT;
        @BindView(R.id.totalAmount_Text)
        TextView totalAmountText;
        @BindView(R.id.proceedForCheckoutBTN)
        Button proceedForCheckoutBTN;
        @BindView(R.id.yourorder_TEXT)
        TextView yourorderTEXT;
        @BindView(R.id.important_message_TEXT)
        TextView importantMessageTEXT;
        @BindView(R.id.addDeviceBTN)
        ImageButton addDeviceBTN;
        @BindView(R.id.lookup_TEXT)
        TextView lookupTEXT;
        @BindView(R.id.details)
        TextView details;

        public HeaderViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public class ItemViewHolder extends MainViewHolder {

        @BindView(R.id.products_image)
        ImageView productsImage;
        @BindView(R.id.name1)
        TextView name1;
        @BindView(R.id.name2)
        TextView name2;
        @BindView(R.id.price1)
        TextView price1;
        @BindView(R.id.price2)
        TextView price2;
        @BindView(R.id.down_arrowBTN)
        ImageButton downArrowBTN;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.up_arrowBTN)
        ImageButton upArrowBTN;
        @BindView(R.id.btnDeleteOrder)
        ImageButton btnDeleteOrder;
        @BindView(R.id.saveBTN)
        Button saveBTN;

        public ItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);


        }
    }

    public interface ItemClickListener {
        void proceedToCheckoutClicked();
    }
}
