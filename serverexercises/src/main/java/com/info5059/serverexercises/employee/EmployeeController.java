package com.info5059.serverexercises.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/api/employees")
    public ResponseEntity<Iterable<Employee>> findAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<Iterable<Employee>>(employees, HttpStatus.OK);
    }

    @PutMapping("/api/employees")
    public ResponseEntity<Employee> updateOne(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }

    @PostMapping("/api/employees")
    public ResponseEntity<Employee> addOne(@RequestBody Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<Integer> deleteOne(@PathVariable long id) {
        return new ResponseEntity<Integer>(employeeRepository.deleteOne(id), HttpStatus.OK);
    }

}
