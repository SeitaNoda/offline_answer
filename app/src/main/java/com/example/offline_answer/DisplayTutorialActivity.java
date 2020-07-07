package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DisplayTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tutorial);
    }

    public void manageQuestion(View view){
        Intent intent = new Intent(this, ManageQuestionActivity.class);
        startActivity(intent);
    }


}
