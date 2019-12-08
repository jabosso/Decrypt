import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class KeyProducer extends Thread {

    private SecretKeyBuffer buffer;
    private List<SecretKey> producedKeys = new ArrayList<SecretKey>();
    private boolean found;

    public KeyProducer(SecretKeyBuffer buffer){
        this.buffer = buffer;
        found = false;
    }

    public void run(){
        while(!found && producedKeys.size() < Math.pow(2, 56)){
            try{
                SecretKey newEncryptionKey = KeyGenerator.getInstance("DES").generateKey();
                if(!producedKeys.contains(newEncryptionKey)){
                    producedKeys.add(newEncryptionKey);
                    buffer.add(newEncryptionKey);
                }
            }catch(NoSuchAlgorithmException e){
                System.out.println("Errore nella creazione della chiave");
            }
        }

    }

}



