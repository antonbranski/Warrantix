package com.warrantix.main.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.warrantix.main.R;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.utils.Constant;
import com.warrantix.main.common.utils.DebugLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 *
 */
public class WalletBrandFinance1 extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private Intent mIntent;
    private Button nextBtn;


    private TextView selectCompanyTXT;
    private TextView selectBrandTXT;
    private TextView selectModelTXT;
    private TextView selectPriceTXT;
    private TextView selectLocationTXT;
    private TextView selectCityTXT;
    private TextView selectPincodeTXT;

    private ListView brandListView;
    private ListView configurationListView;
    private ListView locationListView;

    ImageButton btnDrop1;
    ImageButton btnDrop2;
    ImageButton btnDrop3;

    private EditText otherLocationEdit;
    private String title;

    //    boolean gps_enabled = false;
//    boolean network_enabled = false;
//
//    private LocationManager locationManager;
//    private Location myLocation;
//    private int LOCATIONSETTINGCODE = 101;
    private Context mContext;
    private String latitude = "", longitude = "";

    private LocationManager locationManager;
    private LocationListener locationListener;
    private GoogleApiClient mGoogleApiClient;
    private PendingResult<LocationSettingsResult> result;

    public static final int REQUEST_LOCATION = 20016;


    private MarshMallowPermission marshMallowPermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance1);

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

        mContext = WalletBrandFinance1.this;
        marshMallowPermission = new MarshMallowPermission(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                DebugLog.e("onLocationChanged()");
                latitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());
                getCityName();
                mGoogleApiClient.disconnect();
                DebugLog.e("Latitude:" + latitude + ", longitude:" + longitude);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        // Register the listener with the Location Manager to receive location updates
        if (marshMallowPermission.checkPermissionForFineLocation()) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            if (mGoogleApiClient.isConnected()) {
                locationSettingDialog();
            }
        } else {
            marshMallowPermission.requestPermissionsForFineLocationNew();
        }
        title = getIntent().getStringExtra("title");
        TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);

        nextBtn = (Button) findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandFinance1.this, WalletBrandFinance2.class);
                mIntent.putExtra("title", title);
                startActivityForResult(mIntent, Constant.backCode, true);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        selectCompanyTXT = (TextView) findViewById(R.id.selectCompanyTXT);
        selectBrandTXT = (TextView) findViewById(R.id.selectBrandTXT);
        selectModelTXT = (TextView) findViewById(R.id.selectModelTXT);
        selectPriceTXT = (TextView) findViewById(R.id.selectPriceTXT);
        selectLocationTXT = (TextView) findViewById(R.id.selectLocationTXT);
        selectCityTXT = (TextView) findViewById(R.id.selectCityTXT);
        selectPincodeTXT = (TextView) findViewById(R.id.selectPincodeTXT);

        selectCompanyTXT.setText(getIntent().getStringExtra("brand"));
        selectModelTXT.setText(getIntent().getStringExtra("model"));

        brandListView = (ListView) findViewById(R.id.brandListView);
        configurationListView = (ListView) findViewById(R.id.configurationListView);
        locationListView = (ListView) findViewById(R.id.locationListView);

//        initByLocationInfo();

//        String stateName = addresses.get(0).getAddressLine(1);
//        String countryName = addresses.get(0).getAddressLine(2);

//        btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
//        btnDrop1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (brandListView.getVisibility() == View.GONE) {
//                    hideAllListView();
//
//                    brandListView.setVisibility(View.VISIBLE);
//                    btnDrop1.setBackgroundResource(R.drawable.check_up);
//                }
//                else {
//                    brandListView.setVisibility(View.GONE);
//                    btnDrop1.setBackgroundResource(R.drawable.check);
//                }
//            }
//        });
//
//        btnDrop2 = (ImageButton) findViewById(R.id.dropbutton2);
//        btnDrop2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (configurationListView.getVisibility() == View.GONE) {
//                    hideAllListView();
//
//                    configurationListView.setVisibility(View.VISIBLE);
//                    btnDrop2.setBackgroundResource(R.drawable.check_up);
//                }
//                else {
//                    configurationListView.setVisibility(View.GONE);
//                    btnDrop2.setBackgroundResource(R.drawable.check);
//                }
//            }
//        });

//        btnDrop3 = (ImageButton) findViewById(R.id.dropbutton3);
//        btnDrop3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (locationListView.getVisibility() == View.GONE) {
//                    hideAllListView();
//
//                    locationListView.setVisibility(View.VISIBLE);
//                    btnDrop3.setBackgroundResource(R.drawable.check_up);
//                }
//                else {
//                    locationListView.setVisibility(View.GONE);
//                    btnDrop3.setBackgroundResource(R.drawable.check);
//                }
//            }
//        });

//        String brands[] = {"brand 1", "brand 2", "brand 3"};
//        TokenListAdapter productAdapter = new TokenListAdapter(WalletBrandFinance1.this, brands);
//        selectBrandTXT.setTokenListView(brandListView, productAdapter);
//
//        String configurations[] = {"configuration 1", "configuration 2", "configuration 3"};
//        TokenListAdapter configurationAdapter = new TokenListAdapter(WalletBrandFinance1.this, configurations);
//        selectModelTXT.setTokenListView(configurationListView, configurationAdapter);

//        String locations[] = {"location 1", "location 2", "location 3"};
//        TokenListAdapter locationAdapter = new TokenListAdapter(WalletBrandFinance1.this, locations);
//        selectLocationTXT.setTokenListView(locationListView, locationAdapter);
    }

    private void hideAllListView() {
//        locationListView.setVisibility(View.GONE);
//        configurationListView.setVisibility(View.GONE);
//        brandListView.setVisibility(View.GONE);

//        btnDrop1.setBackgroundResource(R.drawable.check);
//        btnDrop2.setBackgroundResource(R.drawable.check);
//        btnDrop3.setBackgroundResource(R.drawable.check);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.backCode && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish(true);
        }
//        if (requestCode == LOCATIONSETTINGCODE){
//            initByLocationInfo();
//        }

        else if (requestCode == REQUEST_LOCATION) {
            switch (resultCode) {
                case Activity.RESULT_OK: {
                    DebugLog.e("RESULT_OK --");
                    break;
                }
                case Activity.RESULT_CANCELED: {
                    DebugLog.e("RESULT_CANCELED --");
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

//    private void initByLocationInfo(){
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//
//            MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
//            marshMallowPermission.requestPermissionForGPS();
//        }
//
//        // check and location manager setting
//        try {
//            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch(Exception ex) {}
//
//        if (!gps_enabled) {
//
//            Toast.makeText(WalletBrandFinance1.this, "Please enable your location service!", Toast.LENGTH_LONG).show();
//
//            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            this.startActivityForResult(myIntent, LOCATIONSETTINGCODE);
//        } else {
//
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1,  this);
//
//            getMyLocation();
//
//        }
//    }

    private void getCityName() {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = new ArrayList<>();
        try {
            if (!latitude.isEmpty() && !longitude.isEmpty()) {
                addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
            }
            String cityName = addresses.get(0).getAddressLine(0);
//            selectLocationTXT.setText(cityName);
        } catch (IOException e) {

        }


    }

//    public Location getMyLocation() {
//        if (myLocation == null) {
//            List<String> providers = locationManager.getProviders(true);
//            Location bestLocation = null;
//            for (String provider : providers) {
//
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                }
//
//                Location l = locationManager.getLastKnownLocation(provider);
//                if (l == null) {
//                    continue;
//                }
//                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
//                    bestLocation = l;
//                }
//            }
//            myLocation = bestLocation;
//            if (myLocation != null) {
//                getCityName();
//            }
//
//        }
//        return myLocation;
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        myLocation = location;
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
////        Toast.makeText(this, "Please enable GPS location service", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        startActivity(intent, true);
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case MarshMallowPermission.GPS_FINE_LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    DebugLog.e("Permission granted");

                    if (marshMallowPermission.checkPermissionForFineLocation()) {

                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                        if (mGoogleApiClient.isConnected()) {
                            locationSettingDialog();
                        }
                    } else
                        marshMallowPermission.requestPermissionsForFineLocationNew();
                } else {
                    DebugLog.e("Permission denied");
                }
                break;
        }
    }


    private void locationSettingDialog() {
        DebugLog.e("locationSettingDialog()--");
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        DebugLog.e("onResult () -- SUCCESS");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        DebugLog.e("onResult () -- RESOLUTION_REQUIRED");
                        try {
                            status.startResolutionForResult((Activity) mContext, REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        DebugLog.e("onResult () -- SETTINGS_CHANGE_UNAVAILABLE");
                        break;
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        DebugLog.e("onConnected()--");

        // Register the listener with the Location Manager to receive location updates
        if (marshMallowPermission.checkPermissionForFineLocation()) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            if (mGoogleApiClient.isConnected()) {
                locationSettingDialog();
            }
        } else {
            marshMallowPermission.requestPermissionsForFineLocationNew();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

        DebugLog.e("onConnectionSuspended()--");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        DebugLog.e("onConnectionFailed()--");
    }
}
