package com.personal.expense_tracker.service;

import com.personal.expense_tracker.dto.Expense.ExpenseRequest;
import com.personal.expense_tracker.dto.Expense.ExpenseResponse;
import com.personal.expense_tracker.entity.Expense;
import com.personal.expense_tracker.entity.User;
import com.personal.expense_tracker.repository.ExpenseRepository;
import com.personal.expense_tracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public ExpenseResponse addExpense(ExpenseRequest request, UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = Expense.builder()
                .amount(request.getAmount())
                .category(request.getCategory())
                .description(request.getDescription())
                .date(request.getDate())
                .user(user)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getAmount(),
                savedExpense.getCategory(),
                savedExpense.getDescription(),
                savedExpense.getDate()
        );
    }

    public List<Expense> getAllExpensesForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return expenseRepository.findByUser(user);
    }
}
