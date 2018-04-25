package com.delafond.oceane.poker;

import java.util.ArrayList;
import java.util.Collections;

public class SetGame {

    private ArrayList<Card> theCards;

    public SetGame() {
        theCards = new ArrayList<Card>();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                theCards.add(new Card(i, j));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.theCards);
    }

    public void removeCards(int amount) {
        for (int i = 0; i < amount; i++) {
            theCards.remove(0);
        }
    }

    public Card getAndRemoveNextCard() {
        Card theCard = theCards.get(0);
        removeCards(1);
        return theCard;
    }

}
