package com.liber.recipes.handler;

import com.liber.recipes.business.Recipe;

public class RecipeHandler {

    public static void saveOnDB(Recipe recipe) throws Exception {
        /*
            This method has a basic verification of the main fields (name, ingredients, preparation method).
            If any of these fields is null, the recipe will be considered invalid and it will be not saved on the db.
         */
        if(recipe.getName().equals(null) || recipe.getIngredients().equals(null) || recipe.getPreparationMethod().equals(null)) {
            throw new Exception();
        } else {

        }
    }

    public static Recipe searchOnDB(String param){
        return new Recipe();
    }

    public static void updateOnDB(String param, String body){
        Recipe recipe = searchOnDB(param);
    }

    public static void deleteFromDB(String param){
    }

}
