package com.delafond.oceane.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.delafond.oceane.poker.fragment.GameActivity;
import com.delafond.oceane.poker.fragment.HomeActivity;
import com.delafond.oceane.poker.fragment.IcontrollerMain;
import com.delafond.oceane.poker.fragment.ScoreActivity;

public class MainActivity extends AppCompatActivity implements IcontrollerMain {

    private HomeActivity homeActivity;
    private GameActivity gameActivity;
    private ScoreActivity scoreActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creation du fragment (une vue) Home
        homeActivity = (HomeActivity) getFragmentManager().findFragmentById(R.id.idMainFrame);//idMainFrame -> fragment qui va recevoir toutes les vues
        homeActivity = new HomeActivity();
        getFragmentManager().beginTransaction().add(R.id.idMainFrame, homeActivity).commit();
        homeActivity.setController(this);

        // Creation du fragment (une vue) GameActivity
        gameActivity = (GameActivity) getFragmentManager().findFragmentById(R.id.idMainFrame);//idMainFrame -> fragment qui va recevoir toutes les vues
        gameActivity = new GameActivity();
        getFragmentManager().beginTransaction().add(R.id.idMainFrame, gameActivity).commit();
        gameActivity.setController(this);
        //on cache le fragment gameActivity
        getFragmentManager().beginTransaction().hide(gameActivity).show(homeActivity).commit();

        // Creation du fragment (une vue) scoreActivity
        scoreActivity = (ScoreActivity) getFragmentManager().findFragmentById(R.id.idMainFrame);//idMainFrame -> fragment qui va recevoir toutes les vues
        scoreActivity = new ScoreActivity();
        getFragmentManager().beginTransaction().add(R.id.idMainFrame,scoreActivity).commit();
        scoreActivity.setController(this);
        //on cache le fragment gameActivity
        getFragmentManager().beginTransaction().hide(scoreActivity).show(homeActivity).commit();

    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.idMainFrame) != null && gameActivity.isVisible()) {
            getFragmentManager().beginTransaction().
                    hide(gameActivity)
                    .show(homeActivity)
                    .commit();
        } else if (findViewById(R.id.idMainFrame) != null && scoreActivity.isVisible()) {
            getFragmentManager().beginTransaction().
                    hide(scoreActivity)
                    .show(homeActivity)
                    .commit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onViewSelected(String NomFragment) {
        if(findViewById(R.id.idMainFrame)!=null){
            if(NomFragment.equals("GameActivity")) {

                getFragmentManager().beginTransaction().
                        hide(homeActivity)
                        .show(gameActivity)
                        .commit();
            }
            if(NomFragment.equals("Score")) {
                getFragmentManager().beginTransaction().
                        hide(homeActivity)
                        .show(scoreActivity)
                        .commit();
            }

            if(NomFragment.equals("Quitter")) {
                finish();
            }

        }



    }
}
