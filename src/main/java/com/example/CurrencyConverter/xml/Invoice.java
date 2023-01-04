package com.example.CurrencyConverter.xml;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "invoice")
public class Invoice {

    private String itemName;

    private int id;

    private String currencyCode;
    private double costCurrency;
    private double costPln;
    private Date date;

    public String getItemName() {
        return itemName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @XmlElement
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    @XmlElement
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public double getCostCurrency() {
        return costCurrency;
    }
    @XmlElement
    public void setCostCurrency(double costCurrency) {
        this.costCurrency = costCurrency;
    }

    public double getCostPln() {
        return costPln;
    }
    @XmlElement
    public void setCostPln(double costPln) {
        this.costPln = costPln;
    }

    public Date getDate() {
        return date;
    }
    @XmlElement
    public void setDate(Date date) {
        this.date = date;
    }
}
