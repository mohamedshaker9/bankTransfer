package com.swa.task.banktransfer.controller;


import com.swa.task.banktransfer.dto.TransferRequest;
import com.swa.task.banktransfer.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api")
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        return ResponseEntity.ok(transferService.transfer(transferRequest));
    }
}
