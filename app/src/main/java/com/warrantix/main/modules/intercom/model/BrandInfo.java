package com.warrantix.main.modules.intercom.model;

import android.os.Messenger;

import com.warrantix.main.modules.intercom.model.BrandTypeData;

public class BrandInfo {
    private Messenger messenger;
    private BrandTypeData data;

    public BrandInfo(Messenger messenger, BrandTypeData data) {
        this.messenger = messenger; this.data = data;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }
    public Messenger getMessenger() {
        return this.messenger;
    }

    public void setBrandTypeData(BrandTypeData data) {
        this.data = data;
    }
    public BrandTypeData getBrandTypeData() {
        return this.data;
    }
}
