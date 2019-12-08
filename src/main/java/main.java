import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;

public class main {

    public static void main(String[] args){
        String originalPassword = "i";
        String encryptedPassword = "";

        try{
            SecretKey encryptionKey = KeyGenerator.getInstance("DES").generateKey();
            Encrypter originalEncoder = new Encrypter();
            originalEncoder.setKey(encryptionKey);
            encryptedPassword = originalEncoder.encrypt(originalPassword);
        }catch(NoSuchAlgorithmException e){}

        SecretKeyBuffer buffer = new SecretKeyBuffer();
        KeyProducer producer = new KeyProducer(buffer);
        KeyTester[] keyTesters = new KeyTester[6];
        for(int i=0; i<keyTesters.length; i++){
            keyTesters[i] = new KeyTester(buffer, encryptedPassword);
        }

        producer.start();
        for(int i=0; i<keyTesters.length; i++){
            keyTesters[i].start();
        }
        Timer timer = new Timer();
        timer.schedule(new FoundChecker(keyTesters),1000, 20000);
    }
}
