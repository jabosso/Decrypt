import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
public class SecretKeyBuffer {

    private List<SecretKey> keysToTest = new ArrayList<SecretKey>();
    private Semaphore semaphore = new Semaphore(0);
    private int consumed = 0;

    public void add(SecretKey newKey){
        keysToTest.add(newKey);
        semaphore.release();
    }

    public synchronized SecretKey consume(){
        SecretKey newKey = null;
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            System.out.println("Acquire fallito");
            return null;
        }
        if(keysToTest.size() > 0){
            newKey = keysToTest.get(0);
            keysToTest.remove(0);
            consumed++;
            if (consumed % 10000 == 0) {
                System.out.println(consumed / 1000);
            }
        }
        return newKey;
    }
}
