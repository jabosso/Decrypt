import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.sun.mail.util.BASE64EncoderStream;

public class Encrypter {

    private static SecretKey encryptionKey;
    private static Cipher eCipher;
    private List<SecretKey> testedKeys = new ArrayList<SecretKey>();

    public Encrypter(){

        try{
            this.eCipher = Cipher.getInstance("DES");
            encryptionKey = KeyGenerator.getInstance("DES").generateKey();
            testedKeys.add(encryptionKey);
            eCipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
        }catch(NoSuchAlgorithmException e){
            System.out.println("Errore nella creazione dell'encrypter");
            return;
        }catch(NoSuchPaddingException e){
            System.out.println("Errore nel padding dell'algoritmo");
            return ;
        }catch(InvalidKeyException e){
            System.out.println("Errore nell'inizializzazione del cypher");
            return ;
        }

    }

    public String encrypt(String password){
        try {
            byte[] utf8 = password.getBytes("UTF8");
            byte[] enc = eCipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateKey(){
        SecretKey newEncryptionKey;
        try{
            newEncryptionKey = KeyGenerator.getInstance("DES").generateKey();
        }catch(NoSuchAlgorithmException e){
            System.out.println("Errore nella creazione dell'encrypter");
            return;
        }
        if(!testedKeys.contains(newEncryptionKey)) {
            try {
                testedKeys.add(newEncryptionKey);
                encryptionKey = newEncryptionKey;
                eCipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
            } catch (InvalidKeyException e) {
                System.out.println("Errore nell'inizializzazione del cypher");
                return;
            }
        }else{
            System.out.println("Chiave gia usata");
            updateKey();
        }
    }


}
