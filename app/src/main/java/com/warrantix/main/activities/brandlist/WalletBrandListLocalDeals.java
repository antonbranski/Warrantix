package com.warrantix.main.activities.brandlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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
import com.squareup.otto.Subscribe;
import com.warrantix.framework.common.bus.BusProvider;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandListLocalDealList;
import com.warrantix.main.common.event.DealersSuccessEvent;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.response.GetDealersResponse;
import com.warrantix.main.common.utils.DebugLog;
import com.warrantix.main.manager.BackendManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListLocalDeals extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private final static String TAG = WalletBrandListLocalDeals.class.getSimpleName();

    @BindView(R.id.brand_arrow)
    ImageButton brandArrow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list)
    ListView list;

    private String latitude = "", longitude = "";

    private LocationManager locationManager;
    private LocationListener locationListener;
    private GoogleApiClient mGoogleApiClient;
    private PendingResult<LocationSettingsResult> result;

    public static final int REQUEST_LOCATION = 20014;
    private Context mContext;

    private MarshMallowPermission marshMallowPermission;
    private List<Dealer> dealers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_localdeals);
        ButterKnife.bind(this);
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

        mContext = WalletBrandListLocalDeals.this;
        BusProvider.get().register(this);
        marshMallowPermission = new MarshMallowPermission(this);
        brandArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

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
                getSetEventDealData();
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

        BackendManager.getInstance().getDealersResponse();

//        initByLocationInfo();
    }

//    private void initByLocationInfo() {
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
////                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////
////            MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
////            marshMallowPermission.requestPermissionForGPS();
////            marshMallowPermission.requestPermissionForGPS1();
////        }
//
//        // check and location manager setting
//        try {
//            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception ex) {
//        }
//
//        if (!gps_enabled) {
//
//            Toast.makeText(WalletBrandListLocalDeals.this, "Please enable your location service!", Toast.LENGTH_LONG).show();
//
//            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            this.startActivityForResult(myIntent, LOCATIONSETTINGCODE);
//        } else {
//
//            if (marshMallowPermission.checkPermissionForFineLocation()) {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
//                getMyLocation();
//            } else {
//                marshMallowPermission.requestPermissionsForFineLocationNew();
//            }
//
//        }
//    }

    @Subscribe
    public void onDealersSuccessEvent(final DealersSuccessEvent event) {

        DebugLog.e("onOrdersSuccessEvent -- ");
        DebugLog.e("onOrdersSuccessEvent --Response -- " + event.getDealersResponse());

        WalletBrandListLocalDeals.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                if (getDealersResponse != null) {
//                    List<Dealer> dealers = getDealersResponse.getDealers();
//                    List<String> distances = new ArrayList<>();
//                    for (int i = 0; i < dealers.size(); i++) {
//                        Dealer dealer = dealers.get(i);
//                        if (dealer.getContact() != null) {
//                            float distance = -1;
//                            try {
//                                distance = calculateDistanceWithGPS(dealers.get(i).getContact().getLatitude(), dealers.get(i).getContact().getLongitude());
//                            } catch (Exception e) {
//                                Log.d(TAG, "dealer(" + i + ") is NOT valid");
//                            }
//
//                            if (distance == -1) {
//                                distances.add(null);
//                            } else {
//
//                                DecimalFormat format = new DecimalFormat("0.00");
//                                String strDistance = format.format(distance);
//
//                                distances.add(strDistance);
//                            }
//                        }
//                    }
//
//                    WalletBrandListLocalDealList adapter = new WalletBrandListLocalDealList(WalletBrandListLocalDeals.this, dealers, distances);
//                    list.setAdapter(adapter);
//                }

                GetDealersResponse getDealersResponse = event.getDealersResponse();
                dealers = getDealersResponse.getDealers();
                if (!dealers.isEmpty()) {
                    List<String> distances = new ArrayList<>();

                    DecimalFormat format = new DecimalFormat("0.00");
                    for (int i = 0; i < dealers.size(); i++) {
                        if (isLocationAvailable()) {
                            Dealer dealer = dealers.get(i);
                            if (dealer.getContact() != null) {
                                distances.add(format.format(calculateDistanceWithGPS(dealers.get(i).getContact().getLatitude(), dealers.get(i).getContact().getLongitude())));
                            }
                        } else {
                            //Default
                            distances.add("N/A");
                        }
                    }

                    WalletBrandListLocalDealList adapter = new WalletBrandListLocalDealList(WalletBrandListLocalDeals.this, dealers, distances);
                    list.setAdapter(adapter);
                }
//                getSetEventDealData();
            }
        });
    }


//    public Location getMyLocation() {
//        if (myLocation == null) {
//            List<String> providers = locationManager.getProviders(true);
//            Location bestLocation = null;
//            for (String provider : providers) {
//
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                    MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
//                    marshMallowPermission.requestPermissionForGPS();
//                    marshMallowPermission.requestPermissionForGPS1();
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
//
//            if (myLocation != null) {
//
//
//            }
//        }
//        return myLocation;
//    }


    private float calculateDistanceWithGPS(double lat, double lng) {

        Location locationA = new Location("point A");
        Location locationB = new Location("point B");

        if (!latitude.isEmpty() && !longitude.isEmpty()) {
            locationA.setLatitude(Double.parseDouble(latitude));
            locationA.setLongitude(Double.parseDouble(longitude));

            locationB.setLatitude(lat);
            locationB.setLongitude(lng);
            return locationA.distanceTo(locationB);
        } else {
            return 0;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case MarshMallowPermission.GPS_FINE_LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (marshMallowPermission.checkPermissionForFineLocation())
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    else
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

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult()--", Integer.toString(resultCode));

        switch (requestCode) {
            case REQUEST_LOCATION:
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
                break;
        }
    }

    private boolean isLocationAvailable() {
        return !latitude.isEmpty() && !longitude.isEmpty();
    }

    private void getSetEventDealData() {

        if (!dealers.isEmpty()) {
            List<String> distances = new ArrayList<>();

            DecimalFormat format = new DecimalFormat("0.00");
            for (int i = 0; i < dealers.size(); i++) {
                if (isLocationAvailable()) {
                    Dealer dealer = dealers.get(i);
                    if (dealer.getContact() != null) {
                        distances.add(format.format(calculateDistanceWithGPS(dealers.get(i).getContact().getLatitude(), dealers.get(i).getContact().getLongitude())));
                    }
                } else {
                    //Default
                    distances.add("N/A");
                }
            }

            WalletBrandListLocalDealList adapter = new WalletBrandListLocalDealList(WalletBrandListLocalDeals.this, dealers, distances);
            list.setAdapter(adapter);
        }
    }
}
