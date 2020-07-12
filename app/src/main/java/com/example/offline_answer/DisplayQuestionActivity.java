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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        Globals globals = (Globals) this.getApplication();

        /* Display Settings */
        TextView p1Name = findViewById(R.id.p1Name);
        TextView p2Name = findViewById(R.id.p2Name);
        TextView p3Name = findViewById(R.id.p3Name);
        p1Name.setText(globals.p1.name);
        p2Name.setText(globals.p2.name);
        p3Name.setText(globals.p3.name);


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
