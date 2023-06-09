package br.com.rankbet.utils;

public enum AccountType {

    FREE("FREE"),
    PREMIUM1("PREMIUM 1"),
    PREMIUM2("PREMIUM 2");

    public String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }

}
