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

public class LargePersonGroupPersonCreate 
{
    public static void main(String[] args) 
    {
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
            jsonBody.put("name", "Eric Barima");
            jsonBody.put("userData", "King barric is here");
            StringEntity reqEntity = new StringEntity(jsonBody.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
               // System.out.println(EntityUtils.toString(entity));
                JSONObject jsonRes = new JSONObject(EntityUtils.toString(entity));
               String pid =  jsonRes.getString("personId");
                System.err.println("PID: "+pid);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

