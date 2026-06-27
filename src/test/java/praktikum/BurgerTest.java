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
    public void testSetBunsSetsCorrectBun() {
        burger.setBuns(bun);
        Assert.assertEquals("Булка должна успешно установиться в бургер", bun, burger.bun);
    }

    @Test
    public void testAddIngredientAddsToCollection() {
        burger.addIngredient(ingredientFirst);
        Assert.assertTrue("Список ингредиентов должен содержать добавленный элемент", burger.ingredients.contains(ingredientFirst));
    }

    @Test
    public void testAddIngredientIncreasesCollectionSize() {
        burger.addIngredient(ingredientFirst);
        Assert.assertEquals("Размер списка ингредиентов должен стать равен 1", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientLeavesCollectionEmpty() {
        burger.addIngredient(ingredientFirst);
        burger.removeIngredient(0);
        Assert.assertTrue("Список ингредиентов должен стать пустым после удаления", burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredientChangesFirstElementIndex() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(1, 0);
        Assert.assertEquals("Второй ингредиент должен переместиться на позицию первого (индекс 0)", ingredientSecond, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesSecondElementIndex() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(1, 0);
        Assert.assertEquals("Первый ингредиент должен сдвинуться на позицию второго (индекс 1)", ingredientFirst, burger.ingredients.get(1));
    }
}