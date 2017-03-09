package com.warrantix.main.manager;

import android.util.Log;

import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.event.AddProductToCartSuccessEvent;
import com.warrantix.main.common.event.AddRateSuccessEvent;
import com.warrantix.main.common.event.AddReferSuccessEvent;
import com.warrantix.main.common.event.AddRevealSuccessEvent;
import com.warrantix.main.common.event.AddReviewSuccessEvent;
import com.warrantix.main.common.event.BrandImgsSuccessEvent;
import com.warrantix.main.common.event.CartSuccessEvent;
import com.warrantix.main.common.event.ChatMessagesSuccessEvent;
import com.warrantix.main.common.event.ChatRoomSuccessEvent;
import com.warrantix.main.common.event.CitrusBillEvent;
import com.warrantix.main.common.event.DealersSuccessEvent;
import com.warrantix.main.common.event.DeleteOrderSuccessEvent;
import com.warrantix.main.common.event.MessagesSuccessEvent;
import com.warrantix.main.common.event.OrdersSuccessEvent;
import com.warrantix.main.common.event.ProductSuccessEvent;
import com.warrantix.main.common.event.ProductsBestSellingSuccessEvent;
import com.warrantix.main.common.event.ProductsPopularSuccessEvent;
import com.warrantix.main.common.event.ProductsSuccessEvent;
import com.warrantix.main.common.event.ProductsTopRateSuccessEvent;
import com.warrantix.main.common.event.RegisterProductSuccessEvent;
import com.warrantix.main.common.event.RelatedProductSuccessEvent;
import com.warrantix.main.common.event.RelatedProductsSuccessEvent;
import com.warrantix.main.common.event.SearchProductSuccessEvent;
import com.warrantix.main.common.event.ServiceCenterSuccessEvent;
import com.warrantix.main.common.event.ServiceCentersSuccessEvent;
import com.warrantix.main.common.event.ServiceCompaniesSuccessEvent;
import com.warrantix.main.common.event.SigninFailureEvent;
import com.warrantix.main.common.event.SigninSuccessEvent;
import com.warrantix.main.common.event.SignupFailureEvent;
import com.warrantix.main.common.event.SignupSuccessEvent;
import com.warrantix.main.common.event.UsedProductAppliancesSuccessEvent;
import com.warrantix.main.common.event.UsedProductElectronicsSuccessEvent;
import com.warrantix.main.common.event.UsedProductVehiclesSuccessEvent;
import com.warrantix.main.common.event.UsedProductsSuccessEvent;
import com.warrantix.main.common.event.UserAccountSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.LatestMessage;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.Rate;
import com.warrantix.main.common.rest.model.RateMessageContent;
import com.warrantix.main.common.rest.model.ReferMessageContent;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.model.RevealMessageContent;
import com.warrantix.main.common.rest.model.Review;
import com.warrantix.main.common.rest.model.ReviewMessageContent;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.model.Wallet;
import com.warrantix.main.common.rest.request.AddCartRequest;
import com.warrantix.main.common.rest.request.AddProductToCartRequest;
import com.warrantix.main.common.rest.request.ChatMessageRequest;
import com.warrantix.main.common.rest.request.RegisterProductRequest;
import com.warrantix.main.common.rest.request.SearchProductRequest;
import com.warrantix.main.common.rest.request.ServiceCentersRequest;
import com.warrantix.main.common.rest.response.AddProductToCartResponse;
import com.warrantix.main.common.rest.response.BrandImageUrlsResponse;
import com.warrantix.main.common.rest.response.CitrusBillResponse;
import com.warrantix.main.common.rest.response.CustomerLoginResponse;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.DeleteOrderResponse;
import com.warrantix.main.common.rest.response.DeviceResponse;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BackendManager {
    private static final String TAG = BackendManager.class.getSimpleName();

    private static BackendManager instance = null;

    public static BackendManager getInstance() {
        if (instance == null)
            instance = new BackendManager();

        return instance;
    }

    public BackendManager() {

        BusProvider.get().register(this);
    }

    // ---------------------------------------------------------------
    //
    // Asynchronous member functions
    //
    // ---------------------------------------------------------------

    //
    // synchronize all information in Customer model
    //
    private Customer synchronizeCustomer(String customerID) {

        // get customer information
        CustomerResponse response = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(customerID);
        if ((response == null) || (response.getCode() != 0))
            return null;

        // get child devices
        if (response.getDeviceID() != null) {
            for (int i = 0; i < response.getDeviceID().size(); i++) {
                String mainDeviceID = response.getDeviceID().get(i);
                DeviceResponse mainDeviceResponse = WarrantixWebService.getInstance().sendGetDeviceByIDRequest(mainDeviceID);
                if ((mainDeviceResponse == null) || (mainDeviceResponse.getCode() != 0)) {
                    continue;
                }

                response.addDevice(mainDeviceID, mainDeviceResponse);
            }
        }

        return response;
    }


    //
    // synchronize all information in Wallet Model
    //
    private Wallet synchronizeWallet(String walletID) {

        // get wallet information
        WalletResponse response = WarrantixWebService.getInstance().sendGetWalletByIDRequest(walletID);
        if ((response == null) || (response.getCode() != 0))
            return null;

        // if family members exists,
        ArrayList<String> houseHolds = (ArrayList<String>) response.getHousehold();
        if (houseHolds == null)
            return response;

        for (int i = 0; i < houseHolds.size(); i++) {
            String customerID = houseHolds.get(i);

            CustomerResponse familyCustomerRespone = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(customerID);
            if ((familyCustomerRespone == null) || (familyCustomerRespone.getCode() != 0)) {
                continue;
            }

            Customer familyCustomer = synchronizeCustomer(familyCustomerRespone.getId());
            if (familyCustomer == null)
                continue;

            response.addFamily(customerID, familyCustomer);
        }

        return response;
    }

    // update main customer
    public void updateMainCustomer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                if (WarrantixPreference.warrantixConfig.hasMainCustomer() != true)
//                    return;
//
//                //
//                // Get the main customer
//                //
//                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
//                CustomerResponse response = WarrantixWebService.getInstance().sendPutCustomerByIdRequest(mainCustomer.getId(), mainCustomer);
//                if (response == null) {
//                    UserAccountFailedEvent failedEvent = new UserAccountFailedEvent("Failed to update customer");
//                    BusProvider.get().post(failedEvent);
//                } else if (response.getCode() != 0) {
//                    UserAccountFailedEvent failedEvent = new UserAccountFailedEvent(response.getMessage());
//                    BusProvider.get().post(failedEvent);
//                } else {
//                    UserAccountSuccessEvent successEvent = new UserAccountSuccessEvent();
//                    BusProvider.get().post(successEvent);
//                }
                UserAccountSuccessEvent successEvent = new UserAccountSuccessEvent();
                BusProvider.get().post(successEvent);
            }
        }).start();
    }

    // synchronize DB
    public void synchronizeDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (WarrantixPreference.warrantixConfig.hasMainCustomer() != true)
                    return;

                //
                // Get the main customer
                //
                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
                mainCustomer = BackendManager.getInstance().synchronizeCustomer(mainCustomer.getId());
                if (mainCustomer == null)
                    return;

                WarrantixPreference.warrantixConfig.setMainCustomer(mainCustomer);

                //
                // Get the main Wallet ID from main customer
                //
                Wallet mainWallet = WarrantixPreference.warrantixConfig.getMainWallet();
                mainWallet = BackendManager.getInstance().synchronizeWallet(mainWallet.getId());
                if (mainWallet == null)
                    return;

                WarrantixPreference.warrantixConfig.setMainWallet(mainWallet);
                WarrantixPreference.writeConfig();
            }
        }).start();
    }


    // customer signup
    public void customerSignup(final String customerName, final String mobileNumber, final String email, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //
                // register new customer
                //
                CustomerResponse registeredCustomer = WarrantixWebService.getInstance().sendCustomerRegistrationRequest(customerName, email, password, mobileNumber);
                if (registeredCustomer == null) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent("Failed to add new customer");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (registeredCustomer.getCode() != 0) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent(registeredCustomer.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                // Save main customer into preference
                registeredCustomer.setPassword(password); // since password field is omitted from backend
                WarrantixPreference.warrantixConfig.setMainCustomer(registeredCustomer);
                WarrantixPreference.writeConfig();

                //
                // call sign in
                //
                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
                CustomerLoginResponse customerResponse = WarrantixWebService.getInstance().sendCustomerLoginRequest(mainCustomer.getUsername(), mainCustomer.getPassword());

                if (customerResponse == null) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent("Failed to login with new customer");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (customerResponse.getCode() != 0) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent(customerResponse.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                //
                // register new wallet
                //
                String customerId = WarrantixPreference.warrantixConfig.getMainCustomer().getId();
                WalletResponse walletResponse = WarrantixWebService.getInstance().sendWalletRegistrationRequest(customerId);
                if (walletResponse == null) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent("Failed to create wallet for customer");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (walletResponse.getCode() != 0) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent(walletResponse.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                WarrantixPreference.warrantixConfig.setMainWallet(walletResponse);
                WarrantixPreference.writeConfig();

                //
                // register new device
                //
                String mobileNumber = WarrantixPreference.warrantixConfig.getMainCustomer().getContact().getTel();
                DeviceResponse deviceResponse = WarrantixWebService.getInstance().sendDeviceRegistrationRequest(mobileNumber, customerId);
                if (deviceResponse == null) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent("Failed to create device for customer");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (deviceResponse.getCode() != 0) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent(deviceResponse.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                WarrantixPreference.warrantixConfig.setMainDevice(deviceResponse);
                WarrantixPreference.writeConfig();

                //
                // update customer on remote side
                //
                mainCustomer.setWalletID(walletResponse.getId());
                CustomerResponse customerResponse1 = WarrantixWebService.getInstance().sendPutCustomerByIdRequest(mainCustomer.getId(), mainCustomer);
                if (customerResponse1 == null) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent("Failed to add new customer");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (customerResponse1.getCode() != 0) {
                    SignupFailureEvent failureEvent = new SignupFailureEvent(registeredCustomer.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                // Save main customer into preference
                customerResponse1.setPassword(password); // since password field is omitted from backend
                WarrantixPreference.warrantixConfig.setMainCustomer(customerResponse1);
                WarrantixPreference.writeConfig();

                BackendManager.getInstance().synchronizeDB();

                // Fire success event
                SignupSuccessEvent successEvent = new SignupSuccessEvent();
                BusProvider.get().post(successEvent);

            }
        }).start();
    }

    public void customerSignin(final String userName, final String userPassword) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // call /customers/login
                CustomerLoginResponse customerResponse = WarrantixWebService.getInstance().sendCustomerLoginRequest(userName, userPassword);
                if (customerResponse == null) {
                    SigninFailureEvent failureEvent = new SigninFailureEvent("Failed to login");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (customerResponse.getCode() != 0) {
                    String message = customerResponse.getMessage();
                    SigninFailureEvent failureEvent = new SigninFailureEvent(message);
                    BusProvider.get().post(failureEvent);
                    return;
                }

                // call /customer/{id}
                CustomerResponse customer = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(customerResponse.getCustomerID());
                if (customer == null) {
                    SigninFailureEvent failureEvent = new SigninFailureEvent("Failed to get customer info");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (customer.getCode() != 0) {
                    SigninFailureEvent failureEvent = new SigninFailureEvent(customer.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                // Save main customer into preference
                customer.setPassword(userPassword); // since password field is omitted from backend
                WarrantixPreference.warrantixConfig.setMainCustomer(customer);
                WarrantixPreference.writeConfig();

                // call /wallet/{id}
                WalletResponse walletResponse = WarrantixWebService.getInstance().sendGetWalletByIDRequest(customerResponse.getWalletID());
                if (walletResponse == null) {
                    SigninFailureEvent failureEvent = new SigninFailureEvent("Failed to get customer's wallet");
                    BusProvider.get().post(failureEvent);
                    return;
                } else if (walletResponse.getCode() != 0) {
                    SigninFailureEvent failureEvent = new SigninFailureEvent(walletResponse.getMessage());
                    BusProvider.get().post(failureEvent);
                    return;
                }

                WarrantixPreference.warrantixConfig.setMainWallet(walletResponse);
                WarrantixPreference.writeConfig();

                // get devices

                BackendManager.getInstance().synchronizeDB();


                // Fire success event
                SigninSuccessEvent successEvent = new SigninSuccessEvent();
                BusProvider.get().post(successEvent);
            }
        }).start();
    }

    public void registerGCMToken(final String gcmToken) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String deviceId = WarrantixPreference.warrantixConfig.getMainDevice().getId();
                DeviceResponse deviceResponse = WarrantixWebService.getInstance().sendGCMTokenRegistrationRequest(deviceId, gcmToken);

                if (deviceResponse == null) {
                    Log.v(TAG, "Register GCM token is failed");
                } else if (deviceResponse.getCode() != 0) {
                    Log.v(TAG, "Register GCM token is failed");
                } else {
                    WarrantixPreference.warrantixConfig.setMainDevice(deviceResponse);
                }
            }
        }).start();
    }

    // synchronize database from backend
    public void synchronize() {

    }

    public void addFamilyCustomer(final String familyName, final String familyNumber, final String familyEmail) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                // register new customer
                //
                CustomerResponse registeredCustomer = WarrantixWebService.getInstance().sendCustomerRegistrationRequest(familyName, familyName, "qqqqqqqq", familyNumber);
                if (registeredCustomer == null) {
                    WarrantixApplication.getInstance().showMessage("Registration", "Failed to add family customer");
                    return;
                } else if (registeredCustomer.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Registration", registeredCustomer.getMessage());
                    return;
                }

                //
                // add new customer
                //
                Wallet mainWallet = WarrantixPreference.warrantixConfig.getMainWallet();
                if (mainWallet != null && mainWallet.getId() != null)
                    WarrantixWebService.getInstance().sendAddFamilyToWalletRequest(mainWallet.getId(), registeredCustomer);

            }
        }).start();
    }

    public void addDeviceToCustomer(final String mobileNumber, final Customer familyCustomer) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //
                // register new device
                //
                DeviceResponse newDevice = WarrantixWebService.getInstance().sendDeviceRegistrationRequest(mobileNumber, familyCustomer.getId());
                if (newDevice == null) {
                    WarrantixApplication.getInstance().showMessage("Device", "Failed to add device to customer");
                    return;
                } else if (newDevice.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Device", newDevice.getMessage());
                    return;
                }

                //
                // add new device to customer
                //

                List<String> deviceIDs = familyCustomer.getDeviceID();
                if (deviceIDs == null)
                    deviceIDs = new ArrayList<String>();

                deviceIDs.add(newDevice.getId());

                familyCustomer.setDeviceID(deviceIDs);
                familyCustomer.addDevice(newDevice.getId(), newDevice);

                CustomerResponse customerResponse = WarrantixWebService.getInstance().sendPutCustomerByIdRequest(familyCustomer.getId(), familyCustomer);
                if (customerResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Device", "Failed to update customer with new device");
                    return;
                } else if (customerResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Device", customerResponse.getMessage());
                    return;
                }
            }
        }).start();

    }


    // -----------------------------------------------------------------------------------------------
    //
    // event handlers
    //
    // -----------------------------------------------------------------------------------------------

//    @Subscribe
//    public void onAddFamilyCustomerSuccessEvent(AddCustomerSuccessEvent event) {
//        String walletID = event.getMainWalletID();
//        Customer familyCustomer = event.getFamilyCustomer();
//
//        WarrantixPreference.warrantixConfig.addFamilyCustomer(familyCustomer);
//        WarrantixPreference.writeConfig();
//    }

//    @Subscribe
//    public void onAddFamilycustomerFailedEvent(AddCustomerFailedEvent event) {
//        String message = event.getErrorMessage();
//        WarrantixApplication.getInstance().showMessage(message);
//    }

//    @Subscribe
//    public void onAddDeviceToFamilySuccessEvent(AddDeviceToFamilySuccessEvent event) {
//        Customer familyCustomer = event.getFamilyCustomer();
//        WarrantixPreference.warrantixConfig.updateFamilyCustomer(familyCustomer);
//        WarrantixPreference.writeConfig();
//    }

//    @Subscribe
//    public void onAddDeviceToFamilyFailedEvent(AddDeviceToFamilyFailedEvent event) {
//
//    }


    //----------------------------------------------------------------------------------------------------

    //
    // CRUD screens Handlers
    //


    //
    // message backend management module
    //

    public void getMessagesResponse(final String type) {

        final String acType = type;

        new Thread(new Runnable() {
            @Override
            public void run() {
                PullMessageResponse pullMessageResponse = WarrantixWebService.getInstance().getMessages(acType);
                if (pullMessageResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Message", "Failed to get the products");
                } else if (pullMessageResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Message", pullMessageResponse.getMessage());
                } else {

                    // get the customers and products with _ID.
                    List<Product> products = new ArrayList<>();
                    List<CustomerResponse> customerResponses = new ArrayList<>();
                    List<Message> messages = pullMessageResponse.getMessages();

                    if (messages != null) {
                        if (acType.equals("reveal") || acType.equals("refer")) {
                            for (int i = 0; i < messages.size(); i++) {
                                if (acType.equals("reveal")) {
                                    RevealMessageContent content = parseFromJsonToRevealContent(messages.get(i).getContent());
                                    Product product = WarrantixWebService.getInstance().getProduct(content.getProductID(), null).getProduct();
                                    CustomerResponse customerResponse = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(content.getCustomerID());
                                    products.add(product);
                                    customerResponses.add(customerResponse);
                                } else {
                                    ReferMessageContent content = parseFromJsonToReferContent(messages.get(i).getContent());
                                    Product product = WarrantixWebService.getInstance().getProduct(content.getProductID(), null).getProduct();
                                    CustomerResponse customerResponse = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(content.getCustomerID());
                                    products.add(product);
                                    customerResponses.add(customerResponse);
                                }
                            }

                            MessagesSuccessEvent successEvent = new MessagesSuccessEvent(pullMessageResponse, products, customerResponses);
                            BusProvider.get().post(successEvent);

                        } else if (acType.equals("chat")) {

                            List<LatestMessage> latestMessages = generateLatestmessages(messages);

                            for (int i = 0; i < latestMessages.size(); i++) {
                                CustomerResponse customerResponse = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(latestMessages.get(i).getFrom().getId());
                                customerResponses.add(customerResponse);
                            }

                            ChatRoomSuccessEvent successEvent = new ChatRoomSuccessEvent(latestMessages, customerResponses);
                            BusProvider.get().post(successEvent);
                        }
                    }


                }
            }
        }).start();
    }


    // chat room methods

    public List<LatestMessage> generateLatestmessages(List<Message> recentMessages) {

        List<LatestMessage> latestMessages = new ArrayList<>();

        if (recentMessages != null) {

            LatestMessage firstLatestMessage = createLatestMessage(recentMessages.get(0), 0);

            latestMessages.add(firstLatestMessage);
            for (int i = 0; i < recentMessages.size(); i++) {

                int number = 0;

                for (int j = 0; j < latestMessages.size(); j++) {
                    if (recentMessages.get(i).getFrom().getId().equals(latestMessages.get(j).getFrom().getId())) {
                        latestMessages.get(j).setUnreadNumber(latestMessages.get(j).getUnreadNumber() + 1);
                        number++;
                    }
                }

                if (number == 0) {
                    number = 1;
                    LatestMessage nextLastestMessage = createLatestMessage(recentMessages.get(i), number);
                    latestMessages.add(nextLastestMessage);
                }
            }
        }

        return latestMessages;
    }

    public LatestMessage createLatestMessage(Message recentMessage, int unreadNumber) {

        LatestMessage latestMessage = new LatestMessage();
        latestMessage.setId(recentMessage.getId());
        latestMessage.setContent(recentMessage.getContent());
        latestMessage.setUpdatedAt(recentMessage.getUpdatedAt());
        latestMessage.setCreatedAt(recentMessage.getCreatedAt());
        latestMessage.setFrom(recentMessage.getFrom());
        latestMessage.setTo(recentMessage.getTo());
        latestMessage.setType(recentMessage.getType());
        latestMessage.setUnreadNumber(unreadNumber);

        return latestMessage;
    }


    public void getChatMessagesResponse(ChatMessageRequest chatMessageRequest) {

        final ChatMessageRequest acChatMessageRequest = chatMessageRequest;

        new Thread(new Runnable() {
            @Override
            public void run() {
                PullMessageResponse pullMessageResponse = WarrantixWebService.getInstance().getChatMessages(acChatMessageRequest);
                if (pullMessageResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Message", "Failed to get the products");
                } else if (pullMessageResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Message", pullMessageResponse.getMessage());
                } else {

                    // get the customers and products with _ID.
                    List<CustomerResponse> customerResponses = new ArrayList<>();
                    List<Message> messages = pullMessageResponse.getMessages();

                    if (messages != null) {
                        ChatMessagesSuccessEvent successEvent = new ChatMessagesSuccessEvent(pullMessageResponse, customerResponses);
                        BusProvider.get().post(successEvent);
                    }
                }
            }
        }).start();
    }


    public RevealMessageContent parseFromJsonToRevealContent(String content) {
        RevealMessageContent revealMessageContent = new RevealMessageContent();

        if (content != null) {
            try {
                JSONObject json = new JSONObject(content);

                String productID = json.getString("productID");
                String customerID = json.getString("customerID");
                String brandID = json.getString("brandID");
                String type = json.getString("type");

                revealMessageContent.setCustomerID(customerID);
                revealMessageContent.setProductID(productID);
                revealMessageContent.setBrandID(brandID);
                revealMessageContent.setType(type);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Couldn't get any data from the url");
        }

        return revealMessageContent;
    }

    public ReferMessageContent parseFromJsonToReferContent(String content) {
        ReferMessageContent referMessageContent = new ReferMessageContent();

        if (content != null) {
            try {
                JSONObject json = new JSONObject(content);

                String productID = json.getString("productID");
                String customerID = json.getString("customerID");
                String brandID = json.getString("brandID");
                String type = json.getString("type");
                String description = json.getString("content");

                referMessageContent.setCustomerID(customerID);
                referMessageContent.setProductID(productID);
                referMessageContent.setBrandID(brandID);
                referMessageContent.setType(type);
                referMessageContent.setContent(description);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Couldn't get any data from the url");
        }

        return referMessageContent;
    }

    public void addReveal(RevealMessageContent revealMessageContent) {

        final RevealMessageContent acRevealMessageContent = revealMessageContent;
        new Thread(new Runnable() {
            @Override
            public void run() {
                RevealAddResponse revealAddResponse = WarrantixWebService.getInstance().addReveal(acRevealMessageContent);
                if (revealAddResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Message", "Failed to search product");
                } else if (revealAddResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Message", revealAddResponse.getMessage());
                } else {
                    AddRevealSuccessEvent successEvent = new AddRevealSuccessEvent(revealAddResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }


    public void addRefer(ReferMessageContent referMessageContent) {

        final ReferMessageContent acReferMessageContent = referMessageContent;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReferAddResponse referAddResponse = WarrantixWebService.getInstance().addRefer(acReferMessageContent);
                if (referAddResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Message", "Failed to search product");
                } else if (referAddResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Message", referAddResponse.getMessage());
                } else {
                    AddReferSuccessEvent successEvent = new AddReferSuccessEvent(referAddResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    //
    // prduct backend management module
    //

    public void getProductsResponse(String brandID) {

        final String acBrandID = brandID;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(acBrandID);
                if (getProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
                } else if (getProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
                } else {
                    ProductsSuccessEvent successEvent = new ProductsSuccessEvent(getProductsResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();

    }

    public void getProductsResponse(String brandID, final String category) {

        final String acBrandID = brandID;
        final String acCategory = category;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(acBrandID, acCategory);
                if (getProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
                } else if (getProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
                } else {
                    ProductsSuccessEvent successEvent = new ProductsSuccessEvent(getProductsResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

//    public void getProductsResponse(String brandID, final Boolean onSale, final Boolean popular) {
//
//        final String acBrandID = brandID;
//        final Boolean acOnSale = onSale;
//        final Boolean acPopular = popular;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(acBrandID, acOnSale, acPopular);
//                if (getProductsResponse == null) {
//                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
//                } else if (getProductsResponse.getCode() != 0) {
//                    WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
//                } else {
//                    if (acOnSale && acPopular) {
//                        ProductsBestSellingSuccessEvent successEvent = new ProductsBestSellingSuccessEvent(getProductsResponse);
//                        BusProvider.get().post(successEvent);
//                    } else if (!acOnSale) {
//                        ProductsPopularSuccessEvent successEvent = new ProductsPopularSuccessEvent(getProductsResponse);
//                        BusProvider.get().post(successEvent);
//                    } else if (!acPopular) {
//                        ProductsTopRateSuccessEvent successEvent = new ProductsTopRateSuccessEvent(getProductsResponse);
//                        BusProvider.get().post(successEvent);
//                    }
//
//                }
//            }
//        }).start();
//    }

    public void getPopularProductsResponse(String brandID, final Boolean onSale, final Boolean popular) {

        final String acBrandID = brandID;
        final Boolean acOnSale = onSale;
        final Boolean acPopular = popular;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(acBrandID, acOnSale, acPopular);
                if (getProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
                } else if (getProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
                } else {
                    ProductsPopularSuccessEvent successEvent = new ProductsPopularSuccessEvent(getProductsResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    public void getBestSellingProductsResponse(String brandID, final Boolean onSale, final Boolean popular) {

        final String acBrandID = brandID;
        final Boolean acOnSale = onSale;
        final Boolean acPopular = popular;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(acBrandID, acOnSale, acPopular);
                if (getProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
                } else if (getProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
                } else {
                    ProductsBestSellingSuccessEvent successEvent = new ProductsBestSellingSuccessEvent(getProductsResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    public void getTopRateProductsResponse(String brandID, final Boolean onSale, final Boolean popular) {

        final String acBrandID = brandID;
        final Boolean acOnSale = onSale;
        final Boolean acPopular = popular;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(acBrandID, acOnSale, acPopular);
                if (getProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
                } else if (getProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
                } else {
                    ProductsTopRateSuccessEvent successEvent = new ProductsTopRateSuccessEvent(getProductsResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }


    public void getProductResponse(String productID) {

        final String acProductID = productID;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetProductResponse getProductResponse = WarrantixWebService.getInstance().getProduct(acProductID, null);
                if (getProductResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to get the product");
                } else if (getProductResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Product", getProductResponse.getMessage());
                } else {

                    List<Review> reviews = new ArrayList<>();
                    List<Rate> rates = new ArrayList<>();

                    reviews = getProductResponse.getProduct().getReviews();
                    List<Customer> customersByReview = new ArrayList<>();

                    for (int i = 0; i < reviews.size(); i++) {
                        Customer customer = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(reviews.get(i).getCustomerID());
                        customersByReview.add(customer);
                    }
                    List<Customer> customersByRate = new ArrayList<>();
                    rates = getProductResponse.getProduct().getRate();
                    for (int i = 0; i < rates.size(); i++) {
                        Customer customer = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(rates.get(i).getCustomerID());
                        customersByRate.add(customer);
                    }

                    ProductSuccessEvent successEvent = new ProductSuccessEvent(getProductResponse, customersByReview, customersByRate);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();

    }

    public void searchProduct(SearchProductRequest searchProductRequest) {

        final SearchProductRequest acSearchProductRequest = searchProductRequest;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SearchProductResponse searchProductResponse = WarrantixWebService.getInstance().searchProductResponse(acSearchProductRequest);
                if (searchProductResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to search product");
                } else if (searchProductResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Product", searchProductResponse.getMessage());
                } else {
                    SearchProductSuccessEvent successEvent = new SearchProductSuccessEvent(searchProductResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    public void registerProduct(final RegisterProductRequest registerProductRequest) {

        final RegisterProductRequest acRegisterProductRequest = registerProductRequest;
        new Thread(new Runnable() {
            @Override
            public void run() {
                RegisterProductResponse registerProductResponse = WarrantixWebService.getInstance().registerProductResponse(acRegisterProductRequest);
                if (registerProductResponse.getCode() == 204) {
                    RegisterProductSuccessEvent successEvent = new RegisterProductSuccessEvent(registerProductResponse);
                    BusProvider.get().post(successEvent);
                } else {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to register product");
                }

            }
        }).start();
    }


    public void getUsedProducts(String brandID, final String category) {

        final String acBrandID = brandID;
        final String acCategory = category;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetUsedProductsResponse getUsedProductsResponse = WarrantixWebService.getInstance().getUsedProducts(acBrandID, acCategory);
                if (getUsedProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("UsedProduct", "Failed to get the used products");
                } else if (getUsedProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("UsedProduct", getUsedProductsResponse.getMessage());
                } else {

                    List<Product> products = new ArrayList<>();

                    List<UsedProduct> usedProducts = getUsedProductsResponse.getUsedProducts();
                    for (int i = 0; i < usedProducts.size(); i++) {
                        GetProductResponse getProductResponse = WarrantixWebService.getInstance().getProduct(usedProducts.get(i).getProductID(), category);
                        products.add(getProductResponse.getProduct());
                    }

                    if (category == null) {
                        UsedProductsSuccessEvent successEvent = new UsedProductsSuccessEvent(getUsedProductsResponse, products);
                        BusProvider.get().post(successEvent);
                    } else {
                        if (category.equals("electronics")) {
                            UsedProductElectronicsSuccessEvent successEvent = new UsedProductElectronicsSuccessEvent(getUsedProductsResponse, products);
                            BusProvider.get().post(successEvent);
                        } else if (category.equals("appliances")) {
                            UsedProductAppliancesSuccessEvent successEvent = new UsedProductAppliancesSuccessEvent(getUsedProductsResponse, products);
                            BusProvider.get().post(successEvent);
                        } else if (category.equals("vehicles")) {
                            UsedProductVehiclesSuccessEvent successEvent = new UsedProductVehiclesSuccessEvent(getUsedProductsResponse, products);
                            BusProvider.get().post(successEvent);
                        }
                    }
                }
            }
        }).start();

    }

    public void getRelatedProducts(String type, String brandID) {

        final String acBrandID = brandID;
        final String acType = type;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductsResponse getRelatedProductsResponse = WarrantixWebService.getInstance().getRelatedProducts(acType, acBrandID);
                if (getRelatedProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", "Failed to get the related products");
                } else if (getRelatedProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", getRelatedProductsResponse.getMessage());
                } else {

                    List<Product> products = new ArrayList<>();

                    List<RelatedProduct> relatedProducts = getRelatedProductsResponse.getRelatedProducts();
                    for (int i = 0; i < relatedProducts.size(); i++) {
                        GetProductResponse getProductResponse = WarrantixWebService.getInstance().getProduct(relatedProducts.get(i).getProductID(), null);
                        products.add(getProductResponse.getProduct());
                    }

                    RelatedProductsSuccessEvent successEvent = new RelatedProductsSuccessEvent(getRelatedProductsResponse, products);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    public void getRelatedProductResponse(String _Id) {

        final String ac_Id = _Id;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductResponse getRelatedProductResponse = WarrantixWebService.getInstance().getRelatedProduct(ac_Id);
                if (getRelatedProductResponse == null) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", "Failed to get the relatedproduct");
                } else if (getRelatedProductResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", getRelatedProductResponse.getMessage());
                } else {

                    RelatedProductSuccessEvent successEvent = new RelatedProductSuccessEvent(getRelatedProductResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }


    public void addReview(ReviewMessageContent reviewMessageContent, String productID) {

        final ReviewMessageContent acReviewMessageContent = reviewMessageContent;
        final String acProductID = productID;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReviewAddResponse reviewAddResponse = WarrantixWebService.getInstance().addReview(acReviewMessageContent, acProductID);
                if (reviewAddResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to search product");
                } else if (reviewAddResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Product", reviewAddResponse.getMessage());
                } else {
                    AddReviewSuccessEvent successEvent = new AddReviewSuccessEvent(reviewAddResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    public void addRate(RateMessageContent rateMessageContent, String productID) {

        final RateMessageContent acRatemessageContent = rateMessageContent;
        final String acProductID = productID;
        new Thread(new Runnable() {
            @Override
            public void run() {
                RateAddResponse rateAddResponse = WarrantixWebService.getInstance().addReview(acRatemessageContent, acProductID);
                if (rateAddResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Product", "Failed to search product");
                } else if (rateAddResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Product", rateAddResponse.getMessage());
                } else {
                    AddRateSuccessEvent successEvent = new AddRateSuccessEvent(rateAddResponse);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }


    //
    // dealer backend management module
    //


    public void getDealersResponse() {

        DebugLog.e("getDealersResponse()--");
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetDealersResponse getDealersResponse = WarrantixWebService.getInstance().getDealersResponse();
                if (getDealersResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Dealer", "Failed to get the dealers");
                } else if (getDealersResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Dealer", getDealersResponse.getMessage());
                } else {
                    DebugLog.e("getDealersResponse()--successEvent--");
                    DealersSuccessEvent successEvent = new DealersSuccessEvent(getDealersResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }

    //
    // service backend management module
    //

    public GetServicesResponse getServicesResponse(String type, String brandID) {

        final String acBrandID = brandID;
        final String acType = type;

        GetServicesResponse getServicesResponse = WarrantixWebService.getInstance().getServices(acType, acBrandID);
        if (getServicesResponse == null) {
            WarrantixApplication.getInstance().showMessage("Service", "Failed to get the services");
        } else if (getServicesResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("Service", getServicesResponse.getMessage());
        }

        return getServicesResponse;
    }

    public void getServiceCompaniesResponse(String type, String brandID) {

        final String acBrandID = brandID;
        final String acType = type;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetServiceCompaniesResponse getServiceCompaniesResponse = WarrantixWebService.getInstance().getServiceCompanies(acType, acBrandID);
                if (getServiceCompaniesResponse == null) {
                    WarrantixApplication.getInstance().showMessage("ServiceCompany", "Failed to get the service companies");
                } else if (getServiceCompaniesResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("ServiceCompany", getServiceCompaniesResponse.getMessage());
                } else {
                    ServiceCompaniesSuccessEvent successEvent = new ServiceCompaniesSuccessEvent(getServiceCompaniesResponse);
                    BusProvider.get().post(successEvent);
                }
                ;
            }
        }).start();
    }


    public void getServiceCentersResponse(ServiceCentersRequest serviceCentersRequest) {

        final ServiceCentersRequest acServiceCentersRequest = serviceCentersRequest;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetServiceCentersResponse getServiceCentersResponse = WarrantixWebService.getInstance().getServiceCenters(acServiceCentersRequest);
                if (getServiceCentersResponse == null) {
                    WarrantixApplication.getInstance().showMessage("ServiceCompany", "Failed to get the service companies");
                } else if (getServiceCentersResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("ServiceCompany", getServiceCentersResponse.getMessage());
                } else {
                    ServiceCentersSuccessEvent successEvent = new ServiceCentersSuccessEvent(getServiceCentersResponse);
                    BusProvider.get().post(successEvent);
                }
                ;
            }
        }).start();
    }

    public void getServiceCenter(String serviceCenterID) {

        final String acServiceCenterID = serviceCenterID;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetServiceCenterResponse getServiceCenterResponse = WarrantixWebService.getInstance().GetServiceCetnerByID(acServiceCenterID);
                if (getServiceCenterResponse == null) {
                    WarrantixApplication.getInstance().showMessage("ServiceCompany", "Failed to get the service companies");
                } else if (getServiceCenterResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("ServiceCompany", getServiceCenterResponse.getMessage());
                } else {
                    ServiceCenterSuccessEvent successEvent = new ServiceCenterSuccessEvent(getServiceCenterResponse);
                    BusProvider.get().post(successEvent);
                }
                ;
            }
        }).start();
    }

    //
    // order backend management module
    //

    public void getOrdersResponse() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetOrdersResponse getOrdersResponse = WarrantixWebService.getInstance().getOrdersResponse();
                if (getOrdersResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Order", "Failed to get the orders");
                } else if (getOrdersResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Order", getOrdersResponse.getMessage());
                } else {

                    List<Product> products = new ArrayList<>();
                    List<CustomerResponse> customers = new ArrayList<>();

                    List<Order> orders = getOrdersResponse.getOrders();
                    for (int i = 0; i < orders.size(); i++) {
                        GetProductResponse getProductResponse = WarrantixWebService.getInstance().getProduct(orders.get(i).getProductID(), null);
                        products.add(getProductResponse.getProduct());

                        CustomerResponse customerResponse = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(orders.get(i).getCustomerID());
                        customers.add(customerResponse);
                    }

                    OrdersSuccessEvent successEvent = new OrdersSuccessEvent(getOrdersResponse, products, customers);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();

    }

    public void getOrdersResponse(String cartId) {

        final String acCartID = cartId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetOrdersResponse getOrdersResponse = WarrantixWebService.getInstance().getOrdersResponse(acCartID);
                if (getOrdersResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Order", "Failed to get the orders");
                } else if (getOrdersResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Order", getOrdersResponse.getMessage());
                } else {

                    List<Product> products = new ArrayList<>();

                    List<Order> orders = getOrdersResponse.getOrders();
                    for (int i = 0; i < orders.size(); i++) {
                        GetProductResponse getProductResponse = WarrantixWebService.getInstance().getProduct(orders.get(i).getProductID(), null);
                        products.add(getProductResponse.getProduct());
                    }


                    OrdersSuccessEvent successEvent = new OrdersSuccessEvent(getOrdersResponse, products, null);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();

    }

    public void deleteOrder(String _id) {
        final String acID = _id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                DeleteOrderResponse deleteOrderResponse = WarrantixWebService.getInstance().deleteOrderResponse(acID);
                if (deleteOrderResponse.getCode() == 204) {
                    DeleteOrderSuccessEvent successEvent = new DeleteOrderSuccessEvent(deleteOrderResponse);
                    BusProvider.get().post(successEvent);
                } else {
                    WarrantixApplication.getInstance().showMessage("Order", "Failed to delete order");
                }

            }
        }).start();

    }

    //
    // cart backend management module
    //

    public void getCartResponse(String _Id) {

        final String ac_Id = _Id;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetCartResponse getCartResponse = WarrantixWebService.getInstance().getCartResponse(ac_Id);
                if (getCartResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Cart", "Failed to get the relatedproduct");
                } else if (getCartResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Cart", getCartResponse.getMessage());
                } else {

                    CartSuccessEvent successEvent = new CartSuccessEvent(getCartResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }


    //
    // add a product into cart
    //

    public void addProductToCart(AddProductToCartRequest addProductToCartRequest) {

        final AddProductToCartRequest acAddProductToCartRequest = addProductToCartRequest;

        new Thread(new Runnable() {
            @Override
            public void run() {
                AddProductToCartResponse addProductToCartResponse = WarrantixWebService.getInstance().addProductToCart(acAddProductToCartRequest);
                if (addProductToCartResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Cart", "Failed to get the relatedproduct");
                } else if (addProductToCartResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Cart", addProductToCartResponse.getMessage());
                } else {

                    AddProductToCartSuccessEvent successEvent = new AddProductToCartSuccessEvent(addProductToCartResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }

    //
    // brand images backend management module
    //

    public void getBrandImageUrls(String type) {

        final String acType = type;

        new Thread(new Runnable() {
            @Override
            public void run() {
                BrandImageUrlsResponse brandImageUrlsResponse = WarrantixWebService.getInstance().getBrandImageUrls(acType);
                if (brandImageUrlsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Cart", "Failed to get the relatedproduct");
                } else if (brandImageUrlsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Cart", brandImageUrlsResponse.getMessage());
                } else {

                    BrandImgsSuccessEvent successEvent = new BrandImgsSuccessEvent(brandImageUrlsResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }


    public void getBrandImageUrlsBySearchword(String name) {

        final String acName = name;

        new Thread(new Runnable() {
            @Override
            public void run() {
                BrandImageUrlsResponse brandImageUrlsResponse = WarrantixWebService.getInstance().getBrandImageUrlsBySearchword(acName);
                if (brandImageUrlsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Cart", "Failed to get the relatedproduct");
                } else if (brandImageUrlsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Cart", brandImageUrlsResponse.getMessage());
                } else {

                    BrandImgsSuccessEvent successEvent = new BrandImgsSuccessEvent(brandImageUrlsResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }

    // ---------------------------------------------------------------
    //
    // Synchronous member functions
    //
    // ---------------------------------------------------------------

    public String getContactFromProduct(Product product) {
        if (product == null)
            return "";

        return "";
    }

    //
    // add Cart
    //

    public void addCart(final AddCartRequest addCartRequest) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                CitrusBillResponse citrusBillResponse = WarrantixWebService.getInstance().sendAddCartRequest(addCartRequest);

                if (citrusBillResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Cart", citrusBillResponse.getMessage());
                } else {

                    CitrusBillEvent citrusBillEvent = new CitrusBillEvent(citrusBillResponse);
                    BusProvider.get().post(citrusBillEvent);
                }

            }
        }).start();
    }

}

