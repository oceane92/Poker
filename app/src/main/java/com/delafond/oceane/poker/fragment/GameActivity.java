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
import android.widget.TextView;

import com.delafond.oceane.poker.Card;
import com.delafond.oceane.poker.R;

public class GameActivity extends Fragment implements View.OnClickListener {


    ImageView CardPlayer1,CardPlayer2,CardPlayer3,CardPlayer4,CardPlayer5 ;
    TextView namePlayer1,namePlayer2;
    IcontrollerMain controller;
    Card cardPalyer1;
    Card cardPalyer2;

    public IcontrollerMain getController() {
        return controller;
    }

    public void setController(IcontrollerMain controller) {
        this.controller = controller;
    }

    public void setPlayerName1(String name){
        this.namePlayer1.setText(name);
    }
    public void setPlayerName2(String name){
        this.namePlayer2.setText(name);
    }


    /**
     * Création d'une vue qui veux entrer das le fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_game,null );

        namePlayer1 = (TextView) v.findViewById(R.id.p1);
        CardPlayer1 = (ImageView) v.findViewById(R.id.imageViewCardPlayer1);
        CardPlayer2 = (ImageView) v.findViewById(R.id.imageViewCardPlayer2);

        this.cardPalyer1 = new Card(8,1) ;
        this.cardPalyer2  = new Card(9,1) ;

        CardPlayer1.setOnClickListener(this);
        CardPlayer2.setOnClickListener(this);


        this.setCardPlayer1(this.cardPalyer1);
        this.setCardPlayer2(this.cardPalyer2);
        //CardPlayer3.setOnClickListener(this);
        //CardPlayer4.setOnClickListener(this);
        //CardPlayer5.setOnClickListener(this);

        return v;
    }

    public void setCardPlayer1(Card card){
        this.cardPalyer1 = card ;
        String imageName = "c"+this.cardPalyer1.getRank()+"_"+this.cardPalyer1.getSuit();
        CardPlayer1.setImageResource(getActivity().getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName() ));
    }

    public void setCardPlayer2(Card card){
        this.cardPalyer2 = card ;
        String imageName = "c"+this.cardPalyer2.getRank()+"_"+this.cardPalyer2.getSuit();
        CardPlayer2.setImageResource(getActivity().getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName() ));
    }



    @Override
    public void onClick(View v) {

        if (v.equals(CardPlayer1)){
            Log.i("Card", "onClick: Test image 1");
        }
        if (v.equals(CardPlayer2)){
            Log.i("Card", "onClick: Test image 2");
        }/*
        if (v.equals(CardPlayer3)){
            Log.i("Card", "onClick: Test image 3");
        }
        if (v.equals(CardPlayer4)){
            Log.i("Card", "onClick: Test image 4");
        }
        if (v.equals(CardPlayer5)){
            Log.i("Card", "onClick: Test image 5");
        }*/
    }

}
