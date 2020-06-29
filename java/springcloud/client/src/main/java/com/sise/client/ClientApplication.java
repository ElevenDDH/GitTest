package com.sise.client;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ClientApplication {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        for (int i = 0; i < 6; i++){
            HttpGet httpGet = new HttpGet("http://localhost:9000/router");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println("========================================");
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            System.out.println("========================================");
        }
    }

}
