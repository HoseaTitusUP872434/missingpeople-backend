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
public class FacialRecognitionApiCall implements RecognitionInterface {

    @Override
    public void trainFaces() {
          HttpClient httpclient = HttpClients.createDefault();

        try
        {
           URIBuilder builder = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/largepersongroups/missingpersongroupid/train");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", UtilHelper.subscriptionKey);


            // Request body
           // StringEntity reqEntity = new StringEntity("{body}");
           // request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String detectFaces(String faceId) {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/identify");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", UtilHelper.subscriptionKey);


            // Request body
           JSONObject jsonBody = new JSONObject();
           jsonBody.put("largePersonGroupId", "missingpersongroupid");
        String ent = "{\"largePersonGroupId\": \"missingpersongroupid\", \"faceIds\": [\""+faceId+"\"]}";
        
            StringEntity reqEntity = new StringEntity(ent);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                String res = EntityUtils.toString(entity);
                System.out.println(res);
                return res;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
