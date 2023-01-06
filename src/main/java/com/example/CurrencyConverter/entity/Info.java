package com.example.CurrencyConverter.entity;

public class Info {
    public int timestamp;
    public double rate;


    public Info() {
    }

    public Info(int timestamp, double rate) {
        this.timestamp = timestamp;
        this.rate = rate;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
