import org.testng.annotations.*;
import org.testng.*;

public class PasswordGeneratorTests {

    private PasswordGenerator generator;

    @Test
    public void ProducedAllPwds_ReturnsTrue() {
        generator = new PasswordGenerator(3);
        for (int i = 0; i < generator.getMaxAmount(); i++) {
            generator.generate();
        }
        Assert.assertEquals(generator.getAmountGenerated(), generator.getMaxAmount());
    }

    @Test
    public void ProducedAllPwds_ReturnsFalse() {
        generator = new PasswordGenerator(5);
        for (int i = 0; i < generator.getMaxAmount() - 1; i++) {
            generator.generate();
        }
        boolean actual = generator.getAmountGenerated() == generator.getMaxAmount();
        Assert.assertFalse(actual);
    }

    @Test
    public void generate_ReturnsStringWithChars() {
        generator = new PasswordGenerator(8);
        String actual = generator.generate();

        boolean wrongChar = false;
        for (char c : actual.toCharArray()) {
            boolean found = false;
            for (char cSet : PasswordGenerator.charSet) {
                if (c == cSet) {
                    found = true;
                }
            }
            if (!found) {
                wrongChar = true;
            }
        }

        Assert.assertTrue(actual.length() == 8);
        Assert.assertFalse(wrongChar);
    }

    @Test
    public void generate_ReturnsNull() {
        generator = new PasswordGenerator(3);
        for (int i = 0; i < generator.getMaxAmount(); i++) {
            generator.generate();
        }

        String actual = generator.generate();

        Assert.assertEquals(actual, null);
    }

    @Test
    public void getNextPassword_ReturnsArrayIncrementedByOne() {
        generator = new PasswordGenerator(3);
        int[] actual = { 5, 4, 3 };

        actual = generator.getNextPassword(actual);
        int[] expected = { 5, 4, 4 };
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getNextPassword_DealsWithRest() {
        generator = new PasswordGenerator(3);
        int charsetLength = PasswordGenerator.charSet.length;
        int[] actual = { 5, charsetLength - 1, charsetLength - 1 };

        actual = generator.getNextPassword(actual);

        int[] expected = { 6, 0, 0 };
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getNextPassword_StopsAtMaxAmount() {
        generator = new PasswordGenerator(3);
        int charsetLength = PasswordGenerator.charSet.length;
        int[] expected = { charsetLength - 1, charsetLength - 1, charsetLength - 1 };
        int[] actual = expected.clone();

        actual = generator.getNextPassword(actual);

        Assert.assertEquals(actual, expected);
    }
}