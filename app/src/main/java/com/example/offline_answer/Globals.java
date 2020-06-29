package com.example.offline_answer;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Globals extends Application {

    List<Player> playersGlobal = new ArrayList<>();

    Player p1;
    Player p2;
    Player p3;

    int playerNum;

    long mTimeLeftInMillis;

}
