package br.com.rankbet.model.h2h;

import java.util.List;


public class H2h implements java.io.Serializable {
    private String team1;
    private String team2;
    private int win1;
    private int win2;
    private int goals1;
    private int goals2;
    private int draw;
    private List<Match> matches;

    public H2h(String team1, String team2, int goals1, int goals2, int win1, int win2, int draw, List<Match> matches) {
        this.team1 = team1;
        this.team2 = team2;
        this.win1 = win1;
        this.win2 = win2;
        this.goals1 = goals1;
        this.goals2 = goals2;
        this.draw = draw;
        this.matches = matches;
    }

    public H2h() {
    }

    public int getGoals1() {
        return goals1;
    }

    public int getGoals2() {
        return goals2;
    }

    public void setGoals1(int goals1) {
        this.goals1 = goals1;
    }

    public void setGoals2(int goals2) {
        this.goals2 = goals2;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getWin1() {
        return win1;
    }

    public int getWin2() {
        return win2;
    }

    public int getDraw() {
        return draw;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setWin1(int win1) {
        this.win1 = win1;
    }

    public void setWin2(int win2) {
        this.win2 = win2;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}
