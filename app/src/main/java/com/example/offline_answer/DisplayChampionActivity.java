package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayChampionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_champion);
        Intent intent = getIntent();
        String answer = intent.getStringExtra("answer");
        TextView textChamp = (TextView)findViewById(R.id.textViewChamp);
        textChamp.setText(answer);
    }

    public void finishGame(View view) {
//        Globals.initAll();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
