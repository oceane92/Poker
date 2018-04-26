package com.delafond.oceane.poker.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String TABLE_SCORE = "table_score";
    private static final String COL_ID = "ID";
    private static final String COL_PLAYER = "Player";
    private static final String COL_OPPONENT = "Opponent";
    private static final String COL_WINLOSE = "WinOrLose";
    private static final String COL_MAIN = "Main";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_SCORE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PLAYER + " TEXT NOT NULL, "
            + COL_OPPONENT + " TEXT NOT NULL, " + COL_WINLOSE + " TEXT NOT NULL, " + COL_MAIN + " TEXT NOT NULL);";

    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Création de la base de données
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_SCORE + ";");
        onCreate(db);
    }

}
