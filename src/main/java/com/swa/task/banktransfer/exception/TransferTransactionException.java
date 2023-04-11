package com.swa.task.banktransfer.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder

public class TransferTransactionException extends RuntimeException{
    private HttpStatus status;
    private String message;
    private String description;



}
