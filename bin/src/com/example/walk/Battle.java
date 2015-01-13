package com.example.walk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class Battle {
    Data a;
    Data b;
    int phase = 0;
    List<String> results;
    Battle(Data a,Data b){
        this.a = a;
        this.b = b;
        results = new ArrayList<String>();
    }
    void executattack(){
        int a_HP = a.getHP();
        int b_HP = b.getHP();
        for(;a_HP > 0 && b_HP >0;)//１ターン
        {
        	//System.out.println(a_HP+"tete"+b_HP);
            phase++;
            if(a.getAGI() < b.getAGI()){
                a_HP -= attack(b,a);
                results.add(pushBattleText(phase,b,a,attack(b,a),b_HP));
                if(a_HP < 0){
                    results.add(a.getName() + "は倒れた");
                    results.add("LOSE");
                    break;
                }
//                if(b_HP < 0)
//                    break;
                b_HP -= attack(a,b);
                results.add(pushBattleText(phase,a,b,attack(a,b),a_HP));
                if(b_HP < 0){
                    results.add(b.getName() + "は倒れた");
                    results.add("WIN");
                    break;
                }
            }
            else{
                b_HP -= attack(a,b);
                results.add(pushBattleText(phase,a,b,attack(a,b),a_HP));
                if(b_HP < 0){
                    results.add(b.getName() + "は倒れた");
                    results.add("WIN");
                    break;
                }
//                if(a_HP < 0)
//                    break;
                a_HP -= attack(b,a);
                results.add(pushBattleText(phase,b,a,attack(b,a),b_HP));
                if(a_HP < 0){
                    results.add(a.getName() + "は倒れた");
                    results.add("LOSE");
                    break;
                }
            }
        }
        
       

    }

    int attack(Data a,Data b){
        double crt;
        crt = 1;
        double probability = (a.getLUK()-b.getAGI());
        Random rnd1 = new Random();
        int ran1 = rnd1.nextInt(100);
        if(ran1 < probability)
            crt=1.5;            


        double avoid = 1;//b.getAGI() + b.getLUK()/2 - a.getLUK()*1.2;
        Random rnd2 = new Random();

        int ran2 = rnd2.nextInt(4);
        double rand =0.8 + ran2*0.1;
        double ad  = a.getSTR()*crt*rand*100*avoid/(b.getVIT()+100);
        Log.d("bttle","battle"+ad);

        return (int)ad;

    }
    String pushBattleText(int phase,Data a,Data b,int dmg,int hp){
        return phase + "ターン目\n\n" + a.getName() + "の攻撃\n\t" + b.getName() + "は" + dmg + "ダメージを受けた\n残りHP" + hp + "\n\n\n";

    }
    List<String> getBattleText()
    {
        return results;

    }
}
