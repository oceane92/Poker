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
import android.widget.EditText;
import android.widget.TextView;

import com.delafond.oceane.poker.R;

public class HomeActivity extends Fragment implements View.OnClickListener {

    IcontrollerMain controller;
    Button buttonNewGame;
    Button buttonJoinParty;
    Button buttonScores;
    Button buttonExit;
    EditText editTextPseudo;

    public HomeActivity(){ }

    public IcontrollerMain getController() {
        return controller;
    }

    public void setController(IcontrollerMain controller) {
        this.controller = controller;
    }

    public String getName(){
        return this.editTextPseudo.getText().toString() ;
    }

    /**
     * Création d'une vue qui veux entrer das le fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout,null );

        editTextPseudo = (EditText) v.findViewById(R.id.editTextPseudo);
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

          Log.d("buttonJoinParty", "this.controller.onViewSelected(\"GameActivity\")");
            this.controller.onViewSelected("GameActivity");

        }
        if (v.equals(buttonScores)){
            this.controller.onViewSelected("Score");
        }
        if (v.equals(buttonExit)){
            this.controller.onViewSelected("Quitter");
        }
    }
}
