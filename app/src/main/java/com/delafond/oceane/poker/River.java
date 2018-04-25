package com.delafond.oceane.poker;

public class River extends Round {

    public River(Hand theHand) {

        super(theHand);
        theHand.setCardsOnTable(theHand.getTheSet().getAndRemoveNextCard());
    }

}
