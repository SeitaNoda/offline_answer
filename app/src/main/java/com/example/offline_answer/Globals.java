package com.example.offline_answer;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Globals extends Application {

    List<Player> playersGlobal = new ArrayList<>();

    int playerNum = 3;

    Player p1;
    Player p2;
    Player p3;
    String question;

    long mTimeLeftInMillis;

    public Globals(){
        for (int i=0; i<5; i++){
            this.playersGlobal.add(i, this.p1);
        }
    }
}
