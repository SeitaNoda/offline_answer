package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        // 私はとり五目おにぎりがあまり好きではない。たけのこが入っているからだ。　－アイザック・アシモフ
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Globals globals = (Globals) this.getApplication();

        TextView p1Name = findViewById(R.id.playerName1);
        TextView p2Name = findViewById(R.id.playerName2);
        TextView p3Name = findViewById(R.id.playerName3);

        p1.setName(p1Name.getText().toString());
        p2.setName(p2Name.getText().toString());
        p3.setName(p3Name.getText().toString());

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
