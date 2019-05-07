/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.facialrecognition;

/**
 *
 * @author Barima
 */
public interface FacialInterface {
    public String createGroupPerson(String groupId,String data);
    public String addFace(String GroupPersonId,String url);
    
}
