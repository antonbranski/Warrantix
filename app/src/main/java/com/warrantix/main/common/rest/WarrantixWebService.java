package com.warrantix.main.common.rest;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.BuildConfig;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Cart;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Device;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RateMessageContent;
import com.warrantix.main.common.rest.model.ReferMessageContent;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.model.RevealMessageContent;
import com.warrantix.main.common.rest.model.ReviewMessageContent;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCenter;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.model.Wallet;
import com.warrantix.main.common.rest.request.AddCartRequest;
import com.warrantix.main.common.rest.request.AddProductToCartRequest;
import com.warrantix.main.common.rest.request.ChatMessageRequest;
import com.warrantix.main.common.rest.request.CustomerLoginRequest;
import com.warrantix.main.common.rest.request.DeviceUpdateGCMTokenRequest;
import com.warrantix.main.common.rest.request.PullMessageRequest;
import com.warrantix.main.common.rest.request.RegisterProductRequest;
import com.warrantix.main.common.rest.request.SearchProductRequest;
import com.warrantix.main.common.rest.request.WalletAddCustomerRequest;
import com.warrantix.main.common.rest.response.AddProductToCartResponse;
import com.warrantix.main.common.rest.response.BrandImageUrlsResponse;
import com.warrantix.main.common.rest.response.CitrusBillResponse;
import com.warrantix.main.common.rest.request.ServiceCentersRequest;
import com.warrantix.main.common.rest.response.CustomerLoginResponse;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.DeleteOrderResponse;
import com.warrantix.main.common.rest.response.DeviceResponse;
import com.warrantix.main.common.rest.response.ErrorMessageResponse;
import com.warrantix.main.common.rest.response.GetCartResponse;
import com.warrantix.main.common.rest.response.GetDealersResponse;
import com.warrantix.main.common.rest.response.GetOrdersResponse;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;
import com.warrantix.main.common.rest.response.GetServiceCenterResponse;
import com.warrantix.main.common.rest.response.GetServiceCentersResponse;
import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;
import com.warrantix.main.common.rest.response.GetServicesResponse;
import com.warrantix.main.common.rest.response.GetUsedProductsResponse;
import com.warrantix.main.common.rest.response.PullMessageResponse;
import com.warrantix.main.common.rest.response.RateAddResponse;
import com.warrantix.main.common.rest.response.ReferAddResponse;
import com.warrantix.main.common.rest.response.RegisterProductResponse;
import com.warrantix.main.common.rest.response.RevealAddResponse;
import com.warrantix.main.common.rest.response.ReviewAddResponse;
import com.warrantix.main.common.rest.response.SearchProductResponse;
import com.warrantix.main.common.rest.response.WalletResponse;
import com.warrantix.main.common.utils.DebugLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WarrantixWebService {
    public static final String TAG = WarrantixWebService.class.getSimpleName();
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static boolean isDisabled = false;

    private Retrofit client;
    private WarrantixApi mWarrentixApi;
    private String jwtToken = "test";

    private static WarrantixWebService instance = null;

    public static WarrantixWebService getInstance() {
        if (instance == null)
            instance = new WarrantixWebService();

        return instance;
    }

    public WarrantixWebService() {
        if (isDisabled == true)
            return;

//        Gson gSon = new GsonBuilder().setPrettyPrinting().create();


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                okhttp3.Request original = chain.request();
                okhttp3.Request request = original.newBuilder()
                        .header("authorization", jwtToken)
                        .method(original.method(), original.body())

                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient okHttpClient = httpClient.build();
        client = new Retrofit.Builder()
                .baseUrl(Api.SERVER)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(/*gSon*/))
                .build();

        mWarrentixApi = client.create(WarrantixApi.class);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BusProvider.get().register(this);
    }

    //
    //
    //
    public synchronized ErrorMessageResponse sendAddFamilyToWalletRequest(final String walletID, final Customer customer) {
        WalletAddCustomerRequest request = new WalletAddCustomerRequest();
        request.setCustomerID(customer.getId());
        request.setWalletID(walletID);

        Call<ErrorMessageResponse> call = mWarrentixApi.addCustomerToWallet(request);
        try {
            Response<ErrorMessageResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                ErrorMessageResponse errorResponse = new ErrorMessageResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send update customer information to backend service
    //
    public synchronized CustomerResponse sendPutCustomerByIdRequest(final String customerID, final Customer customer) {

        Call<CustomerResponse> call = mWarrentixApi.updateCustomerById(customerID, customer);
        try {
            Response<CustomerResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                CustomerResponse errorResponse = new CustomerResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send Get Customer information request to backend service
    //
    public synchronized CustomerResponse sendGetCustomerByIDRequest(final String customerID) {

//        Call<CustomerResponse> call = mWarrentixApi.getCustomerById(customerID);
//        try {
//            Response<CustomerResponse> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                return response.body();
//            }
//            else {
//                okhttp3.Response raw = response.raw();
//
//                CustomerResponse errorResponse = new CustomerResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // Mock data
        CustomerResponse customerResponse = MockData.getCustomerByID(customerID);
        return customerResponse;

    }

    //
    // send Get Wallet information request to backend service
    //
    public synchronized WalletResponse sendGetWalletByIDRequest(final String walletID) {

        Call<WalletResponse> call = mWarrentixApi.getWalletById(walletID);
        try {
            Response<WalletResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                WalletResponse errorResponse = new WalletResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //
    // send Get Device information request to backend service
    //
    public synchronized DeviceResponse sendGetDeviceByIDRequest(final String deviceID) {

        Call<DeviceResponse> call = mWarrentixApi.getDeviceById(deviceID);
        try {
            Response<DeviceResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                DeviceResponse errorResponse = new DeviceResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //
    // send GCM token registration request to backend service
    //
    public synchronized DeviceResponse sendGCMTokenRegistrationRequest(final String deviceID, final String gcmToken) {

        DeviceUpdateGCMTokenRequest gcmTokenRequest = new DeviceUpdateGCMTokenRequest();
        gcmTokenRequest.setToken(gcmToken);

        Call<DeviceResponse> call = mWarrentixApi.updateGCMTokenForDevice(deviceID, gcmTokenRequest);
        try {
            Response<DeviceResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                DeviceResponse errorResponse = new DeviceResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //
    // send device registration request to backend service
    //
    public synchronized DeviceResponse sendDeviceRegistrationRequest(final String mobileNumber, final String customerID) {
        Device newDevice = new Device();
        newDevice.setTel(mobileNumber);
        newDevice.setOwner(customerID);

        Call<DeviceResponse> call = mWarrentixApi.registerDevices(newDevice);
        try {
            Response<DeviceResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                DeviceResponse errorResponse = new DeviceResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send wallet registration request to backend service
    //
    public synchronized WalletResponse sendWalletRegistrationRequest(final String customerID) {
        Wallet newWallet = new Wallet();
        newWallet.setPrimaryID(customerID);

        Call<WalletResponse> call = mWarrentixApi.registerWallet(newWallet);
        try {
            Response<WalletResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                WalletResponse errorResponse = new WalletResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send customer login request to backend service
    //
    public synchronized CustomerLoginResponse sendCustomerLoginRequest(final String username, final String password) {
        CustomerLoginRequest loginRequest = new CustomerLoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call<CustomerLoginResponse> call = mWarrentixApi.customerLogin(loginRequest);
        try {
            Response<CustomerLoginResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                jwtToken = response.body().getJWT();
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                CustomerLoginResponse errorResponse = new CustomerLoginResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send customer registration request to WALLET backend service
    //
    public synchronized CustomerResponse sendCustomerRegistrationRequest(final String customerName, final String email, final String password, final String mobileNumber) {
        Customer mainCustomer = new Customer();
        Contact mainContact = new Contact();
        mainContact.setTel(mobileNumber);

        mainCustomer.setContact(mainContact);
        mainCustomer.setUsername(email);
        mainCustomer.setPassword(password);

        Call<CustomerResponse> call = mWarrentixApi.registerCustomer(mainCustomer);
        try {
            Response<CustomerResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                CustomerResponse errorResponse = new CustomerResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------------------------------------
    // Message API Call
    // ------------------------------------------------

    //
    // call api to get the messages with type
    //

    public synchronized PullMessageResponse getMessages(String type) {

        String acType = type;

        RoleInfo to = new RoleInfo();
        to.setRole("consumer");
        to.setId("c1");

//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
//        String strDate = "Current Date : " + mdformat.format(calendar.getTime());

        final PullMessageRequest acPullMessageRequest = new PullMessageRequest();
        acPullMessageRequest.setTo(to);
        acPullMessageRequest.setSince("2016-06-01");
        acPullMessageRequest.setType(acType);

        List<Message> messages = new ArrayList<>();

        if (acType.equals("b2c")) { // this condition dont need in integrating server api
            // get the messages from server
            Call<List<Message>> call = mWarrentixApi.getMessages(acPullMessageRequest);
            try {
                Response<List<Message>> response = call.execute();
                if ((response != null) && (response.body() != null)) {
                    PullMessageResponse pullMessageResponse = new PullMessageResponse();
                    pullMessageResponse.setMessages(response.body());
                    return pullMessageResponse;
                } else {
                    okhttp3.Response raw = response.raw();
                    PullMessageResponse errorResponse = new PullMessageResponse();
                    errorResponse.setCode(raw.code());
                    errorResponse.setMessage(raw.message());
                    return errorResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {  // get the messages from mock Data

            if (acType.equals("reminder")) {
                messages = MockData.createReminderMessages();
            } else if (acType.equals("reveal")) {
                messages = MockData.createRevealMessages();
            } else if (acType.equals("chat")) {
                messages = MockData.createChatMessages(true);
            } else {
                messages = MockData.createReferMessages();
            }

            PullMessageResponse pullMessageResponse = new PullMessageResponse();
            pullMessageResponse.setMessages(messages);
            return pullMessageResponse;
        }

    }

    public synchronized PullMessageResponse getChatMessages(ChatMessageRequest chatMessageRequest) {

        ChatMessageRequest acChatmessageRequest = chatMessageRequest;

        List<Message> messages = new ArrayList<>();

//        // get the messages from server
//        Call<List<Message>> call = mWarrentixApi.getChatMessages(acChatmessageRequest);
//        try {
//            Response<List<Message>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                PullMessageResponse pullMessageResponse = new PullMessageResponse();
//                pullMessageResponse.setMessages(response.body());
//                return pullMessageResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                PullMessageResponse errorResponse = new PullMessageResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // get the messages from mock Data

        messages = MockData.getChatMessagesByCustomerId(chatMessageRequest.getFrom().getId());

        PullMessageResponse pullMessageResponse = new PullMessageResponse();
        pullMessageResponse.setMessages(messages);
        return pullMessageResponse;
    }


    public synchronized RevealAddResponse addReveal(RevealMessageContent revealMessageContent) {

//        RevealMessageContent acRevealMessageContent = revealMessageContent;
//// api call
//        Call<RevealAddResponse> call = mWarrentixApi.addReveal(acRevealMessageContent);
//        try {
//            Response<RevealAddResponse> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                RevealAddResponse revealAddResponse = new RevealAddResponse();
////                revealAddResponse.set(response.body());
//                return revealAddResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                RevealAddResponse errorResponse = new RevealAddResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        RevealAddResponse revealAddResponse = new RevealAddResponse();
        return revealAddResponse;

    }

    public synchronized ReferAddResponse addRefer(ReferMessageContent referMessageContent) {

//        ReferMessageContent acReferMessageContent = referMessageContent;
//// api call
//        Call<ReferAddResponse> call = mWarrentixApi.addRefer(acReferMessageContent);
//        try {
//            Response<ReferAddResponse> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                ReferAddResponse referAddResponse = new ReferAddResponse();
////                revealAddResponse.set(response.body());
//                return referAddResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                ReferAddResponse errorResponse = new ReferAddResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        ReferAddResponse referAddResponse = new ReferAddResponse();
        return referAddResponse;

    }


    // ------------------------------------------------
    // Product API Call
    // ------------------------------------------------

    //
    // call api to get the products
    //

    public synchronized GetProductsResponse getProducts(String brandID) {

        String acBrandID = brandID;
//
//        Call<List<Product>> call = mWarrentixApi.getProducts(acBrandID);
//        try {
//            Response<List<Product>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetProductsResponse getProductsResponse = new GetProductsResponse();
//                getProductsResponse.setProducts(response.body());
//                return getProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetProductsResponse errorResponse = new GetProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        List<Product> products = MockData.createProducts();
        List<Product> subProducts = new ArrayList<>();
        GetProductsResponse getProductsResponse = new GetProductsResponse();
        if (products.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if (product.getBrandID().equals(acBrandID)) {
                    subProducts.add(product);
                }
            }
            getProductsResponse.setProducts(subProducts);
        } else {
            getProductsResponse.setCode(400);
            getProductsResponse.setMessage("There is not Product with ");
        }

        return getProductsResponse;

    }

    //
    // call api to get the products with brandId and Category
    //

    public synchronized GetProductsResponse getProducts(String brandID, String category) {

        String acBrandID = brandID;
        String acCategory = category;

//        Call<List<Product>> call = mWarrentixApi.getProducts(acBrandID, acCategory);
//        try {
//            Response<List<Product>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetProductsResponse getProductsResponse = new GetProductsResponse();
//                getProductsResponse.setProducts(response.body());
//                return getProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetProductsResponse errorResponse = new GetProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        List<Product> products = MockData.createProducts();
        List<Product> subProducts = new ArrayList<>();
        GetProductsResponse getProductsResponse = new GetProductsResponse();
        if (products.size() != 0) {
//            for (int i = 0; i < products.size(); i++){
//                Product product = products.get(i);
//                if (product.getBrandID().equals(acBrandID) && product.getCategory().equals(acCategory)){
//                    subProducts.add(product);
//                }
//            }
//            getProductsResponse.setProducts(subProducts);
            getProductsResponse.setProducts(products);
        } else {
            getProductsResponse.setCode(400);
            getProductsResponse.setMessage("There is not Product with ");
        }

        return getProductsResponse;

    }

    public synchronized GetProductsResponse getProducts(String brandID, Boolean onSale, Boolean popular) {

        String acBrandID = brandID;
        Boolean acOnSale = onSale;
        Boolean acPopular = popular;

        Call<List<Product>> call;


//        call = mWarrentixApi.getProducts(acBrandID, acOnSale, acPopular);

//        try {
//            Response<List<Product>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetProductsResponse getProductsResponse = new GetProductsResponse();
//                getProductsResponse.setProducts(response.body());
//                return getProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetProductsResponse errorResponse = new GetProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data

        List<Product> products = new ArrayList<>();
        if (!acOnSale) {
            products = MockData.createPopularProducts();
        } else if (!acPopular) {
            products = MockData.createTopRateProducts();
        } else if (acOnSale && acPopular) {
            products = MockData.createBestSellingProducts();
        }

        List<Product> subProducts = new ArrayList<>();
        GetProductsResponse getProductsResponse = new GetProductsResponse();
        if (products.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if (product.getBrandID().equals(acBrandID)) {
                    subProducts.add(product);
                }
            }
            getProductsResponse.setProducts(subProducts);
        } else {
            getProductsResponse.setCode(400);
            getProductsResponse.setMessage("There is not Product with ");
        }

        return getProductsResponse;

    }


    //
    // call api to get the used products
    //


    public synchronized GetUsedProductsResponse getUsedProducts(String brandID, String category) {

        String acBrandID = brandID;
        String acCategory = category;

//        Call<List<UsedProduct>> call;
//
//        if (acBrandID != null) {
//            call = mWarrentixApi.getUsedProducts(acBrandID);
//        } else {
//            call = mWarrentixApi.getUsedProducts(acBrandID, acCategory);
//        }
//
//        try {
//            Response<List<UsedProduct>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetUsedProductsResponse getUsedProductsResponse = new GetUsedProductsResponse();
//                getUsedProductsResponse.setUsedProducts(response.body());
//                return getUsedProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetUsedProductsResponse errorResponse = new GetUsedProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data

        List<UsedProduct> usedProducts;
        if (acCategory == null) {
            usedProducts = MockData.createUsedProducts();
        } else {
            usedProducts = MockData.createUsedProducts(acBrandID, acCategory);
        }

        GetUsedProductsResponse getUsedProductResponse = new GetUsedProductsResponse();
        if (usedProducts.size() != 0) {
            getUsedProductResponse.setUsedProducts(usedProducts);
        } else {
            getUsedProductResponse.setCode(400);
            getUsedProductResponse.setMessage("There is not Product with ");
        }

        return getUsedProductResponse;

    }

    //
    // call api to get the related product with type and id
    //

    public synchronized GetRelatedProductsResponse getRelatedProducts(String type, String brandID) {

        String acType = type;
        String acBrandID = brandID;

//        Call<List<RelatedProduct>> call = mWarrentixApi.getRelatedProducts(acType,acBrandID);
//        try {
//            Response<List<RelatedProduct>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetRelatedProductsResponse getRelatedProductsResponse = new GetRelatedProductsResponse();
//                getRelatedProductsResponse.setRelatedProducts(response.body());
//                return getRelatedProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetRelatedProductsResponse errorResponse = new GetRelatedProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        List<RelatedProduct> relatedProducts = MockData.createRelatedProducts();
        GetRelatedProductsResponse getRelatedProductsResponse = new GetRelatedProductsResponse();
        if (relatedProducts.size() != 0) {
            getRelatedProductsResponse.setRelatedProducts(relatedProducts);
        } else {
            getRelatedProductsResponse.setCode(400);
            getRelatedProductsResponse.setMessage("There is not Product with ");
        }

        return getRelatedProductsResponse;

    }


    //
    // call api to get the product with id
    //

    public synchronized GetProductResponse getProduct(String productID, String category) {

        String acProductID = productID;
        String acCategory = category;
// api call
//        Call<Product> call = mWarrentixApi.getProduct(acProductID);
//        try {
//            Response<Product> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetProductResponse getProductResponse = new GetProductResponse();
//                getProductResponse.setProduct(response.body());
//                return getProductResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetProductResponse errorResponse = new GetProductResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data
        Product product = MockData.getProduct(productID, category);
        GetProductResponse getProductResponse = new GetProductResponse();
        if (product != null) {
            getProductResponse.setProduct(product);
        } else {
            getProductResponse.setCode(400);
            getProductResponse.setMessage("There is not Product with " + productID);
        }

        return getProductResponse;
    }

    //
    // call api to get the related product with id
    //

    public synchronized GetRelatedProductResponse getRelatedProduct(String _Id) {

//        GetRelatedProductResponse getRelatedProductResponse = new GetRelatedProductResponse();
//        Call<RelatedProduct> call = mWarrentixApi.getRelatedProduct(_Id);
//        try {
//            Response<RelatedProduct> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                getRelatedProductResponse.setRelatedProduct(response.body());
//                return getRelatedProductResponse;
//            }
//            else {
//                okhttp3.Response raw = response.raw();
//                GetRelatedProductResponse errorResponse = new GetRelatedProductResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data
        RelatedProduct relatedProduct = MockData.getRelatedProduct(_Id);
        GetRelatedProductResponse getRelatedProductResponse = new GetRelatedProductResponse();
        getRelatedProductResponse.setRelatedProduct(relatedProduct);

        return getRelatedProductResponse;

    }

    //
    //  call api to search the product
    //

    public synchronized SearchProductResponse searchProductResponse(SearchProductRequest searchProductRequest) {
        SearchProductRequest acSearchProductRequest = searchProductRequest;

//        Call<SearchProductResponse> call = mWarrentixApi.searchProduct(acSearchProductRequest);
//        try {
//            Response<SearchProductResponse> response = call.execute();
//
//            okhttp3.Response raw = response.raw();
//            SearchProductResponse errorResponse = new SearchProductResponse();
//            errorResponse.setCode(raw.code());
//            errorResponse.setMessage(raw.message());
//            return errorResponse;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // mock Data
        SearchProductResponse searchProductResponse = new SearchProductResponse();
        searchProductResponse.setCode(204);
        searchProductResponse.setMessage("This is the test using mock data");
        return searchProductResponse;
    }


    //
    // register the product
    //

    public synchronized RegisterProductResponse registerProductResponse(RegisterProductRequest registerProductRequest) {
        RegisterProductRequest acRegisterProductRequest = registerProductRequest;

//        Call<RegisterProductResponse> call = mWarrentixApi.registerProduct(acRegisterProductRequest);
//        try {
//            Response<RegisterProductResponse> response = call.execute();
//
//            okhttp3.Response raw = response.raw();
//            RegisterProductResponse errorResponse = new RegisterProductResponse();
//            errorResponse.setCode(raw.code());
//            errorResponse.setMessage(raw.message());
//            return errorResponse;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        //mock Data
        RegisterProductResponse registerProductResponse = new RegisterProductResponse();
        registerProductResponse.setCode(204);
        registerProductResponse.setMessage("This is the test using mock data");
        return registerProductResponse;
    }

    public synchronized ReviewAddResponse addReview(ReviewMessageContent reviewMessageContent, String productID) {

//        ReviewMessageContent acReviewMessageContent = reviewMessageContent;
//        String acProductID = productID;
//// api call
//        Call<ReviewAddResponse> call = mWarrentixApi.addReview(acReviewMessageContent, acProductID);
//        try {
//            Response<ReviewAddResponse> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                ReviewAddResponse reviewAddResponse = new ReviewAddResponse();
////                revealAddResponse.set(response.body());
//                return reviewAddResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                ReviewAddResponse errorResponse = new ReviewAddResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        ReviewAddResponse reviewAddResponse = new ReviewAddResponse();
        return reviewAddResponse;

    }

    public synchronized RateAddResponse addReview(RateMessageContent rateMessageContent, String productID) {

//        RateMessageContent acRateMessageContent = rateMessageContent;
//        String acProductID = productID;
//// api call
//        Call<RateAddResponse> call = mWarrentixApi.addRate(acRateMessageContent, acProductID);
//        try {
//            Response<RateAddResponse> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                RateAddResponse rateAddResponse = new RateAddResponse();
////                revealAddResponse.set(response.body());
//                return rateAddResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                RateAddResponse errorResponse = new RateAddResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        RateAddResponse rateAddResponse = new RateAddResponse();
        return rateAddResponse;

    }


    // ------------------------------------------------
    // Dealer API Call
    // ------------------------------------------------

    //
    // call api to get the dealers
    //

    public synchronized GetDealersResponse getDealersResponse() {

//        Call<List<Dealer>> call = mWarrentixApi.getDealers();
//        try {
//            Response<List<Dealer>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetDealersResponse getDealersResponse= new GetDealersResponse();
//                getDealersResponse.setDealers(response.body());
//                return getDealersResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetDealersResponse errorResponse = new GetDealersResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        DebugLog.e("getDealersResponse()--");
        // call mock data

        List<Dealer> dealers = MockData.createDealer();
        GetDealersResponse getDealersResponse = new GetDealersResponse();


        DebugLog.e("getDealersResponse()--dealers--" + dealers.size());

        if (dealers.size() != 0) {
            DebugLog.e("getDealersResponse()--setDealers--");
            getDealersResponse.setDealers(dealers);
        } else {
            getDealersResponse.setCode(400);
            getDealersResponse.setMessage("There is not Product with ");
        }

        return getDealersResponse;

    }


    // ------------------------------------------------
    // Service API Call
    // ------------------------------------------------

    //
    // call api to get the services
    //

    public synchronized GetServicesResponse getServices(String type, String brandID) {

//        String acType = type;
//        String acBrandID = brandID;
//
//        Call<List<Service>> call = mWarrentixApi.getServices(acType, acBrandID);
//        try {
//            Response<List<Service>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetServicesResponse getServicesResponse = new GetServicesResponse();
//                getServicesResponse.setServices(response.body());
//                return getServicesResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetServicesResponse errorResponse = new GetServicesResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data
        List<Service> services = MockData.createServices();
        GetServicesResponse getServicesResponse = new GetServicesResponse();
        if (services.size() != 0) {
            getServicesResponse.setServices(services);
        } else {
            getServicesResponse.setCode(400);
            getServicesResponse.setMessage("There is not Product with ");
        }

        return getServicesResponse;

    }

    //
    //call api to get the service caompanies
    //

    public synchronized GetServiceCompaniesResponse getServiceCompanies(String type, String brandID) {

        String acType = type;
        String acBrandID = brandID;

//        Call<List<ServiceCompany>> call = mWarrentixApi.getServiceCompanies(acType, acBrandID);
//        try {
//            Response<List<ServiceCompany>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetServiceCompaniesResponse getServiceCompaniesResponse = new GetServiceCompaniesResponse();
//                getServiceCompaniesResponse.setServiceCompanies(response.body());
//                return getServiceCompanyResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetServiceCompaniesResponse errorResponse = new GetServiceCompaniesResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data

        List<ServiceCompany> serviceCompanies;
        if (acType.equalsIgnoreCase(WarrantixPreference.TYPE_AMC) == true)
            serviceCompanies = MockData.createServiceCompanies();
        else if (acType.equalsIgnoreCase(WarrantixPreference.TYPE_INSURANCE) == true)
            serviceCompanies = MockData.createInsuranceServiceCompanies();
        else
            serviceCompanies = MockData.createFinanceServiceCompanies();

        GetServiceCompaniesResponse getServiceCompaniesResponse = new GetServiceCompaniesResponse();
        if (serviceCompanies.size() != 0) {
            getServiceCompaniesResponse.setServiceCompanies(serviceCompanies);
        } else {
            getServiceCompaniesResponse.setCode(400);
            getServiceCompaniesResponse.setMessage("There is not ServiceCompanies");
        }

        return getServiceCompaniesResponse;
    }


    // get the service centers
    public synchronized GetServiceCentersResponse getServiceCenters(ServiceCentersRequest serviceCentersRequest) {

        ServiceCentersRequest acServiceCentersRequest = serviceCentersRequest;

//        Call<List<ServiceCenter>> call = mWarrentixApi.getServiceCenters(acServiceCentersRequest);
//        try {
//            Response<List<ServiceCenter>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetServiceCentersResponse getServiceCentersResponse = new GetServiceCentersResponse();
//                getServiceCentersResponse.setServiceCenters(response.body());
//                return getServiceCentersResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetServiceCentersResponse errorResponse = new GetServiceCentersResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data

        List<ServiceCenter> serviceCenters;


        serviceCenters = MockData.createServiceCenters();

        GetServiceCentersResponse getServiceCentersResponse = new GetServiceCentersResponse();
        if (serviceCenters.size() != 0) {
            getServiceCentersResponse.setServiceCenters(serviceCenters);
        } else {
            getServiceCentersResponse.setCode(400);
            getServiceCentersResponse.setMessage("There is not ServiceCompanies");
        }

        return getServiceCentersResponse;
    }

    public synchronized GetServiceCenterResponse GetServiceCetnerByID(String serviceCenterID) {

        String acServiceCenterID = serviceCenterID;
        GetServiceCenterResponse getServiceCenterResponse = new GetServiceCenterResponse();

        ServiceCenter serviceCenter = MockData.getServiceCetner(acServiceCenterID);

        getServiceCenterResponse.setServiceCenter(serviceCenter);

        return getServiceCenterResponse;
    }

    // ------------------------------------------------
    // Order API Call
    // ------------------------------------------------

    //
    // call api to get the orders

    public synchronized GetOrdersResponse getOrdersResponse() {

//        Call<List<Order>> call = mWarrentixApi.getOrders();
//        try {
//            Response<List<Order>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
//                getOrdersResponse.setOrders(response.body());
//                return getOrdersResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetOrdersResponse errorResponse = new GetOrdersResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        // call mock data
        List<Order> orders = MockData.createOrders();
        GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
        if (orders.size() != 0) {
            getOrdersResponse.setOrders(orders);
        } else {
            getOrdersResponse.setCode(400);
            getOrdersResponse.setMessage("There is not Product with ");
        }

        return getOrdersResponse;

    }

    //
    //call api to get orders with cartID
    //

    public synchronized GetOrdersResponse getOrdersResponse(String cartID) {

//        String acCartID = cartID;
//
//        Call<List<Order>> call = mWarrentixApi.getOrders(acCartID);
//        try {
//            Response<List<Order>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
//                getOrdersResponse.setOrders(response.body());
//                return getOrdersResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetOrdersResponse errorResponse = new GetOrdersResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        List<Order> orders = MockData.getOrders(cartID);
        GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
        if (orders.size() != 0) {
            getOrdersResponse.setOrders(orders);
        } else {
            getOrdersResponse.setCode(400);
            getOrdersResponse.setMessage("There is not Product with ");
        }

        return getOrdersResponse;

    }


    //
    // delete the order
    //

    public synchronized DeleteOrderResponse deleteOrderResponse(String orderID) {
        String acOrderID = orderID;

//        Call<DeleteOrderResponse> call = mWarrentixApi.deleteOrder(acOrderID);
//        try {
//            Response<DeleteOrderResponse> response = call.execute();
//
//            okhttp3.Response raw = response.raw();
//            DeleteOrderResponse errorResponse = new DeleteOrderResponse();
//            errorResponse.setCode(raw.code());
//            errorResponse.setMessage(raw.message());
//            return errorResponse;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        //mock Data
        MockData.deleteOrder(orderID);

        DeleteOrderResponse deleteOrderResponse = new DeleteOrderResponse();
        deleteOrderResponse.setCode(204);
        deleteOrderResponse.setMessage("This is the test using mock data");
        return deleteOrderResponse;
    }


    // ------------------------------------------------
    // Cart API Call
    // ------------------------------------------------

    //
    // call api to get the carts
    //

    public synchronized GetCartResponse getCartResponse(String _Id) {

//        GetCartResponse getCartResponse = new GetCartResponse();
//        Call<Cart> call = mWarrentixApi.getCart(_Id);
//        try {
//            Response<Cart> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                getCartResponse.setCart(response.body());
//                return getCartResponse;
//            }
//            else {
//                okhttp3.Response raw = response.raw();
//                GetCartResponse errorResponse = new GetCartResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        Cart cart = MockData.getCart(_Id);
        GetCartResponse getCartResponse = new GetCartResponse();
        getCartResponse.setCart(cart);

        return getCartResponse;

    }

    //
    // call api to get the carts
    //

    public synchronized AddProductToCartResponse addProductToCart(AddProductToCartRequest addProductToCartRequest) {

        AddProductToCartRequest acAddProductToCartRequest = addProductToCartRequest;


        // mock Data
        AddProductToCartResponse addProductToCartResponse = new AddProductToCartResponse();
        String message = MockData.addOrder(acAddProductToCartRequest);
        addProductToCartResponse.setMessage(message);
        addProductToCartResponse.setCode(0);

        return addProductToCartResponse;
    }

    public synchronized BrandImageUrlsResponse getBrandImageUrls(String type) {

        final String acType = type;
//
//        Call<List<String>> call;
//        if (acType != null){
//           call = mWarrentixApi.getBrandImageUrls();
//        } else {
//           call = mWarrentixApi.getBrandImageUrls(acType);
//        }
//        try {
//            Response<List<String>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                BrandImageUrlsResponse brandImageUrlsResponse = new BrandImageUrlsResponse();
//                brandImageUrlsResponse.setBrandImgUrls(response.body());
//                return brandImageUrlsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                BrandImageUrlsResponse errorResponse = new BrandImageUrlsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        List<String> brandImgUrls;

        brandImgUrls = MockData.creatBrandImgUrls(acType);

        BrandImageUrlsResponse brandImageUrlsResponse = new BrandImageUrlsResponse();
        if (brandImgUrls.size() != 0) {
            brandImageUrlsResponse.setBrandImgUrls(brandImgUrls);
        } else {
            brandImageUrlsResponse.setCode(400);
            brandImageUrlsResponse.setMessage("failed");
        }

        return brandImageUrlsResponse;

    }

    public synchronized BrandImageUrlsResponse getBrandImageUrlsBySearchword(String name) {

        final String acName = name;

//        Call<List<String>> call;
//        call = mWarrentixApi.getBrandImageUrlsBySearchword(acName);
//
//        try {
//            Response<List<String>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                BrandImageUrlsResponse brandImageUrlsResponse = new BrandImageUrlsResponse();
//                brandImageUrlsResponse.setBrandImgUrls(response.body());
//                return brandImageUrlsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                BrandImageUrlsResponse errorResponse = new BrandImageUrlsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        // call mock data
        List<String> brandImgUrls;

        brandImgUrls = MockData.creatBrandImgUrlsBySearchword(acName);

        BrandImageUrlsResponse brandImageUrlsResponse = new BrandImageUrlsResponse();
        if (brandImgUrls.size() != 0) {
            brandImageUrlsResponse.setBrandImgUrls(brandImgUrls);
        } else {
            brandImageUrlsResponse.setCode(400);
            brandImageUrlsResponse.setMessage("failed");
        }

        return brandImageUrlsResponse;

    }


    //
    // send Get Device information request to backend service
    //
    public synchronized CitrusBillResponse sendAddCartRequest(AddCartRequest addCartRequest) {


        Log.e(TAG, "sendAddCartRequest: " + new Gson().toJson(addCartRequest));

        Call<CitrusBillResponse> call = mWarrentixApi.addCart(addCartRequest);
        try {
            Response<CitrusBillResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            } else {
                okhttp3.Response raw = response.raw();

                CitrusBillResponse citrusBillResponse = new CitrusBillResponse();

                Log.e(TAG, "citrusBillResponse: " + new Gson().toJson(citrusBillResponse));
                citrusBillResponse.setCode(raw.code());
                citrusBillResponse.setMessage(raw.message());
                return citrusBillResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
