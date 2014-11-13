package com.example.walk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BattleActivity extends Activity {
	//ボタンインスタンスの作成
	Button mapbtn,mainbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		//ボタン内部の作成
		mapbtn = (Button)findViewById(R.id.btl_mapbtn);
		mainbtn = (Button)findViewById(R.id.btl_mainbtn);
		

	}
	
	@Override
	protected void onResume(){
		super.onResume();
		//ボタンが押されたときの行動作成
		mapbtn.setOnClickListener(new OnClickListener() {   	 
	        public void onClick(View v) {
	        	Intent intent = new Intent(BattleActivity.this,MapActivity.class);
	        	startActivity(intent);
	        	finish();
	        }
	    });
		mainbtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
	        	Intent intent = new Intent(BattleActivity.this,MainActivity.class);
	        	startActivity(intent);
	        	finish();
			}
		});
	}


}
