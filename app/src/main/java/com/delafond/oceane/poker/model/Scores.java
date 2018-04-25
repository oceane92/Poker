package com.delafond.oceane.poker.model;

public class Scores {

    private String namePlayer;
    private String score;
    private String time;
    private String main;

    public Scores(String namePlayer, String score, String time, String main) {
        this.namePlayer = namePlayer;
        this.score = score;
        this.time = time;
        this.main = main;
    }

    public Scores(){
        this.namePlayer = "";
        this.score = "";
        this.time = "";
        this.main = "";
    }

    public String getNamePlayer() { return namePlayer; }
    public void setNamePlayer(String namePlayer) { this.namePlayer = namePlayer; }

    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    @Override
    public String toString() {
        return "Joueur :" + namePlayer +
                "\nScore : " + score +
                "\nTemps : " + time +
                "\n%Main : " + main;
    }

}
