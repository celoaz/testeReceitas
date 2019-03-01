package com.liber.recipes.handler;

import com.liber.recipes.business.Recipe;
import com.liber.recipes.dao.RecipesRepository;
import com.techx.generatedsources.entity.tables.records.RecipesRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeHandler {

    /*
        This class has basic methods to access the repository class
     */

    protected RecipesRepository recipesRepository;

    @Autowired
    public RecipeHandler(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public void saveOnDB(Recipe recipe) throws Exception {
        /*
            This method has a basic verification of the main fields (name, ingredients, preparation method).
            If any of these fields is null, the recipe will be considered invalid and it will be not saved on the db.
         */
        if(recipe.getName().equals(null) || recipe.getIngredients().equals(null) || recipe.getPreparationMethod().equals(null)) {
            throw new Exception();
        } else {
            recipesRepository.insertRecipes(recipe);
        }
    }

    public List<Recipe> searchOnDB(String param){
        List<Recipe> recipeList = new ArrayList<>();
        recipeList = recipesRepository.recoverRecipes(param);
        return recipeList;
    }

    public void updateOnDB(Recipe recipe){
        recipesRepository.updateRecipes(recipe);
    }

    public void deleteFromDB(String recipeName){
        recipesRepository.deleteRecipes(recipeName);
    }

}
