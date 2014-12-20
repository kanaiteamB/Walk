package com.example.walk;

import com.example.walk.monster_status.Monster;

public class Data {

    private int exp;
    private int Lv;

    //スレッド間の受け渡し用
    int ID = 0;
    int Latitude = 0;
    int Longitude = 0;
    private Monster monster;
    public Data(int ID, Monster monster, int Lv){
        this.ID = ID;
        this.monster = monster;
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
    int getLv() {
        return exp / 100 + 1;
    }
    
}
