package praktikum;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();

        Assert.assertNotNull(buns);
        Assert.assertEquals(3, buns.size());
        Assert.assertEquals("black bun", buns.get(0).getName());
    }

    @Test
    public void testAvailableIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();

        Assert.assertNotNull(ingredients);
        Assert.assertEquals(6, ingredients.size());
        Assert.assertEquals("hot sauce", ingredients.get(0).getName());
    }
}