package com.example.CurrencyConverter.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "purchases")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "bid")
    private double bid;

    @Column(name = "cost_currency")
    private double costCurrency;

    @Column(name = "cost_PLN")
    private double costPln;

    public Transaction() {
    }

    public Transaction(int id, String itemName, Date date, String currencyCode, double bid, double costCurrency, double costPln) {
        this.id = id;
        this.itemName = itemName;
        this.date = date;
        this.currencyCode = currencyCode;
        this.bid = bid;
        this.costCurrency = costCurrency;
        this.costPln = costPln;
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "id=" + id +
               ", itemName='" + itemName + '\'' +
               ", date=" + date +
               ", currencyCode='" + currencyCode + '\'' +
               ", bid=" + bid +
               ", costCurrency=" + costCurrency +
               ", costPln=" + costPln +
               '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getCostCurrency() {
        return costCurrency;
    }

    public void setCostCurrency(double costCurrency) {
        this.costCurrency = costCurrency;
    }

    public double getCostPln() {
        return bid*costCurrency;
    }

    public void setCostPln(double costPln) {
        this.costPln = costPln;
    }
}
