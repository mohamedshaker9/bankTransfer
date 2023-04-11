package com.swa.task.banktransfer.repository;

import com.swa.task.banktransfer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
