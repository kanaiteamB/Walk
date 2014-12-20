package com.example.walk;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        int emid = intent.getIntExtra("emid", 0);
        int hid = intent.getIntExtra("hid", 0);
        int eid = intent.getIntExtra("eid", 0);
        int hlv = intent.getIntExtra("hlv", 0);
        int elv = intent.getIntExtra("elv", 0);
        
        Monster hero = getMonster(hmid);
        Monster enemy = getMonster(emid);
        Data h = new Data(hid, hero, hlv);
        Data e = new Data(eid, enemy, elv);
        Battle battle = new Battle(h, e);
        battle.executattack();
        final List<String> results = battle.getBattleText();
        setContentView(R.layout.activity_battleactive);
        heroView = (ImageView) findViewById(R.id.imageView1);
        heroView.setImageDrawable(hero.getDrawable(this));
        enemyView = (ImageView) findViewById(R.id.imageView2);
        enemyView.setImageDrawable(enemy.getDrawable(this));
        resultView = (TextView) findViewById(R.id.textView2);
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
