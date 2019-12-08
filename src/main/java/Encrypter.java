import com.sun.mail.util.BASE64EncoderStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Encrypter {

    private static SecretKey encryptionKey;
    private static Cipher eCipher;

    public Encrypter(){
        try{
            eCipher = Cipher.getInstance("DES");
        }catch(NoSuchAlgorithmException e){
            System.out.println("Errore nella creazione dell'encrypter");
        }catch(NoSuchPaddingException e){
            System.out.println("Errore nel padding dell'algoritmo");
        }
    }

    public void setKey(SecretKey newKey){
        try{
            encryptionKey = newKey;
            eCipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }
    }

    public String encrypt(String password){
        try {
            byte[] utf8 = password.getBytes("UTF8");
            byte[] enc = eCipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
        }catch (Exception e) {
        }
        return null;
    }
}
