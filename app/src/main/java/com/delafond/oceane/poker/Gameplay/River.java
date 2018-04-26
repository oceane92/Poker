package com.delafond.oceane.poker.Gameplay;

public class River extends Round {

	public River(Hand theHand) {

        super(theHand);
    	System.out.println("RIVER()");
        theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
    }
	
	public River(Hand theHand, boolean tapis) {

        super(theHand);
    	System.out.println("RIVER()");
        theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
    }
}
