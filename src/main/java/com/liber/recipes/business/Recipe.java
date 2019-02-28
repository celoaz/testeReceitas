package com.liber.recipes.business;

import java.util.List;

public class Recipe {

    private String name;
    private List<String> ingredients;
    private String preparationMethod;
    private RecipeMetadata metadata;
    private List<String> categories;

    public Recipe(String name, List<String> ingredients, String preparationMethod, RecipeMetadata metadata, List<String> categories) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparationMethod = preparationMethod;
        this.metadata = metadata;
        this.categories = categories;
    }

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }

    public RecipeMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(RecipeMetadata metadata) {
        this.metadata = metadata;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
