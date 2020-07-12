package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class DisplayQuestionActivity extends AppCompatActivity {
    public static final String ID_ANSWER = "com.example.offline_answer.MESSAGE";

    private CountDownTimer mCountDownTimer;
    private TextView mTextViewCountDown;
    private static final long START_TIME = 1000*60*3;   // initial value of TIMER
    private boolean mTimerRunning;                      // timer Status boolean
    private long mTimeLeftInMillis = START_TIME;        // if stop, store remaining time

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
        setContentView(R.layout.activity_display_question);

        Globals globals = (Globals) this.getApplication();

        // TODO: べた書き対処
        playerViews[0] = findViewById(R.id.p1Name);
        playerViews[1] = findViewById(R.id.p2Name);
        playerViews[2] = findViewById(R.id.p3Name);
        playerViews[3] = findViewById(R.id.p4Name);
        playerViews[4] = findViewById(R.id.p5Name);

        buttons[0] = findViewById(R.id.p1Button);
        buttons[1] = findViewById(R.id.p2Button);
        buttons[2] = findViewById(R.id.p3Button);
        buttons[3] = findViewById(R.id.p4Button);
        buttons[4] = findViewById(R.id.p5Button);

        playerNum = globals.playerNum;

        // Textviewはdefault:invisible設定
        for (int i=0; i<playerNum; i++){
            playerViews[i].setText(globals.playersGlobal.get(i).name);
            playerViews[i].setVisibility(View.VISIBLE);
            buttons[i].setVisibility(View.VISIBLE);
        }


        TextView q = findViewById(R.id.textView5);
        q.setText(globals.question);

        mTextViewCountDown = findViewById(R.id.timerTextView);

        if (getIntent().getBooleanExtra("pauseTimer", false)) {
            mTimeLeftInMillis = globals.mTimeLeftInMillis;
        }

        startTimer();

    }

    public void MoveInputAnswer(View view) {
        Intent intent = new Intent(this, InputAnswerActivity.class);

        int buttonID = view.getId();
        intent.putExtra("id", buttonID);

        pauseTimer();

        startActivity(intent);

    }

    public void moveDisplayResult(View view) {
        Intent intent = new Intent(this, DisplayResultActivity.class);
        startActivity(intent);
    }

    /* Timer Methods */
    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

        mTimerRunning = true;
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;

        Globals globals = (Globals) this.getApplication();
        globals.mTimeLeftInMillis = mTimeLeftInMillis;

    }

    private void updateCountDownText(){
        int minutes = (int)(mTimeLeftInMillis/1000)/60;
        int seconds = (int)(mTimeLeftInMillis/1000)%60;
        String timerLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timerLeftFormatted);
    }


 }
