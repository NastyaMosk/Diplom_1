package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValues() {
        IngredientType[] types = IngredientType.values();
        Assert.assertEquals(2, types.length);
        Assert.assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        Assert.assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}