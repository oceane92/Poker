package com.delafond.oceane.poker.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.delafond.oceane.poker.R;
import com.delafond.oceane.poker.model.ScoreAdapter;
import com.delafond.oceane.poker.model.Scores;

import java.util.ArrayList;

public class ScoreActivity extends Fragment {

    ArrayList<Scores> scores = new ArrayList<Scores>();
    IcontrollerMain controller;
    ListView listViewScores ;

    public ScoreActivity() {
    }

    public IcontrollerMain getController() {
        return controller;
    }

    public void setController(IcontrollerMain controller) {
        this.controller = controller;
    }


    /**
     * Création d'une vue qui veux entrer das le fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_scores,null );

        scores.add(new Scores("Joueur 1","Test","2:30","50%"));
        scores.add(new Scores("Joueur 2", "Test", "2:50","20%"));

        listViewScores = (ListView) v.findViewById(R.id.ListViewScore);
        ScoreAdapter scoresAdapter = new ScoreAdapter(getActivity(), scores);
        listViewScores.setAdapter(scoresAdapter);

        return v ;
    }
}
