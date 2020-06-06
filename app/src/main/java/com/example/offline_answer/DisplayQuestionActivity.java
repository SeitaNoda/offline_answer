package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayQuestionActivity extends AppCompatActivity {
    public static final String ID_ANSWER = "com.example.offline_answer.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        Globals globals = (Globals) this.getApplication();

        TextView p1Name = findViewById(R.id.p1Name);
        TextView p2Name = findViewById(R.id.p2Name);
        TextView p3Name = findViewById(R.id.p3Name);
        p1Name.setText(globals.p1.name);
        p2Name.setText(globals.p2.name);
        p3Name.setText(globals.p3.name);
    }

    public void MoveInputAnswer(View view) {
        Intent intent = new Intent(this, InputAnswerActivity.class);

        int buttonID = view.getId();
        intent.putExtra("id", buttonID);

        startActivity(intent);

    }

    public void moveDisplayResult(View view) {
        Intent intent = new Intent(this, DisplayResultActivity.class);
        startActivity(intent);
    }


}
