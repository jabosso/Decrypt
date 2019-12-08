import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class main {



    public static void main(String[] args){
        String password = "ottootto";
        Encrypter originalEncoder = new Encrypter();
        String encodedPassword = originalEncoder.encrypt(password);
        Encrypter attackEncoder = new Encrypter();
        PasswordGenerator generator = new PasswordGenerator(password.length());
        List<String> testedPasswords = new ArrayList<String>();
        boolean found = false;
        int keyCounter = 0;

        while(!found && keyCounter < Math.pow(2, 56)) {
            String testAttackPassword = generator.generate();
            if(!testedPasswords.contains(testAttackPassword)){
                String testAttackPasswordEncoded = attackEncoder.encrypt(testAttackPassword);
                if(testAttackPasswordEncoded.equals(encodedPassword) ) {
                    found = true;
                    System.out.println("La password era " + testAttackPassword);
                }else{
                    testedPasswords.add(testAttackPassword);
                    if(testedPasswords.size() == Math.pow(36, password.length())){
                        attackEncoder.updateKey();
                        keyCounter++;
                        if((keyCounter%10000) == 0 && keyCounter > 10000){
                            System.out.println(keyCounter/1000);
                        }
                        testedPasswords = new ArrayList<String>();
                    }
                }
            }
        }
    }
}
