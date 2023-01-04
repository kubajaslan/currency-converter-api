package com.example.CurrencyConverter.entity;

public class Rate {
    public String no;
    public String effectiveDate;
    public double bid;
    public double ask;

    @Override
    public String toString() {
        return "Rate{" +
               "no='" + no + '\'' +
               ", effectiveDate='" + effectiveDate + '\'' +
               ", bid=" + bid +
               ", ask=" + ask +
               '}';
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }
}
