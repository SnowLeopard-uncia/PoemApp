package com.snowleopard.poemapp.logic.model;

import java.io.Serializable;

public class CorrectMistakes implements Serializable {
   private int correctNum;
   private int mistakesNum;
   private double rate;

    public CorrectMistakes(int correctNum, int mistakesNum, double rate) {
        this.correctNum = correctNum;
        this.mistakesNum = mistakesNum;
        this.rate = rate;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }

    public int getMistakesNum() {
        return mistakesNum;
    }

    public void setMistakesNum(int mistakesNum) {
        this.mistakesNum = mistakesNum;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
