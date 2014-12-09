package com.example.walk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class MainActivity extends Activity {
    Intent back;
    int ID;
    String[] contents = new String[3];
    MyHttpPost mhp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            
            getFragmentManager().beginTransaction()
                    .add(R.id.main, new Menu_Fragment()).commit();
        }
        // バックグランド処理を行うスレッドの作成
        back = new Intent(this, Map.class);
        back.putExtra("TIME", 5 * 60 * 1000);
        this.startService(back);
        SharedPreferences sharedpreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        ID = sharedpreferences.getInt("ID", 5);
        Log.d("Start_Activity",Integer.toString(ID));
        contents[0] = String.valueOf(ID);
        Log.d("Main", "ポスト通信開始");
//        mhp = new MyHttpPost(contents);
//        mhp.execute(contents);
    }

    @Override
    protected void onResume() {
        Log.d("Main", "onResume");
        super.onResume();
    }

}
