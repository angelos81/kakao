package com.hanbit.kakao.presentation.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hanbit.kakao.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        String position = intent.getExtras().getString("position");
        Log.d("Address Position", position);

        String[] str = position.split(",");
        double lat = Double.parseDouble(str[0]);
        double lng = Double.parseDouble(str[1]);

        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(pos).title("Marker in shinchoun"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 16));
    }
}
