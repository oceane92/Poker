package com.delafond.oceane.poker.Gameplay;

public class Flop extends Round {


    public Flop(Hand theHand) {
        super(theHand);

        for (int i = 0; i < 3; i++) {
            theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
        }
    }
}
