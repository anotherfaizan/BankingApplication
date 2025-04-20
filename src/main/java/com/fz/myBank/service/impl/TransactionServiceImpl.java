package com.fz.myBank.service.impl;

import com.fz.myBank.dao.Account;
import com.fz.myBank.dao.Transaction;
import com.fz.myBank.dao.TransactionType;
import com.fz.myBank.repository.AccountRepository;
import com.fz.myBank.repository.TransactionRepository;
import com.fz.myBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction createTransaction(Long accountId, TransactionType transactionType, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account with id: " + accountId + " doesn't exist.")
        );
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
