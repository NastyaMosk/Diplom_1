package praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class BurgerPriceAndReceiptTest {

    // Добавляем правило ErrorCollector для реализации Soft Assertions
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    private final String bunName;
    private final float bunPrice;
    private final List<IngredientData> ingredientsData;
    private final float expectedPrice;

    static class IngredientData {
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
        Assert.assertEquals("Итоговая цена бургера рассчитана некорректно", expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testBurgerGetReceiptContainsAllRequiredData() {
        Burger burger = createMockBurger();
        String receipt = burger.getReceipt();

        // Переписываем проверки на collector.checkThat с использованием матчера containsString
        collector.checkThat("Чек должен содержать название булки",
                receipt, containsString(bunName));

        for (IngredientData data : ingredientsData) {
            collector.checkThat("Чек должен содержать тип ингредиента",
                    receipt, containsString(data.type.toString().toLowerCase()));
            collector.checkThat("Чек должен содержать название ингредиента",
                    receipt, containsString(data.name));
        }

        // Локалезависимый формат флоата заменяем на проверку базовой строки стоимости, чтобы избежать проблем с запятой/точкой
        collector.checkThat("Чек должен содержать итоговую стоимость",
                receipt, containsString(String.format("%f", expectedPrice)));
    }
}