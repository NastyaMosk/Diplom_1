package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class BunTest {

    @Test
    public void testBunGetNameReturnsCorrectName() {
        String expectedName = "Булка с кунжутом";
        float expectedPrice = 150.5f;

        Bun bun = new Bun(expectedName, expectedPrice);

        Assert.assertEquals("Имя булки должно совпадать с заданным в конструкторе", expectedName, bun.getName());
    }

    @Test
    public void testBunGetPriceReturnsCorrectPrice() {
        String expectedName = "Булка с кунжутом";
        float expectedPrice = 150.5f;

        Bun bun = new Bun(expectedName, expectedPrice);

        Assert.assertEquals("Цена булки должна совпадать с заданной в конструкторе", expectedPrice, bun.getPrice(), 0.001);
    }
}