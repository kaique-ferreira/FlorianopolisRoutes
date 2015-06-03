package com.aruana.florianopolisroutes;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    private Context context;

    private static final double FLORIANOPOLIS_LATITUDE = -27.59667;
    private static final double FLORIANOPOLIS_LONGITUDE = -48.54917;

    private String tappedAddress;
    private Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.context = this;
        setUpMapIfNeeded();

        buttonOk = (Button)findViewById(R.id.buttonOkMap);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tappedAddress != null && !tappedAddress.isEmpty()) {
                    int dotIndex = tappedAddress.indexOf(".");

                    if (dotIndex+2 < tappedAddress.length()) {
                        tappedAddress = tappedAddress.substring(dotIndex + 2);

                        Intent intent = new Intent(context, RoutesListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("address", tappedAddress);
                        context.startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context,context.getResources().getString(R.string.malformed_address),Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(context,context.getResources().getString(R.string.invalid_address),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        mMap.clear();
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.addMarker(markerOptions);

                        Double[] latLong = new Double[] {latLng.latitude,latLng.longitude};
                        new ReverseGeocodingTask(context).execute(latLong);
                    }
                });
            }
        }
    }

    private void setUpMap() {
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(FLORIANOPOLIS_LATITUDE,FLORIANOPOLIS_LONGITUDE));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    private class ReverseGeocodingTask extends AsyncTask<Double, Void, String> {
        Context context;

        public ReverseGeocodingTask(Context context){
            super();
            this.context = context;
        }

        @Override
        protected String doInBackground(Double... params) {
            Geocoder geocoder = new Geocoder(context);
            double latitude = params[0];
            double longitude = params[1];

            List<Address> addresses = null;

            try {
                addresses = geocoder.getFromLocation(latitude, longitude,1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(addresses != null && addresses.size() > 0 ){
                return addresses.get(0).getThoroughfare();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String addressText) {
            Toast.makeText(context, addressText, Toast.LENGTH_SHORT).show();
            tappedAddress = addressText;
        }
    }

}

