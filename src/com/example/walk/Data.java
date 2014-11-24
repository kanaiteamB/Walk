package com.example.walk;

public class Data {

    private int exp;
    // 初期値
    final int STR = 10;
    final int VIT = 10;
    final int AGI = 10;
    final int LUK = 10;
    final int HP = 10;
    final int LV = 1;

    //スレッド間の受け渡し用
    int ID = 0;
    int Latitude = 0;
    int Longitude = 0;
    Data(int ID){
        this.ID = ID;
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
    int getSTR() {
        return 100 * getLv() + STR;
    }
    int getVIT() {
        return 100 * getLv() + VIT;
    }
    int getAGI() {
        return 100 * getLv() + AGI;
    }
    int getLUK() {
        return 100 * getLv() + LUK;
    }
    int getHP() {
        return 100 * getLv() + HP;
    }
}
