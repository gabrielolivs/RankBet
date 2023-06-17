package br.com.rankbet.model.game;

import java.io.Serializable;

public class Games implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Game games;

    public Games(Game games) {
        this.games = games;
    }

    public Games() {

    }

    public Game getGames() {
        return games;
    }

    public void setGames(Game games) {
        this.games = games;
    }
    

}
