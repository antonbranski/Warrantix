package com.warrantix.main.common.pref;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Device;
import com.warrantix.main.common.rest.model.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class WarrantixConfig {

    @SerializedName("maincusteomer")
    @Expose
    private Customer mainCustomer;

    @SerializedName("mainwallet")
    @Expose
    private Wallet mainWallet;

    @SerializedName("maindevice")
    @Expose
    private Device mainDevice;

    public boolean hasMainCustomer() {
        if (mainCustomer == null)
            return false;

        return true;
    }
    public void setMainCustomer(Customer mainCustomer) {
        this.mainCustomer = mainCustomer;
    }
    public Customer getMainCustomer() {
        return this.mainCustomer;
    }

    public boolean hasMainWallet() {
        if (mainWallet == null)
            return false;

        return true;
    }
    public void setMainWallet(Wallet mainWallet) {
        this.mainWallet = mainWallet;
    }
    public Wallet getMainWallet() {
        return this.mainWallet;
    }

    public boolean hasMainDevice() {
        if (mainDevice == null)
            return false;

        return true;
    }
    public void setMainDevice(Device newDevice) {
        this.mainDevice = newDevice;
    }
    public Device getMainDevice() {
        return this.mainDevice;
    }

    public ArrayList<Customer> getFamilyCustomers() {

        if (mainWallet == null)
            return null;

        if (mainWallet.getFamilyMembers() == null)
            return null;

        ArrayList<Customer> familyCustomers = new ArrayList<>();
        for (String key : mainWallet.getFamilyMembers().keySet()) {
            Customer family = mainWallet.getFamilyMembers().get(key);
//            if (family.getId() == mainCustomer.getId())
//                continue;

            familyCustomers.add(family);
        }

        return familyCustomers;
    }
}