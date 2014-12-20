package com.example.walk;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SettingActivity extends Activity {
    private Button ok;
    private EditText text;
    private SharedPreferences sp;
    private ImageButton[] monster;
    private int charid;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_setting);
        ok = (Button) findViewById(R.id.setting_button);
        text = (EditText) findViewById(R.id.setting_name);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        monster = new ImageButton[5];
        monster[0] = (ImageButton) findViewById(R.id.dedenne);
        monster[1] = (ImageButton) findViewById(R.id.jibanyan);
        monster[2] = (ImageButton) findViewById(R.id.kujira);
        monster[3] = (ImageButton) findViewById(R.id.slime);
        monster[4] = (ImageButton) findViewById(R.id.teemo);
        text.setText("ユーザ名");
        charid = -1;
    }
    protected void onResume() {
        super.onResume();
        ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String check = text.getEditableText().toString();
                if ((!check.equals(String.valueOf("")))
                        && (!check.equals(String.valueOf("nameless")))
                        &&(charid!=-1)) {
                    sp.edit().putString("name", check).commit();
                    sp.edit().putInt("charid", charid).commit();
                    finish();
                } else
                    showToast();
            }
        });
        for (id = 0; id < 5; id++){
            
            monster[id].setOnClickListener(new OnClickListener() {
                int test = id;
                @Override
                public void onClick(View v) {
                    charid=test;
                    Log.d("SETTINGACTIVITY",String.valueOf(test));
                    colorchange(v);
                }
            });
        }
    }
    void colorchange(View v) {
        for (int i = 0; i < 5; i++)
            monster[i].setBackgroundColor(Color.WHITE);
        v.setBackgroundColor(Color.BLUE);
    }
    void showToast() {
        Toast.makeText(this, "名前を入れてください", Toast.LENGTH_LONG).show();
    }
}
