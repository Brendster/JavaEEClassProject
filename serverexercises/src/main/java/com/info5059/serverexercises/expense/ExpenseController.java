package com.info5059.serverexercises.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/api/expenses")
    public ResponseEntity<Iterable<Expense>> findAll() {
        Iterable<Expense> expenses = expenseRepository.findAll();
        return new ResponseEntity<Iterable<Expense>>(expenses, HttpStatus.OK);
    }

    @PutMapping("/api/expenses")
    public ResponseEntity<Expense> updateOne(@RequestBody Expense expense) {
        Expense updatedExpense = expenseRepository.save(expense);
        return new ResponseEntity<Expense>(updatedExpense, HttpStatus.OK);
    }

    @PostMapping("/api/expenses")
    public ResponseEntity<Expense> addOne(@RequestBody Expense expense) {
        Expense newExpense = expenseRepository.save(expense);
        return new ResponseEntity<Expense>(newExpense, HttpStatus.OK);
    }

    @DeleteMapping("/api/expenses/{id}")
    public ResponseEntity<Integer> deleteOne(@PathVariable long id) {
        return new ResponseEntity<Integer>(expenseRepository.deleteOne(id), HttpStatus.OK);
    }

    @GetMapping("/api/expenses/{employeeid}")
    public ResponseEntity<Iterable<Expense>> findByEmployee(@PathVariable Long employeeid) {
        Iterable<Expense> expenses = expenseRepository.findByEmployeeid(employeeid);
        return new ResponseEntity<Iterable<Expense>>(expenses, HttpStatus.OK);
    }

    @GetMapping(value = "/api/expenses/paged", params = { "p", "s" })
    public Page<Expense> findPaginated(@RequestParam("p") int page, @RequestParam("s") int size) {
        Page<Expense> resultPage = expenseRepository.findAll(PageRequest.of(page, size));
        return resultPage;
    }

}
