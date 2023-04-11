package com.swa.task.banktransfer.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class TransferRequest {

    @NotEmpty
    private String fromAccount;
    @NotEmpty
    private String toAccount;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
}
