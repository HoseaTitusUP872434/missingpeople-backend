/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.facialrecognition;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Barima
 */
public class JsonTest {
    
    public static void main(String[] args) {
        String str = "[" +
"    {" +
"        \"faceId\": \"c5c24a82-6845-4031-9d5d-978df9175426\"," +
"        \"candidates\": [" +
"            {" +
"                \"personId\": \"25985303-c537-4467-b41d-bdb45cd95ca1\"," +
"                \"confidence\": 0.92\n" +
"            }" +
"        ]" +
"    }," +
"    {" +
"        \"faceId\": \"65d083d4-9447-47d1-af30-b626144bf0fb\"," +
"        \"candidates\": [" +
"            {" +
"                \"personId\": \"2ae4935b-9659-44c3-977f-61fac20d0538\"," +
"                \"confidence\": 0.89" +
"            }" +
"        ]" +
"    }" +
"]";
        
         //JSONObject jsonBody = new JSONObject(str);
         
         JSONArray ja = new JSONArray(str);
          System.err.println("JN: "+ja);
         ja.forEach(e->{
         
          JSONObject can = new JSONObject(e.toString());
           System.err.println("FALL: : "+can.getJSONArray("candidates"));
           System.err.println("ENT: "+can.getString("faceId"));
         JSONArray canArray =  can.getJSONArray("candidates");
         canArray.forEach(a->{
         JSONObject resOb = new JSONObject(a.toString());
         String personId = resOb.getString("personId");
         Double confidence = resOb.getDouble("confidence");
             System.err.println("PERSONID: "+personId+" confidence: "+confidence*100);
         
         });
         });
        
    }
    
}
