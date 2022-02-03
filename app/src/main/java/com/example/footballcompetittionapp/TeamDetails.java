package com.example.footballcompetittionapp;

public class TeamDetails {
    String teamName, firstPl, secondPl, thirdPl, forthPl, fifthPl;


    public TeamDetails(String teamName, String firstPl, String secondPl, String thirdPl, String forthPl, String fifthPl) {
        this.teamName = teamName;
        this.firstPl = firstPl;
        this.secondPl = secondPl;
        this.thirdPl = thirdPl;
        this.forthPl = forthPl;
        this.fifthPl = fifthPl;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setFirstPl(String firstPl) {
        this.firstPl = firstPl;
    }

    public void setSecondPl(String secondPl) {
        this.secondPl = secondPl;
    }

    public void setThirdPl(String thirdPl) {
        this.thirdPl = thirdPl;
    }

    public void setForthPl(String forthPl) {
        this.forthPl = forthPl;
    }

    public void setFifthPl(String fifthPl) {
        this.fifthPl = fifthPl;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getFirstPl() {
        return firstPl;
    }

    public String getSecondPl() {
        return secondPl;
    }

    public String getThirdPl() {
        return thirdPl;
    }

    public String getForthPl() {
        return forthPl;
    }

    public String getFifthPl() {
        return fifthPl;
    }
}
