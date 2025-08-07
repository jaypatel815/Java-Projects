package com.personal.expense_tracker.controller;

import com.personal.expense_tracker.dto.Expense.ExpenseRequest;
import com.personal.expense_tracker.dto.Expense.ExpenseResponse;
import com.personal.expense_tracker.entity.Expense;
import com.personal.expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(
            @RequestBody ExpenseRequest request,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        ExpenseResponse response = expenseService.addExpense(request, userDetails);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        List<Expense> expenses = expenseService.getAllExpensesForUser(userDetails.getUsername());
        return ResponseEntity.ok(expenses);
    }

}
