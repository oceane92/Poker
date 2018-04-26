package com.delafond.oceane.poker;

public abstract class Round {

    protected Hand theHand;
    protected int potDealer;
    protected int potNonDealer;
    protected int cptRound;
    protected boolean checked;
    protected boolean tapis;

    public Round(Hand theHand) {
        this.theHand = theHand;
        this.potDealer = 0;
        this.potNonDealer = 0;
        this.cptRound = 1;
        this.checked = false;
        this.tapis = false;
    }

    public boolean[] possibleActions() {
        boolean[] ret = new boolean[4];

        if (potDealer != potNonDealer) {
            ret[0] = false;     //check
            ret[1] = true;      //call
            ret[3] = true;      //fold
        } else {
            ret[0] = true;
            ret[1] = false;
            ret[3] = false;      
        }
        ret[2] = true;      //raise
        
        if (tapis) {
        	ret[2] = false;
        }

        return ret;
    }

    public void check() {
    	System.out.println("CHECK()");

        if (checked) {
            this.endRound();
        } else {
            cptRound++;
            checked = true;
        }
    }

    public void call() {

    	checked = false;
    	
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
        	theHand.setPot(potDealer + potNonDealer);
        	theHand.endRoundTapis();
        } else {
            this.endRound();        	
        }
    }

    public boolean raise(int amount) {

        if (this.cptRound%2 == 0) {
            if (potDealer + amount < potNonDealer * 2) {
                return false;
            } else {
                amount = amount - (potNonDealer - potDealer);
                
                theHand.getDealer().modifSomme(-(this.potNonDealer - this.potDealer));
                this.potDealer = this.potNonDealer;
                
                this.potDealer += amount;
                theHand.getDealer().modifSomme(-amount);
            }
        } else {
            if (potNonDealer + amount < potDealer * 2) {
                return false;
            } else {
                amount = amount - (potDealer - potNonDealer);
                
                theHand.getNonDealer().modifSomme(-(this.potDealer - this.potNonDealer));
                this.potNonDealer = this.potDealer;
                
                this.potNonDealer += amount;
                theHand.getNonDealer().modifSomme(-amount);
            }
        }
        cptRound++;
        return true;
    }

    public void fold() {

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
            if (potDealer<=potNonDealer) {
            	theHand.setPot(potDealer + potNonDealer);
            	theHand.endRoundTapis();
            } else {
            	tapis = true;
            }
        } else {
            potNonDealer += theHand.getNonDealer().getSomme();
            theHand.getNonDealer().modifSomme(-(theHand.getNonDealer().getSomme()));
            if (potNonDealer<=potDealer) {
            	theHand.setPot(potDealer + potNonDealer);
            	theHand.endRoundTapis();
            } else {
            	tapis = true;
            }
        }
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
