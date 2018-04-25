package com.delafond.oceane.poker;

public class Main {

    public static void main(String[] args) {
        Player p1 = new Player("player1");
        Player p2 = new Player("player2");

        Game.initialize(p1, p2);
    }
}
