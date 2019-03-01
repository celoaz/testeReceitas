package com.liber.recipes.service;

import com.liber.recipes.business.Recipe;
import com.liber.recipes.controller.RecipeController;
import com.liber.recipes.dao.RecipesRepository;
import com.liber.recipes.handler.RecipeHandler;
import com.liber.recipes.utils.LiberUtils;
import com.techx.generatedsources.entity.tables.Recipes;
import com.techx.generatedsources.entity.tables.records.CategoriesRecord;
import com.techx.generatedsources.entity.tables.records.IngredientsRecord;
import com.techx.generatedsources.entity.tables.records.RecipeMetadataRecord;
import com.techx.generatedsources.entity.tables.records.RecipesRecord;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.http.ResponseEntity;
import java.io.IOException;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DSLContext.class, Result.class, InsertResultStep.class})
@PowerMockIgnore("javax.management.*")
public class RecipesTest {

    @Mock
    private DSLContext dslContext = Mockito.mock(DSLContext.class, Mockito.RETURNS_DEEP_STUBS);

    @Mock
    private InsertResultStep<RecipesRecord> recipesResultRecord;

    @InjectMocks
    private RecipesRepository recipesRepository = new RecipesRepository(dslContext);

    @InjectMocks
    private RecipeHandler recipeHandler = new RecipeHandler(recipesRepository);

    @InjectMocks
    private RecipeController recipeController = new RecipeController(recipeHandler);

    @Before
    public void init() {

        PowerMockito.spy(DSLContext.class);

        PowerMockito.spy(Result.class);
        mockStatic(Result.class);

        PowerMockito.spy(InsertResultStep.class);
        mockStatic(InsertResultStep.class);
    }

    @Test
    public void testValidData() throws IOException {
        String payload = generateValidPayload();

        mockCreateRecipe(payload);
        ResponseEntity<String> response = recipeController.createRecipe(payload);

        assertEquals(200,response.getStatusCodeValue());
    }

    private void mockCreateRecipe(String payload) throws IOException {
        Recipe recipe = LiberUtils.getRequestObject(payload);

        BDDMockito.given(dslContext.select(Recipes.RECIPES.fields()).from(Recipes.RECIPES)
                .where(Recipes.RECIPES.RECIPE_NAME.containsIgnoreCase(recipe.getName()))
                .fetchInto(Recipes.RECIPES)).willReturn(null);

        when(dslContext.insertInto(Recipes.RECIPES)
                        .set(Recipes.RECIPES.RECIPE_NAME, recipe.getName())
                        .set(Recipes.RECIPES.PREPARATION_METHOD, recipe.getPreparationMethod())
                        .returning()).thenReturn(recipesResultRecord);

    }

    private String generateValidPayload() {
    return  "{" +
                " \"name\": \"Bolo legal\"," +
                " \"ingredients\": [" +
                " \"ovos\", \"leite\", \"farinha de trigo\", \"fermento\" " +
                "],"+
                " \"preparationMethod\": \"Bata os ingredientes em uma batedeira e leve ao forno por 45 minutos\"," +
                " \"categories\": [ " +
                    " \"bolo\", \"doce\", \"facil\"]," +
                " \"metadata\": { " +
                    " \"preparationTimeInMinutes\": 55, " +
                    " \"numberOfServings\": 3, " +
                    " \"additionalNotes\": \"Não abra o forno antes dos 30 minutos\"" +
            "}}";
    }
}
