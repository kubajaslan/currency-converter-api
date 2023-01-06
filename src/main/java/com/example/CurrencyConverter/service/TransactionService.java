package com.example.CurrencyConverter.service;

import com.example.CurrencyConverter.entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    public List<Transaction> findAll();

    public void save(Transaction transaction);

    public Transaction findByName(String name);

    public Transaction findByDate(Date date);

    public List<Transaction>  findByItemNameContaining(String name);
    public List<Transaction>  findAllByDate(Date date);

    public Transaction findById(int id);

    public void deleteById(int id);


}
