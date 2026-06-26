package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientFirst;

    @Mock
    private Ingredient ingredientSecond;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientFirst);
        Assert.assertTrue(burger.ingredients.contains(ingredientFirst));
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientFirst);
        burger.removeIngredient(0);
        Assert.assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(1, 0);
        Assert.assertEquals(ingredientSecond, burger.ingredients.get(0));
        Assert.assertEquals(ingredientFirst, burger.ingredients.get(1));
    }
}