package com.warrantix.main.common.rest;

import com.warrantix.main.BuildConfig;

public class Api {
    private final static String MOCKSERVER = "https://wtx-hub-mock-dev.warrantix.net/v1/";
    public static final String SERVER;
    static {
        SERVER = BuildConfig.ENDPOINT;
    }
}
