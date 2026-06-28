package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        // Используем встроенный метод getPrice() для косвенной проверки, что булка встала на место
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Assert.assertEquals("Булка должна успешно рассчитаться в стоимости бургера", 200f, burger.getPrice(), 0.001);
    }

    @Test
    public void testAddIngredientIncreasesCollectionSizeAndCalculatesPrice() {
        burger.addIngredient(ingredientFirst);
        Mockito.when(ingredientFirst.getPrice()).thenReturn(50f);
        // Проверяем факт добавления через метод подсчета цены (так как прямого геттера к списку ингредиентов в классе Burger нет)
        Assert.assertEquals("Цена бургера должна включать добавленный ингредиент", 50f, burger.getPrice(), 0.001);
    }

    @Test
    public void testRemoveIngredientDecreasesPriceToZero() {
        burger.addIngredient(ingredientFirst);
        Mockito.when(ingredientFirst.getPrice()).thenReturn(50f);
        burger.removeIngredient(0);
        Assert.assertEquals("Цена бургера должна стать равной 0 после удаления единственного ингредиента", 0f, burger.getPrice(), 0.001);
    }

    @Test
    public void testMoveIngredientChangesOrderInReceipt() {
        Mockito.when(bun.getName()).thenReturn("Булочка");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Mockito.when(ingredientFirst.getName()).thenReturn("Соус");
        Mockito.when(ingredientFirst.getType()).thenReturn(IngredientType.SAUCE);

        Mockito.when(ingredientSecond.getName()).thenReturn("Начинка");
        Mockito.when(ingredientSecond.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);

        // Перемещаем начинку на первое место
        burger.moveIngredient(1, 0);

        String receipt = burger.getReceipt();

        // Проверяем регулярным выражением или индексами, что Начинка в чеке теперь идет ПЕРЕД Соусом
        int fillingIndex = receipt.indexOf("filling Начинка");
        int sauceIndex = receipt.indexOf("sauce Соус");

        Assert.assertTrue("Начинка должна идти в чеке раньше Соуса после перемещения", fillingIndex < sauceIndex);
    }
}