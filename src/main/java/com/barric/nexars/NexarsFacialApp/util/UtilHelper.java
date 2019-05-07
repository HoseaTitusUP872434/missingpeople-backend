/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Barima
 */
public class UtilHelper {

    public static final String ADMIN = "admin";
    public static final String MERCHANT = "merchant";
    public static final String USER = "user";
    public static final String FILEPATH = "";
  
//     public static final String IMAGEPATH = "C:/opt/files/";
//      public static final String IMAGEURL = "localhost:8084/files/";
//    public static final String APPURL = "localhost:8084/";
    public static final String APPURL="http://40.86.95.117:8084";
     public static final String IMAGEPATH = "/opt/files/";
    public static final String IMAGEURL = "http://40.86.95.117:8084/files/";
    public static final String IMAGETYPE_PROFILE = "profile";
    public static final String IMAGETYPE_PRODUCT = "product";
    public static final String subscriptionKey = "bf16848833c942488ae2d25814820601";

    public static boolean saveFile(InputStream is, String filename) {
        try {
            boolean flag = false;
            OutputStream os = new FileOutputStream(new File(filename));
            byte[] buffer = new byte[256];
            int bytes = 0;
            while ((bytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytes);
            }
        } catch (IOException iOException) {
            System.err.println("ERROR IO: " + iOException);
            return false;
        }
        return true;
    }

    public static boolean saveByte(String base64, String path) {
        try {
            File f = new File(path);
            if (!f.getParentFile().exists()) {
                f.mkdir();
            }
            byte[] imageByte = Base64.decodeBase64(base64);
            new FileOutputStream(path).write(imageByte);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean saveBase(String b4, String p) {
          String path = IMAGEPATH+p;
       //   System.err.println("path: "+path+" img:"+b4);
         if (!new File(path).getParentFile().exists()) {
             System.err.println("New path created! ");
                new File(path).getParentFile().mkdir();
            }
        byte[] data = Base64.decodeBase64(b4);
        try (OutputStream stream = new FileOutputStream(path)) {
            stream.write(data);
            System.err.println("Written....");
        } catch (Exception e) {
            System.err.println("ERROR SAVING: "+e);
        }
        return true;
    }

    public static String base64Encoder(String imagePath) {
        String any = "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";
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            //no worries just return default image
         //   System.out.println("ERR: "+e);
            
            return any;

        } catch (IOException ioe) {
            //same thing goes here
          //  System.out.println("ERR IO: "+ioe);
            return any;
        }
        return base64Image;
    }

}
