package com.example.walk;

import android.app.Activity;
import android.os.Bundle;

public class BattleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.battle, new Menu_Fragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
