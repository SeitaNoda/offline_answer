package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.offline_answer.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MoveQuestion(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayQuestionActivity.class);

        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Globals globals = (Globals) this.getApplication();

        p1.setName("Fukutoku");
        p1.setAnswer("ジャルジャル");

        p2.setName("Hideki Matsui");
        p2.setAnswer("檻の中のゴリラ");

        p3.setName("shinji ikari");
        p3.setAnswer("鶏肉");

        globals.p1 = p1;
        globals.p2 = p2;
        globals.p3 = p3;

        startActivity(intent);

    }

    public void MoveTutorial(View view){

        Intent intent = new Intent(this, DisplayTutorialActivity.class);
        startActivity(intent);

    }
}
