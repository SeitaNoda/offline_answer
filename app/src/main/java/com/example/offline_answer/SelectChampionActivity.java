package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class SelectChampionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_champion);

        Globals globals = (Globals) this.getApplication();
        Player p1 = globals.p1;
        Player p2 = globals.p2;
        Player p3 = globals.p3;

        CheckBox checkN1 = (CheckBox)findViewById(R.id.checkBoxN1);
        checkN1.setText(p1.name);
        TextView textA1 = (TextView)findViewById(R.id.textViewAns1);
        textA1.setText(p1.answer);

        CheckBox checkN2 = (CheckBox) findViewById(R.id.checkBoxN2);
        checkN2.setText(p2.name);
        TextView textA2 = (TextView)findViewById(R.id.textViewAns2);
        textA2.setText(p2.answer);

        CheckBox checkN3 = (CheckBox)findViewById(R.id.checkBoxN3);
        checkN3.setText(p3.name);
        TextView textA3 = (TextView)findViewById(R.id.textViewAns3);
        textA3.setText(p3.answer);
    }

    public void showChampion(View view) {
        TextView textChamp = (TextView)findViewById(R.id.textViewChampion);
        textChamp.setText("seikai desu");
    }


    public void finishGame(View view) {
//        Globals.initAll();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
