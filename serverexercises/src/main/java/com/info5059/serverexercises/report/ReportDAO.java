package com.info5059.serverexercises.report;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Component
public class ReportDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Report create(Report clientrep) {
        Report realReport = new Report();
        realReport.setDatecreated(LocalDateTime.now());
        realReport.setEmployeeid(clientrep.getEmployeeid());
        entityManager.persist(realReport);
        for (ReportItem item : clientrep.getItems()) {
            ReportItem realItem = new ReportItem();
            realItem.setExpenseid(item.getExpenseid());
            realItem.setReportid(realReport.getId());
            entityManager.persist(realItem);
        }
        entityManager.flush();
        entityManager.refresh(realReport);
        return realReport;
    }
}
