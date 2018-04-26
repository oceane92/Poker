package com.delafond.oceane.poker;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public static SetGame theSetGame;
    public static Player p1;
    public static Player p2;
    public static Hand currentHand;
    public static Timer timer;
    public static int bigBlind;
    public static final int[] bigBlindOrder = {100, 150, 200, 300, 400, 600, 800, 1000, 1200, 1600, 2000};
    public static int rankBlind;
    public static Player theWinner;

    public static void initialize(Player p1, Player p2) {
        theSetGame = new SetGame();
        Game.p1 = p1;
        Game.p2 = p2;
        bigBlind = 100;
        rankBlind = 0;
        newHand();
        p1.setButton(true);

        blindTimer();
    }

    public static void newHand() {
        theSetGame.shuffle();
        if (p1.isDealer()) {
            currentHand = new Hand(theSetGame, p1, p2);
            p1.setButton(false);
            p2.setButton(true);
        } else {
            currentHand = new Hand(theSetGame, p2, p1);
            p1.setButton(true);
            p2.setButton(false);
        }
    }

    public static void blindTimer() {

        TimerTask task = new TimerTask()
        {
            int seconds = 60*3;
            int i = 0;
            @Override
            public void run()
            {
                i++;

                if(i % seconds == 0) {

                    rankBlind++;
                    try {
                        bigBlind = bigBlindOrder[rankBlind];
                    }
                    catch (ArrayIndexOutOfBoundsException e) {

                    }
                    //////
                    System.out.println("Nouvelle grosse blinde : " + bigBlind);
                    //////
                }
                else {
                    //Mettre à jour le blind timer
                }

            }
        };

        timer = new Timer();
        timer.schedule(task, 0, 1000);

    }
    
    public static boolean isOver() {
    	//ICI SCORES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    	if (p1.getSomme() == 0 || p2.getSomme() == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
