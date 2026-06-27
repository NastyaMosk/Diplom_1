package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeHasCorrectCountOfValues() {
        IngredientType[] types = IngredientType.values();
        Assert.assertEquals("Перечисление IngredientType должно содержать ровно 2 типа", 2, types.length);
    }

    @Test
    public void testIngredientTypeContainsSauceValue() {
        Assert.assertEquals("Должен существовать тип ингредиента SAUCE", IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void testIngredientTypeContainsFillingValue() {
        Assert.assertEquals("Должен существовать тип ингредиента FILLING", IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}