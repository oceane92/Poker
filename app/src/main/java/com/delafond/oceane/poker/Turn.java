package com.delafond.oceane.poker;

public class Turn extends Round {

    public Turn(Hand theHand) {

        super(theHand);
        theHand.setCardsOnTable(theHand.getTheSet().getAndRemoveNextCard());
    }

}
