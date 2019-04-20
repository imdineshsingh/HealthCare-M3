package com.dinesh_singh.healthcare;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dinesh_singh.healthcare.databinding.ActivitySelectLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SelectLocation extends FragmentActivity implements OnMapReadyCallback {

    private Context mContext;
    private TextView mTitle;
    private TextView mSkip;
    private ImageView mGoBack;
    private static final String TAG = "TAG";
    ActivitySelectLocationBinding getBinding;

    private GoogleMap mMap;
    private Geocoder mGeocoder;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final int INTERNET_PERMISSION_REQUEST_CODE=100;
    //var
    private Boolean mLocationPermissionGranted = false;
    private static final int DEFAULT_ZOOM = 10;

    private static final int REQUEST_CODE=100;
    private String address,city,state,country,postalCode,knownName;
    //private static final String ADDRESS,CITY,COUNTRY,POSTALCODE,KNOWNNAME;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
       getBinding=DataBindingUtil.setContentView(this,R.layout.activity_select_location);

       setToolbar();
       listeners();
       getPermissions();
       initializeMap();
    }

    //setToolbar() STARTED
    private void setToolbar() {
        mTitle=findViewById(R.id.title);
        mTitle.setText("Select Location");
        mSkip=findViewById(R.id.skip);
        mSkip.setVisibility(View.INVISIBLE);

        mGoBack=findViewById(R.id.back_button);
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                finish();
            }
        });
    }

    //LISTENERS() STARTED
    private void listeners() {
        //ADDING INTENTS & PASSING DATA TO PREVIOUS ACTIVITY(i.e. WORKADDRESS ACTIVITY)
        getBinding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent=new Intent(SelectLocation.this,WorkAddressActivity.class);
                intent.putExtra("address",address);
                intent.putExtra("city",city);
                intent.putExtra("country",country);
                intent.putExtra("zip",postalCode);

                startActivityForResult(intent,REQUEST_CODE);
                finish();

            }
        });

        getBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit( String query ) {
                getLocation();
                return false;
            }
            @Override
            public boolean onQueryTextChange( String newText ) {
                return false;
            }
        });
    }

    @Override
    public void onMapReady( GoogleMap googleMap ) {
        mMap = googleMap;
    }
    private void initializeMap() {
        Log.d(TAG, "initializeMap: initialized map");
        if (mMap == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            assert mapFragment != null;
            mapFragment.getMapAsync(this);

           // getDeviceLocation();

        }
    }

    private void getLocation() {
        mGeocoder = new Geocoder(this);
        String location = (String) getBinding.searchView.getQuery().toString();
        List<Address> list = null;
        try {
            list = mGeocoder.getFromLocationName(location, 1);
            double lat = list.get(0).getLatitude();
            double lon = list.get(0).getLongitude();
            goToLocation(lat, lon, DEFAULT_ZOOM);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list == null) {
            Toast.makeText(this, getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private void goToLocation( double lat, double lon, int zoom ) {
        LatLng latLng = new LatLng(lat, lon);
        String location = getBinding.searchView.getQuery().toString();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.addMarker(new MarkerOptions().position(latLng)).setTitle(location);
        mMap.moveCamera(cameraUpdate);
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting device current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionGranted) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete( @NonNull Task task ) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found Location");
                            Location currentLocation = (Location) task.getResult();
                            if (currentLocation != null) {
                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
                                Geocoder geocoder;
                                List<Address> addresses;
                                geocoder = new Geocoder(SelectLocation.this, Locale.getDefault());
                                try {
                                    addresses = geocoder.getFromLocation(currentLocation.getLatitude(),currentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                    city = addresses.get(0).getLocality();
                                    state = addresses.get(0).getAdminArea();
                                    country = addresses.get(0).getCountryName();
                                    postalCode = addresses.get(0).getPostalCode();
                                    knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                                    getBinding.tvCurrentLocation.setText(address+","+city+""+state+""+country+""+postalCode+""+knownName);

                                    /*String adminArea=addresses.get(0).getAdminArea();
                                    String featureName=addresses.get(0).getFeatureName();
                                    String premises=addresses.get(0).getPremises();
                                    String subAdminArea=addresses.get(0).getSubAdminArea();
                                    String subLocality=addresses.get(0).getSubLocality();


                                    getBinding.tvCurrentLocation.setText("adminArea:"+adminArea+"\nfeature" +
                                            featureName+"premises"+premises+"subAdminArea"+subAdminArea+"subLocality"+subLocality);
*/

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Log.d(TAG, "onComplete: couldn;t find the location");
                            Toast.makeText(mContext, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: sercurityException" + e.getMessage());
        }
    }
    private void moveCamera( LatLng latLng ) {
        Log.d(TAG, "moveCamera: lat: " + latLng.latitude + ", long: " + latLng.longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, SelectLocation.DEFAULT_ZOOM);
        mMap.addMarker(new MarkerOptions().position(latLng)).setTitle("you are here");
        mMap.moveCamera(cameraUpdate);
        // mMap.setMyLocationEnabled(true);
    }
    private void getPermissions() {
        Log.d(TAG, "getPermissions: getting location permissio");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                //initializeMap();
                getDeviceLocation();
            }

        }
        else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);





    }
}
