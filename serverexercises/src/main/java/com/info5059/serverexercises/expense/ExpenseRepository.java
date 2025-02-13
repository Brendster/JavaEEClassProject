package com.info5059.serverexercises.expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "expenses", path = "expenses")
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    // extend so we can return the number of rows deleted
    @Modifying
    @Transactional
    @Query("delete from Expense where id = ?1")
    int deleteOne(Long expenseid);
    List<Expense> findByEmployeeid(Long employeeid);
}
