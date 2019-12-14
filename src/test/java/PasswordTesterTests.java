import org.testng.Assert;
import org.testng.annotations.*;

public class PasswordTesterTests {

    private PasswordTester tester;
    private PasswordGenerator pwdGenerator = new PasswordGenerator(8);
    private FoundChecker fc = new FoundChecker();

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void constructor_ThrowsEmptyPasswordException() {
        tester = new PasswordTester("", 256, fc, pwdGenerator);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void constructor_ThrowsNoKeyToTestException() {
        tester = new PasswordTester("password", -1, fc, pwdGenerator);
    }

    @Test
    public void testPassword_ReturnsTrue() {
        tester = new PasswordTester("f8X9Zk5RKY9WVt3Ocdr8gA==", 256, fc, pwdGenerator);

        boolean actual = tester.testPasswordWithKey("00029305", 77);

        Assert.assertTrue(actual);
    }

    @Test
    public void testPassword_ReturnsFalse() {
        tester = new PasswordTester("WRONG_PASSWORD", 256, fc, pwdGenerator);

        boolean actual = tester.testPasswordWithKey("00029305", 77);

        Assert.assertFalse(actual);
    }

}