/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.Citizens;
import com.barric.nexars.NexarsFacialApp.entities.NigeriaStateLga;
import com.barric.nexars.NexarsFacialApp.repositories.CitizensRepo;
import com.barric.nexars.NexarsFacialApp.repositories.NigeriaStateLgaRepo;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Barima
 */
@RestController
@RequestMapping(value = "/api/lga")
public class NigerianStateLgaResource {

    @Autowired
    private NigeriaStateLgaRepo repo;

    @GetMapping(value = "/bystateid/{id}")
    public List<NigeriaStateLga> find(@PathVariable("id") Integer id) {
        return repo.findByStateId(id);

    }

   

}
