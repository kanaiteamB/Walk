package com.example.walk;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class Map extends Service
        implements
            LocationListener,
            AsyncTaskInterface {
    int time;
    MyHttpPost mhp;
    private LocationManager lm;
    SharedPreferences sharedpreferences;
    private NameValuePair[] pair;

    @Override
    public void onCreate() {
        Log.d("Map", "oncreat_Map.java");
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Map", "スタートコマンド");
        time = intent.getIntExtra("TIME", 5000);
        Log.d("Map", "time" + String.valueOf(time));

        // ロケーション条件設定
        Criteria cri = new Criteria();
        cri.setAltitudeRequired(false);
        cri.setSpeedRequired(false);
        cri.setBearingRequired(false);
        cri.setCostAllowed(false);
        final String provider = lm.getBestProvider(cri, true);
        lm.requestLocationUpdates(provider, time, 1 , this,
                this.getMainLooper());
        Toast.makeText(this, "更新開始", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "change", Toast.LENGTH_LONG).show();
        Log.d("Map", "ロケーションマネージャー");
        pair = new NameValuePair[3];
        pair[0] = new BasicNameValuePair("id", String.valueOf(sharedpreferences
                .getInt("id", -1)));
        pair[1] = new BasicNameValuePair("latitude", String.valueOf(location
                .getLatitude()));
        pair[2] = new BasicNameValuePair("longitude", String.valueOf(location
                .getLongitude()));
        mhp = new MyHttpPost(this, pair, "http://10.29.31.145/map.php");
        mhp.execute();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 開放処理
        lm.removeUpdates(this);
        Log.d("Map", "デストロイ");

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void callback(String[] data) {
        // sharedpreferences.edit().putInt("exp",
        // Integer.parseInt(data[0])).commit();
    }

}
