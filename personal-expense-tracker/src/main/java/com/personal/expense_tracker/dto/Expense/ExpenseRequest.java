package com.personal.expense_tracker.dto.Expense;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequest {
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
}
