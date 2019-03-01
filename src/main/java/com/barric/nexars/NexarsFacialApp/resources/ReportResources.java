/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.Reports;
import com.barric.nexars.NexarsFacialApp.repositories.ReportRepo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Barima
 */
@RestController
@RequestMapping(value = "/api/reports")
public class ReportResources {

    private Map map;
    @Autowired
    private ReportRepo repo;

    @PostMapping(value = "/save")
    public Reports find(@RequestBody final Reports reports) {
      
        reports.setReportTypeId(0);
        reports.setAnonimity(false);
        reports.setDateCreated(new Date());
        repo.save(reports);
        return reports;
    }

    @GetMapping(value = "/all")
    public List<Reports> findAll() {
        return repo.findAllReports();
    }


}
