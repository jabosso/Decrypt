import org.testng.annotations.*;
import java.lang.reflect.Field;
import org.testng.*;

public class PasswordGeneratorTests {

    @Test
    public void TestedAllPwds_ReturnsTrue() throws NoSuchFieldException, IllegalAccessException {
        Field amountGenerated = PasswordGenerator.class.getDeclaredField("amountGenerated");
        amountGenerated.setAccessible(true);
        PasswordGenerator generator = new PasswordGenerator(8);
        long myAmount = 100000000L;
        amountGenerated.set(generator, myAmount);

        final boolean actual = generator.testedAllPwds();

        Assert.assertTrue(actual);
    }

    @Test
    public void TestedAllPwds_ReturnsFalse() throws NoSuchFieldException, IllegalAccessException {
        Field amountGenerated = PasswordGenerator.class.getDeclaredField("amountGenerated");
        amountGenerated.setAccessible(true);
        PasswordGenerator generator = new PasswordGenerator(8);
        long myAmount = 1L;
        amountGenerated.set(generator, myAmount);

        final boolean actual = generator.testedAllPwds();

        Assert.assertFalse(actual);
    }

    @Test
    public void Reset_ReturnsZeroArray() throws NoSuchFieldException, IllegalAccessException {

        Field pwdVectorField = PasswordGenerator.class.getDeclaredField("pwdVector");
        pwdVectorField.setAccessible(true);
        PasswordGenerator generator = new PasswordGenerator(8);
        int[] pwdVector = new int[8];
        for (int i = 0; i < pwdVector.length; i++) {
            pwdVector[i] = (int) (Math.random() * ((10)));
        }

        pwdVectorField.set(generator, pwdVector);
        generator.reset();

        Assert.assertTrue(generator.isPwdVectorEmpty());
        Assert.assertTrue(generator.isAmountZero());
    }

    @Test
    public void generate_ReturnsStringWithChars() {
        PasswordGenerator generator = new PasswordGenerator(8);
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
    public void generate_ReturnsNull() throws NoSuchFieldException, IllegalAccessException {
        Field amountGeneratedField = PasswordGenerator.class.getDeclaredField("amountGenerated");
        amountGeneratedField.setAccessible(true);
        PasswordGenerator generator = new PasswordGenerator(8);
        long amountGenerated = generator.getMaxAmount();
        amountGeneratedField.set(generator, amountGenerated);

        String actual = generator.generate();

        Assert.assertEquals(actual, null);
    }

}