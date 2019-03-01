/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.repositories;

import com.barric.nexars.NexarsFacialApp.entities.Citizens;
import com.barric.nexars.NexarsFacialApp.entities.NigerianStates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Barima
 */
public interface NigerianStateRepo extends JpaRepository<NigerianStates, Integer>{
//    Citizens findByEmail(@Param("email") String email);
//    Citizens findByFirstname(@Param("firstname") String firstname);
}
