package com.fz.myBank.service.impl;

import com.fz.myBank.dao.Account;
import com.fz.myBank.dao.Transaction;
import com.fz.myBank.dao.TransactionType;
import com.fz.myBank.dto.AccountDto;
import com.fz.myBank.dto.TransactionDto;
import com.fz.myBank.exception.AccountNotFoundException;
import com.fz.myBank.mappers.AccountMapper;
import com.fz.myBank.mappers.TransactionMapper;
import com.fz.myBank.repository.AccountRepository;
import com.fz.myBank.service.AccountService;
import com.fz.myBank.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;
    private TransactionService transactionService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService){
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        transactionService.createTransaction(savedAccount.getId(), TransactionType.DEPOSIT, savedAccount.getBalance());
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        try {
            Account account = accountRepository.findById(id).orElseThrow(
                    () -> new AccountNotFoundException("Account with id: " + id + " doesn't exist.")
            );
            return AccountMapper.mapToAccountDto(account);
        } catch (AccountNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while fetching account by id: " + id, e);
        }
    }

    @Override
    @Transactional
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    @Transactional
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with id: " + id + " doesn't exist.")
        );
        double currentBalance = account.getBalance();
        double updatedBalance = currentBalance + amount;
        account.setBalance(updatedBalance);
        Account updatedAccount = accountRepository.save(account);
        transactionService.createTransaction(updatedAccount.getId(), TransactionType.DEPOSIT, amount);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    @Transactional
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with id: " + id + " doesn't exist.")
        );
        double currentBalance = account.getBalance();
        if (currentBalance >= amount) {
            double newBalance = currentBalance - amount;
            account.setBalance(newBalance);
            Account updatedAccount = accountRepository.save(account);
            transactionService.createTransaction(updatedAccount.getId(), TransactionType.WITHDRAWAL, amount);
            return AccountMapper.mapToAccountDto(updatedAccount);
        } else{
            throw new RuntimeException("Your account doesn't have sufficient balance");
        }
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountsList = accountRepository.findAll();
        return accountsList.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with id: " + id + " doesn't exist.")
        );
        accountRepository.deleteById(id);

    }

    @Override
    public List<TransactionDto> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with id: " + accountId + " doesn't exist.")
        );
        List<Transaction> transactionList = account.getTransactions();

        return TransactionMapper.mapToTransactonDtoList(transactionList);
    }
}
