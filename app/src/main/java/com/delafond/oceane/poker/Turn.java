package com.delafond.oceane.poker;

public class Turn extends Round {

	public Turn(Hand theHand) {

        super(theHand);
    	System.out.println("TURN()");
        theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
    }
	
	public Turn(Hand theHand, boolean tapis) {

        super(theHand);
    	System.out.println("TURNTAPIS()");
        theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
    }
}
