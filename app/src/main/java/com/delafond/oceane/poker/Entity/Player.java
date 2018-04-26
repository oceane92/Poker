package com.delafond.oceane.poker.Entity;

import com.delafond.oceane.poker.Gameplay.Card;
import com.delafond.oceane.poker.Gameplay.Game;
import com.delafond.oceane.poker.Gameplay.Hand;

public class Player {
    private String name;
    private int somme;
    private boolean button;
    private Card[] hand;
    private Hand currentHand;

    public Player(String name) {
        this.name = name;
        this.somme = 15000;
        hand = new Card[2];
    }

    public int getSomme() {
        return somme;
    }
    
    public String getName() {
    	return name;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card c1, Card c2) {
        hand[0] = c1;
        hand[1] = c2;
    }

    public boolean isDealer() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    //Retourne la première carte
    public Card getFirstCard() {
    	return hand[0];
    }

    //Retourne la seconde carte
    public Card getSecondCard() {
    	return hand[1];
    }

    //Ajoute ou retire la somme d'argent du joueur
    public void modifSomme(int amount) {
        this.somme += amount;
    }

    //Met à zéro la sommedu joueur lorsqu'il fait tapis
    public void tapis() {
        this.somme = 0;
    }

    //Lancée à chaque tour, paye la blind
    public void payBlind() {
        if (button) {
            currentHand.getCurrentRound().setPotDealer(Game.bigBlind);
            modifSomme(-(Game.bigBlind));
        } else {
            currentHand.getCurrentRound().setPotNonDealer(Game.bigBlind / 2);
            modifSomme(-(Game.bigBlind/2));
        }
    }
}
