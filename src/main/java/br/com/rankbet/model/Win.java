package br.com.rankbet.model;

import java.io.Serializable;

public abstract class Win implements Serializable {
    private double v;

    public Win(double v){
        this.v = v;
    }
    public Win(){

    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Win{" +
                "v=" + v +
                '}';
    }
}
