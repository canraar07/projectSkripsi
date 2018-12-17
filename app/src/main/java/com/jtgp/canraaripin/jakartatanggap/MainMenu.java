package com.jtgp.canraaripin.jakartatanggap;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jtgp.canraaripin.jakartatanggap.response.ItemsAllHospital;
import com.jtgp.canraaripin.jakartatanggap.response.ResponseAllHospital;
import com.jtgp.canraaripin.jakartatanggap.util.api.Baseservice;
import com.jtgp.canraaripin.jakartatanggap.util.api.UtilAPI;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainMenu extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,GoogleMap.OnCameraMoveListener {
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Marker mCurrLocationMarker;
    private Location mLastLocation;
    private int PLACE_PICKER_REQUEST = 1;
    private Button buttonClick,buttonSendlock;
    Geocoder geocoder;
    String adress,  Id, codeZip;
    Double Latitude, Longtitiude;
    private static final String KEY_ID = "id";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_CODEZIP = "codezip";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONG_TITUDE = "longtitude";
    TextView textSet,textSetDetail;
    LinearLayout linearLayout1;
    ProgressBar loading;
    ObjectAnimator mAnim;
    Marker marker;
    Baseservice mApiservice;
    Context mContext;
    Circle mCircle;
    float[] distance = new float[2];
    ProgressDialog ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mApiservice = UtilAPI.getAPIService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        SupportMapFragment mapFragment1 = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment1.getMapAsync(this);
        //loading = (ProgressBar) findViewById(R.id.progressBarSbmt);
        buttonClick = (Button) findViewById(R.id.button2);
        textSet = (TextView) findViewById(R.id.textView4);
        textSetDetail = (TextView) findViewById(R.id.textDetailPhonePmd);
        linearLayout1 = (LinearLayout) findViewById(R.id.layoutChoicePmd);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MainMenu.this),PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        //=============================
        buttonSendlock = (Button) findViewById(R.id.buttonSendLoc);
        buttonSendlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocationInsident();
            }
        });

    }



    @Override
   /* public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.3092293, -122.1136845))
                .title("Apple"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4233438, -122.0728817), 10));
    }*/

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //Toast.makeText(getApplicationContext(),"Hallo test",Toast.LENGTH_LONG).show();
        geocoder = new Geocoder(this, Locale.getDefault());
        //Memulai Google Play Services  FusedLocationProviderClient
        getDataPemadam();
        FusedLocationProviderClient mylocation = LocationServices.getFusedLocationProviderClient(this);
        mylocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        adress = addresses.get(0).getAddressLine(0);
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                .title("")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.markeraccident)));
                    } catch (IOException e) {
                        e.printStackTrace();
                        adress = "Tidak terdeteksi";
                    }

                    //Toast.makeText(getApplicationContext(),"Lokasi Anda "+ adress +" Lat : "+location.getLatitude() + " Long : "+ location.getLongitude(),Toast.LENGTH_LONG).show();
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(16).build();
                    //Toast.makeText(getApplicationContext(),"Lat :" + location.getLatitude() + "Long :" + latLng.longitude,Toast.LENGTH_LONG).show();
                    Latitude = location.getLatitude();
                    Longtitiude = location.getLongitude();
                    Id = "1";
                    codeZip="0000";
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    Circle circle = mMap.addCircle(new CircleOptions()
                            .center(new LatLng(location.getLatitude(), location.getLongitude()))
                            .radius(300)
                            .strokeColor(Color.RED)
                            //.fillColor(Color.BLUE)
                            );
                    mCircle = circle;
                    //detectMarkerinCircle(location,circle);
                  /*  location.distanceBetween( location.getLatitude(), location.getLongitude(),
                            circle.getCenter().latitude, circle.getCenter().longitude, distance);

                    if( distance[0] > circle.getRadius()  ){
                        Toast.makeText(getBaseContext(), "Outside", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
                    }*/
                }else{
                    Toast.makeText(getApplicationContext(),"Hallo test",Toast.LENGTH_LONG).show();
                }
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //  buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                //onLocationChanged();
            }
        }
        else {
            // buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(data,this);
            setMarkermaps(place.getLatLng().latitude,place.getLatLng().longitude,place.getAddress().toString(),place.getAddress().toString());
        }
    }

    public void setMarkermaps(Double dataLat, Double datalong,String almt,String cdzip){

        Id = "1";
        Latitude = dataLat;
        Longtitiude = datalong;
        adress = almt;
        codeZip = almt;

        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(dataLat, datalong))
                .title("")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.markeraccident)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dataLat, datalong), 16));
        getDataPemadam();
        Circle circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(dataLat, datalong))
                        .radius(300)
                        .strokeColor(Color.BLUE)
                //.fillColor(Color.BLUE)
        );
        mCircle = circle;
    }

    // send location kejadian kebakaran

    public void sendLocationInsident(){
        mApiservice.add_locationInsiden(Id,adress,codeZip,Latitude,Longtitiude).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                      //  mAnim.end();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }


    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }
    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latLng.latitude, latLng.longitude)).zoom(16).build();
       // Toast.makeText(getApplicationContext(),"Lat :" + location.getLatitude() + "Long :" + latLng.longitude,Toast.LENGTH_LONG).show();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //menghentikan pembaruan lokasi
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
        }

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Izin diberikan.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            //buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Izin ditolak.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        return null;
    }

    public void getDataPemadam(){
        ld = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<ItemsAllHospital> call = mApiservice.getData();
        call.enqueue(new Callback<ItemsAllHospital>() {
            @Override
            public void onResponse(Call<ItemsAllHospital> call, retrofit2.Response<ItemsAllHospital> response) {
                ld.dismiss();
                List<ResponseAllHospital> rspn = response.body().getItems();
                loadDt(rspn);

            }

            @Override
            public void onFailure(Call<ItemsAllHospital> call, Throwable t) {
                ld.dismiss();

            }
        });
    }
    private void loadDt(List<ResponseAllHospital> rspn){
        for(ResponseAllHospital data: rspn){
            setMarkermapsPskbk(data.getLatitude(),data.getLongtitude(),data.getAlamat(),data.getIdpemadam(),data.getNamars());
        }

    }

    public void setMarkermapsPskbk(String dataLat, String datalong,String almt,String cdzip,String nmrs){
        Double lat = Double.parseDouble(dataLat);
        Double lng = Double.parseDouble(datalong);
        //textSet.setText(lat.toString());
        //Toast.makeText(getApplicationContext(),"Lat : "+lat+"    Long: "+lng+"alamat     "+almt,Toast.LENGTH_LONG).show();
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat,lng))
                .title(almt)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hospitalmarker)));
        detectMarkerinCircle(mCircle,lat,lng,cdzip,nmrs);
        //mMap.setOnCameraMoveListener();
       // marker.getPosition().longitude();
    }

    public void detectMarkerinCircle(Circle circle,Double lat,Double lng,String id,String namars){
        Location.distanceBetween( lat, lng,
                circle.getCenter().latitude, circle.getCenter().longitude, distance);

        if( distance[0] > circle.getRadius()  ){
            Toast.makeText(getBaseContext(), "Outside  "+lat+" "+lng, Toast.LENGTH_LONG).show();
            linearLayout1.setVisibility(View.INVISIBLE);

        } else {
            Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
            textSet.setText("Inside      :"+lat.toString() +"   "+lng.toString()+"  "+id);
            linearLayout1.setVisibility(View.INVISIBLE);
            textSetDetail.setText(namars);
        }
    }


    @Override
    public void onCameraMove() {

    }

    public void permessionreq(){

    }

    public void onRequestPermissionsResult(){

    }

}
