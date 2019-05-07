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
 

// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import com.barric.nexars.NexarsFacialApp.util.UtilHelper;
import java.net.URI;
import javax.json.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FacialIdentify 
{
    public static void main(String[] args) 
    {
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
        String ent = "{\"largePersonGroupId\": \"missingpersongroupid\", \"faceIds\": [\"1018ab78-140a-4d25-8eb5-ab0bef9e4c27\"]}";
        
            StringEntity reqEntity = new StringEntity(ent);
            request.setEntity(reqEntity);

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
}