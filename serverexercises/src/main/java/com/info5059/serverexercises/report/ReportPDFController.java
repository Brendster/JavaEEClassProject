package com.info5059.serverexercises.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info5059.serverexercises.employee.EmployeeRepository;
import com.info5059.serverexercises.expense.ExpenseRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@CrossOrigin
@RestController
public class ReportPDFController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping(value = "/ReportPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> streamPDF(HttpServletRequest request) throws IOException {
        // get formatted pdf as a stream
        String repid = request.getParameter("repid");

        ByteArrayInputStream bis = ReportPDFGenerator.generateReport(repid,
                reportRepository,
                employeeRepository,
                expenseRepository);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=expensereport.pdf");
        // dump stream to browser
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}