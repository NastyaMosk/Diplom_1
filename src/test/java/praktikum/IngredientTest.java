package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Сырный соус", 50f},
                {IngredientType.FILLING, "Говяжья котлета", 150f}
        });
    }

    @Test
    public void testIngredientGetTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Тип ингредиента должен совпадать с заданным", type, ingredient.getType());
    }

    @Test
    public void testIngredientGetNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Имя ингредиента должно совпадать с заданным", name, ingredient.getName());
    }

    @Test
    public void testIngredientGetPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Цена ингредиента должна совпадать с заданной", price, ingredient.getPrice(), 0.001);
    }
}