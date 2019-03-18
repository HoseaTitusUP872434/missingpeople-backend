/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.repositories;

import com.barric.nexars.NexarsFacialApp.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Barima
 */
public interface MediaRepo extends JpaRepository<Media, Integer>{
    Media findByReportId(@Param("id") int id);
}
