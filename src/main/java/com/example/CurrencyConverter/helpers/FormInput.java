package com.example.CurrencyConverter.helpers;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FormInput {

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public FormInput() {
    }

    public FormInput(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
