package com.liber.recipes.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liber.recipes.business.RecipeResponse;
import com.liber.recipes.business.Recipe;

import java.io.IOException;

public class LiberUtils {

    private static final Integer CREATION = 1;
    private static final Integer RECOVER = 2;
    private static final Integer UPDATE = 3;
    private static final Integer DELETE = 4;

    public static Recipe getRequestObject(String requestJson) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        final Recipe recipeRequest = mapper.readValue(requestJson, Recipe.class);

        return recipeRequest;
    }

    public static String getObjectJson(Object o) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        try {
            return mapper.writeValueAsString(o);
        }
        catch (Exception e) {
            System.out.println("Pricer MS failed to map object to json.");
        }

        return "";
    }

    public static RecipeResponse buildResponseObject(Boolean isError, Integer requestType) {
        RecipeResponse recipeResponse = new RecipeResponse();
        if(requestType == CREATION) {
            if (isError) {
                recipeResponse.setStatusCode(500);
                recipeResponse.setDescription("Your recipe was not saved on database. Please, check your request and try again.");
            } else {
                recipeResponse.setDescription("Your recipe was saved on database.");
                recipeResponse.setStatusCode(200);
            }
        } else if (requestType == RECOVER) {
            recipeResponse.setStatusCode(200);
            recipeResponse.setDescription("Your recipe was not found on the database.");
        } else if (requestType == UPDATE) {

        } else if (requestType == DELETE) {

        }

        return recipeResponse;
    }
}
