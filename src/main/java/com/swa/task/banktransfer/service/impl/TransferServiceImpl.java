package com.swa.task.banktransfer.service.impl;

import com.swa.task.banktransfer.dto.TransferRequest;
import com.swa.task.banktransfer.exception.ResourceNotFoundException;
import com.swa.task.banktransfer.exception.TransferTransactionException;
import com.swa.task.banktransfer.model.Account;
import com.swa.task.banktransfer.model.Transaction;
import com.swa.task.banktransfer.repository.AccountRepository;
import com.swa.task.banktransfer.repository.TransactionRepository;
import com.swa.task.banktransfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;


    public String transfer(TransferRequest transferRequest) {
        Account from = accountRepository.findByAccountNumber(transferRequest.getFromAccount())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "accountNumber", transferRequest.getFromAccount()));

        Account to = accountRepository.findByAccountNumber(transferRequest.getToAccount())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "accountNumber", transferRequest.getToAccount()));

        validateAccountBalanceSufficiency(from.getBalance(), transferRequest.getAmount());

        accountRepository.decreaseBalance(transferRequest.getAmount(), transferRequest.getFromAccount());
        accountRepository.increaseBalance(transferRequest.getAmount(), transferRequest.getToAccount());

        transactionRepository.save(
                Transaction.builder()
                        .amount(transferRequest.getAmount())
                        .from(from)
                        .to(to)
                        .build());

        return "Success Transfer";
    }

    private void validateAccountBalanceSufficiency(BigDecimal balance, BigDecimal transferAmount)
            throws TransferTransactionException {
        if (balance.compareTo(transferAmount) < 0) {
            throw TransferTransactionException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insufficient Balance")
                    .description("Sending Account has no sufficient Balance")
                    .build();
        }
    }

}
