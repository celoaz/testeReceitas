package com.liber.recipes.business;

public class RecipeResponse {
    private Integer statusCode;
    private String description;

    public RecipeResponse(Integer statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public RecipeResponse() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
