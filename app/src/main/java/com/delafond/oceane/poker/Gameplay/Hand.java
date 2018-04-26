package com.delafond.oceane.poker.Gameplay;

import com.delafond.oceane.poker.Entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {

    private SetGame theSetGame;
    private Player dealer;
    private Player nonDealer;
    private Player[] thePlayers;
    private Round[] theRounds;
    private Round currentRound;
    private int pot;
    private ArrayList<Card> cardsOnTable;
    private Card highCard;
    private Card highCardP1;
    private Card highCardP2;

    public Hand(SetGame theSetGame, Player dealer, Player nonDealer) {
        this.theSetGame = theSetGame;
        this.dealer = dealer;
        this.nonDealer = nonDealer;
        thePlayers = new Player[2];
        theRounds = new Round[4];
        cardsOnTable = new ArrayList<Card>();

        thePlayers[0] = dealer;
        thePlayers[1] = nonDealer;
        dealer.setButton(true);

        theRounds[0] = new FirstBettingRound(this);
        theRounds[1] = null;
        theRounds[2] = null;
        theRounds[3] = null;

        currentRound = theRounds[0];
        this.pot = 0;

        for (int i = 0; i < 2; i++) {
            thePlayers[i].setCurrentHand(this);
            thePlayers[i].setHand(theSetGame.getAndRemoveNextCard(), theSetGame.getAndRemoveNextCard());
            thePlayers[i].payBlind();
        }
    }

    public SetGame getTheSetGame() {
        return theSetGame;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot += pot;
    }
    
    public void setCardsOnTable(ArrayList<Card> cardsOnTable) {
    	this.cardsOnTable = cardsOnTable;
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
    
    /*public void showCards() {
    	for (Card c : cardsOnTable) {
    		System.out.print(c + " ---- ");
    	}
    }*/

    public void nextRound() {
        if (theRounds[1] == null) {
            theRounds[1] = new Flop(this);
            currentRound = theRounds[1];
            //showCards();
        } else if (theRounds[2] == null) {
            theRounds[2] = new Turn(this);
            currentRound = theRounds[2];
            //showCards();
        } else if (theRounds[3] == null) {
            theRounds[3] = new River(this);
            currentRound = theRounds[3];
            //showCards();
        } else {
            showdown();
        }
    }

    public void endRoundTapis() {
    	
    	if (theRounds[1] == null) {
            theRounds[1] = new Flop(this);
            endRoundTapis();
        } else if (theRounds[2] == null) {
            theRounds[2] = new Turn(this);
            endRoundTapis();
        } else if (theRounds[3] == null) {
            theRounds[3] = new River(this);
            endRoundTapis();
        } else {
            showdown();
        }
    }

    public Player showdown() {
    	ArrayList<Card> setP1 = new ArrayList<Card>();
    	ArrayList<Card> setP2 = new ArrayList<Card>();
    	int indexP1 = 0;
    	int indexP2 = 0;
    	
    	for (int i = 0; i < 5; i++) {
    		setP1.add(cardsOnTable.get(i));
    		setP2.add(cardsOnTable.get(i));
    	}
    	setP1.add(Game.p1.getFirstCard());
    	setP1.add(Game.p1.getSecondCard());
    	setP2.add(Game.p2.getFirstCard());
    	setP2.add(Game.p2.getSecondCard());
    	
    	if (containsRoyalFlush((ArrayList<Card>)setP1.clone())) {
    		indexP1 = 9;
    	} else if (containsStraightFlush((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 8;
    	} else if (containsFourofAKind((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 7;
    	} else if (containsFull((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 6;
    	} else if (containsFlush((ArrayList<Card>)setP1.clone())) {
    		indexP1 = 5;
    	} else if (containsStraight((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 4;
    	} else if (containsThreeofAKind((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 3;
    	} else if (containsDoublePair((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 2;
    	} else if (containsPair((ArrayList<Card>)setP1.clone())) {
    		highCardP1 = highCard;
    		indexP1 = 1;
    	} else {
    		indexP1 = 0;
    	}
    	
    	if (containsRoyalFlush((ArrayList<Card>)setP2.clone())) {
    		indexP2 = 9;
    	} else if (containsStraightFlush((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 8;
    	} else if (containsFourofAKind((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 7;
    	} else if (containsFull((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 6;
    	} else if (containsFlush((ArrayList<Card>)setP2.clone())) {
    		indexP2 = 5;
    	} else if (containsStraight((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 4;
    	} else if (containsThreeofAKind((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 3;
    	} else if (containsDoublePair((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 2;
    	} else if (containsPair((ArrayList<Card>)setP2.clone())) {
    		highCardP2 = highCard;
    		indexP2 = 1;
    	} else {
    		indexP2 = 0;
    	}
    	
    	if (indexP1 > indexP2) {
    		endHand(Game.p1);
    		return Game.p1;
    	} else if (indexP2 > indexP1) {
    		endHand(Game.p2);
    		return Game.p2;
    	} else if (indexP1 == 0) {
    		highCardP1 = highCard(setP1);
    		highCardP2 = highCard(setP2);
    	}
		
		if (highCardP1!=null && highCardP1.getRank() > highCardP2.getRank()) {
    		endHand(Game.p1);
			return Game.p1;
		} else if (highCardP1!=null && highCardP2.getRank() > highCardP1.getRank()) {
    		endHand(Game.p2);
			return Game.p2;
		} else {
			endHand((Player)null);
			return null;
		}
    }
    
    public boolean containsRoyalFlush(ArrayList<Card> list) {

        Collections.sort(list, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return Integer.compare(card1.getRank(), card2.getRank());
            }
        });
        int cptStraight = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getRank() == list.get(i-1).getRank() + 1 && list.get(i).getSuit() == list.get(i-1).getSuit() && list.get(i).getRank()>=8) {
                cptStraight++;
            } else {
                cptStraight = 0;
            }

            if (cptStraight == 4) {
                return true;
            }
        }

        return false;
    }
    
    public boolean containsStraightFlush(ArrayList<Card> list) {

        Collections.sort(list, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return Integer.compare(card1.getRank(), card2.getRank());
            }
        });
        
        int cptStraight = 0;
        for (int i = 1; i < list.size(); i++) {
        	
            if (list.get(i).getRank() == list.get(i-1).getRank() + 1 && list.get(i).getSuit() == list.get(i-1).getSuit()) {
                cptStraight++;
            } else if (list.get(i).getRank() == list.get(i-1).getRank() + 1 && list.get(i).getSuit() != list.get(i-1).getSuit()){
            	ArrayList<Card> newList = (ArrayList<Card>) list.clone();
            	newList.remove(newList.get(i));
            	return containsStraightFlush(newList);
            } else {
                cptStraight = 0;
            }

            if (cptStraight == 4) {
            	highCard = list.get(i);
                return true;
            }
        }

        boolean contains1 = false;
        boolean contains2 = false;
        boolean contains3 = false;
        boolean contains4 = false;
        boolean contains5 = false;

        ArrayList<Card> list1_5 = new ArrayList<Card>();

        for (Card c : list) {
            if (c.getRank() == 12) {
                contains1 = true;
                list1_5.add(c);
            } else if (c.getRank() == 0) {
                contains2 = true;
                list1_5.add(c);
            } else if (c.getRank() == 1) {
                contains3 = true;
                list1_5.add(c);
            } else if (c.getRank() == 2) {
                contains4 = true;
                list1_5.add(c);
            } else if (c.getRank() == 3) {
                contains5 = true;
                list1_5.add(c);
            }
        }

        if (contains1 && contains2 && contains3 && contains4 && contains5) {
        	highCard = new Card(3, 0);
            return containsFlush(list1_5);
        }

        return false;
    }
    
    public boolean containsFourofAKind(ArrayList<Card> list) {

        ArrayList<Integer> listRanks = new ArrayList<Integer>();
        for (Card c : list) {

            int cpt = 0;

            for (Integer j : listRanks) {
                if (j == c.getRank()) {
                    cpt++;
                }
                if (cpt==3) {
                	highCard = c;
                    return true;
                }
            }

            listRanks.add(c.getRank());
        }

        return false;
    }
    
    public boolean containsFull(ArrayList<Card> list) {
    	ArrayList<Card> cardToRemove = new ArrayList<Card>();
    	if (containsThreeofAKind(list)) {
            ArrayList<Integer> listRanks = new ArrayList<Integer>();
            for (Card c : list) {

                int cpt = 0;

                for (Integer j : listRanks) {
                    if (j == c.getRank()) {
                        cpt++;
                    }
                    if (cpt==2) {

                        for (Card c2 : list) {
                            if (c2.getRank() == j) {
                                cardToRemove.add(c2);
                            }
                        }
                        for (Card theCardToRemove : cardToRemove) {

                            list.remove(theCardToRemove);
                        }
                        
                        return containsPair(list);
                    }
                }

                listRanks.add(c.getRank());
            }
        }

    	return false;
    }
    
    public boolean containsFlush(ArrayList<Card> list) {
    	int cptDiamond = 0;
    	int cptHeart = 0;
    	int cptClubs = 0;
    	int cptSpades = 0;
    	
    	for (Card c : list) {
    		switch(c.getSuit()) {
    			case 0:
    				cptDiamond++;
    				break;
    			case 1:
    				cptClubs++;
    				break;
    			case 2:
    				cptHeart++;
    				break;
    			case 3:
    				cptSpades++;
    				break;
    		}
    		if (cptDiamond == 5 || cptHeart == 5 || cptClubs == 5 || cptSpades == 5) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public boolean containsStraight(ArrayList<Card> list) {
    	Collections.sort(list, new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				return Integer.compare(card1.getRank(), card2.getRank());
			}
		});
    	
    	int cptStraight = 0;
    	for (int i = 1; i < list.size(); i++) {
    		if (list.get(i).getRank() == list.get(i-1).getRank() + 1) {
    			cptStraight++;
    		} else if (list.get(i).getRank() != list.get(i-1).getRank()) {
    			cptStraight = 0;
    		}
    		
    		if (cptStraight == 4) {
    			highCard = list.get(i);
    			return true;
    		}
    	}

        boolean contains1 = false;
        boolean contains2 = false;
        boolean contains3 = false;
        boolean contains4 = false;
        boolean contains5 = false;

        for (Card c : list) {
            if (c.getRank() == 12) {
                contains1 = true;
            } else if (c.getRank() == 0) {
                contains2 = true;
            } else if (c.getRank() == 1) {
                contains3 = true;
            } else if (c.getRank() == 2) {
                contains4 = true;
            } else if (c.getRank() == 3) {
                contains5 = true;
            }
        }

        if (contains1 && contains2 && contains3 && contains4 && contains5) {
        	highCard = new Card(3, 0);
            return true;
        }

    	return false;
    }
    
    public boolean containsThreeofAKind(ArrayList<Card> list) {
    	ArrayList<Integer> listRanks = new ArrayList<Integer>();
    	for (Card c : list) {
    		
			int cpt = 0;
    		
    		for (Integer j : listRanks) {
    			if (j == c.getRank()) {
    				cpt++;
    			}
    			if (cpt==2) {
    				highCard = c;
    				return true;
    			}
    		}
    		
    		listRanks.add(c.getRank());
    	}
    	
    	return false;
    }
    
    public boolean containsDoublePair(ArrayList<Card> list) {
    	ArrayList<Integer> listRanks = new ArrayList<Integer>();
    	
    	for (Card c : list) {
    		if (listRanks.contains(c.getRank())) {
    		    highCard = c;
    			list.remove(c);
    			return containsPair(list);
    		}
    		listRanks.add(c.getRank());
    	}
    	
    	return false;
    }
    
    public boolean containsPair(ArrayList<Card> list) {    	
    	
    	ArrayList<Integer> listRanks = new ArrayList<Integer>();
    	for (Card c : list) {

    		if (listRanks.contains(c.getRank())) {
    		    if (highCard != null && c.getRank()>highCard.getRank()) {
                    highCard = c;
                }
    			return true;
    		}
    		listRanks.add(c.getRank());
    	}
    	return false;
    }
    
    public Card highCard(ArrayList<Card> list) {
    	Card theHighCard = list.get(0);
    	
    	for (Card c : list) {
    		if (theHighCard.getRank()<c.getRank()) {
    			theHighCard = c;
    		}
    	}    	
    	
    	return theHighCard;
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
    
    public void endHand(Player theWinner) {
    	if (theWinner != null) {
    		theWinner.modifSomme(pot);
    	} else {
    		int moitiePot = (int)pot/2;
    		dealer.modifSomme(moitiePot);
    		nonDealer.modifSomme(pot-moitiePot);
    	}
        dealer.setButton(false);
        nonDealer.setButton(true);
        Game.newHand();
    }
}
