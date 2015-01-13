package com.example.walk.monster_status;

import android.app.Activity;
import android.graphics.drawable.Drawable;


public interface Monster {
    
    public int getSTRbase();
    public int getVITbase();
    public int getAGIbase();
    public int getLUKbase();
    public int getHPbase();
    public int getSTRper();
    public int getVITper();
    public int getAGIper();
    public int getLUKper();
    public int getHPper();
    public int getSTR();
    public String getName();
    public void setHP(int a);
    public Drawable getDrawable(Activity act);
    
    public double getLVexp(int exp);
}
