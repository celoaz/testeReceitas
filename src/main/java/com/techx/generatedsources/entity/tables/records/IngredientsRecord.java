/*
 * This file is generated by jOOQ.
 */
package com.techx.generatedsources.entity.tables.records;


import com.techx.generatedsources.entity.tables.Ingredients;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class IngredientsRecord extends UpdatableRecordImpl<IngredientsRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = -239935583;

    /**
     * Setter for <code>ingredients.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>ingredients.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>ingredients.INGREDIENT</code>.
     */
    public void setIngredient(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ingredients.INGREDIENT</code>.
     */
    public String getIngredient() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ingredients.RECIPE_NAME</code>.
     */
    public void setRecipeName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ingredients.RECIPE_NAME</code>.
     */
    public String getRecipeName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Ingredients.INGREDIENTS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Ingredients.INGREDIENTS.INGREDIENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Ingredients.INGREDIENTS.RECIPE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getIngredient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getRecipeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getIngredient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getRecipeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IngredientsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IngredientsRecord value2(String value) {
        setIngredient(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IngredientsRecord value3(String value) {
        setRecipeName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IngredientsRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached IngredientsRecord
     */
    public IngredientsRecord() {
        super(Ingredients.INGREDIENTS);
    }

    /**
     * Create a detached, initialised IngredientsRecord
     */
    public IngredientsRecord(Integer id, String ingredient, String recipeName) {
        super(Ingredients.INGREDIENTS);

        set(0, id);
        set(1, ingredient);
        set(2, recipeName);
    }
}