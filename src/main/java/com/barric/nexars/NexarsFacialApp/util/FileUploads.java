/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.util;

/**
 *
 * @author Barima
 */
public class FileUploads {
    private String file;
    private int id;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FileUploads{" + "file=" + file + ", id=" + id + '}';
    }
    
    
}
