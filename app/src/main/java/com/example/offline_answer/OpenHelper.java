package com.example.offline_answer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    public static final int DATABASE_VERSION = 1;
    // データーベース名
    public static final String DATABASE_NAME = "QuestionDB.db";
    private static final String TABLE_NAME = "questiondb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "question";
    private static final String COLUMN_NAME_SUBTITLE = "favorite";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(
                SQL_CREATE_ENTRIES
        );
        saveData(db, "人前でやると恥ずかしいことは？", 1);
        saveData(db, "ご飯が何杯でも食べられるおかずは？", 1);
        saveData(db, "最もうつくしいものは？", 1);
        saveData(db, "プレゼントされたら絶対に喜ぶ一品は？", 1);
        saveData(db, "やられるとイラっとすることは？", 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除して新規作成
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveData(SQLiteDatabase db, String question, int favo){
        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("favorite", favo);

        db.insert("questiondb", null, values);
    }


}
