/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.facialrecognition;

import com.barric.nexars.NexarsFacialApp.util.UtilHelper;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author Barima
 */
public class CreateFacesImpl implements FacialInterface{

    @Override
    public String createGroupPerson(String username, String data) {
  
          HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/largepersongroups/missingpersongroupid/persons");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", UtilHelper.subscriptionKey);


            // Request body
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", username);
            jsonBody.put("userData", data);
            StringEntity reqEntity = new StringEntity(jsonBody.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
               // System.out.println(EntityUtils.toString(entity));
                JSONObject jsonRes = new JSONObject(EntityUtils.toString(entity));
               String pid =  jsonRes.getString("personId");
                System.err.println("PersonId: "+pid);
                return pid;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    
        return null;
    }

    @Override
    public String addFace(String personId, String imageUrl) {
       
      HttpClient httpclient = HttpClients.createDefault();

        try
        {
           //  personId = this.createGroupPerson(GroupPersonId);
            URIBuilder builder = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/largepersongroups/missingpersongroupid/persons/"+personId+"/persistedfaces");

          //  builder.setParameter("userData", "{string}");
          //  builder.setParameter("targetFace", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", UtilHelper.subscriptionKey);


            // Request body
            //e6ba99af-1491-4c9d-82f7-6bdc2f0e4ff1, 1018ab78-140a-4d25-8eb5-ab0bef9e4c27
             JSONObject jsonBody = new JSONObject();
            jsonBody.put("url", imageUrl); 
            StringEntity reqEntity = new StringEntity(jsonBody.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
                   JSONObject jsonRes = new JSONObject(EntityUtils.toString(entity));
               String pid =  jsonRes.getString("persistedFaceId");
                System.err.println("Persisted ID: "+pid);
                return pid;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean trainFaces(String groupId){
    return true;
    }
}
