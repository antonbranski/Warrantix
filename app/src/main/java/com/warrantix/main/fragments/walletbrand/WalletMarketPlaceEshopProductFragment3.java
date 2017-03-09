package com.warrantix.main.fragments.walletbrand;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.citrus.sdk.Callback;
import com.citrus.sdk.CitrusClient;
import com.citrus.sdk.CitrusUser;
import com.citrus.sdk.Environment;
import com.citrus.sdk.TransactionResponse;
import com.citrus.sdk.classes.Amount;
import com.citrus.sdk.classes.CitrusException;
import com.citrus.sdk.classes.Month;
import com.citrus.sdk.classes.Year;
import com.citrus.sdk.payment.CreditCardOption;
import com.citrus.sdk.payment.DebitCardOption;
import com.citrus.sdk.payment.MerchantPaymentOption;
import com.citrus.sdk.payment.NetbankingOption;
import com.citrus.sdk.payment.PaymentType;
import com.citrus.sdk.response.CitrusError;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.PaymentManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletMarketPlaceEshopProductFragment3 extends BaseFragment {

    @BindView(R.id.comformplay)
    TextView comformplay;
    @BindView(R.id.accept_select)
    ImageView acceptSelect;
    @BindView(R.id.acceptTXT)
    TextView acceptTXT;
    @BindView(R.id.doneBTN)
    Button doneBTN;
    @BindView(R.id.layout_youtube)
    LinearLayout layoutYoutube;

    private Context mContext;
    private Intent mIntent;
    private boolean isAccepted = false;
    private CitrusClient citrusClient;

    //SandBox
    private String BILL_URL = "https://salty-plateau-1529.herokuapp.com/billGenerator.sandbox.php";
    private String CARDNAME = "Test";
    private String CARDNO = "4111111111111111"; //VISA
    private String USERNAME = "ks.reddy@warrantix.com";
    private String USERMOBILE = "9898089931";
    private String BANKNAME = "ICICI Bank";
    private String BANKCODE = "CID001";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_brand_product3, container, false);
        InitializeView();
        ButterKnife.bind(this, mView);
        return mView;
    }


    public void InitializeView() {
        this.mContext = getActivity();
        citrusClient = CitrusClient.getInstance(mContext); //pass Activity Context
        citrusClient.init(PaymentManager.CITRUS_SIGNUP_ID, PaymentManager.CITRUS_SIGNUP_SECRET, PaymentManager.CITRUS_SIGNIN_ID, PaymentManager.CITRUS_SIGNIN_SECRET, PaymentManager.CITRUS_VANITY, Environment.SANDBOX);
        citrusClient.enableLog(true); // (Make sure you are enabling logs before citrusClient.init() method.)
        //First Parameter – SignUp Key
        //Second Parameter – SignUp Secret
        //Third Parameter – SignIn Key
        //Fourth Parameter - SignIn Secret
        //Fifth Parameter -   Vanity
        //Sixth Parameter -   Environment
//        payByCreditCard();
    }


    @OnClick({R.id.acceptTXT, R.id.accept_select, R.id.doneBTN})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.acceptTXT:
                WarrantixApplication.getInstance().openPDFFiles(getActivity(), "manual.pdf");
                break;

            case R.id.accept_select:
                isAccepted = !isAccepted;
                acceptSelect.setBackgroundResource(isAccepted ? R.drawable.square_tick : R.drawable.square);
                break;


            case R.id.doneBTN:

//                String fromWhere = ((WalletMarketplaceEshopProducts) mContext).fromWhichScreen();
//                if ((fromWhere != null) && (fromWhere.equalsIgnoreCase("ShoppingCart"))) {
//                    // Shopping Cart
//                    if ((mContext != null) && (mContext instanceof BaseActivity)) {
//                        ((BaseActivity) mContext).finish(true);
//                    } else {
//                        ((Activity) mContext).finish();
//                    }
//                } else {
//                    // SubCategory
//                    Intent intent = new Intent(mContext, WalletMarketplaceSubCategory.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    if ((mContext != null) && (mContext instanceof BaseActivity)) {
//                        ((BaseActivity) mContext).startActivity(intent, true, true);
//                    } else {
//                        mContext.startActivity(intent);
//                    }
//                }
//                payByCreditCard();
                if (isAccepted == true)
                    getActivity().finish();
                    // payByCreditCard();
                else {
                    MessageDialog dialog = new MessageDialog(getActivity());
                    dialog.setTitle("Info");
                    dialog.setMessage("Please accept the terms and conditions.");
                    dialog.show();
                }
                break;
        }
    }

    private void payByDebitCard() {
        CitrusClient citrusClient = CitrusClient.getInstance(getActivity());
        DebitCardOption debitCardOption = new DebitCardOption(CARDNAME, CARDNO, "123", Month.getMonth("12"), Year.getYear("18"));
        Amount amount = new Amount("5");

        // Init PaymentType
        PaymentType.PGPayment pgPayment = null;
        try {
            pgPayment = new PaymentType.PGPayment(amount, BILL_URL, debitCardOption, new CitrusUser(USERNAME, USERMOBILE));
        } catch (CitrusException e) {
            e.printStackTrace();
        }

        citrusClient.simpliPay(pgPayment, new Callback<TransactionResponse>() {
            @Override
            public void success(TransactionResponse transactionResponse) {
                Log.e("payByDebitCard()-", "success()");
            }

            @Override
            public void error(CitrusError error) {
                Log.e("payByDebitCard()-", "error()-" + error.getMessage());
            }
        });

    }

    private void payByCreditCard() {

        Log.e("payByCreditCard()-", "success()");
        Toast.makeText(mContext, "Payment Success", Toast.LENGTH_SHORT).show();
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).finish(true);
        else
            getActivity().finish();

        //
        // Just For Hero mockup app, temporary commented; in future this will be used
        //
//        CitrusClient citrusClient = CitrusClient.getInstance(getActivity());
//        CreditCardOption creditCardOption = new CreditCardOption(CARDNAME, CARDNO, "123", Month.getMonth("12"), Year.getYear("18"));
//        Amount amount = new Amount("5");
//
//        // Init PaymentType
//        PaymentType.PGPayment pgPayment = null;
//        try {
//            pgPayment = new PaymentType.PGPayment(amount, BILL_URL, creditCardOption, new CitrusUser("ks.reddy@warrantix.com", "9898089931"));
//        } catch (CitrusException e) {
//            e.printStackTrace();
//        }
//
//        citrusClient.simpliPay(pgPayment, new Callback<TransactionResponse>() {
//            @Override
//            public void success(TransactionResponse transactionResponse) {
//                Log.e("payByCreditCard()-", "success()");
//                Toast.makeText(mContext, "Payment Success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void error(CitrusError error) {
//                Log.e("payByCreditCard()-", "error()-" + error.getMessage());
//                Toast.makeText(mContext, "Payment Fail", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private void payUsingNetBanking() {
        CitrusClient citrusClient = CitrusClient.getInstance(getActivity());


        NetbankingOption netbankingOption = new NetbankingOption(BANKNAME, BANKCODE);

        Amount amount = new Amount("5");
        PaymentType.PGPayment pgPayment = null;
        try {
            pgPayment = new PaymentType.PGPayment(amount, BILL_URL, netbankingOption, new CitrusUser(USERNAME, USERMOBILE));
        } catch (CitrusException e) {
            e.printStackTrace();
        }

        citrusClient.simpliPay(pgPayment, new Callback<TransactionResponse>() {
            @Override
            public void success(TransactionResponse transactionResponse) {
                Log.e("payUsingNetBanking()-", "success()");
            }

            @Override
            public void error(CitrusError error) {
                Log.e("payUsingNetBanking()-", "error()-" + error.getMessage());
            }
        });
    }

    private void getBanksForNetBanking() {

        CitrusClient.getInstance(getActivity()).getMerchantPaymentOptions(new Callback<MerchantPaymentOption>() {
            @Override
            public void success(MerchantPaymentOption merchantPaymentOption) {
                ArrayList<NetbankingOption> mNetbankingOptions = merchantPaymentOption.getNetbankingOptionList();
                Log.e("getBanks()-", "success()-" + mNetbankingOptions.size());

                for (NetbankingOption netbankingOption : mNetbankingOptions) {
                    Log.e("Bank Name -", "->" + netbankingOption.getBankName());
                    Log.e("Bank ID -", "->" + netbankingOption.getBankCID());

                }
            }

            @Override
            public void error(CitrusError error) {
                Log.e("getBanks()-", "error()-" + error.getMessage());
            }
        });


    }
}
