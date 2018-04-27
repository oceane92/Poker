package com.delafond.oceane.poker.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.delafond.oceane.poker.entity.Player;
import com.delafond.oceane.poker.Gameplay.Card;
import com.delafond.oceane.poker.Gameplay.Game;
import com.delafond.oceane.poker.R;

public class GameActivity extends Fragment implements View.OnClickListener {


    ImageView CardPlayer1,CardPlayer2,cardRiver1,cardRiver2,cardRiver3,cardRiver4,cardRiver5,imageViewCardPlayerTwo1,imageViewCardPlayerTwo2 ;
    TextView namePlayer1,namePlayer2,ValueTokenPlayer1,ValueTokenPlayer2,ValuePot1,ValuePot2,curentplayer;
    IcontrollerMain controller;
    Card cardPalyer1;
    Card cardPalyer2;
    Player player1,player2;
    public static int relance  ;
    LayoutInflater inflater;

    Button ch,ca,fo,ta,ra;

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
        this.inflater = inflater;

        namePlayer1 = (TextView) v.findViewById(R.id.p1);
        CardPlayer1 = (ImageView) v.findViewById(R.id.imageViewCardPlayer1);
        CardPlayer2 = (ImageView) v.findViewById(R.id.imageViewCardPlayer2);
        imageViewCardPlayerTwo1 = (ImageView)v.findViewById(R.id.imageViewCardPlayerTwo1);
        imageViewCardPlayerTwo2 = (ImageView)v.findViewById(R.id.imageViewCardPlayerTwo2);



        //Instanciation des buttons

        ch = (Button) v.findViewById(R.id.idCh);
        ch.setOnClickListener(this);
        ca = (Button) v.findViewById(R.id.idCa);
        ca.setOnClickListener(this);
        fo = (Button) v.findViewById(R.id.idFo);
        fo.setOnClickListener(this);
        ta = (Button) v.findViewById(R.id.idTa);
        ta.setOnClickListener(this);
        ra = (Button) v.findViewById(R.id.idRa);
        ra.setOnClickListener(this);

        this.player1 = new Player("Player 1 ");
        this.player2 = new Player("Player 2 ");
        Game.initialize(this.player1,this.player2);
        this.cardPalyer1 = new Card(8,1) ;
        this.cardPalyer2  = new Card(9,1) ;
        CardPlayer1.setOnClickListener(this);
        CardPlayer2.setOnClickListener(this);

        ValueTokenPlayer1 = (TextView) v.findViewById(R.id.ValueTokenPlayer1);
        ValueTokenPlayer2 = (TextView) v.findViewById(R.id.ValueTokenPlayer2);

        ValuePot1 = (TextView) v.findViewById(R.id.tokenPot1);
        ValuePot2 = (TextView) v.findViewById(R.id.tokenPot2);


        curentplayer = (TextView) v.findViewById(R.id.idCurentePlayer);
        //mettre images cartes
        this.setCardPlayer1();
        this.setCardPlayer2();

        //donner les valeurs des tokens
        this.SetValuesToken();

        cardRiver1 = (ImageView) v.findViewById(R.id.cardRiver1)          ;


        return v;
    }

    public void setCardPlayer1(){
        Card card  =Game.p1.getFirstCard();
        String imageName = "c"+card.getRank()+"_"+card.getSuit();
        CardPlayer1.setImageResource(getActivity().getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName() ));
    }

    public void setCardPlayer2(){
        Card card  =Game.p1.getSecondCard();
        String imageName = "c"+card.getRank()+"_"+card.getSuit();
        CardPlayer2.setImageResource(getActivity().getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName() ));
    }
    public void setCardPlayer3(){
        Card card  =Game.p2.getFirstCard();
        String imageName = "c"+this.cardPalyer1.getRank()+"_"+this.cardPalyer1.getSuit();
        imageViewCardPlayerTwo1.setImageResource(getActivity().getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName() ));
    }

    public void setCardPlayer4(){
        Card card  =Game.p2.getSecondCard();
        String imageName = "c"+card.getRank()+"_"+card.getSuit();
        imageViewCardPlayerTwo2.setImageResource(getActivity().getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName() ));
    }



    @Override
    public void onClick(View v) {

        if (v.equals(ch)){
            this.NextTurn("ch");
        }
        if (v.equals(ca)){
            this.NextTurn("ca");
        }
        if (v.equals(ra)){
            this.popUpRaise();
            this.NextTurn("ra");
        }
        if (v.equals(ta)){
            this.NextTurn("ta");
        }
        if (v.equals(fo)){
            this.NextTurn("fo");
        }


    }

    public void popUpRaise(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder( getActivity() );
        View view1 = this.inflater.inflate( R.layout.pop_up_raise,null );
        final EditText valueRaise = (EditText) view1.findViewById(R.id.valueRaise) ;
        AlertDialog dialog = null;

        mBuilder.setView( view1 );

        mBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameActivity.relance = Integer.parseInt(valueRaise.getText().toString());
                        dialog.cancel();
                    }
                });

        dialog = mBuilder.create() ;
        dialog.show();
    }



    public void SetValuesToken(){
        ValueTokenPlayer1.setText(Game.p1.getSomme()+"");
        ValueTokenPlayer2.setText(Game.p2.getSomme()+"");
        if (Game.p1.isDealer()){
            ValuePot2.setText(Game.currentHand.getCurrentRound().getPotDealer()+"");
            ValuePot1.setText(Game.currentHand.getCurrentRound().getPotNonDealer()+"");

        }else {
            ValuePot1.setText(Game.currentHand.getCurrentRound().getPotDealer()+"");
            ValuePot2.setText(Game.currentHand.getCurrentRound().getPotNonDealer()+"");
        }
        this.CurrentPlayer();

    }


    public void revealCards(){
        for (int e = 1; e<=5;e++){

            if (e<=Game.currentHand.getCardsOnTable().size()){

                Card c = Game.currentHand.getCardsOnTable().get(e-1);
                String imageName = "c"+c.getRank()+"_"+c.getSuit();
                ImageView cardReveal = (ImageView) this.getView().findViewById( getActivity().getResources().getIdentifier("cardRiver"+e,"id",getActivity().getPackageName()) );
                cardReveal.setImageResource(getActivity().getResources().getIdentifier( imageName, "drawable", getActivity().getPackageName() ));
            }else {
                ImageView cardReveal = (ImageView) this.getView().findViewById( getActivity().getResources().getIdentifier("cardRiver"+e,"id",getActivity().getPackageName()) );
                cardReveal.setImageResource(getActivity().getResources().getIdentifier( "card_face_down", "drawable", getActivity().getPackageName() ));
            }

        }
    }

    public void CurrentPlayer(){

        boolean[] possibleActions = Game.currentHand.getCurrentRound().possibleActions();
        ch.setEnabled(possibleActions[0]);
        ca.setEnabled(possibleActions[1]);
        ra.setEnabled(possibleActions[2]);
        fo.setEnabled(possibleActions[3]);

        if(Game.currentHand.getCurrentRound().getCptRound()%2==0){

            if (Game.p1.isDealer()){
                curentplayer.setText("current : Player 1 ");
                this.setCardPlayer1();
                this.setCardPlayer2();
                imageViewCardPlayerTwo1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                imageViewCardPlayerTwo2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));

            }else{
                curentplayer.setText("current : Player 2 ");
                this.setCardPlayer3();
                this.setCardPlayer4();
                CardPlayer1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                CardPlayer2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
            }
        }else {
            if (Game.p1.isDealer()){
                curentplayer.setText("current : Player 2 ");
                this.setCardPlayer3();
                this.setCardPlayer4();
                CardPlayer1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                CardPlayer2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
            }else{
                curentplayer.setText("current : Player 1 ");
                this.setCardPlayer1();
                this.setCardPlayer2();
                imageViewCardPlayerTwo1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                imageViewCardPlayerTwo2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
            }
        }

    }

    public void switchCardsVisible(){
        /*this.setCardPlayer3();
        this.setCardPlayer4();*/

        if(Game.currentHand.getCurrentRound().getCptRound()%2==0){

            if (Game.p1.isDealer()){
                this.setCardPlayer1();
                this.setCardPlayer2();
                imageViewCardPlayerTwo1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                imageViewCardPlayerTwo2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));

            }else{
                this.setCardPlayer3();
                this.setCardPlayer4();
                CardPlayer1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                CardPlayer2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
            }
        }else {
            if (Game.p1.isDealer()){
                this.setCardPlayer1();
                this.setCardPlayer2();
                imageViewCardPlayerTwo1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                imageViewCardPlayerTwo2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));

            }else{
                this.setCardPlayer3();
                this.setCardPlayer4();
                CardPlayer1.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
                CardPlayer2.setImageResource(getActivity().getResources().getIdentifier("card_face_down", "drawable", getActivity().getPackageName() ));
            }
        }
    }


    public void NextTurn(String com){
        switch (com) {
            case "ch":
                Game.currentHand.getCurrentRound().check();
                this.SetValuesToken();
                this.revealCards();
                // this.switchCardsVisible();
                break;
            case "ca":
                Game.currentHand.getCurrentRound().call();
                this.SetValuesToken();
                this.revealCards();
                // this.switchCardsVisible();
                break;
            case "ra":
                Log.e("relance:",relance+"");
                Game.currentHand.getCurrentRound().raise(relance);
                this.SetValuesToken();
                this.revealCards();
                //this.switchCardsVisible();
                break;
            case "ta":
                Game.currentHand.getCurrentRound().tapis();
                this.SetValuesToken();
                this.revealCards();
                //this.switchCardsVisible();
                break;
            case "fo":
                Game.currentHand.getCurrentRound().fold();
                this.SetValuesToken();
                this.revealCards();
                //this.switchCardsVisible();
        }
    }

}
