package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Request request) throws JsonProcessingException {
        return mapper.writeValueAsString(request);
    }

    public static String toJSON(Response response) throws JsonProcessingException {
        return mapper.writeValueAsString(response);
    }

    public static Request JSONtoRequest(String jsonRequest) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(jsonRequest);
        return new Request(jsonNode.get("atmNumber").asText());
    }

    public static Response JSONtoResponse(String jsonResponse) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(jsonResponse);
        return new Response(jsonNode.get("statusCode").asText());
    }
}
