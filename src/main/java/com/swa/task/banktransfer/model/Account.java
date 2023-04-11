package com.swa.task.banktransfer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String accountNumber;

    private BigDecimal balance;

}
