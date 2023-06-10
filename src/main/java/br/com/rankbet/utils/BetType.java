package br.com.rankbet.utils;

public enum BetType {

    VICTORY("VICTORY"),
    DEFEAT("DEFEAT"),
    TIE("TIE");

    public String bet;

    BetType(String type) {
        this.bet = type;
    }

    public String getBetType(){
        return bet;
    }

}
