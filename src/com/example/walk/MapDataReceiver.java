package com.example.walk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MapDataReceiver extends BroadcastReceiver {

    private MapActivity activity;
    public MapDataReceiver(MapActivity content) {
        this.activity = content;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String latitude = intent.getStringExtra("Latitude");
        String Longitude = intent.getStringExtra("Longitude");
        //activity
    }

}
