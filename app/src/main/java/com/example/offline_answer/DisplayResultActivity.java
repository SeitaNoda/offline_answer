package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        OpenHelper dbhelper = new OpenHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        TextView text0 = (TextView)findViewById(R.id.textViewQ);
        text0.setText("text");


        Globals globals = (Globals) this.getApplication();
        Player p1 = globals.p1;
        Player p2 = globals.p2;
        Player p3 = globals.p3;

        TextView text1 = (TextView)findViewById(R.id.textViewP1);
        text1.setText(p1.name);

        TextView text2 = (TextView)findViewById(R.id.textViewP2);
        text2.setText(p2.name);

        TextView text3 = (TextView)findViewById(R.id.textViewP3);
        text3.setText(p3.name);

    }

    /** Called when the user taps the Send button */

    public void moveDisplayAnswerP1(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra("ID", 1);
        startActivity(intent);
    }
    public void moveDisplayAnswerP2(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra("ID", 2);
        startActivity(intent);
    }
    public void moveDisplayAnswerP3(View view) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra("ID", 3);
        startActivity(intent);
    }

    public void moveSelectChampion(View view) {
        Intent intent = new Intent(this, SelectChampionActivity.class);
        startActivity(intent);
    }




}
