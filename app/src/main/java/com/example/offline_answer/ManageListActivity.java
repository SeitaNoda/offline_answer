package com.example.offline_answer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperColors;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManageListActivity extends AppCompatActivity {

    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_manage_list);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        setContentView(linearLayout);

        OpenHelper dbhelper = new OpenHelper(this);
        final SQLiteDatabase db = dbhelper.getWritableDatabase();
        final Intent reload = new Intent(this, ManageListActivity.class);


        Button button1 = new Button(this);
        button1.setText("Delete");
        linearLayout.addView(button1, createParam(WC, WC));


        Button button2 = new Button(this);
        button2.setText("Favorite");
        linearLayout.addView(button2, createParam(WC, WC));


        Button button3 = new Button(this);
        button3.setText("Un-Favo");
        linearLayout.addView(button3, createParam(WC, WC));


        final CheckBox[] CheckBox = new CheckBox[100];
        final int[] id = new int[100];


        //record:list

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ScrollView.LayoutParams(MP, WC));
        linearLayout.addView(scrollView);

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout2);

        Cursor cursor = db.query(
                "questiondb",
                new String[] { "_id","question","favorite" },
                null,
                null,
                null,
                null,
                null
        );

        int i = 0;

        if(cursor.moveToFirst()){
            do{
                StringBuilder sbuilder = new StringBuilder();
                sbuilder.append(cursor.getInt(2));
                sbuilder.append(":  ");
                sbuilder.append(cursor.getString(1));
                id[i] = cursor.getInt(0);
                CheckBox[i] = new CheckBox(this);
                linearLayout2.addView(CheckBox[i], createParam(WC,WC));
                CheckBox[i].setText(sbuilder.toString());
                i++;

            }while(cursor.moveToNext());
        }else{
            cursor.close();
        }

        final int record_num = i;
        System.out.println(record_num);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Delete Button Clicked");
                List<String> checkdid = new ArrayList<String>();
                for(int j = 0; j<record_num; j++){
                    if(CheckBox[j].isChecked()){
                        checkdid.add(Integer.toString(id[j]));
                    }
                }

                if(checkdid.size() != 0) {
                     for(String deleteID : checkdid) {
                         System.out.println(deleteID);
                         db.delete("questiondb", "_id = ?", new String[]{deleteID});
                         startActivity(reload);
                     }
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Favorite Button Clicked");
                List<String> checkdid = new ArrayList<String>();
                for(int j = 0; j<record_num; j++){
                    if(CheckBox[j].isChecked()){
                        checkdid.add(Integer.toString(id[j]));
                    }
                }

                if(checkdid.size() != 0) {
                    ContentValues values = new ContentValues();
                    for(String switchID : checkdid) {
                        values.put("favorite",1);
                        db.update("questiondb", values, "_id=?", new String[]{switchID});
                        startActivity(reload);
                    }
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Favorite Button Clicked");
                List<String> checkdid = new ArrayList<String>();
                for(int j = 0; j<record_num; j++){
                    if(CheckBox[j].isChecked()){
                        checkdid.add(Integer.toString(id[j]));
                    }
                }

                if(checkdid.size() != 0) {
                    ContentValues values = new ContentValues();
                    for(String switchID : checkdid) {
                        values.put("favorite",0);
                        db.update("questiondb", values, "_id=?", new String[]{switchID});
                        startActivity(reload);
                    }
                }
            }
        });



    }




    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }


}
