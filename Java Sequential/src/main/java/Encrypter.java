import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;


import com.sun.mail.util.BASE64EncoderStream;

public class Encrypter {

    private Cipher cipher;
    private final byte[] keyAsLong = new byte[8];
    private final byte[] desKey = new byte[8];

    public Encrypter(String algName) throws IllegalStateException {
        try {
            cipher = Cipher.getInstance(algName);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Unable to init the cipher for the current algorithm", e);
        }
    }

    public void setKey(long newKey) {
        try {
            parseAsByteArray(newKey);
            formatAsDES(keyAsLong, desKey);
            SecretKeySpec key = new SecretKeySpec(desKey, "DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            System.out.println("Unable to use " + newKey + " as a key. Moving to next");
        }
    }

    private void parseAsByteArray(long newKey) {
        //Bit manipulation to partition the key as bytes
        keyAsLong[0] = (byte) (newKey & 0xFF);
        keyAsLong[1] = (byte) ((newKey >> 8) & 0xFF);
        keyAsLong[2] = (byte) ((newKey >> 16) & 0xFF);
        keyAsLong[3] = (byte) ((newKey >> 24) & 0xFF);
        keyAsLong[4] = (byte) ((newKey >> 32) & 0xFF);
        keyAsLong[5] = (byte) ((newKey >> 40) & 0xFF);
        keyAsLong[6] = (byte) ((newKey >> 48) & 0xFF);
        keyAsLong[7] = (byte) ((newKey >> 56) & 0xFF);
    }

    private static void formatAsDES(final byte[] in, final byte[] out){
        out[0] = (byte) ((in[0] >> 1) & 0xff);
        out[1] = (byte) ((((in[0] & 0x01) << 6) | (((in[1] & 0xff) >> 2) & 0xff)) & 0xff);
        out[2] = (byte) ((((in[1] & 0x03) << 5) | (((in[2] & 0xff) >> 3) & 0xff)) & 0xff);
        out[3] = (byte) ((((in[2] & 0x07) << 4) | (((in[3] & 0xff) >> 4) & 0xff)) & 0xff);
        out[4] = (byte) ((((in[3] & 0x0F) << 3) | (((in[4] & 0xff) >> 5) & 0xff)) & 0xff);
        out[5] = (byte) ((((in[4] & 0x1F) << 2) | (((in[5] & 0xff) >> 6) & 0xff)) & 0xff);
        out[6] = (byte) ((((in[5] & 0x3F) << 1) | (((in[6] & 0xff) >> 7) & 0xff)) & 0xff);
        out[7] = (byte) (in[6] & 0x7F);

        for (int i = 0; i < 8; i++) {
            out[i] = (byte) (out[i] << 1);
        }
    }

    //encrypt the password with the the current key and return it as a string
    //returns null if the current password can not be encoded with the current cipher
    public String encrypt(String password, String charsetName) {
        byte[] utf8 = null;
        try {
            utf8 = password.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to use current charset for encryption ", e);
        }catch( NullPointerException e){
            System.out.println(password);
        }
        try {
            byte[] enc = cipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
        } catch (IllegalStateException | GeneralSecurityException | IllegalArgumentException b) {
            return null;
        }
    }
}

