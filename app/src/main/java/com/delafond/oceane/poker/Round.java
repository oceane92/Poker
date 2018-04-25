package com.delafond.oceane.poker;

public abstract class Round {

    protected Hand theHand;
    protected int potDealer;
    protected int potNonDealer;
    protected int cptRound;
    protected boolean checked;

    public Round(Hand theHand) {
        this.theHand = theHand;
        this.potDealer = 0;
        this.potNonDealer = 0;
        this.cptRound = 1;
        this.checked = false;
    }

    public boolean[] possibleActions() {
        boolean[] ret = new boolean[4];

        if (potDealer != potNonDealer) {
            ret[0] = false;     //check
            ret[1] = true;      //call
        } else {
            ret[0] = true;
            ret[1] = false;
        }
        ret[2] = true;      //raise
        ret[3] = true;      //all-in

        return ret;
    }

    public void check() {

        if (checked) {
            this.endRound();
        } else {
            cptRound++;
            checked = true;
        }
    }

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

        this.endRound();
    }

    public boolean raise(int amount) {

        if (this.cptRound%2 == 0) {
            if (potDealer + amount < potNonDealer * 2) {
                return false;
            } else {
                amount = amount - (potNonDealer - potDealer);
                this.call();
                this.potDealer += amount;
                theHand.getDealer().modifSomme(-amount);
            }
        } else {
            if (potNonDealer + amount < potDealer * 2) {
                return false;
            } else {
                amount = amount - (potDealer - potNonDealer);
                this.call();
                this.potNonDealer += amount;
                theHand.getNonDealer().modifSomme(-amount);
            }
        }
        return true;
    }

    public void fold(Player thePlayer) {

        if (this.cptRound%2 == 0) {
            theHand.endHand(true);
        } else {
            theHand.endHand(false);
        }
    }

    public void tapis() {
        if (this.cptRound%2 == 0) {
            potDealer += theHand.getDealer().getSomme();
            theHand.getDealer().modifSomme(-(theHand.getDealer().getSomme()));
        } else {
            potNonDealer += theHand.getNonDealer().getSomme();
            theHand.getNonDealer().modifSomme(-(theHand.getNonDealer().getSomme()));
        }
        theHand.endRoundTapis();
    }

    public void endRound() {
        theHand.setPot(potDealer + potNonDealer);
        theHand.nextRound();
    }

    public int getPotDealer() {
        return potDealer;
    }

    public void setPotDealer(int potDealer) {
        this.potDealer += potDealer;
    }

    public int getPotNonDealer() {
        return potNonDealer;
    }

    public void setPotNonDealer(int potNonDealer) {
        this.potNonDealer += potNonDealer;
    }

}
