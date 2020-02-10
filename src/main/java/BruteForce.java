import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BruteForce {

    private static Encrypter encoder = new Encrypter("DES");

    public static void main(String[] args){
        String originalPassword = "";
        long keyBits = 0L;

        //Parameters input
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Input password");
            originalPassword = scanner.nextLine();

            System.out.println("Input key bits");
            keyBits = Long.parseLong(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Missing inputs");
        }
        scanner.close();

        //Generate the to-be-decrypted password depending on the previously defined bits and encode it
        Random randomKeyGenerator = new Random();

        long maxKey = ~(0L);
        maxKey = maxKey >>> (64 - keyBits);
        long key = randomKeyGenerator.nextLong();
        key = key & maxKey;
        encoder.setKey(key);

        String originalPasswordEncrypted = encoder.encrypt(originalPassword, "UTF8");

        PasswordGenerator pwdGenerator = new PasswordGenerator(originalPassword.length());
        boolean found = false;
        long currentKey = 0L;

        //Start time monitoring
        long startTime = System.currentTimeMillis();

        while(!found && !pwdGenerator.producedAllPwds()) {
            String currentPassword = pwdGenerator.generate();
            currentKey = 0L;
            while(currentKey <= maxKey && !found){
                found = testPasswordWithKey(originalPasswordEncrypted, currentPassword, currentKey);
                currentKey++;
            }
            pwdGenerator.getAmountGenerated();
        }

        long elapsed = System.currentTimeMillis() - startTime;
        long keys = maxKey + 1;

        System.out.println("Completed brute force on " + keys + " keys in " + elapsed + " ms.");
        System.exit(1);

    }

    public static boolean testPasswordWithKey(String originalPasswordEncrypted, String pwdToTest, long testedKey) {
        encoder.setKey(testedKey);
        String pwdToTestEncrypted = encoder.encrypt(pwdToTest, "UTF8");
        if (pwdToTestEncrypted != null && !pwdToTest.equals("")) {
            if (originalPasswordEncrypted.equals(pwdToTestEncrypted)) {
                System.out.println("Decrypted password: " + pwdToTest);
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Unable to encode password");
            return false;
        }
    }
}

