package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SelectChampionActivity extends AppCompatActivity {

    private int maxPlayerNum = 5;
    private RadioButton[] radioButtons = new RadioButton[maxPlayerNum];
    private TextView[] playerViews = new TextView[maxPlayerNum];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_champion);

        Globals globals = (Globals) this.getApplication();

        radioButtons[0] = (RadioButton) findViewById(R.id.radioButton1);
        radioButtons[1] = (RadioButton) findViewById(R.id.radioButton2);
        radioButtons[2] = (RadioButton) findViewById(R.id.radioButton3);
        radioButtons[3] = (RadioButton) findViewById(R.id.radioButton4);
        radioButtons[4] = (RadioButton) findViewById(R.id.radioButton5);

        playerViews[0] = (TextView)findViewById(R.id.textViewAns1);
        playerViews[1] = (TextView)findViewById(R.id.textViewAns2);
        playerViews[2] = (TextView)findViewById(R.id.textViewAns3);
        playerViews[3] = (TextView)findViewById(R.id.textViewAns4);
        playerViews[4] = (TextView)findViewById(R.id.textViewAns5);

        // Textviewはdefault:invisible設定
        for (int i=0; i<globals.playerNum; i++){
            playerViews[i].setText(globals.playersGlobal.get(i).name);
            radioButtons[i].setText(globals.playersGlobal.get(i).answer);
            radioButtons[i].setVisibility(View.VISIBLE);
            playerViews[i].setVisibility(View.VISIBLE);
        }
    }

    public void showChampion(View view) {
        String text = "何も選択されていません";
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if(checkedId != -1) {
            text = (String)((RadioButton)findViewById(checkedId)).getText();
        }
        TextView textChamp = (TextView)findViewById(R.id.textViewError);
        textChamp.setText(text);
     }



    public void decideChamp(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if(checkedId != -1) {
            String text = (String)((RadioButton)findViewById(checkedId)).getText();
            Intent intent = new Intent(this, DisplayChampionActivity.class);
            intent.putExtra("answer", text);
            startActivity(intent);
        }else{
            TextView textChamp = (TextView)findViewById(R.id.textViewError);
            textChamp.setText("何も選択されていません");
        }

    }


}
