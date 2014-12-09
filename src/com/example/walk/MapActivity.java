package com.example.walk;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MapActivity extends FragmentActivity {
    GoogleMap map;
    MarkerOptions op =new MarkerOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("MAP", "スタート");
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        map = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
        MapsInitializer.initialize(this);
        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.map, new Menu_Fragment()).commit();
        }
//        op.icon(BitmapDescriptorFactory.fromResource(R.drawable.TTTTTT));
        // Google Play Servicesが使えるかどうかのステータス
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getBaseContext());
        Log.d("MAP",Integer.toString(status));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
