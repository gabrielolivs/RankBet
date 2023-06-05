package br.com.rankbet.model;

import java.io.Serializable;

public class MarketSbobet implements Serializable {

    public double win1;
    public double win2;
    public double winx;
    public double win1x;
    public double win12;
    public double win2x;

    public void setWin1(double win1) {
        this.win1 = win1;
    }

    public void setWin2(double win2) {
        this.win2 = win2;
    }

    public void setWinx(double winx) {
        this.winx = winx;
    }

    public void setWin1x(double win1x) {
        this.win1x = win1x;
    }

    public void setWin12(double win12) {
        this.win12 = win12;
    }

    public void setWin2x(double win2x) {
        this.win2x = win2x;
    }

    public double getWin1() {
        return win1;
    }

    public double getWin2() {
        return win2;
    }

    public double getWinx() {
        return winx;
    }

    public double getWin1x() {
        return win1x;
    }

    public double getWin12() {
        return win12;
    }


    public double getWin2x() {
        return win2x;
    }

}
