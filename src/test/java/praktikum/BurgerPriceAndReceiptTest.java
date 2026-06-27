package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerPriceAndReceiptTest {

    private final String bunName;
    private final float bunPrice;
    private final List<IngredientData> ingredientsData;
    private final float expectedPrice;

    private static class IngredientData {
        IngredientType type;
        String name;
        float price;

        IngredientData(IngredientType type, String name, float price) {
            this.type = type;
            this.name = name;
            this.price = price;
        }
    }

    public BurgerPriceAndReceiptTest(String bunName, float bunPrice, List<IngredientData> ingredientsData, float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientsData = ingredientsData;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {"Космическая булка", 100f, Arrays.asList(), 200f},
                {"Марсианская булка", 150f, Arrays.asList(new IngredientData(IngredientType.SAUCE, "Галактический соус", 50f)), 350f},
                {"Звездная булка", 120f, Arrays.asList(
                        new IngredientData(IngredientType.SAUCE, "Экзотомат", 40f),
                        new IngredientData(IngredientType.FILLING, "Мясо кратера", 200f)
                ), 480f}
        });
    }

    // Вспомогательный метод для сборки бургера из моков, чтобы избежать дублирования кода
    private Burger createMockBurger() {
        Burger burger = new Burger();

        Bun mockBun = Mockito.mock(Bun.class);
        Mockito.when(mockBun.getName()).thenReturn(bunName);
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        for (IngredientData data : ingredientsData) {
            Ingredient mockIngredient = Mockito.mock(Ingredient.class);
            Mockito.when(mockIngredient.getType()).thenReturn(data.type);
            Mockito.when(mockIngredient.getName()).thenReturn(data.name);
            Mockito.when(mockIngredient.getPrice()).thenReturn(data.price);
            burger.addIngredient(mockIngredient);
        }
        return burger;
    }

    @Test
    public void testBurgerGetPriceCalculatesCorrectTotalPrice() {
        Burger burger = createMockBurger();
        // Проверка №1: Только расчет цены
        Assert.assertEquals("Итоговая цена бургера рассчитана некорректно", expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testBurgerGetReceiptContainsAllRequiredData() {
        Burger burger = createMockBurger();
        String receipt = burger.getReceipt();

        // Проверка №2: Структура чека (для проверки контента допустимо использовать связанные ассерты)
        Assert.assertTrue("Чек должен содержать название булки", receipt.contains(bunName));

        for (IngredientData data : ingredientsData) {
            Assert.assertTrue("Чек должен содержать тип ингредиента", receipt.contains(data.type.toString().toLowerCase()));
            Assert.assertTrue("Чек должен содержать название ингредиента", receipt.contains(data.name));
        }

        Assert.assertTrue("Чек должен содержать итоговую стоимость", receipt.contains(String.format("%f", expectedPrice)));
    }
}