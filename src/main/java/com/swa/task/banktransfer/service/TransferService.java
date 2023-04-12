package com.swa.task.banktransfer.service;

import com.swa.task.banktransfer.dto.TransferRequest;
import org.springframework.transaction.annotation.Transactional;

public interface TransferService {

    @Transactional
    void transfer(TransferRequest transferRequest);
}
