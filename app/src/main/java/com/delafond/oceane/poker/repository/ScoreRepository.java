package com.delafond.oceane.poker.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.delafond.oceane.poker.model.Scores;

import java.util.ArrayList;

public class ScoreRepository implements IScoreRepository {

    private static ScoreRepository instance;
    private DatabaseManager dbm;

    private ScoreRepository(Context context){
        dbm = DatabaseManager.getInstance(context);
    }

    public static ScoreRepository getInstance(Context context){
        if(instance==null){
            instance= new ScoreRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(Scores s) {
        if(isScores(s)) return false;
        ContentValues values = new ContentValues();
        values.put("title", s.getNamePlayer());
        values.put("score", s.getScore());
        long line= dbm.getWritableDatabase().insert("scores", null, values);
        return line != 0;
    }

    @Override
    public boolean remove(Scores s) {
        String[] identifier = {s.getScore()};
        long line= dbm.getWritableDatabase().delete("score", "title=? and artist=? and album=?", identifier);
        return line != 0;
    }

    @Override
    public boolean isScores(Scores s) {
        String[] identifier = {s.getScore()};
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from score where title=? and artist=? and album=?;", identifier);
        return c.getCount()>0;
    }

    @Override
    public ArrayList<Scores> getAll() {
        ArrayList<Scores> musics = new ArrayList<Scores>();
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from score ", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Scores m= new Scores();
            m.setScore(c.getString(0));
            musics.add(m);
            c.moveToNext();
        }
        c.close();
        return musics;
    }
}