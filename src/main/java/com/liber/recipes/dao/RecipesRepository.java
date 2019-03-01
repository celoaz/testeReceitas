package com.liber.recipes.dao;

import com.liber.recipes.business.Recipe;
import com.techx.generatedsources.entity.tables.Categories;
import com.techx.generatedsources.entity.tables.Ingredients;
import com.techx.generatedsources.entity.tables.RecipeMetadata;
import com.techx.generatedsources.entity.tables.Recipes;
import com.techx.generatedsources.entity.tables.records.CategoriesRecord;
import com.techx.generatedsources.entity.tables.records.IngredientsRecord;
import com.techx.generatedsources.entity.tables.records.RecipeMetadataRecord;
import com.techx.generatedsources.entity.tables.records.RecipesRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RecipesRepository {
    private final DSLContext dslContext;

    @Autowired
    public RecipesRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void insertRecipes(Recipe recipe) {
        this.dslContext.insertInto(
                Recipes.RECIPES).columns(
                Recipes.RECIPES.RECIPE_NAME,
                Recipes.RECIPES.PREPARATION_METHOD).values(
                recipe.getName(), recipe.getPreparationMethod()).onDuplicateKeyIgnore().execute();
        insertIngredients(recipe);
    }

    public void insertIngredients(Recipe recipe) {
        for (String ingredient : recipe.getIngredients()) {
            this.dslContext.insertInto(
                    Ingredients.INGREDIENTS).columns(
                    Ingredients.INGREDIENTS.INGREDIENT,
                    Ingredients.INGREDIENTS.RECIPE_NAME).values(
                    ingredient, recipe.getName()).onDuplicateKeyIgnore().execute();
        }
        insertMetadata(recipe);
    }

    public void insertMetadata(Recipe recipe) {
        com.liber.recipes.business.RecipeMetadata recipeMetadata = recipe.getMetadata();

        this.dslContext.insertInto(
                RecipeMetadata.RECIPE_METADATA).columns(
                RecipeMetadata.RECIPE_METADATA.RECIPE_NAME,
                RecipeMetadata.RECIPE_METADATA.ADDITIONAL_NOTES,
                RecipeMetadata.RECIPE_METADATA.NUMBER_OF_SERVINGS,
                RecipeMetadata.RECIPE_METADATA.PREPARATION_TIME_IN_MINUTES).values(
                recipe.getName(), recipeMetadata.getAdditionalNotes(), recipeMetadata.getNumberOfServings(),
                recipeMetadata.getPreparationTimeInMinutes()).onDuplicateKeyIgnore().execute();

        insertCategories(recipe);
    }

    public void insertCategories(Recipe recipe) {
        for(String category : recipe.getCategories()) {
            this.dslContext.insertInto(
                    Categories.CATEGORIES).columns(
                    Categories.CATEGORIES.CATEGORY_NAME,
                    Categories.CATEGORIES.RECIPE_NAME).values(
                    category, recipe.getName()).onDuplicateKeyIgnore().execute();
        }
    }

    public List<Recipe> recoverRecipes(String param) {
        final Result<RecipesRecord> recipesRecords;
        final Result<RecipesRecord> recipesRecordsForIngredients;
        final Result<RecipesRecord> recipesRecordsForCategories;
        final Result<IngredientsRecord> ingredientsRecords;
        final Result<CategoriesRecord> categoriesRecords;
        List<Recipe> recipes;

        //Check if the param is a recipe name
        recipes = getByName(param);

        ingredientsRecords = this.dslContext.select(Ingredients.INGREDIENTS.RECIPE_NAME).from(Ingredients.INGREDIENTS)
                                            .where(Ingredients.INGREDIENTS.INGREDIENT.contains(param))
                                            .fetchInto(Ingredients.INGREDIENTS);
        if(ingredientsRecords != null || !ingredientsRecords.isEmpty() || ingredientsRecords.size() > 0) {
            List<Recipe> recipesWithIngredients;
            for(IngredientsRecord recipeName : ingredientsRecords) {
                recipesWithIngredients = getByName(recipeName.value3());
                recipes.addAll(recipesWithIngredients);
            }
        }

        categoriesRecords = this.dslContext.select(Categories.CATEGORIES.RECIPE_NAME).from(Categories.CATEGORIES)
                                            .where(Categories.CATEGORIES.CATEGORY_NAME.contains(param))
                                            .fetchInto(Categories.CATEGORIES);

        if(categoriesRecords != null || !categoriesRecords.isEmpty() || categoriesRecords.size() > 0) {
            List<Recipe> recipesWithIngredients;
            for(CategoriesRecord recipeName : categoriesRecords) {
                recipesWithIngredients = getByName(recipeName.value3());
                recipes.addAll(recipesWithIngredients);
            }
        }

        return recipes;
    }

    public List<Recipe> getByName(String name) {
        List<Recipe> recipeList = new ArrayList<>();

        Result<RecipesRecord> recipesRecord =  this.dslContext.select(Recipes.RECIPES.fields()).from(Recipes.RECIPES)
                                                             .where(Recipes.RECIPES.RECIPE_NAME.containsIgnoreCase(name))
                                                             .fetchInto(Recipes.RECIPES);
        if(recipesRecord.size() > 0) {
            Recipe recipe = new Recipe();
            for(RecipesRecord recipeFromDB : recipesRecord) {
                Result<IngredientsRecord> ingredientsRecords = this.dslContext.select(Ingredients.INGREDIENTS.fields())
                        .from(Ingredients.INGREDIENTS)
                        .where(Ingredients.INGREDIENTS.RECIPE_NAME.eq(recipeFromDB.getRecipeName()))
                        .fetchInto(Ingredients.INGREDIENTS);
                Result<CategoriesRecord> categoriesRecords = this.dslContext.select(Categories.CATEGORIES.fields())
                        .from(Categories.CATEGORIES)
                        .where(Categories.CATEGORIES.RECIPE_NAME.eq(recipeFromDB.getRecipeName()))
                        .fetchInto(Categories.CATEGORIES);
                Result<RecipeMetadataRecord> recipeMetadataRecords = this.dslContext.select(RecipeMetadata.RECIPE_METADATA.fields())
                        .from(RecipeMetadata.RECIPE_METADATA)
                        .where(RecipeMetadata.RECIPE_METADATA.RECIPE_NAME.eq(recipeFromDB.getRecipeName()))
                        .fetchInto(RecipeMetadata.RECIPE_METADATA);

                com.liber.recipes.business.RecipeMetadata recipeMetadata = new com.liber.recipes.business.RecipeMetadata();
                recipeMetadata.setAdditionalNotes(recipeMetadataRecords.get(0).getAdditionalNotes());
                recipeMetadata.setNumberOfServings(recipeMetadataRecords.get(0).getNumberOfServings());
                recipeMetadata.setPreparationTimeInMinutes(recipeMetadataRecords.get(0).getPreparationTimeInMinutes());

                List<String> ingredientsList = new ArrayList<>();
                for (IngredientsRecord ingredient : ingredientsRecords) {
                    ingredientsList.add(ingredient.getIngredient());
                }

                List<String> categoriesList = new ArrayList<>();
                for (CategoriesRecord category : categoriesRecords) {
                    categoriesList.add(category.getCategoryName());
                }

                recipe.setName(recipesRecord.get(0).getRecipeName());
                recipe.setMetadata(recipeMetadata);
                recipe.setIngredients(ingredientsList);
                recipe.setPreparationMethod(recipesRecord.get(0).getPreparationMethod());
                recipe.setCategories(categoriesList);
                recipeList.add(recipe);
            }
       }
       return recipeList;
    }

    public void updateRecipes(Recipe recipe) {
        List<Recipe> recipeList = recoverRecipes(recipe.getName());
        if(recipeList != null) {
            deleteRecipes(recipe.getName());
            insertRecipes(recipe);
        }
    }

    public void deleteRecipes(String recipeName) {
        this.dslContext.delete(Ingredients.INGREDIENTS)
                .where(Ingredients.INGREDIENTS.RECIPE_NAME.eq(recipeName))
                .execute();

        this.dslContext.delete(RecipeMetadata.RECIPE_METADATA)
                .where(RecipeMetadata.RECIPE_METADATA.RECIPE_NAME.eq(recipeName))
                .execute();

        this.dslContext.delete(Categories.CATEGORIES)
                .where(Categories.CATEGORIES.RECIPE_NAME.eq(recipeName))
                .execute();

        this.dslContext.delete(Recipes.RECIPES)
                .where(Recipes.RECIPES.RECIPE_NAME.eq(recipeName))
                .execute();
    }

}
