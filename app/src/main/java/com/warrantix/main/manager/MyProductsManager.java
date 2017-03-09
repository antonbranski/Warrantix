package com.warrantix.main.manager;

import android.util.Log;

import com.google.gson.Gson;
import com.warrantix.main.common.localdb.DatabaseObject;
import com.warrantix.main.common.localdb.WarrantixLocalDatabase;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.utils.DebugLog;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyProductsManager {
    private final static String TAG = MyProductsManager.class.getSimpleName();
    private final static String DBOBJECT_TYPE_MYPRODUCT = "myproducts";
    private final static String DBOBJECT_TYPE_CARTPRODUCT = "cartProducts";

    private ArrayList<Product> myProducts;
    private ArrayList<Product> cartProducts;

    public static MyProductsManager instance = null;

    public static MyProductsManager getInstance() {
        if (instance == null)
            instance = new MyProductsManager();

        return instance;
    }

    public MyProductsManager() {
        // for testing
        createMockupData();
    }

    private void createMockupData() {
        myProducts = (ArrayList<Product>) MockData.createMyProducts();
        cartProducts = (ArrayList<Product>) MockData.createCartProducts();

        // error occured in Realm
//        updateDatabaseObject();
    }

    private void updateDatabaseObject() {
        final Gson gson = new Gson();

        WarrantixLocalDatabase.getMyProductsDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (myProducts == null)
                    return;

                for (int i = 0; i < myProducts.size(); i++) {
                    DatabaseObject tempObject = WarrantixLocalDatabase.getMyProductsDatabase()
                            .where(DatabaseObject.class)
                            .equalTo("objectType", DBOBJECT_TYPE_MYPRODUCT)
                            .equalTo("id", myProducts.get(i).getId())
                            .findFirst();
                    if (tempObject == null)
                        tempObject = realm.createObject(DatabaseObject.class);

                    //Set product_id as id
                    final String jsonMessage = gson.toJson(myProducts.get(i));
                    if (!myProducts.isEmpty())
                        tempObject.setId(myProducts.get(i).getId());

                    tempObject.setObjectType(DBOBJECT_TYPE_MYPRODUCT);
                    tempObject.setConditionField("");
                    tempObject.setJsonData(jsonMessage);

                }
            }
        });

        WarrantixLocalDatabase.getCartProductsDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (cartProducts == null)
                    return;

                for (int i = 0; i < cartProducts.size(); i++) {
                    DatabaseObject tempObject = WarrantixLocalDatabase.getCartProductsDatabase()
                            .where(DatabaseObject.class)
                            .equalTo("objectType", DBOBJECT_TYPE_CARTPRODUCT)
                            .equalTo("id", cartProducts.get(i).getId())
                            .findFirst();
                    if (tempObject == null)
                        tempObject = realm.createObject(DatabaseObject.class);

                    //Set product_id as id
                    final String jsonMessage = gson.toJson(cartProducts.get(i));
                    if (!cartProducts.isEmpty())
                        tempObject.setId(cartProducts.get(i).getId());

                    tempObject.setObjectType(DBOBJECT_TYPE_CARTPRODUCT);
                    tempObject.setConditionField("");
                    tempObject.setJsonData(jsonMessage);

                }
            }
        });
    }

    // -----------------------------------------------
    //
    // MyProducts
    //
    // -----------------------------------------------
    public ArrayList<Product> getAllMyProducts() {
        return myProducts;
    }

    public ArrayList<Product> getBrandMyProducts(String brandapp) {
        String brandId = "";

        if (brandapp == null || brandapp.equalsIgnoreCase(""))
            return getAllMyProducts();
        else if (brandapp.equalsIgnoreCase("com.warrantix.hero"))
            brandId = "heroId";
        else if (brandapp.equalsIgnoreCase("com.warrantix.godrej"))
            brandId = "godrejId";

        // find out with brandid
        ArrayList<Product> brandProducts = new ArrayList<>();
        for (int i = 0; i < myProducts.size(); i++) {
            Product product = myProducts.get(i);
            if (product.getBrandID().equalsIgnoreCase(brandId) == true)
                brandProducts.add(product);
        }
        return brandProducts;
    }

    // -----------------------------------------------
    //
    // CartProducts
    //
    // -----------------------------------------------
    public void addCartProduct(final Product product) {


        final Gson gson = new Gson();

        WarrantixLocalDatabase.getCartProductsDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (product != null) {
                    DebugLog.e("addCartProduct()--Product_Id--" + product.getId());
                    DatabaseObject databaseObject = new DatabaseObject(product.getId(), DBOBJECT_TYPE_CARTPRODUCT, "", gson.toJson(product));
                    realm.copyToRealmOrUpdate(databaseObject);
                }
            }
        });
    }

    public List<Product> getCartProducts() {

        DebugLog.e("getCartProducts()--");
        List<Product> productList = new ArrayList<>();

        final Gson gson = new Gson();
        RealmResults<DatabaseObject> objects = WarrantixLocalDatabase.getCartProductsDatabase().where(DatabaseObject.class)
                .equalTo("objectType", DBOBJECT_TYPE_CARTPRODUCT)
                .findAll();

        if (!objects.isEmpty()) {
            for (DatabaseObject databaseObject : objects) {
                Log.d("DATA --", "" + databaseObject.getJsonData());
                productList.add(gson.fromJson(databaseObject.getJsonData(), Product.class));
            }
        }

        DebugLog.e("myProducts()--" + cartProducts.size());
        return productList;
    }

    public void updateProductQuantity(final String productId, final int quantity) {

        DebugLog.e("updateProductQuantity()--Product_Id--" + productId + "--quantity--" + quantity);
        final Gson gson = new Gson();

        WarrantixLocalDatabase.getCartProductsDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DatabaseObject objects = realm.where(DatabaseObject.class)
                        .equalTo("objectType", DBOBJECT_TYPE_CARTPRODUCT)
                        .equalTo("id", productId)
                        .findFirst();

                Product product = gson.fromJson(objects.getJsonData(), Product.class);
                product.setQuantity(quantity);

                //save update data
                objects.setJsonData(gson.toJson(product));
            }
        });

    }

    public void deleteProductFromCart(final String productId) {
        DebugLog.e("deleteProductFromCart()--Product_Id--" + productId);

//        if (cartProducts != null) {
//            for (int i = 0; i < cartProducts.size(); i++) {
//                Product cartProduct = cartProducts.get(i);
//                if (cartProduct.getId().equalsIgnoreCase(productId))
//                    cartProducts.remove(cartProduct);
//            }
//        }


        WarrantixLocalDatabase.getMyProductsDatabase().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DatabaseObject objects = realm.where(DatabaseObject.class)
                        .equalTo("objectType", DBOBJECT_TYPE_CARTPRODUCT)
                        .equalTo("id", productId)
                        .findFirst();

                if (objects != null)
                    objects.removeFromRealm();
            }
        });
    }

}