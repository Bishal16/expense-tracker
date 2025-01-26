package dev.mahathir.expencetracker.repository;

import dev.mahathir.expencetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepo extends JpaRepository<Expense, Integer> {

    List<Expense> findByUserId(Integer userId);

}
