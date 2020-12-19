package com.tracker.coronavirustracker.model;

public class LocationStates {

    private String state;
    private String country;
    private int totalCases;

    public LocationStates() {

    }

    @Override
    public String toString() {
        return "LocationStates{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCases=" + totalCases +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public LocationStates(String state, String country, int totalCases) {
        this.state = state;
        this.country = country;
        this.totalCases = totalCases;
    }
}
