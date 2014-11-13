package com.example.walk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MapActivity extends Activity{
	//ボタンインスタンスの作成
	Button mainbtn,btlbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		//ボタン内部の作成
		mainbtn = (Button)findViewById(R.id.map_mainbtn);
		btlbtn = (Button)findViewById(R.id.map_btlbtn);
		Intent intent = new Intent(this,Map.class);
		intent.putExtra("TIME", 1*60*1000);
		this.startService(intent);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		//ボタンが押されたときの行動作成
		mainbtn.setOnClickListener(new OnClickListener() {   	 
	        public void onClick(View v) {
	        	Intent intent = new Intent(MapActivity.this,MainActivity.class);
	        	startActivity(intent);
	        	finish();
	        }
	    });
		btlbtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
	        	Intent intent = new Intent(MapActivity.this,BattleActivity.class);
	        	startActivity(intent);
	        	finish();
			}
		});
		
	}
}
