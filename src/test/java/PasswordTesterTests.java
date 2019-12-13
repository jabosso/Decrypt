import org.testng.annotations.*;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

@RunWith(ConcurrentTestRunner.class)
public class PasswordTesterTests {

    private PasswordTester tester;
    private PasswordGenerator pwdGenerator = new PasswordGenerator(8);
    private FoundChecker fc = new FoundChecker();

    @Test
    public void run_ReturnsFound() {

    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void constructor_ThrowsEmptyPasswordException() throws Exception {
        tester = new PasswordTester("", 256, fc, pwdGenerator);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void constructor_ThrowsNullPasswordException() throws Exception {
        tester = new PasswordTester(null, 256, fc, pwdGenerator);
    }

}