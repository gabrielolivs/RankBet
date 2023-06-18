package br.com.rankbet.model.h2h;

public class Match implements java.io.Serializable {
    private int score1;
    private int score2;

    public Match(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;
    }

    public Match() {
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

}
