package com.fz.myBank.mappers;

import com.fz.myBank.dao.Account;
import com.fz.myBank.dto.AccountDto;
import org.springframework.beans.BeanUtils;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return  account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account, accountDto);
        return accountDto;
    }
}
