package com.example.walk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BattleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        FrameLayout layout = (FrameLayout) findViewById(R.id.battle);
        final int hlv = 20, hid = 0, hmid = 0;

        {
            final int elv = 20, eid = 0, emid = 0;
            String name = "たかし";
            String monsterName = "イッペイ";
            TextView enemy = new TextView(getApplicationContext());
            enemy.setText(String
                    .format("%s: %s(Lv:%d)", name, monsterName, elv));
            enemy.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            enemy.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO 自動生成されたメソッド・スタブ
                    Intent intent = new Intent(getApplicationContext(),
                            BattleActiveActivity.class);
                    intent.putExtra("hlv", hlv);// レベル
                    intent.putExtra("hid", hid);// プレイヤーのID
                    intent.putExtra("hmid", hmid);// モンスターのID
                    intent.putExtra("elv", elv);// レベル
                    intent.putExtra("eid", eid);// プレイヤーのID
                    intent.putExtra("emid", emid);// モンスターのID
                    startActivity(intent);
                }
            });
            layout.addView(enemy);

        }

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
