package com.sise.service01;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

public class CxfClient {
    public static void main(String[] args) throws IOException {
        WebClient client = WebClient.create("http://localhost:8085/person/1");
        Response response = client.get();
        InputStream inputStream = (InputStream) response.getEntity();
        String content = IOUtils.readStringFromStream(inputStream);
        System.out.println(content);

    }
}
