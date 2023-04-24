package br.com.rankbet.model.game;

import br.com.rankbet.model.MarketAll;

public class GameAll extends Game {

    private MarketAll markets;

    public GameAll(int id, String title, String team1, String team2, int score1, int score2, String href, boolean isLive, int minute, int seconds, MarketAll markets, MarketAll markets1) {
        super(id, title, team1, team2, score1, score2, href, isLive, minute, seconds, markets);
        this.markets = markets1;
    }

    public GameAll(MarketAll markets) {
        this.markets = markets;
    }

    public GameAll() {

    }

    public MarketAll getMarkets() {
        return markets;
    }

    public void setMarkets(MarketAll markets) {
        this.markets = markets;
    }
}
