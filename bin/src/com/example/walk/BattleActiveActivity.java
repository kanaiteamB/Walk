package com.example.walk;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walk.monster_status.Monster;
import com.example.walk.monster_status.dedenne;
import com.example.walk.monster_status.jibanyan;
import com.example.walk.monster_status.kujira;
import com.example.walk.monster_status.suraim;
import com.example.walk.monster_status.teemo;

public class BattleActiveActivity extends Activity {

    ImageView heroView, enemyView;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int hmid = intent.getIntExtra("hmid", 0);
        int emid = intent.getIntExtra("emid", 1);
        int hlv = intent.getIntExtra("hlv", 0);
        int elv = intent.getIntExtra("elv", 100);
        
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        
        Monster hero = getMonster(sp.getInt("charid", 0));
        Monster enemy = getMonster(emid);
        Data h = new Data(Data.getMonster(sp.getInt("charid", 0)),sp.getInt("exp", 500));
        Data e = new Data(enemy, elv);
        Battle battle = new Battle(h, e);
        battle.executattack();
        final List<String> results = battle.getBattleText();
        setContentView(R.layout.activity_battleactive);
        heroView = (ImageView) findViewById(R.id.imageView1);
        heroView.setImageDrawable(hero.getDrawable(this));
        enemyView = (ImageView) findViewById(R.id.imageView2);
        enemyView.setImageDrawable(enemy.getDrawable(this));
        resultView = (TextView) findViewById(R.id.textView2);
    //    ScrollView a = (ScrollView)findViewById(R.id.ScrollView);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.lalala);
        layout.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(results.size()>0) {
                    String result = resultView.getText() + results.remove(0);
                    resultView.setText(result);
                }
            }
        });
    }
    
    private Monster getMonster(int id) {
        switch(id) {
        case 0: return new dedenne();
        case 1: return new jibanyan();
        case 2: return new kujira();
        case 3: return new suraim();
        case 4: return new teemo();
        default: return null;
        }
    }
}
