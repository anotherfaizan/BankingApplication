package com.fz.myBank.controller;

import com.fz.myBank.dto.AccountDto;
import com.fz.myBank.dto.TransactionDto;
import com.fz.myBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bankfz")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return  new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.updateAccount(accountDto), HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping("/admin/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountsList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountsList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account with id: " + id + " was deleted successfully.");
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionHistory(@PathVariable Long id){
        List<TransactionDto> transactionsList = accountService.getTransactionHistory(id);
        return ResponseEntity.ok(transactionsList);
    }
}
