package com.delafond.oceane.poker;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static SetGame theSet;
    public static Player p1;
    public static Player p2;
    public static Hand currentHand;
    public static Timer timer;
    public static int bigBlind;
    public static final int[] bigBlindOrder = {100, 150, 200, 300, 400, 600, 800, 1000, 1200, 1600, 2000};
    public static int rankBlind;

    public static void initialize(Player p1, Player p2) {
        theSet = new SetGame();
        Game.p1 = p1;
        Game.p2 = p2;
        bigBlind = 100;
        rankBlind = 0;
        newHand();
        p1.setButton(true);

        blindTimer();
    }

    public static void newHand() {
        theSet.shuffle();
        if (p1.isDealer()) {
            currentHand = new Hand(theSet, p1, p2);
        } else {
            currentHand = new Hand(theSet, p2, p1);
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

}
