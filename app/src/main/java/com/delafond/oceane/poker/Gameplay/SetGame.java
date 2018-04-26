package com.delafond.oceane.poker.Gameplay;

import java.util.ArrayList;
import java.util.Collections;

public class SetGame {
    private ArrayList<Card> theCards;

    //Génère les 52 cartes dans le paquet
    public SetGame() {
        theCards = new ArrayList<Card>();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                theCards.add(new Card(i, j));
            }
        }
    }

    //Mélange les cartes
    public void shuffle() {
        Collections.shuffle(this.theCards);
    }

    //Enlève le nombre de cartes passé en paramètre du paquet
    public void removeCards(int amount) {
        for (int i = 0; i < amount; i++) {
            theCards.remove(0);
        }
    }

    //Retourne une carte et enlève cette dernière du paquet
    public Card getAndRemoveNextCard() {
        Card theCard = theCards.get(0);
        removeCards(1);
        return theCard;
    }
}
