/*
 * This file is generated by jOOQ.
 */
package com.techx.generatedsources.entity;


import com.techx.generatedsources.entity.tables.Categories;
import com.techx.generatedsources.entity.tables.Ingredients;
import com.techx.generatedsources.entity.tables.RecipeMetadata;
import com.techx.generatedsources.entity.tables.Recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = -64330479;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>categories</code>.
     */
    public final Categories CATEGORIES = com.techx.generatedsources.entity.tables.Categories.CATEGORIES;

    /**
     * The table <code>ingredients</code>.
     */
    public final Ingredients INGREDIENTS = com.techx.generatedsources.entity.tables.Ingredients.INGREDIENTS;

    /**
     * The table <code>recipe_metadata</code>.
     */
    public final RecipeMetadata RECIPE_METADATA = com.techx.generatedsources.entity.tables.RecipeMetadata.RECIPE_METADATA;

    /**
     * The table <code>recipes</code>.
     */
    public final Recipes RECIPES = com.techx.generatedsources.entity.tables.Recipes.RECIPES;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Categories.CATEGORIES,
            Ingredients.INGREDIENTS,
            RecipeMetadata.RECIPE_METADATA,
            Recipes.RECIPES);
    }
}
