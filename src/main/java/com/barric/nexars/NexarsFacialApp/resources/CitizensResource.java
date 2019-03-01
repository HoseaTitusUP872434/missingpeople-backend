/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.Citizens;
import com.barric.nexars.NexarsFacialApp.repositories.CitizensRepo;
import com.barric.nexars.NexarsFacialApp.util.ResponseMessage;
import com.barric.nexars.NexarsFacialApp.util.UserLogin;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value = "/api/citizens")
public class CitizensResource {

    private Map map;
    @Autowired
    private CitizensRepo repo;

    @GetMapping(value = "/byemail/{email}")
    public Citizens find(@PathVariable("email") String email) {
       Citizens citi = repo.findByEmail(email);
       citi.setPassword("");
       return citi;

    }

    @PostMapping(value = "/login")
    public UserLogin find(@RequestBody final UserLogin login) {
        Citizens citi = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());
        UserLogin nL = new UserLogin();
        if (citi != null) {
            nL.setCode(0);
            citi.setPassword("");
            nL.setCitizen(citi);
        } else {
            nL.setCode(-1);
        }
        return nL;
    }

    @GetMapping(value = "/all")
    public List<Citizens> findAll() {
        return repo.findAll();
    }

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestBody final Citizens citizens) {
        try {
//            System.out.println(repo.findByEmail(citizens.getEmail()));
            if (repo.findByEmail(citizens.getEmail()) != null) {
                return new ResponseMessage(1, "Email already exist, please login if is yours");
            }
            if (repo.findByNin(citizens.getNin()) != null) {
                return new ResponseMessage(2, "NIN already exist,please make use of your NIN");
            }
            repo.save(citizens);

            return new ResponseMessage(0, "Regigistration Successful, please login to continue");
        } catch (Exception e) {
            return new ResponseMessage(-1, "Error occured while registering");
        }

    }

}
