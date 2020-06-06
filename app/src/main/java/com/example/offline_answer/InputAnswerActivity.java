package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputAnswerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_answer);

        Globals globals = (Globals) this.getApplication();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Player target = new Player();
        if (id == 0) {
            // TODO:error
        } else if (id == R.id.p1Button){
            target = globals.p1;
        } else if (id == R.id.p2Button){
            target = globals.p2;
        } else if (id == R.id.p3Button){
            target = globals.p3;
        }

        TextView targetName = findViewById(R.id.targetPlayerName);
        targetName.setText(target.name);

    }

    public void FinishAnswer(View view){
        Globals globals = (Globals) this.getApplication();

        EditText answerText = (EditText) findViewById(R.id.PlayerAnswer);
        String answer = answerText.getText().toString();

        Intent prevIntent = getIntent();
        int id = prevIntent.getIntExtra("id", 0);
        if (id == 0) {
            // TODO:error
        } else if (id == R.id.p1Button){
            globals.p1.setAnswer(answer);
        } else if (id == R.id.p2Button){
            globals.p2.setAnswer(answer);
        } else if (id == R.id.p3Button){
            globals.p3.setAnswer(answer);
        }

        Intent intent = new Intent(this, DisplayQuestionActivity.class);
        startActivity(intent);
    }
}
