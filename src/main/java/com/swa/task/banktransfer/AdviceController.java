package com.swa.task.banktransfer;

import com.swa.task.banktransfer.dto.ErrorDto;
import com.swa.task.banktransfer.exception.ResourceNotFoundException;
import com.swa.task.banktransfer.exception.TransferTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFound(ResourceNotFoundException ex,
                                                                     WebRequest request) {
        return new ResponseEntity<>( ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(TransferTransactionException.class)
    public ResponseEntity<ErrorDto> handleTransferTransaction(TransferTransactionException ex,
                                                  WebRequest request) {
        return new ResponseEntity<>(ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build(), ex.getStatus());
    }
}
