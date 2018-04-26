package com.delafond.oceane.poker.Gameplay;

public class Turn extends Round {

	public Turn(Hand theHand) {

        super(theHand);
        theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
    }
}
