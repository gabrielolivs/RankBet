package br.com.rankbet.model.game;

import java.io.Serializable;

public class Game implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String title;
    private String team1;
    private String team2;
    private float win1;
    private float win2;
    private String href;

    public Game(String title, String team1, String team2, float win1, float win2, String href) {
        this.title = title;
        this.team1 = team1;
        this.team2 = team2;
        this.win1 = win1;
        this.win2 = win2;
        this.href = href;
    }

    public Game() {

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTeam1() {
        return team1;
    }
    public void setTeam1(String team1) {
        this.team1 = team1;
    }
    public String getTeam2() {
        return team2;
    }
    public void setTeam2(String team2) {
        this.team2 = team2;
    }
    public float getWin1() {
        return win1;
    }
    public void setWin1(float win1) {
        this.win1 = win1;
    }
    public float getWin2() {
        return win2;
    }
    public void setWin2(float win2) {
        this.win2 = win2;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    
    
}

