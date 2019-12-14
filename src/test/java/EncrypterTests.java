import org.testng.Assert;
import org.testng.annotations.*;

public class EncrypterTests {

    private Encrypter encrypter;
    private String charsetName = "UTF8";

    @Test(expectedExceptions = { IllegalStateException.class })
    public void constructor_ThrowsIllegalAlgorithm() {
        encrypter = new Encrypter("BAD_ALGORITHM");
    }

    @Test
    public void encrypt_ReturnsNull() {
        encrypter = new Encrypter("DES");
        String actual = encrypter.encrypt("tespassword", charsetName);
        Assert.assertEquals(actual, null);
    }

    @Test
    public void encrypt_ReturnsCorrectString() {
        encrypter = new Encrypter("DES");
        encrypter.setKey(1);
        String actual = encrypter.encrypt("testpassword", charsetName);

        Assert.assertEquals(actual, "zT6v24eOQqTrlxOoDxw5Kg==");
    }

    @Test(expectedExceptions = { IllegalStateException.class })
    public void encrypt_ThrowsIllegalCharsetException() {
        encrypter = new Encrypter("DES");
        encrypter.setKey(1);
        encrypter.encrypt("testpassword", "BAD_CHARSET");
    }

    @Test
    public void setKey_ValidForAllLong() {
        encrypter = new Encrypter("DES");
        encrypter.setKey(Long.MAX_VALUE);
        encrypter.setKey(Long.MIN_VALUE);
        encrypter.setKey(0);
    }

}