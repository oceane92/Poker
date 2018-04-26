package com.delafond.oceane.poker.Gameplay;

public class Flop extends Round {


    public Flop(Hand theHand) {
        super(theHand);
    	System.out.println("FLOP()");

        for (int i = 0; i < 3; i++) {
            theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
        }
    }
    
    public Flop(Hand theHand, boolean tapis) {
    	super(theHand);
    	System.out.println("FLOPTAPIS()");

        for (int i = 0; i < 3; i++) {
            theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
        }
    }
}
