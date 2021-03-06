/*
 * This file is generated by jOOQ.
 */
package com.techx.generatedsources.entity;


import com.techx.generatedsources.entity.tables.Categories;
import com.techx.generatedsources.entity.tables.Ingredients;
import com.techx.generatedsources.entity.tables.RecipeMetadata;
import com.techx.generatedsources.entity.tables.Recipes;
import com.techx.generatedsources.entity.tables.records.CategoriesRecord;
import com.techx.generatedsources.entity.tables.records.IngredientsRecord;
import com.techx.generatedsources.entity.tables.records.RecipeMetadataRecord;
import com.techx.generatedsources.entity.tables.records.RecipesRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CategoriesRecord, Integer> IDENTITY_CATEGORIES = Identities0.IDENTITY_CATEGORIES;
    public static final Identity<IngredientsRecord, Integer> IDENTITY_INGREDIENTS = Identities0.IDENTITY_INGREDIENTS;
    public static final Identity<RecipeMetadataRecord, Integer> IDENTITY_RECIPE_METADATA = Identities0.IDENTITY_RECIPE_METADATA;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CategoriesRecord> KEY_CATEGORIES_PRIMARY = UniqueKeys0.KEY_CATEGORIES_PRIMARY;
    public static final UniqueKey<IngredientsRecord> KEY_INGREDIENTS_PRIMARY = UniqueKeys0.KEY_INGREDIENTS_PRIMARY;
    public static final UniqueKey<RecipeMetadataRecord> KEY_RECIPE_METADATA_PRIMARY = UniqueKeys0.KEY_RECIPE_METADATA_PRIMARY;
    public static final UniqueKey<RecipesRecord> KEY_RECIPES_PRIMARY = UniqueKeys0.KEY_RECIPES_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CategoriesRecord, RecipesRecord> FK_CATEGORIES_RECIPES = ForeignKeys0.FK_CATEGORIES_RECIPES;
    public static final ForeignKey<IngredientsRecord, RecipesRecord> FK_INGREDIENTS_RECIPES = ForeignKeys0.FK_INGREDIENTS_RECIPES;
    public static final ForeignKey<RecipeMetadataRecord, RecipesRecord> FK_METADATA_RECIPES = ForeignKeys0.FK_METADATA_RECIPES;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CategoriesRecord, Integer> IDENTITY_CATEGORIES = Internal.createIdentity(Categories.CATEGORIES, Categories.CATEGORIES.ID);
        public static Identity<IngredientsRecord, Integer> IDENTITY_INGREDIENTS = Internal.createIdentity(Ingredients.INGREDIENTS, Ingredients.INGREDIENTS.ID);
        public static Identity<RecipeMetadataRecord, Integer> IDENTITY_RECIPE_METADATA = Internal.createIdentity(RecipeMetadata.RECIPE_METADATA, RecipeMetadata.RECIPE_METADATA.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<CategoriesRecord> KEY_CATEGORIES_PRIMARY = Internal.createUniqueKey(Categories.CATEGORIES, "KEY_categories_PRIMARY", Categories.CATEGORIES.ID);
        public static final UniqueKey<IngredientsRecord> KEY_INGREDIENTS_PRIMARY = Internal.createUniqueKey(Ingredients.INGREDIENTS, "KEY_ingredients_PRIMARY", Ingredients.INGREDIENTS.ID);
        public static final UniqueKey<RecipeMetadataRecord> KEY_RECIPE_METADATA_PRIMARY = Internal.createUniqueKey(RecipeMetadata.RECIPE_METADATA, "KEY_recipe_metadata_PRIMARY", RecipeMetadata.RECIPE_METADATA.ID);
        public static final UniqueKey<RecipesRecord> KEY_RECIPES_PRIMARY = Internal.createUniqueKey(Recipes.RECIPES, "KEY_recipes_PRIMARY", Recipes.RECIPES.RECIPE_NAME);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<CategoriesRecord, RecipesRecord> FK_CATEGORIES_RECIPES = Internal.createForeignKey(com.techx.generatedsources.entity.Keys.KEY_RECIPES_PRIMARY, Categories.CATEGORIES, "FK_CATEGORIES_RECIPES", Categories.CATEGORIES.RECIPE_NAME);
        public static final ForeignKey<IngredientsRecord, RecipesRecord> FK_INGREDIENTS_RECIPES = Internal.createForeignKey(com.techx.generatedsources.entity.Keys.KEY_RECIPES_PRIMARY, Ingredients.INGREDIENTS, "FK_INGREDIENTS_RECIPES", Ingredients.INGREDIENTS.RECIPE_NAME);
        public static final ForeignKey<RecipeMetadataRecord, RecipesRecord> FK_METADATA_RECIPES = Internal.createForeignKey(com.techx.generatedsources.entity.Keys.KEY_RECIPES_PRIMARY, RecipeMetadata.RECIPE_METADATA, "FK_METADATA_RECIPES", RecipeMetadata.RECIPE_METADATA.RECIPE_NAME);
    }
}
