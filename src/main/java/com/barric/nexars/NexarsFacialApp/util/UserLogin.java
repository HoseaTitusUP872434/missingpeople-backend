/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.util;

import com.barric.nexars.NexarsFacialApp.entities.Citizens;

/**
 *
 * @author Barima
 */
public class UserLogin {
    private int id;
    private String email;
    private String password;
    private int code;
    private Citizens citizen;

    public Citizens getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizens citizen) {
        this.citizen = citizen;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    
}
