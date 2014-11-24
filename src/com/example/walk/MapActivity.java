package com.example.walk;

import android.app.Activity;
import android.os.Bundle;

public class MapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new Menu_Fragment()).commit();
        }
//        Intent intent = new Intent(this, Map.class);
//        intent.putExtra("TIME", 1 * 60 * 1000);
//        this.startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
