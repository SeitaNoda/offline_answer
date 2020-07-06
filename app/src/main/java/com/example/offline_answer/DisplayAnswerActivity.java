package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayAnswerActivity extends AppCompatActivity {

    private Player target = new Player();
    private int targetNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_answer);

        Globals globals = (Globals) this.getApplication();

        TextView text1 = (TextView)findViewById(R.id.textViewName);
        TextView text2 = (TextView)findViewById(R.id.textViewAnswer);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        // TODO:べた書き対処
        switch (id){
            case R.id.buttonP1:
            targetNum = 0;
            break;
            case R.id.buttonP2:
                targetNum = 1;
                break;
            case R.id.buttonP3:
                targetNum = 2;
                break;
            case R.id.buttonP4:
                targetNum = 3;
                break;
            case R.id.buttonP5:
                targetNum = 4;
                break;

        }
        target = globals.playersGlobal.get(targetNum);

        text1.setText(target.name);
        text2.setText(target.answer);

//        switch(id){
//            case 1:
//                text1.setText(p1.name);
//                text2.setText(p1.answer);
//                break;
//            case 2:
//                text1.setText(p2.name);
//                text2.setText(p2.answer);
//                break;
//
//            case 3:
//                text1.setText(p3.name);
//                text2.setText(p3.answer);
//                break;
//        }

    }

}
