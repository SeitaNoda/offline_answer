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

    private int playerNum = 3;
    private int maxPlayerNum = 5;
    private int minPlayerNum = 2;
    private Player[] players = new Player[maxPlayerNum];
    private TextView[] playerViews = new TextView[maxPlayerNum];
    private String[] playerIds = new String[playerNum];

    private String[] playerNameInit = {"test", "test2", "test3", "test4", "test5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals globals = (Globals) this.getApplication();

        playerNum = globals.playerNum;

        // TODO: べた書き対処
        playerViews[0] = findViewById(R.id.playerName1);
        playerViews[1] = findViewById(R.id.playerName2);
        playerViews[2] = findViewById(R.id.playerName3);
        playerViews[3] = findViewById(R.id.playerName4);
        playerViews[4] = findViewById(R.id.playerName5);

        // Textviewはdefault:invisible設定
        for (int i=0; i<playerNum; i++){
            if (globals.playersGlobal.get(i) == null) {
                playerViews[i].setText(playerNameInit[i]);
            }else {
                playerViews[i].setText(globals.playersGlobal.get(i).name);
            }
            playerViews[i].setVisibility(View.VISIBLE);
        }
    }

    public void moveQuestion(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayQuestionActivity.class);

        Globals globals = (Globals) this.getApplication();

        for (int i=0; i<playerNum; i++){
            players[i] = new Player();
            players[i].setName(playerViews[i].getText().toString());
            globals.playersGlobal.set(i, players[i]);
        }

        globals.playerNum = playerNum;

        String question = getQuestion();
        globals.question = question;

        startActivity(intent);

    }

    public void manageQuestion(View view){
        Intent intent = new Intent(this, ManageQuestionActivity.class);
        startActivity(intent);
    }


    public void addPlayer(View view){

        Globals globals = (Globals) this.getApplication();

        if ( playerNum < maxPlayerNum && playerNum >= minPlayerNum){
            playerNum++;
        }

        if (globals.playersGlobal.get(playerNum-1) == null) {
            playerViews[playerNum-1].setText(playerNameInit[playerNum-1]);
        }else {
            playerViews[playerNum-1].setText(globals.playersGlobal.get(playerNum-1).name);
        }
        playerViews[playerNum-1].setVisibility(View.VISIBLE);
    }

    public void subtractPlayer(View view){

        if ( playerNum <= maxPlayerNum && playerNum > minPlayerNum){
            playerNum--;
        }
        playerViews[playerNum].setVisibility(View.INVISIBLE);
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
