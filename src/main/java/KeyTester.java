import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

public class KeyTester extends Thread{

    private SecretKeyBuffer buffer;
    private SecretKey testedKey;
    private String encodedPassword;
    private SecretKey usedKey;
    private String resultPassword;
    private PasswordGenerator generator;
    private List<String> testedPasswords = new ArrayList<String>();
    private Encrypter encrypter;
    private boolean found;

    public KeyTester(SecretKeyBuffer buffer, String encodedPassword){
        this.buffer = buffer;
        this.encodedPassword = encodedPassword;
        generator = new PasswordGenerator(1);
        testedKey = null;
        encrypter = new Encrypter();
        found = false;
        resultPassword = "";
    }

    public void run(){
        while(!found) {
            if(testedKey == null) {
                updateKey();
            }
            while (testedKey != null && testedPasswords.size() < Math.pow(36, 1)) {
                String passwordToTest = generator.generate();
                if (!testedPasswords.contains(passwordToTest)) {
                    String encodedToTest = encrypter.encrypt(passwordToTest);
                    if (encodedToTest != null && encodedToTest.equals(encodedPassword)) {
                        found = true;
                        usedKey = testedKey;
                        resultPassword = passwordToTest;
                    } else {
                        testedPasswords.add(passwordToTest);
                    }
                }
            }
            testedKey = null;
            testedPasswords = new ArrayList<String>();
        }
    }

    private void updateKey(){
        testedKey = buffer.consume();
        if(testedKey != null){
            encrypter.setKey(testedKey);
        }else{
            testedKey = null;
        }

    }

    public boolean hasFound(){
        return found;
    }

    public void setFound(boolean found){
        this.found = found;
    }
}
