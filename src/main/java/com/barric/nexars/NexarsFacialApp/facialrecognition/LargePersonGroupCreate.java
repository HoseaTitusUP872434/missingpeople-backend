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
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class LargePersonGroupCreate {

    private static final String subscriptionKey = "bf16848833c942488ae2d25814820601";

    public static void main(String[] args) {
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder  = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/largepersongroups/missingpersongroupid");
             // = new URIBuilder("https://uksouth.api.cognitive.microsoft.com/face/v1.0/largepersongroups/missingpersongroupid");

            URI uri = builder.build();
            HttpPut request = new HttpPut(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            /*
            {
    "name": "large-person-group-name",
    "userData": "User-provided data attached to the large person group.",
    "recognitionModel": "recognition_02"
}
             */
            // Request body
           //012686c7-4b10-4d7e-8bb9-70eb5535e586
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", "MissingpersonLargegroup");
            jsonBody.put("recognitionModel", "recognition_02");
            System.out.println(jsonBody.toString());
            StringEntity reqEntity = new StringEntity(jsonBody.toString());

            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
