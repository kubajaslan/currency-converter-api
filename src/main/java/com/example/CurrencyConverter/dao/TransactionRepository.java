package com.example.CurrencyConverter.dao;

import com.example.CurrencyConverter.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findByItemNameContaining(String name);
    public List<Transaction> findAllByDate(Date date);
}
