package com.liber.recipes.controller;

import com.liber.recipes.business.RecipeResponse;
import com.liber.recipes.business.Recipe;
import com.liber.recipes.handler.RecipeHandler;
import com.liber.recipes.utils.LiberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/1/liber")
public class RecipeController {

    private static final Integer CREATION = 1;
    private static final Integer RECOVER = 2;
    private static final Integer UPDATE = 3;
    private static final Integer DELETE = 4;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createRecipe(@RequestBody String body) {
        RecipeResponse recipeResponse;
        try {
            Recipe recipe = LiberUtils.getRequestObject(body);
            RecipeHandler.saveOnDB(recipe);
        } catch (Exception e) {
            e.printStackTrace();
            recipeResponse = LiberUtils.buildResponseObject(true, CREATION);
            return new ResponseEntity<String>(LiberUtils.getObjectJson(recipeResponse), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        recipeResponse = LiberUtils.buildResponseObject(false, CREATION);
        return new ResponseEntity<String>(LiberUtils.getObjectJson(recipeResponse), HttpStatus.OK);
    }

    @RequestMapping(path = "/recover", method = RequestMethod.GET)
    public ResponseEntity<String> getRecipe(@RequestParam String param) {
        Recipe recipe;
        try {
            recipe = RecipeHandler.searchOnDB(param);
        } catch (Exception e) {
            e.printStackTrace();
            RecipeResponse recipeResponse = LiberUtils.buildResponseObject(true, RECOVER);
            return new ResponseEntity<String>(LiberUtils.getObjectJson(recipeResponse), HttpStatus.OK);
        }

        return new ResponseEntity<String>(LiberUtils.getObjectJson(recipe), HttpStatus.OK);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateRecipe(@RequestParam String param, @RequestBody String body) {
        Recipe recipe;
        try {
            RecipeHandler.updateOnDB(param, body);
        } catch (Exception e) {
            e.printStackTrace();
            RecipeResponse recipeResponse = LiberUtils.buildResponseObject(true, UPDATE);
            return new ResponseEntity<String>(LiberUtils.getObjectJson(recipeResponse), HttpStatus.OK);
        }

        RecipeResponse recipeResponse = LiberUtils.buildResponseObject(true, UPDATE);
        return new ResponseEntity<String>(LiberUtils.getObjectJson(recipe), HttpStatus.OK);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public ResponseEntity<String> deleteRecipe(@RequestParam String param) {
        RecipeResponse recipeResponse;
        try {
            RecipeHandler.deleteFromDB(param);
        } catch (Exception e) {
            e.printStackTrace();
            recipeResponse = LiberUtils.buildResponseObject(true, DELETE);
            return new ResponseEntity<String>(LiberUtils.getObjectJson(recipeResponse), HttpStatus.OK);
        }
        recipeResponse = LiberUtils.buildResponseObject(false, DELETE);
        return new ResponseEntity<String>(LiberUtils.getObjectJson(recipeResponse), HttpStatus.OK);
    }
}
