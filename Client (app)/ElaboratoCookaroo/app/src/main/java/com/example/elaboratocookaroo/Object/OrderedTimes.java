package com.example.elaboratocookaroo.Object;

public class OrderedTimes {
    private String dish;
    private int times;
    public OrderedTimes(String dish,int times){
        this.dish=dish;
        this.times=times;
    }

    public String getDish() {
        return dish;
    }

    public int getTimes() {
        return times;
    }
}
