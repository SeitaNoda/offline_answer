package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

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

        TextView p1Name = findViewById(R.id.playerName1);
        TextView p2Name = findViewById(R.id.playerName2);
        TextView p3Name = findViewById(R.id.playerName3);

        p1.setName(p1Name.getText().toString());
        p2.setName(p2Name.getText().toString());
        p3.setName(p3Name.getText().toString());

        globals.p1 = p1;
        globals.p2 = p2;
        globals.p3 = p3;

        String question = getQuestion();
        globals.question = question;

        startActivity(intent);

    }

    public void MoveTutorial(View view){

        Intent intent = new Intent(this, DisplayTutorialActivity.class);
        startActivity(intent);

    }

    private String getQuestion(){
        OpenHelper dbhelper = new OpenHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query(
                "questiondb",
                new String[] { "question" },
                "favorite like ?",
                new String[] {"1"},
                null,
                null,
                "random()",
                "1"
        );

        String[] mojiList = {"あ","い","う","え","お","か","き","く","こ","さ","し","す",
                "そ","た","て","と","な","は","ひ","ほ","ま","み","も","が","ぎ","ご","ざ","じ",
                "だ","ど","ば","び","ぼ","ぱ","ぽ","わ","ら","り","れ","ろ","や","ゆ","よ"};

        int rnd = new Random().nextInt(mojiList.length);
        String moji = mojiList[rnd];

        String q = "問題を取得できませんでした。";

        if(cursor.moveToFirst()){
            do{
                q = cursor.getString(cursor.getColumnIndex("question"));
            }while(cursor.moveToNext());
            q = "「" + moji + "」から始まる、"+ q ;
        }else{
            q = "問題を取得できませんでした。";
        }
        cursor.close();

        return(q);
    }


}
