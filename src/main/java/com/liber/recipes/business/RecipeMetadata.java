package com.liber.recipes.business;

public class RecipeMetadata {

    private Integer preparationTimeInMinutes;
    private Integer numberOfServings;
    private String additionalNotes;

    public RecipeMetadata(Integer preparationTimeInMinutes, Integer numberOfServings, String additionalNotes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
        this.numberOfServings = numberOfServings;
        this.additionalNotes = additionalNotes;
    }

    public RecipeMetadata() {
    }

    public Integer getPreparationTimeInMinutes() {
        return preparationTimeInMinutes;
    }

    public void setPreparationTimeInMinutes(Integer preparationTimeInMinutes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
