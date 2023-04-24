package br.com.rankbet.model.game;

import br.com.rankbet.model.MarketAll;
import br.com.rankbet.model.MarketSbobet;

public class GameSbobet extends Game{

    private MarketSbobet markets;

    public GameSbobet(int id, String title, String team1, String team2, int score1, int score2, String href, boolean isLive, int minute, int seconds, MarketAll markets, MarketSbobet markets1) {
        super(id, title, team1, team2, score1, score2, href, isLive, minute, seconds, markets);
        this.markets = markets1;
    }

    public GameSbobet(MarketSbobet markets) {
        this.markets = markets;
    }

    public GameSbobet() {
    }
    public MarketSbobet getMarkets() {
        return markets;
    }

    public void setMarkets(MarketSbobet markets) {
        this.markets = markets;
    }
}
