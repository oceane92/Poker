package com.delafond.oceane.poker.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Base de données pour les scores
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PokerBase";
    private static final int CURRENT_DB_VERSION = 1;

    private static DatabaseManager instance;

    public static DatabaseManager getInstance(Context context){
        if(instance==null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DB_VERSION);
    }

    /**
     * Création de la base de données
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table score "+
                "(id INTEGER, player TEXT, adversary TEXT, winlose TEXT, "+
                "main FLOAT);");
    }

    /**
     * Mise à jour de la base de données si changement de version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        switch (i){
            case 1:
                //code sql 1->2
            case 2:
                //code sql 2->3
            case 3:
                //code sql 3->4
            default:
        }
    }
}