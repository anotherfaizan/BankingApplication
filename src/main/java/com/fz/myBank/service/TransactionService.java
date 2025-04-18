package com.fz.myBank.service;

import com.fz.myBank.dao.Transaction;
import com.fz.myBank.dao.TransactionType;

public interface TransactionService {
    Transaction createTransaction(Long accountId, TransactionType transactionType, double amount);
}
