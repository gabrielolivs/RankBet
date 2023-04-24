package br.com.rankbet.model.game;

import br.com.rankbet.model.MarketAll;

import java.io.Serializable;

public class Game implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private int id;

	private String title;
    private String team1;
    private String team2;
    private int score1;
    private int score2;
    private String href;
    private boolean isLive;
    private int minute;
    private int seconds;


    public Game(int id, String title, String team1, String team2, int score1, int score2, String href, boolean isLive, int minute, int seconds, MarketAll markets) {
        this.id = id;
        this.title = title;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.href = href;
        this.isLive = isLive;
        this.minute = minute;
        this.seconds = seconds;
    }

    public Game() {
		// TODO Auto-generated constructor stub
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

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public String getHref() {
        String[] site = href.split("/");
        return site[2];
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getId() {
        return id;
    }

    public String getFullHref() {
        return this.href;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", href='" + href + '\'' +
                ", isLive=" + isLive +
                ", minute=" + minute +
                ", seconds=" + seconds +
                '}';
    }