package com.example.cotonavirustracker.models;

public class LocationStats {
    private String state;
    private String countrey;
    private int lastestTotalcases;
    private int difFromPrevDay;

    public int getDifFromPrevDay() {
        return difFromPrevDay;
    }

    public void setDifFromPrevDay(int difFromPrevDay) {
        this.difFromPrevDay = difFromPrevDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountrey() {
        return countrey;
    }

    public void setCountrey(String countrey) {
        this.countrey = countrey;
    }

    public int getLastestTotalcases() {
        return lastestTotalcases;
    }

    public void setLastestTotalcases(int lastestTotalcases) {
        this.lastestTotalcases = lastestTotalcases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", countrey='" + countrey + '\'' +
                ", lastestTotalcases=" + lastestTotalcases +
                '}';
    }
}
