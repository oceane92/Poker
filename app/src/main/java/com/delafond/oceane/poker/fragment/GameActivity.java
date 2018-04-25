package com.delafond.oceane.poker.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.delafond.oceane.poker.R;

public class GameActivity extends Fragment implements View.OnClickListener {

    public Button buttonScore;
    ImageView CardPlayer1,CardPlayer2,CardPlayer3,CardPlayer4,CardPlayer5 ;
    IcontrollerMain controller;

    public IcontrollerMain getController() {
        return controller;
    }

    public void setController(IcontrollerMain controller) {
        this.controller = controller;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(buttonScore)){
            Intent myIntent = new Intent(v.getContext(), ScoreActivity.class);
            startActivityForResult(myIntent, 0);
        }
        if (v.equals(CardPlayer1)){
            Log.i("Card", "onClick: Test image 1");
        }
        if (v.equals(CardPlayer2)){
            Log.i("Card", "onClick: Test image 2");
        }
        if (v.equals(CardPlayer3)){
            Log.i("Card", "onClick: Test image 3");
        }
        if (v.equals(CardPlayer4)){
            Log.i("Card", "onClick: Test image 4");
        }
        if (v.equals(CardPlayer5)){
            Log.i("Card", "onClick: Test image ");
        }
    }

    /**
     * Création d'une vue qui veux entrer das le fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_game,null );

        return v;
    }
}
