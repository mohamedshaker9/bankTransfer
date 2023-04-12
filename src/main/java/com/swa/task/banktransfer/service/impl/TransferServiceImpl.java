package com.swa.task.banktransfer.service.impl;

import com.swa.task.banktransfer.dto.TransferRequest;
import com.swa.task.banktransfer.exception.ResourceNotFoundException;
import com.swa.task.banktransfer.exception.TransferException;
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


    public void transfer(TransferRequest transferRequest) {
        Account from = findByAccountNumber(transferRequest.getFromAccount());
        Account to = findByAccountNumber(transferRequest.getToAccount());

        checkIfAccountBalanceSufficient(from.getBalance(), transferRequest.getAmount());

        updateAccounts(transferRequest.getFromAccount(), transferRequest.getToAccount(), transferRequest.getAmount());

        saveTransaction(from, to, transferRequest.getAmount());
    }

    private Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "accountNumber", accountNumber));
    }

    private void checkIfAccountBalanceSufficient(BigDecimal balance, BigDecimal transferAmount)
            throws TransferException {
        if (balance.compareTo(transferAmount) < 0) {
            throw TransferException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insufficient Balance")
                    .description("Sending Account has no sufficient Balance")
                    .build();
        }
    }

    private void updateAccounts(String from, String to, BigDecimal amount){
        accountRepository.decreaseBalance(amount, from);
        accountRepository.increaseBalance(amount, to);
    }

    private void saveTransaction(Account from, Account to, BigDecimal amount) {
        transactionRepository.save(
                Transaction.builder()
                        .amount(amount)
                        .from(from)
                        .to(to)
                        .build());
    }

}
