package com.example.walk;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
import android.widget.ImageButton;
public class StartActivity extends Activity
        implements
            OnClickListener,
            AsyncTaskInterface {
    private int id;
    private int charid;
    private String name;
    private MyHttpPost myhttppost;
    private ImageButton startbtn;
    private ProgressDialog dialog;
    private SharedPreferences sp;
    private NameValuePair[] pair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // ボタン初期化
        startbtn = (ImageButton) findViewById(R.id.start_btn);
        // アンドロイドに保存してあるデータの管理を行う変数
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 自分のidが存在した場合読み込み、ない場合-1が入る
        id = sp.getInt("id", -1);
        // 自分のidが存在した場合読み込み、ない場合namelessが入る
        name = sp.getString("name", "nameless");
        charid = sp.getInt("charid", -1);
        startbtn.setOnClickListener(this);
    }
    protected boolean isConencted(Context context) {

        Log.d("StartActivity", "isConected");
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        return (networkInfo.isConnected());
    }
    @Override
    public void onClick(View v) {
        //
        if ((name == "nameless") || (charid == -1)) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);

        } else {
            // 通信中を表示する
            dialog = new ProgressDialog(StartActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("通信中");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            // ネットに接続できる場合
            if (isConencted(this)) {
                pair = new BasicNameValuePair[3];
                pair[0] = new BasicNameValuePair("id", String.valueOf(id));
                pair[1] = new BasicNameValuePair("name", name);
                pair[2] = new BasicNameValuePair("charid",
                        String.valueOf(charid));
                myhttppost = new MyHttpPost(this, pair,
                        "http://10.29.31.145/start.php");
                myhttppost.execute();
            } else {
                dialog.setMessage("接続に失敗しました");
                dialog.dismiss();
            }
        }
    }
    // 非同期処理を行うスレッドで呼び出される
    @Override
    public void callback(String[] data) {
        dialog.dismiss();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(data[0]!=null){
            sp.edit().putInt("id", Integer.parseInt(data[0])).commit();
            sp.edit().putInt("exp", Integer.parseInt(data[1])).commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else{
//            Toast.makeText(this, "データ取得に失敗しました", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }

}
