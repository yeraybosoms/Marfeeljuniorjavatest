package com.marfeel.juniortest.dto;

public class CityInfo {
    private String mayor;
    private int foundation;
    private long population;

    public CityInfo(){}

    public CityInfo(String mayor, int foundation, long population){
        this.mayor = mayor;
        this.foundation = foundation;
        this.population = population;
    }

    public String getMayor() {
        return mayor;
    }

    public int getFoundation() {
        return foundation;
    }

    public long getPopulation() {
        return population;
    }
}
