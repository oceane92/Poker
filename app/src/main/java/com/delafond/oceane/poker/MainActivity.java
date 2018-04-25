package com.delafond.oceane.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.delafond.oceane.poker.fragment.GameActivity;
import com.delafond.oceane.poker.fragment.HomeActivity;
import com.delafond.oceane.poker.fragment.IcontrollerMain;

public class MainActivity extends AppCompatActivity implements IcontrollerMain {

    private HomeActivity homeActivity;
    private GameActivity gameActivity;

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
        }

        Button buttonJoinParty = (Button) findViewById(R.id.buttonJoinParty);
        buttonJoinParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ButtonAction", "onClick: buttonJoinParty");
            }
        });
    }
}
