package com.example.walk;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Map extends Service implements LocationListener {
    int time;
    MyHttpPost mhp;
    private LocationManager lm;
    private String[] contents;

    @Override
    public void onCreate() {
        Log.d("Map", "oncreat_Map.java");
        // locationmanagerオブジェクトの生成
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Map", "スタートコマンド");
        time = intent.getIntExtra("TIME", 5 * 1000 * 60);
        Log.d("Map","time"+Integer.toString(time));

        // エラー原因
        //ロケーション条件設定
        Criteria cri = new Criteria();
        cri.setAltitudeRequired(false);
        cri.setSpeedRequired(false);
        cri.setBearingRequired(false);
        cri.setCostAllowed(false);
        final String provider = lm.getBestProvider(cri, true);
        lm.requestLocationUpdates(provider ,time, 1000*500 , this,
        this.getMainLooper());
        Toast.makeText(this, "更新開始", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Map", "ロケーションマネージャー");
        contents[1] = String.valueOf(location.getLatitude());
        contents[2] = String.valueOf(location.getLongitude());
        // mhp = new MyHttpPost(contents);
        // mhp.execute(contents);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //開放処理
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

}
