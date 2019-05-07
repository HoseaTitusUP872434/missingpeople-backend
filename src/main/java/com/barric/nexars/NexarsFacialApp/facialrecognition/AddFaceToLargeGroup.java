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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class AddFaceToLargeGroup 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/largepersongroups/missingpersongroupid/persons/012686c7-4b10-4d7e-8bb9-70eb5535e586/persistedfaces");

          //  builder.setParameter("userData", "{string}");
          //  builder.setParameter("targetFace", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", UtilHelper.subscriptionKey);


            // Request body
            //e6ba99af-1491-4c9d-82f7-6bdc2f0e4ff1, 1018ab78-140a-4d25-8eb5-ab0bef9e4c27
             JSONObject jsonBody = new JSONObject();
            jsonBody.put("url", "http://40.86.95.117:8084/files/1557208826413image.png"); 
            StringEntity reqEntity = new StringEntity(jsonBody.toString());
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


