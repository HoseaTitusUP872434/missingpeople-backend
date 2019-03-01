/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.NigerianStates;
import com.barric.nexars.NexarsFacialApp.repositories.NigerianStateRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Barima
 */
@RestController
@RequestMapping(value = "/api/states")
public class NigeriaStateResource {

    @Autowired
    private NigerianStateRepo repo;

//    @GetMapping(value = "/byemail/{email}")
//    public Citizens find(@PathVariable("email") String email) {
//        return repo.findByEmail(email);
//
//    }

    @GetMapping(value = "/all")
    public List<NigerianStates> findAll() {
        return repo.findAll();
    }

//    @PostMapping(value = "/save")
//    public void save(@RequestBody final Citizens citizens) {
//        System.out.println(citizens);
//        repo.save(citizens);
//    }

}
