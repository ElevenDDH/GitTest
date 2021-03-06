package com.sise.service01;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestletClient {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) throws IOException {
        ClientResource clientResource = new ClientResource("http://localhost:8085/person/1");
        Representation representation = clientResource.get(MediaType.APPLICATION_JSON);
        JacksonRepresentation jacksonRepresentation = new JacksonRepresentation(representation, HashMap.class);
        Map result = (HashMap) jacksonRepresentation.getObject();
        System.out.println(result.get("personid") + "-" + result.get("pname") + "-"
        + result.get("page") + "-" + result.get("message"));

    }
}
