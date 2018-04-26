package com.delafond.oceane.poker.Gameplay;

public class FirstBettingRound extends Round {

    //Pré-Flop
    public FirstBettingRound(Hand theHand) {
        super(theHand);
    }

    @Override
    public void check() {
        super.endRound();
    }

    @Override
    public void call() {
        if (this.cptRound%2 == 0) {
            if (theHand.getDealer().getSomme() < this.potNonDealer - this.potDealer) {
                this.tapis();
            } else {
                theHand.getDealer().modifSomme(-(this.potNonDealer - this.potDealer));
                this.potDealer = this.potNonDealer;
            }
        } else {
            if (theHand.getNonDealer().getSomme() < this.potDealer - this.potNonDealer) {
                this.tapis();
            } else {
                theHand.getNonDealer().modifSomme(-(this.potDealer - this.potNonDealer));
                this.potNonDealer = this.potDealer;
            }
        }

        if (tapis) {
        	theHand.endRoundTapis();
        } else {
            if (cptRound>1) {
                super.endRound();
            } else {
                cptRound++;
            }        	
        }
        
    }


}
