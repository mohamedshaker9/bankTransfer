package com.swa.task.banktransfer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter

public class ErrorDto {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
