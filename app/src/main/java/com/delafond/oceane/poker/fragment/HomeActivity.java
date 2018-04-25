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

import com.delafond.oceane.poker.R;

public class HomeActivity extends Fragment implements View.OnClickListener {

    IcontrollerMain controller;
    Button buttonNewGame;
    Button buttonJoinParty;
    Button buttonScores;
    Button buttonExit;

    public HomeActivity(){ }

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
        View v = inflater.inflate(R.layout.activity_main,null );

        buttonNewGame = (Button) v.findViewById(R.id.buttonNewGame);
        buttonNewGame.setOnClickListener(this);
        buttonJoinParty = (Button) v.findViewById(R.id.buttonJoinParty);
        buttonJoinParty.setOnClickListener(this);
        buttonScores = (Button) v.findViewById(R.id.buttonScores);
        buttonScores.setOnClickListener(this);
        buttonExit = (Button) v.findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(this);

        return v ;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(buttonNewGame)){
            this.controller.onViewSelected("GameActivity");
        }
        if (v.equals(buttonJoinParty)){
            Intent myIntent = new Intent(v.getContext(), GameActivity.class);
            startActivityForResult(myIntent, 0);
        }
        if (v.equals(buttonScores)){
            Log.i("ButtonAction", "onClick: buttonScores");
        }
        if (v.equals(buttonExit)){
            Log.i("ButtonAction", "onClick: buttonExit");
        }
    }
}
