/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.repositories;

import com.barric.nexars.NexarsFacialApp.entities.Reports;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Barima
 */
public interface ReportRepo extends JpaRepository<Reports, Integer>{
    List<Reports> findAllReports();
  //  Reports findByFirstname(@Param("firstname") String firstname);
//    Citizens findByNin(@Param("nin") String nin);
//    Citizens findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
