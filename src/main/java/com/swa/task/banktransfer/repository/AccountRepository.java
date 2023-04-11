package com.swa.task.banktransfer.repository;

import com.swa.task.banktransfer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    @Modifying
    @Query("UPDATE Account  account SET account.balance = account.balance + :balance" +
            " where account.accountNumber = :accountNumber")
    int increaseBalance( @Param("balance") BigDecimal balance,
                         @Param("accountNumber") String accountNumber);

    @Modifying
    @Query("UPDATE Account  account SET account.balance = account.balance - :balance" +
            " where account.accountNumber = :accountNumber")
    int decreaseBalance( @Param("balance") BigDecimal balance,
                         @Param("accountNumber") String accountNumber);

}
