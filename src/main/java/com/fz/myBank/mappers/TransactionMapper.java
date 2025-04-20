package com.fz.myBank.mappers;

import com.fz.myBank.dao.Transaction;
import com.fz.myBank.dto.TransactionDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        return  transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction, transactionDto);
        return transactionDto;
    }

    public static List<TransactionDto> mapToTransactonDtoList(List<Transaction> transactions){
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for(Transaction transaction : transactions){
            transactionDtoList.add(mapToTransactionDto(transaction));
        }
        return transactionDtoList;
    }
}
