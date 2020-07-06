package com.example.offline_answer;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Globals extends Application {

    List<Player> playersGlobal = new ArrayList<>();

    int playerNum;

    long mTimeLeftInMillis;

}
