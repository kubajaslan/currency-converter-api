package com.example.CurrencyConverter.entity;

import java.util.ArrayList;

public class Root{
    public boolean success;
    public Query query;
    public Info info;
    public String date;
    public double result;
    public boolean historical;



    public Root() {
    }

    public Root(boolean success, Query query, Info info, String date, double result, boolean historical) {
        this.success = success;
        this.query = query;
        this.info = info;
        this.date = date;
        this.result = result;
        this.historical = historical;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isHistorical() {
        return historical;
    }

    public void setHistorical(boolean historical) {
        this.historical = historical;
    }
}
