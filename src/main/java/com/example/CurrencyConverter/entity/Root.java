package com.example.CurrencyConverter.entity;

import java.util.ArrayList;

public class Root{
    public String table;
    public String currency;
    public String code;
    public ArrayList<Rate> rates;

    @Override
    public String toString() {
        return "Root{" +
               "table='" + table + '\'' +
               ", currency='" + currency + '\'' +
               ", code='" + code + '\'' +
               ", rates=" + rates +
               '}';
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }
}
