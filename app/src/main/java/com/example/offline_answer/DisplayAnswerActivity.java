package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_answer);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 1);

        Globals globals = (Globals) this.getApplication();
        Player p1 = globals.p1;
        Player p2 = globals.p2;
        Player p3 = globals.p3;

        TextView q = findViewById(R.id.textView3);
        q.setText(globals.question);

        TextView text1 = (TextView)findViewById(R.id.textViewName);
        TextView text2 = (TextView)findViewById(R.id.textViewAnswer);



        switch(id){
            case 1:
                text1.setText(p1.name);
                text2.setText(p1.answer);
                break;
            case 2:
                text1.setText(p2.name);
                text2.setText(p2.answer);
                break;

            case 3:
                text1.setText(p3.name);
                text2.setText(p3.answer);
                break;
        }

    }

}
