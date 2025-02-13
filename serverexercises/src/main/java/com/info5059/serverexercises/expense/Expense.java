package com.info5059.serverexercises.expense;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;

/**
 * Expense entity
 */
@Entity
@Data
@RequiredArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long employeeid; // FK
    private String categoryid; // FK
    private String description;
    private boolean receipt;
    private BigDecimal amount;
    private String dateincurred;
    // needed in 2nd case study
    @Basic(optional = true)
    @Lob
    private String receiptscan;
}
