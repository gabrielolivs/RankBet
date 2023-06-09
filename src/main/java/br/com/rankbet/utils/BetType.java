package br.com.rankbet.utils;

public enum BetType {

    VITORIA("VICTORY"),
    DEFEAT("DEFEAT"),
    ATIE("ATIE");

    public String bet;

    BetType(String type) {
        this.bet = type;
    }

    public String getBetType(){
        return bet;
    }

}
