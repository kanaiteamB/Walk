package com.example.walk;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class StartActivity extends Activity implements OnClickListener ,AsyncTaskInterface{
    Button startbtn;
    Data _Data;
    MyHttpPost myhttppost;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("StartActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startbtn = (Button) findViewById(R.id.start_btn);
        // ID取得
        SharedPreferences sharedpreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        _Data = new Data(sharedpreferences.getInt("ID", 0));
        Log.d("Start_Activity",Integer.toString(_Data.ID));
        Log.d("StartActivity","onCreate end");

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("StartActivity","onResume");
        startbtn.setOnClickListener(this);
        Log.d("StartActivity","onResume end");

    }
    protected boolean isConencted(Context context) {
        Log.d("StartActivity","isConected");
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        return (networkInfo.isConnected());
    }
    @Override
    public void onClick(View v) {
        Log.d("StartActivity","onClick");
        dialog = new ProgressDialog(StartActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("通信中");
        dialog.show();
        
        if (isConencted(this)) {
            Log.d("Main", "ポスト通信開始");
            myhttppost = new MyHttpPost(this);
            myhttppost.execute(_Data);
        }
        else{
            
        }
    }
    //非同期処理を行うスレッドで呼び出される
    @Override
    public void callback(Data data) {
        dialog.dismiss();
        Log.d("Start_Activity",Integer.toString(data.ID));
        SharedPreferences sharedpreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        sharedpreferences.edit().putInt("ID", data.ID).commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);        
    }

}
