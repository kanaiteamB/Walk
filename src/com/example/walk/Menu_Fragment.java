package com.example.walk;

import org.apache.http.message.BasicNameValuePair;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class Menu_Fragment extends Fragment
        implements
            OnClickListener,
            AsyncTaskInterface {
    private ProgressDialog dialog;
    private BasicNameValuePair[] pair;
    private MyHttpPost myhttppost;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container,
                false);
        RelativeLayout layout = (RelativeLayout) rootView;
        final ImageButton mainbtn = (ImageButton) layout
                .findViewById(R.id.main_btn);
        final ImageButton mapbtn = (ImageButton) layout
                .findViewById(R.id.map_btn);
        final ImageButton battlebtn = (ImageButton) layout
                .findViewById(R.id.battle_btn);
        mainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        battlebtn.setOnClickListener(this);
        return rootView;
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
    public void callback(String[] data) {
        int i = 0;
        Intent intent = new Intent(getActivity(), BattleActivity.class);
        while (data[i] != null) {
            //バトルに送るデータ(敵の名前、キャラID、経験地)
            intent.putExtra("tekiname", data[i]);
            intent.putExtra("tekicharid", data[i+1]);
            intent.putExtra("tekiexp", data[i+2]);
            i+=3;
        }
        startActivity(intent);

    }
    @Override
    public void onClick(View v) {
        // 通信中を表示する
        dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("通信中");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        // ネットに接続できる場合
        if (isConencted(getActivity())) {
            pair = new BasicNameValuePair[3];
            pair[0] = new BasicNameValuePair("id", String.valueOf(sp.getInt(
                    "id", -1)));
            myhttppost = new MyHttpPost(this, pair,
                    "http://10.29.31.145/battle.php");
            myhttppost.execute();
        } else {
            dialog.setMessage("接続に失敗しました");
            dialog.dismiss();
        }

    }

}
