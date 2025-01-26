package dev.mahathir.expencetracker.controller;

import dev.mahathir.expencetracker.entity.Expense;
import dev.mahathir.expencetracker.service.ExpensesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {
    private final ExpensesService expensesService;

    public ExpenseController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody @Valid Expense expense) {
        expensesService.addNewExpense(expense);
        return ResponseEntity.ok("Successfully added new expense");
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable Integer userId) {
        List<Expense> expenses = expensesService.getExpenseByUser(userId);
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer expenseId) {
        expensesService.deleteExpenseById(expenseId);
        return ResponseEntity.ok("Successfully deleted expense");
    }
}
