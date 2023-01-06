package com.example.CurrencyConverter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Query {
    public String from;
    @JsonProperty("to")
    public String myto;
    public int amount;

    public Query() {
    }

    public Query(String from, String myto, int amount) {
        this.from = from;
        this.myto = myto;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMyto() {
        return myto;
    }

    public void setMyto(String myto) {
        this.myto = myto;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
