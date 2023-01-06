package com.example.CurrencyConverter.service;

import com.example.CurrencyConverter.dao.TransactionRepository;
import com.example.CurrencyConverter.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction findByName(String name) {
        return null;
    }

    @Override
    public Transaction findByDate(Date date) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> findByItemNameContaining(String name) {
        return transactionRepository.findByItemNameContaining(name);
    }

    @Override
    public Transaction findById(int id) {
        Optional<Transaction> result = transactionRepository.findById(id);

        Transaction transaction = null;

        if (result.isPresent()) {
            transaction = result.get();
        } else {
            throw new RuntimeException("Could not find employee id - " + id);
        }
        return transaction;
    }
}
