package com.fz.myBank.service;

import com.fz.myBank.dto.AccountDto;
import com.fz.myBank.dto.TransactionDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto updateAccount(AccountDto accountDto);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
    List<TransactionDto> getTransactionHistory(Long accountId);

}
