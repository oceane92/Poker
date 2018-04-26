package com.delafond.oceane.poker.Gameplay;

public class Card {
    private final int rank;
    private final int suit;

    // Kinds of suits
    public final static int DIAMONDS = 0;
    public final static int CLUBS    = 1;
    public final static int HEARTS   = 2;
    public final static int SPADES   = 3;

    // Kinds of ranks
    public final static int DEUCE = 0;
    public final static int THREE = 1;
    public final static int FOUR  = 2;
    public final static int FIVE  = 3;
    public final static int SIX   = 4;
    public final static int SEVEN = 5;
    public final static int EIGHT = 6;
    public final static int NINE  = 7;
    public final static int TEN   = 8;
    public final static int JACK  = 9;
    public final static int QUEEN = 10;
    public final static int KING  = 11;
    public final static int ACE   = 12;

    public Card(int rank, int suit) {
        assert isValidRank(rank);
        assert isValidSuit(suit);
        this.rank = rank;
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public static boolean isValidRank(int rank) {
        return DEUCE <= rank && rank <= ACE;
    }

    public static boolean isValidSuit(int suit) {
        return DIAMONDS <= suit && suit <= SPADES;
    }

    public String rankToString() {
        switch (rank) {
            case ACE:
                return "As";
            case DEUCE:
                return "Deux";
            case THREE:
                return "Trois";
            case FOUR:
                return "Quatre";
            case FIVE:
                return "Cinq";
            case SIX:
                return "Six";
            case SEVEN:
                return "Sept";
            case EIGHT:
                return "Huit";
            case NINE:
                return "Neuf";
            case TEN:
                return "Dix";
            case JACK:
                return "Valet";
            case QUEEN:
                return "Dame";
            case KING:
                return "Roi";
            default:
                return null;
        }
    }

    public String suitToString() {
        switch (suit) {
            case DIAMONDS:
                return "Carreaux";
            case CLUBS:
                return "Tr�fles";
            case HEARTS:
                return "Coeurs";
            case SPADES:
                return "Piques";
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return rankToString() + " de " + suitToString();
    }
}
