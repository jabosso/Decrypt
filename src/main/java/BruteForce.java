
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class BruteForce {

    public static void main(String[] args) {

        String originalPassword = "";
        int numThreads = 0;
        long keyBits = 0L;

        //Parameters input
        Scanner scanner = new Scanner(System.in);
        try {
            while (originalPassword.equals("") || numThreads == 0 || keyBits == 0L) {
                if (originalPassword.equals("")) {
                    System.out.println("Input password");
                    originalPassword = scanner.nextLine();
                }
                if (numThreads == 0) {
                    System.out.println("Input number of threads");
                    numThreads = Integer.parseInt(scanner.nextLine());
                }
                if (keyBits == 0L) {
                    System.out.println("Input key bits");
                    keyBits = Long.parseLong(scanner.nextLine());
                }
            }
        } catch (Exception e) {
            System.out.println("Missing inputs");
        }
        scanner.close();

        //Generate the to-be-decrypted password depending on the previously defined bits and encode it
        PasswordGenerator pwdGenerator = new PasswordGenerator(originalPassword.length());
        Encrypter encoder = new Encrypter("DES");
        Random generator = new Random();

        long maxKey = ~(0L);
        maxKey = maxKey >>> (64 - keyBits);
        long key = generator.nextLong();
        key = key & maxKey;
        encoder.setKey(key);

        String encryptedPassword = encoder.encrypt(originalPassword, "UTF8");

        //Start time monitoring
        long startTime = System.currentTimeMillis();

        PasswordTester[] threads = new PasswordTester[numThreads];
        FoundChecker fc = new FoundChecker(threads);

        for (int m = 0; m < numThreads; m++) {
            threads[m] = new PasswordTester(encryptedPassword, maxKey, fc, pwdGenerator);
            threads[m].start();
        }

        Timer timer = new Timer();
        timer.schedule(new FoundChecker(threads), 1000, 10000);

        for (int m = 0; m < numThreads; m++) {
            try {
                threads[m].join();
            } catch (InterruptedException e) {
                System.out.println(
                        "Thread " + m + " interrupted. Error: " + e.toString() + " Message: " + e.getMessage());
                return;
            }
        }

        long elapsed = System.currentTimeMillis() - startTime;
        long keys = maxKey + 1;

        System.out.println("Completed brute force on " + keys + " keys in " + elapsed + " ms.");
        System.exit(1);
    }
}
