package com.example.walk.monster_status;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.walk.R;

public class teemo implements Monster {
    String name = "ティーモ";
    int STRbase =5;
    int VITbase = 1;
    int AGIbase = 8;
    int LUKbase = 12;
    int HPbase = 11;
    
    int STRper = 1;
    int VITper = 1;
    int AGIper = 4;
    int LUKper = 4;
    int HPper = 2;
    @Override
    public int getSTRbase() {
        // TODO 自動生成されたメソッド・スタブ
        return STRbase;
    }
    @Override
    public int getVITbase() {
        // TODO 自動生成されたメソッド・スタブ
        return VITbase;
    }
    @Override
    public int getAGIbase() {
        // TODO 自動生成されたメソッド・スタブ
        return AGIbase;
    }
    @Override
    public int getLUKbase() {
        // TODO 自動生成されたメソッド・スタブ
        return LUKbase;
    }
    @Override
    public int getHPbase() {
        // TODO 自動生成されたメソッド・スタブ
        return HPbase;
    }
    @Override
    public int getSTRper() {
        // TODO 自動生成されたメソッド・スタブ
        return STRper;
    }
    @Override
    public int getVITper() {
        // TODO 自動生成されたメソッド・スタブ
        return VITper;
    }
    @Override
    public int getAGIper() {
        // TODO 自動生成されたメソッド・スタブ
        return AGIper;
    }
    @Override
    public int getLUKper() {
        // TODO 自動生成されたメソッド・スタブ
        return LUKper;
    }
    @Override
    public int getHPper() {
        // TODO 自動生成されたメソッド・スタブ
        return HPper;
    }
    @Override
    public int getSTR() {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }
    @Override
    public void setHP(int a) {
        // TODO 自動生成されたメソッド・スタブ
        
    }
    @Override
    public String getName() {
        // TODO 自動生成されたメソッド・スタブ
        return name;
    }
    public Drawable getDrawable(Activity activity) {
        // TODO 自動生成されたメソッド・スタブ
        return activity.getResources().getDrawable(R.drawable.teemo);
    }
    public double getLVexp(int exp){
    	return (Math.sqrt((double)exp)+4)/7;
    }

}
