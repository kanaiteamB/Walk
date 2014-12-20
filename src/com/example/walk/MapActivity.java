package com.example.walk;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class MapActivity extends FragmentActivity
        implements
            ConnectionCallbacks,
            OnConnectionFailedListener,
            LocationListener {

    final LocationRequest request = LocationRequest.create().setInterval(5000)
            .setFastestInterval(16)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    GoogleMap map = null;
    LocationClient lc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MAPACTIVITY", "スタート");
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        map = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment)).getMap();
        if (map != null)
            map.setMyLocationEnabled(true);
        lc = new LocationClient(getApplicationContext(), this, this);
        UiSettings settings = map.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        map.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(getApplicationContext(), "現在位置",
                        Toast.LENGTH_LONG).show();;
                return false;
            }
        });

        if (lc != null)
            lc.connect();
        MapsInitializer.initialize(this);
        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.map, new Menu_Fragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onLocationChanged(Location location) {}

    @Override
    public void onConnected(Bundle arg0) {
        lc.requestLocationUpdates(request, this);

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        // TODO 自動生成されたメソッド・スタブ

    }
}
