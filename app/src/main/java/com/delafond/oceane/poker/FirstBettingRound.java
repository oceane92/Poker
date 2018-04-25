package com.delafond.oceane.poker;

public class FirstBettingRound extends Round {

    public FirstBettingRound(Hand theHand) {
        super(theHand);
    }

    @Override
    public void check() {
        super.endRound();
    }

    @Override
    public void call() {
        if (this.cptRound % 2 == 0) {
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

        if (cptRound > 1) {
            super.endRound();
        } else {
            cptRound++;
        }
    }

    @Override
    public boolean raise(int amount) {
        if (this.cptRound%2 == 0) {
            if (potDealer + amount < potNonDealer * 2) {
                return false;
            } else {
                amount = amount - (potNonDealer - potDealer);
                super.call();
                this.potDealer += amount;
                theHand.getDealer().modifSomme(-amount);
            }
        } else {
            if (potNonDealer + amount < potDealer * 2) {
                return false;
            } else {
                amount = amount - (potDealer - potNonDealer);
                super.call();
                this.potNonDealer += amount;
                theHand.getNonDealer().modifSomme(-amount);
            }
        }
        return true;
    }

}
