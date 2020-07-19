package com.example.offline_answer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InputAnswerActivity extends AppCompatActivity {

    private int playerNum = 3;
    private int maxPlayerNum = 5;
    private int minPlayerNum = 2;
    private Player players[] = new Player[playerNum];
    private TextView[] playerViews = new TextView[maxPlayerNum];
    private Button[] buttons = new Button[maxPlayerNum];
    private String playerIds[] = new String[playerNum];
    private Player target = new Player();
    private int targetNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_answer);

        Globals globals = (Globals) this.getApplication();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        // TODO:べた書き対処
        switch (id){
            case R.id.p1Button:
                targetNum = 0;
                break;
            case R.id.p2Button:
                targetNum = 1;
                break;
            case R.id.p3Button:
                targetNum = 2;
                break;
            case R.id.p4Button:
                targetNum = 3;
                break;
            case R.id.p5Button:
                targetNum = 4;
                break;
        }
        target = globals.playersGlobal.get(targetNum);

        TextView targetName = findViewById(R.id.targetPlayerName);
        targetName.setText(target.name);

        TextView q = findViewById(R.id.textView10);
        q.setText(globals.question);


    }

    public void FinishAnswer(View view){
        Globals globals = (Globals) this.getApplication();

        EditText answerText = (EditText) findViewById(R.id.PlayerAnswer);
        String answer = answerText.getText().toString();
        target.setAnswer(answer);

        globals.playersGlobal.set(targetNum, target);

        Intent intent = new Intent(this, DisplayQuestionActivity.class);
        intent.putExtra("pauseTimer", true);
        startActivity(intent);
    }
}
