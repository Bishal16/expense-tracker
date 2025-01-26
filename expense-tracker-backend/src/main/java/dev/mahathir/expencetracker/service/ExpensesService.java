package dev.mahathir.expencetracker.service;

import dev.mahathir.expencetracker.entity.Expense;
import dev.mahathir.expencetracker.repository.ExpensesRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesService {
    private final ExpensesRepo expensesRepo;

    public ExpensesService(ExpensesRepo expensesRepo) {
        this.expensesRepo = expensesRepo;
    }

    public void addNewExpense(Expense expense) {
        expensesRepo.save(expense);
    }

    public List<Expense> getExpenseByUser(Integer userId) {
       return expensesRepo.findByUserId(userId);
    }

    public void deleteExpenseById(Integer expenseId) {
        expensesRepo.deleteById(expenseId);
    }
}
