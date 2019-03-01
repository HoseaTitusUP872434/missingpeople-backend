/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.repositories;

import com.barric.nexars.NexarsFacialApp.entities.NigeriaStateLga;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Barima
 */
public interface NigeriaStateLgaRepo extends JpaRepository<NigeriaStateLga, Integer>{
    List<NigeriaStateLga> findByStateId(@Param("id") Integer id);
}
