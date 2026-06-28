package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBunsIsNotEmpty() {
        List<Bun> buns = database.availableBuns();
        Assert.assertNotNull("Список доступных булок не должен быть null", buns);
    }

    @Test
    public void testAvailableBunsHasCorrectSize() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("В базе данных должно быть ровно 3 булки", 3, buns.size());
    }

    @Test
    public void testAvailableBunsReturnsCorrectFirstName() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Первая булка в списке должна называться 'black bun'", "black bun", buns.get(0).getName());
    }

    @Test
    public void testAvailableIngredientsIsNotEmpty() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertNotNull("Список доступных ингредиентов не должен быть null", ingredients);
    }

    @Test
    public void testAvailableIngredientsHasCorrectSize() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("В базе данных должно быть ровно 6 ингредиентов", 6, ingredients.size());
    }

    @Test
    public void testAvailableIngredientsReturnsCorrectFirstName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Первый ингредиент в списке должен называться 'hot sauce'", "hot sauce", ingredients.get(0).getName());
    }
}