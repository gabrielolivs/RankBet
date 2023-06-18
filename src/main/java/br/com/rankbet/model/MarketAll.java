package br.com.rankbet.model;

import java.io.Serializable;

public class MarketAll implements Serializable {
    public Win1 win1;
    public Win2 win2;
    public WinX winx;
    public Win1X win1x;
    public Win12 win12;
    public Win2X win2x;

    public MarketAll(Win1 win1, Win2 win2, WinX winx, Win1X win1x, Win12 win12, Win2X win2x) {
        this.win1 = win1;
        this.win2 = win2;
        this.winx = winx;
        this.win1x = win1x;
        this.win12 = win12;
        this.win2x = win2x;
    }

    public MarketAll() {

    }

    public void setWin1(Win1 win1) {
        this.win1 = win1;
    }

    public void setWin2(Win2 win2) {
        this.win2 = win2;
    }

    public void setWinx(WinX winx) {
        this.winx = winx;
    }

    public void setWin1x(Win1X win1x) {
        this.win1x = win1x;
    }

    public void setWin12(Win12 win12) {
        this.win12 = win12;
    }

    public void setWin2x(Win2X win2x) {
        this.win2x = win2x;
    }

    public double getWin1() {
        return win1.getV();
    }

    public double getWin2() {
        return win2.getV();
    }

    public double getWinx() {
        return winx.getV();
    }

    public double getWin1x() {
        return win1x.getV();
    }


    public double getWin12() {
        return win12.getV();
    }

    public double getWin2x() {
        return win2x.getV();
    }



    @Override
    public String toString() {
        return "Market{" +
                "win1=" + win1 +
                ", win2=" + win2 +
                ", winx=" + winx +
                ", win1x=" + win1x +
                ", win12=" + win12 +
                ", win2x=" + win2x +
                '}';
    }
}

