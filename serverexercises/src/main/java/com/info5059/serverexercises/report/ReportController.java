package com.info5059.serverexercises.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ReportController {
    @Autowired
    private ReportDAO reportDAO;
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/api/reports")
    public ResponseEntity<Report> addOne(@RequestBody Report clientrep) { // use RequestBody here
        return new ResponseEntity<Report>(reportDAO.create(clientrep), HttpStatus.OK);
    }

    // @GetMapping("/api/reports/{employeeid}")
    // public ResponseEntity<Iterable<Report>> findByEmployeeid(@PathVariable Long employeeid) {
    //     Iterable<Report> reports = reportRepository.findByEmployeeid(employeeid);
    //     return new ResponseEntity<Iterable<Report>>(reports, HttpStatus.OK);
    // }

    @GetMapping("/api/reports/{id}")
    public ResponseEntity<Iterable<Report>> findByEmployee(@PathVariable Long id) {
        return new ResponseEntity<Iterable<Report>>(reportRepository.findByEmployeeid(id), HttpStatus.OK);
    }

}
