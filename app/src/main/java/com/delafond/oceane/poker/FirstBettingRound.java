package com.delafond.oceane.poker;

public class FirstBettingRound extends Round {

    public FirstBettingRound(Hand theHand) {
        super(theHand);
    	System.out.println("1BR()");
    }

    @Override
    public void check() {
    	System.out.println("CHECK1BR()");
        super.endRound();
    }

    @Override
    public void call() {
    	System.out.println("CALL1BR()");
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
