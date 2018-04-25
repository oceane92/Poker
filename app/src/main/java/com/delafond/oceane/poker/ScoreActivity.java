package com.delafond.oceane.poker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.delafond.oceane.poker.model.ScoreAdapter;
import com.delafond.oceane.poker.model.Scores;

import java.util.ArrayList;

public class ScoreActivity extends Activity {

    ArrayList<Scores> scores = new ArrayList<Scores>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_scores);

        scores.add(new Scores("Joueur 1","Test","2:30","20%"));
        scores.add(new Scores("Joueur 2", "Test", "2:50","30%"));

        ListView listViewScores = (ListView) findViewById(R.id.ListViewScore);
        ScoreAdapter scoresAdapter = new ScoreAdapter(this, scores);
        listViewScores.setAdapter(scoresAdapter);
    }
}
