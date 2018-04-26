package com.delafond.oceane.poker.Gameplay;

public class River extends Round {

	public River(Hand theHand) {

        super(theHand);
        theHand.setCardsOnTable(theHand.getTheSetGame().getAndRemoveNextCard());
    }
}
