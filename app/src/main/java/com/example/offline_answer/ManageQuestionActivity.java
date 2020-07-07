package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ManageQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_question);
    }

    public void submitQuestion(View view){
        OpenHelper dbhelper = new OpenHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        EditText edit = findViewById(R.id.editQuestion);
        String q = edit.getText().toString();

        ContentValues values = new ContentValues();
        values.put("question",q);
        values.put("favorite",1);
        long id = db.insert("questiondb", null, values);

        TextView error = findViewById(R.id.textError);
        if (id < 0){
            error.setText("登録に失敗");
        }else{ error.setText("");}

        edit.getEditableText().clear();
    }

    public void manageQuestionList(View view) {
        Intent intent = new Intent(this, ManageListActivity.class);
        startActivity(intent);
    }


}
