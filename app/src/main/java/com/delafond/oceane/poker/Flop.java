package com.delafond.oceane.poker;

public class Flop extends Round {

    public Flop(Hand theHand) {
        super(theHand);

        for (int i = 0; i < 3; i++) {
            theHand.setCardsOnTable(theHand.getTheSet().getAndRemoveNextCard());
        }
    }
}
