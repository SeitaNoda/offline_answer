package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.offline_answer.MESSAGE";

    private int playerNum = 3;
    private int maxPlayerNum = 5;
    private int minPlayerNum = 2;
    private Player players[] = new Player[maxPlayerNum];
    private TextView[] playerViews = new TextView[maxPlayerNum];
    private String playerIds[] = new String[playerNum];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: べた書き対処
        playerViews[0] = findViewById(R.id.playerName1);
        playerViews[1] = findViewById(R.id.playerName2);
        playerViews[2] = findViewById(R.id.playerName3);
        playerViews[3] = findViewById(R.id.playerName4);
        playerViews[4] = findViewById(R.id.playerName5);

        // Textviewはdefault:invisible設定
        for (int i=0; i<playerNum; i++){
            playerViews[i].setVisibility(View.VISIBLE);
        }
    }

    public void moveQuestion(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayQuestionActivity.class);

        Globals globals = (Globals) this.getApplication();

        for (int i=0; i<playerNum; i++){
            players[i] = new Player();
            players[i].setName(playerViews[i].getText().toString());
            globals.playersGlobal.add(i, players[i]);
        }

        globals.playerNum = playerNum;

        startActivity(intent);

    }

    public void addPlayer(View view){

        if ( playerNum < maxPlayerNum && playerNum >= minPlayerNum){
            playerNum++;
        }
        playerViews[playerNum-1].setVisibility(View.VISIBLE);
    }

    public void subtractPlayer(View view){

        if ( playerNum <= maxPlayerNum && playerNum > minPlayerNum){
            playerNum--;
        }
        playerViews[playerNum].setVisibility(View.INVISIBLE);
    }

    public void MoveTutorial(View view){

        Intent intent = new Intent(this, DisplayTutorialActivity.class);
        startActivity(intent);

    }
}
