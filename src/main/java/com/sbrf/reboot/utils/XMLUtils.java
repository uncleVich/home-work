package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

import java.util.Collection;

public class XMLUtils {

    private static final XmlMapper mapper = new XmlMapper();

    public static String toXML(Request request) throws JsonProcessingException {
        return mapper.writeValueAsString(request);
    }

    public static String toXML(Response response) throws JsonProcessingException {
        return mapper.writeValueAsString(response);
    }

    public static Request XMLtoRequest(String xmlRequest) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(xmlRequest);
        return new Request(jsonNode.get("atmNumber").asText());
    }

    public static Response XMLtoResponse(String xmlResponse) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(xmlResponse);
        return new Response(jsonNode.get("statusCode").asText());
    }
}
