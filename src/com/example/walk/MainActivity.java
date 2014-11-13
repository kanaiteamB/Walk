package com.example.walk;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
		//ボタンインスタンスの作成
		Button mapbtn,btlbtn;
		Intent back;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			//ボタン内部の作成
			mapbtn = (Button)findViewById(R.id.main_mapbtn);
			btlbtn = (Button)findViewById(R.id.main_btlbtn);
			//バックグランド処理を行うスレッドの作成
			back = new Intent(this,Map.class);
			back.putExtra("TIME", 5*60*1000);
			this.startService(back);

		}
		
		@Override
		protected void onResume(){
			super.onResume();
			//ボタンが押されたときの行動作成
			mapbtn.setOnClickListener(new OnClickListener() {   	 
		        public void onClick(View v) {
		        	Intent intent = new Intent(MainActivity.this,MapActivity.class);
		        	startActivity(intent);
		        	finish();
		        }
		    });
			btlbtn.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
		        	Intent intent = new Intent(MainActivity.this,MapActivity.class);
		        	startActivity(intent);
		        	finish();
				}
			});
		}


}
