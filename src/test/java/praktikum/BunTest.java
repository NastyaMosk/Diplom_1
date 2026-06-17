package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class BunTest {

    @Test
    public void testBunConstructorAndGetters() {
        String expectedName = "Булка с кунжутом";
        float expectedPrice = 150.5f;

        Bun bun = new Bun(expectedName, expectedPrice);

        Assert.assertEquals(expectedName, bun.getName());
        Assert.assertEquals(expectedPrice, bun.getPrice(), 0.001);
    }
}