import org.testng.Assert;
import org.testng.annotations.*;

public class EncrypterTests {

    private Encrypter encrypter = new Encrypter();

    @Test
    public void encrypt_ReturnsNull() {
        String actual = encrypter.encrypt("tespassword");
        Assert.assertEquals(actual, null);
    }

    @Test
    public void encrypt_ReturnsCorrectString() {
        encrypter.setKey(1);
        String actual = encrypter.encrypt("testpassword");

        Assert.assertEquals(actual, "zT6v24eOQqTrlxOoDxw5Kg==");
    }

}