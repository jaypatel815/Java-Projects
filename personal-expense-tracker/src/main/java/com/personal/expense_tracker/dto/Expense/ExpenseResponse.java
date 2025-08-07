package com.personal.expense_tracker.dto.Expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExpenseResponse {
    private Long id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
}
