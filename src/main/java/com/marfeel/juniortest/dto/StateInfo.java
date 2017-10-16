package com.marfeel.juniortest.dto;

public class StateInfo {
    private String timezone;
    private String governor;
    private String abbreviation;

    public StateInfo(){}

    public StateInfo(String timezone, String governor, String abbreviation){
        this.timezone = timezone;
        this.governor = governor;
        this.abbreviation = abbreviation;
    }


    public String getTimezone() {
        return timezone;
    }

    public String getGovernor() {
        return governor;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
