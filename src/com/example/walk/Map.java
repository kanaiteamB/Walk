package com.example.walk;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class Map extends Service implements LocationListener{
	int time;
	MyHttpPost mhp;
	private LocationManager lm;
	private String[] contents;
	
	@Override
	public void onCreate(){
		//locationmanagerオブジェクトの生成
		lm = (LocationManager)getSystemService(LOCATION_SERVICE);
		
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId){
		time = intent.getIntExtra("TIME", 5*1000*60);
		
		//ロケーション条件設定
		Criteria cri = new Criteria();
		cri.setAccuracy(Criteria.ACCURACY_HIGH);
		cri.setAltitudeRequired(false);
		cri.setSpeedRequired(false);
		cri.setBearingRequired(false);
		cri.setCostAllowed(false);
		
		lm.requestLocationUpdates(time, 1000*500, cri, this, this.getMainLooper());

		return START_STICKY;
	}

	@Override
	public void onLocationChanged(Location location) {
		contents[1] = String.valueOf(location.getLatitude());
		contents[2] = String.valueOf(location.getLongitude());
		mhp = new MyHttpPost(contents);
		mhp.execute(contents);

	}
	@Override
	public void onDestroy(){
		
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
