package com.example.walk;

import com.example.walk.monster_status.Monster;
import com.example.walk.monster_status.dedenne;
import com.example.walk.monster_status.jibanyan;
import com.example.walk.monster_status.kujira;
import com.example.walk.monster_status.suraim;
import com.example.walk.monster_status.teemo;

public class Data {

    private int exp;
    private int Lv;

    //スレッド間の受け渡し用
    private Monster monster;
    public Data(Monster monster, double Lv){
        this.monster = monster;
        this.Lv = (int)Lv;
    }
    public Data(Monster monster, int exp){
    	this.monster =monster;
    	this.Lv = (int)this.monster.getLVexp(exp);
    	
    }
    public int getSTR() {
        return monster.getSTRbase() + Lv * monster.getSTRper();
    }
    public int getVIT() {
        return monster.getVITbase() + Lv * monster.getVITper();
    }
    public int getAGI() {
        return monster.getAGIbase() + Lv * monster.getAGIper();
    }
    public int getLUK() {
        return monster.getLUKbase() + Lv * monster.getLUKper();
    }
    public int getHP() {
        return monster.getHPbase() + Lv * monster.getHPper();
    }
    public void setHP(){
        monster.setHP(getHP());
    }
    public String getName(){
        return monster.getName();
    }

    void setExp(int t) {
        this.exp = t;
    }

    int getExp() {
        return exp;
    }    
    static Monster getMonster(int ID) {
        if (ID == 0)
            return new dedenne();
        else if (ID == 1)
            return new jibanyan();
        else if (ID == 2)
            return new kujira();
        else if (ID == 3)
            return new suraim();
        else
            return new teemo();
    }
}
