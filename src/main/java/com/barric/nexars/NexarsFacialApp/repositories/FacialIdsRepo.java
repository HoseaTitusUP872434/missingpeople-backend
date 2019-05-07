/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.repositories;

import com.barric.nexars.NexarsFacialApp.entities.FacialFaceIds;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Barima
 */
public interface FacialIdsRepo extends JpaRepository<FacialFaceIds, Integer>{
    FacialFaceIds findByPersistedFaceId(@Param("persistedFaceId") String persistedFaceId);
    FacialFaceIds findByPersonId(@Param("personId") String personId);
    FacialFaceIds findByPersonIdOrPersist(@Param("personId") String personId, @Param("persist") String persist );
}
