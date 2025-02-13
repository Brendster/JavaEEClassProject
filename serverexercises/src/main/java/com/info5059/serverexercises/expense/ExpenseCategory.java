package com.info5059.serverexercises.expense;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entity class to keep track of Expense types
 */
@Entity
@Data
@RequiredArgsConstructor
public class ExpenseCategory {
    @Id
    private String id;
    private String description;
}