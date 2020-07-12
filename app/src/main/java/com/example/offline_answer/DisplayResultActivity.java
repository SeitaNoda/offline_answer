package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DisplayResultActivity extends AppCompatActivity {

    private int playerNum = 3;
    private int maxPlayerNum = 5;
    private int minPlayerNum = 2;
    private Player players[] = new Player[playerNum];
    private TextView[] playerViews = new TextView[maxPlayerNum];
    private Button[] buttons = new Button[maxPlayerNum];
    private String playerIds[] = new String[playerNum];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);


        Globals globals = (Globals) this.getApplication();

        // TODO: べた書き対処
        playerViews[0] = findViewById(R.id.textViewP1);
        playerViews[1] = findViewById(R.id.textViewP2);
        playerViews[2] = findViewById(R.id.textViewP3);
        playerViews[3] = findViewById(R.id.textViewP4);
        playerViews[4] = findViewById(R.id.textViewP5);

        buttons[0] = findViewById(R.id.buttonP1);
        buttons[1] = findViewById(R.id.buttonP2);
        buttons[2] = findViewById(R.id.buttonP3);
        buttons[3] = findViewById(R.id.buttonP4);
        buttons[4] = findViewById(R.id.buttonP5);

        playerNum = globals.playerNum;

        // Textviewはdefault:invisible設定
        for (int i=0; i<playerNum; i++){
            playerViews[i].setText(globals.playersGlobal.get(i).name);
            playerViews[i].setVisibility(View.VISIBLE);
            buttons[i].setVisibility(View.VISIBLE);
        }

        TextView q = findViewById(R.id.textViewQ);
        q.setText(globals.question);


    }

    /** Called when the user taps the Send button */
    public void moveDisplayAnswer(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);

        int buttonID = view.getId();
        intent.putExtra("id", buttonID);

        startActivity(intent);

    }

    public void moveDisplayAnswerP1(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra("ID", 1);
        startActivity(intent);
    }
    public void moveDisplayAnswerP2(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra("ID", 2);
        startActivity(intent);
    }
    public void moveDisplayAnswerP3(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra("ID", 3);
        startActivity(intent);
    }

    public void moveSelectChampion(View view) {
        Intent intent = new Intent(this, SelectChampionActivity.class);
        startActivity(intent);
    }




}
