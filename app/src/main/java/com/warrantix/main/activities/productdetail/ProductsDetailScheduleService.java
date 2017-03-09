package com.warrantix.main.activities.productdetail;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.common.date.DateUtil;
import com.warrantix.main.common.event.ServiceCenterSuccessEvent;
import com.warrantix.main.common.event.ServiceCentersSuccessEvent;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.ServiceCenter;
import com.warrantix.main.common.rest.request.ServiceCentersRequest;
import com.warrantix.main.common.rest.response.GetServiceCenterResponse;
import com.warrantix.main.common.rest.response.GetServiceCentersResponse;
import com.warrantix.main.common.utils.DebugLog;
import com.warrantix.main.customview.MultilineTextTouchListener;
import com.warrantix.main.customview.TokenTextView;
import com.warrantix.main.manager.BackendManager;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class ProductsDetailScheduleService extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private TextView titleTXT;
    private TextView FIXES_NEEDEDTXT;

    private TokenTextView serviceTokenView;
    private TokenTextView txtTOSCInput;

    private EditText date_styleTXT;
    private EditText hour_styleTXT;
    private TokenTextView Fixes_1TXT;
    private TokenTextView Fixes_2TXT;
    private TokenTextView Fixes_3TXT;
    private EditText messageTXT;
    private View bottomSpaceView;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private SimpleDateFormat hourFormatter = new SimpleDateFormat("hh:mm", Locale.US);


    private ListView listTOSC = null;
    private ListView tosListView = null;
    private ListView fix1ListView = null;
    private ListView fix2ListView = null;
    private ListView fix3ListView = null;

    ImageButton btnTOSC;
    ImageButton btnDrop1;
    ImageButton btnDrop2;
    ImageButton btnDrop3;
    ImageButton btnDrop4;

    private String tos[];

    private List<ServiceCenter> mServiceCenters = new ArrayList<>();
    private ServiceCenter mServiceCenter = new ServiceCenter();

    private Product myProduct = null;

    private ArrayList<String> mechanicalFixes = new ArrayList<>();
    private ArrayList<String> engineFixes = new ArrayList<>();
    private ArrayList<String> eletronicalFixes = new ArrayList<>();

    private String tosc[] = {"Service Center 1", "Service Center 2", "Service Center 3"};

    TokenListAdapter tosAdapter;
    TokenListAdapter toscAdapter;
    private DatePickerDialog dialog;


//    boolean gps_enabled = false;
//    boolean network_enabled = false;
//
//    private LocationManager locationManager;
//    private Location myLocation;
//    private int LOCATIONSETTINGCODE = 101;


    private String latitude = "", longitude = "";

    private LocationManager locationManager;
    private LocationListener locationListener;
    private GoogleApiClient mGoogleApiClient;
    private PendingResult<LocationSettingsResult> result;

    public static final int REQUEST_LOCATION = 20015;
    private Context mContext;

    private MarshMallowPermission marshMallowPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products_detail_service);
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
        mContext = ProductsDetailScheduleService.this;
        String jsonString = getIntent().getStringExtra("product");
        myProduct = convertJSONtoProduct(jsonString);

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
                getServiceCenters();
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


        titleTXT = (TextView) findViewById(R.id.title);
        FIXES_NEEDEDTXT = (TextView) findViewById(R.id.FIXES_NEEDEDTXT);

        date_styleTXT = (EditText) findViewById(R.id.date_styleTXT);
        hour_styleTXT = (EditText) findViewById(R.id.hour_styleTXT);
        Fixes_1TXT = (TokenTextView) findViewById(R.id.Fixes_1TXT);
        Fixes_2TXT = (TokenTextView) findViewById(R.id.Fixes_2TXT);
        Fixes_3TXT = (TokenTextView) findViewById(R.id.Fixes_3TXT);
        messageTXT = (EditText) findViewById(R.id.messageTXT);
        messageTXT.setOnTouchListener(new MultilineTextTouchListener());
        bottomSpaceView = (View) findViewById(R.id.bottomSpaceView);

        if (myProduct != null) {
            mechanicalFixes = (ArrayList<String>) myProduct.getMechanicalFixes();
            engineFixes = (ArrayList<String>) myProduct.getEngineFixes();
            eletronicalFixes = (ArrayList<String>) myProduct.getElectricalFixes();

            Fixes_1TXT.setText(mechanicalFixes.get(0));
            Fixes_2TXT.setText(engineFixes.get(0));
            Fixes_3TXT.setText(eletronicalFixes.get(0));
        } else {
            createTempMockData();
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button btnDone = (Button) findViewById(R.id.doneBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish(true);
            }
        });

        date_styleTXT.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        date_styleTXT.setLongClickable(false);
        date_styleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                try {
                    newCalendar.setTime(dateFormatter.parse(date_styleTXT.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // if dialog is showed, ...
                if ((dialog != null) && (dialog.isShowing() == true))
                    return;

                dialog = new DatePickerDialog(ProductsDetailScheduleService.this, scheduleDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        hour_styleTXT.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        hour_styleTXT.setLongClickable(false);
        hour_styleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                try {
                    newCalendar.setTime(hourFormatter.parse(hour_styleTXT.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerDialog dialog = new TimePickerDialog(ProductsDetailScheduleService.this, scheduleTimePickerListener,
                        newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });


        txtTOSCInput = (TokenTextView) findViewById(R.id.txt_TOSCInput);
        btnTOSC = (ImageButton) findViewById(R.id.btn_TOSC);
        listTOSC = (ListView) findViewById(R.id.list_TOSC);

        toscAdapter = new TokenListAdapter(ProductsDetailScheduleService.this, tosc);
        txtTOSCInput.setTokenListView(listTOSC, toscAdapter, btnTOSC);

        btnTOSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listTOSC.getVisibility() == View.GONE) {
                    hideAllListView();

                    listTOSC.setVisibility(View.VISIBLE);
                    btnTOSC.setBackgroundResource(R.drawable.check_up);
                } else {
                    listTOSC.setVisibility(View.GONE);
                    btnTOSC.setBackgroundResource(R.drawable.check);
                }
            }
        });


        btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tosListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    tosListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                } else {
                    tosListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        serviceTokenView = (TokenTextView) findViewById(R.id.First_Free_ServiceTXT);
        tosListView = (ListView) findViewById(R.id.tosListView);


        btnDrop2 = (ImageButton) findViewById(R.id.dropbutton2);
        btnDrop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fix1ListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    fix1ListView.setVisibility(View.VISIBLE);
                    btnDrop2.setBackgroundResource(R.drawable.check_up);
                } else {
                    fix1ListView.setVisibility(View.GONE);
                    btnDrop2.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String[] mhFixes = new String[mechanicalFixes.size()];
        mhFixes = mechanicalFixes.toArray(mhFixes);

        TokenListAdapter fix1Adapter = new TokenListAdapter(ProductsDetailScheduleService.this, mhFixes);
        fix1ListView = (ListView) findViewById(R.id.fix1ListView);
        Fixes_1TXT.setTokenListView(fix1ListView, fix1Adapter, btnDrop2);

        btnDrop3 = (ImageButton) findViewById(R.id.dropbutton3);
        btnDrop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fix2ListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    fix2ListView.setVisibility(View.VISIBLE);
                    btnDrop3.setBackgroundResource(R.drawable.check_up);
                } else {
                    fix2ListView.setVisibility(View.GONE);
                    btnDrop3.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String[] enFixes = new String[engineFixes.size()];
        enFixes = engineFixes.toArray(enFixes);

        TokenListAdapter fix2Adapter = new TokenListAdapter(ProductsDetailScheduleService.this, enFixes);
        fix2ListView = (ListView) findViewById(R.id.fix2ListView);
        this.Fixes_2TXT.setTokenListView(fix2ListView, fix2Adapter, btnDrop3);

        btnDrop4 = (ImageButton) findViewById(R.id.dropbutton4);
        btnDrop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fix3ListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    fix3ListView.setVisibility(View.VISIBLE);
                    btnDrop4.setBackgroundResource(R.drawable.check_up);
                } else {
                    fix3ListView.setVisibility(View.GONE);
                    btnDrop4.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String[] elFixes = new String[eletronicalFixes.size()];
        elFixes = eletronicalFixes.toArray(elFixes);
        TokenListAdapter fix3Adapter = new TokenListAdapter(ProductsDetailScheduleService.this, elFixes);
        fix3ListView = (ListView) findViewById(R.id.fix3ListView);
        this.Fixes_3TXT.setTokenListView(fix3ListView, fix3Adapter, btnDrop4);

//        initByLocationInfo();


    }

    private void createTempMockData() {
        for (int i = 0; i < 3; i++) {
            mechanicalFixes.add(i, "mechanicalFix" + (i + 1));
            engineFixes.add(i, "engineFix" + (i + 1));
            eletronicalFixes.add(i, "electronicalFix" + (i + 1));

            Fixes_1TXT.setText(mechanicalFixes.get(0));
            Fixes_2TXT.setText(engineFixes.get(0));
            Fixes_3TXT.setText(eletronicalFixes.get(0));
        }
    }

    private DatePickerDialog.OnDateSetListener scheduleDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            date_styleTXT.setText(dateFormatter.format(newDate.getTime()));
        }
    };

    private TimePickerDialog.OnTimeSetListener scheduleTimePickerListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_styleTXT.setText("" + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
        }
    };

    private void hideAllListView() {
        fix3ListView.setVisibility(View.GONE);
        fix2ListView.setVisibility(View.GONE);
        fix1ListView.setVisibility(View.GONE);
        tosListView.setVisibility(View.GONE);
        listTOSC.setVisibility(View.GONE);


        btnTOSC.setBackgroundResource(R.drawable.check);
        btnDrop1.setBackgroundResource(R.drawable.check);
        btnDrop2.setBackgroundResource(R.drawable.check);
        btnDrop3.setBackgroundResource(R.drawable.check);
        btnDrop4.setBackgroundResource(R.drawable.check);
    }

    @Subscribe
    public void onServiceCentersSuccessEvent(final ServiceCentersSuccessEvent event) {
        ProductsDetailScheduleService.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetServiceCentersResponse getServiceCentersResponse = event.getServiceCentersResponse();
                if (getServiceCentersResponse != null) {
                    mServiceCenters = getServiceCentersResponse.getServiceCenters();
                    if (mServiceCenters.size() != 0) {

                        tos = new String[mServiceCenters.size()];

                        for (int i = 0; i < mServiceCenters.size(); i++) {
                            tos[i] = mServiceCenters.get(i).getName();

                        }


                        tosAdapter = new TokenListAdapter(ProductsDetailScheduleService.this, tos);
                        serviceTokenView.setTokenListView(tosListView, tosAdapter, btnDrop1);
                        serviceTokenView.setTokenUpdatedListener(new TokenTextView.OnTokenUpdatedListener() {
                            @Override
                            public void onTokenUpdated(String token) {
                                if (mServiceCenters != null) {
                                    for (int i = 0; i < mServiceCenters.size(); i++) {
                                        ServiceCenter center = mServiceCenters.get(i);
                                        if (center.getName().equalsIgnoreCase(token)) {
                                            BackendManager.getInstance().getServiceCenter(mServiceCenters.get(i).getId());
                                            break;
                                        }
                                    }
                                }
                            }
                        });
                        serviceTokenView.setText(tos[0]);
                        BackendManager.getInstance().getServiceCenter(mServiceCenters.get(0).getId());

                    }
                }
            }
        });
    }

    @Subscribe
    public void onServiceCenterSuccessEvent(final ServiceCenterSuccessEvent event) {
        ProductsDetailScheduleService.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetServiceCenterResponse getServiceCenterResponse = event.getServiceCenterResponse();
                if (getServiceCenterResponse != null) {
                    mServiceCenter = getServiceCenterResponse.getServiceCenter();
                    if (mServiceCenters != null) {

                        // split date and time
                        StringTokenizer tk = new StringTokenizer(mServiceCenter.getAvailability().get(0));
                        String date = tk.nextToken();  // <---  yyyy-mm-dd
                        String time = tk.nextToken();

                        date_styleTXT.setText(DateUtil.dateFarmatWithString(date));
                        hour_styleTXT.setText(time);
                    }
                }
            }
        });
    }

    private Product convertJSONtoProduct(String jsonString) {
        Type type = new TypeToken<Product>() {
        }.getType();
        return new Gson().fromJson(jsonString, type);
    }

//    @Override
//    public void onLocationChanged(Location location) {
//
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
//
//    }

    private class ActionModeCallbackInterceptor implements android.view.ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == LOCATIONSETTINGCODE) {
//            initByLocationInfo();
//        }
//    }

//    private void initByLocationInfo() {
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
//            marshMallowPermission.requestPermissionForGPS();
//        }
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
//            Toast.makeText(ProductsDetailScheduleService.this, "Please enable your location service!", Toast.LENGTH_LONG).show();
//
//            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            this.startActivityForResult(myIntent, LOCATIONSETTINGCODE);
//        } else {
//
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
//
//            getMyLocation();
//
//        }
//    }

    private void getServiceCenters() {

//        if (myLocation != null) {
//            ServiceCentersRequest serviceCentersRequest = new ServiceCentersRequest();
//            serviceCentersRequest.setLatitude(myLocation.getLatitude() + "");
//            serviceCentersRequest.setLongitude(myLocation.getLongitude() + "");
//
//            BackendManager.getInstance().getServiceCentersResponse(serviceCentersRequest);
//        }

        if (!latitude.isEmpty() && !longitude.isEmpty()) {
            ServiceCentersRequest serviceCentersRequest = new ServiceCentersRequest();
            serviceCentersRequest.setLatitude(latitude);
            serviceCentersRequest.setLongitude(longitude);

            BackendManager.getInstance().getServiceCentersResponse(serviceCentersRequest);
        }

    }

//    public Location getMyLocation() {
//        if (myLocation == null) {
//            List<String> providers = locationManager.getProviders(true);
//            Location bestLocation = null;
//            for (String provider : providers) {
//
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
//                getServiceCenters();
//            }
//
//        }
//        return myLocation;
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
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                latitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());
                getServiceCenters();
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

}
