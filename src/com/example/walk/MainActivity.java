package com.example.walk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private Intent back;
    private SharedPreferences sp;
    private int charimage[] = {R.drawable.dedenne, R.drawable.jibanyan,
            R.drawable.kujira, R.drawable.slime, R.drawable.teemo};
    private String charnames[] = {"デデンネ", "ジバニャン", "クジラ", "スライム", "ティーモ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.main, new Menu_Fragment()).commit();
        }

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        ImageView MyCharacterImage = (ImageView) findViewById(R.id.main_image);
        MyCharacterImage.setImageResource(charimage[sp.getInt("charid", 0)]);

        TextView pname = (TextView) findViewById(R.id.main_pname);
        pname.setText(sp.getString("name", "名前"));

        TextView charname = (TextView) findViewById(R.id.main_charname);
        charname.setText(charnames[sp.getInt("charid", 0)]);

        // 各パラメータ
        TextView hp = (TextView) findViewById(R.id.main_hp);
        hp.setText("HP : " + String.valueOf(1));

        TextView agi = (TextView) findViewById(R.id.main_agi);
        agi.setText("AGI : " + String.valueOf(2));

        TextView luk = (TextView) findViewById(R.id.main_luk);
        luk.setText("LUK : " + String.valueOf(2));

        TextView str = (TextView) findViewById(R.id.main_str);
        str.setText("AGI : " + String.valueOf(2));

        TextView vit = (TextView) findViewById(R.id.main_vit);
        vit.setText("VIT : " + String.valueOf(2));

        // バックグランド処理を行うスレッドの作成
        back = new Intent(this, Map.class);
        back.putExtra("TIME", 1000);
        this.startService(back);
    }
    @Override
    protected void onResume() {
        Log.d("Main", "onResume");
        super.onResume();
    }

}
