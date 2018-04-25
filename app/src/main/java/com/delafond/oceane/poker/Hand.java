package com.delafond.oceane.poker;

import java.util.ArrayList;

public class Hand {

    private SetGame theSet;
    private Player dealer;
    private Player nonDealer;
    private Player[] thePlayers;
    private Round[] theRounds;
    private Round currentRound;
    private int pot;
    private ArrayList<Card> cardsOnTable;

    public Hand(SetGame theSet, Player dealer, Player nonDealer) {
        this.theSet = theSet;
        this.dealer = dealer;
        this.nonDealer = nonDealer;
        thePlayers = new Player[2];
        theRounds = new Round[4];
        cardsOnTable = new ArrayList<Card>();

        thePlayers[0] = dealer;
        thePlayers[1] = nonDealer;
        dealer.setButton(true);

        theRounds[0] = new FirstBettingRound(this);

        currentRound = theRounds[0];
        this.pot = 0;

        for (int i = 0; i < 2; i++) {
            thePlayers[i].setCurrentHand(this);
            thePlayers[i].setHand(theSet.getAndRemoveNextCard(), theSet.getAndRemoveNextCard());
            thePlayers[i].payBlind();
        }
    }

    public SetGame getTheSet() {
        return theSet;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot += pot;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getNonDealer() {
        return nonDealer;
    }

    public ArrayList<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(Card theCard) {
        cardsOnTable.add(theCard);
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public void nextRound() {
        if (theRounds.length == 1) {
            theRounds[1] = new Flop(this);
        } else if (theRounds.length == 2) {
            theRounds[2] = new Turn(this);
        } else if (theRounds.length == 3) {
            theRounds[3] = new River(this);
        } else {
            showdown();
        }
    }

    public void endRoundTapis() {

    }

    public Player showdown() {
        return null;
    }

    public void endHand(boolean isDealerTheWinner) {
        if (isDealerTheWinner) {
            dealer.modifSomme(pot);
        } else {
            nonDealer.modifSomme(pot);
        }
        dealer.setButton(false);
        nonDealer.setButton(true);
        Game.newHand();
    }
}
