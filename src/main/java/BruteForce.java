
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class BruteForce {

    public static void main(String[] args) {

        String originalPassword = "";
        int numThreads = 0;
        long keyBits = 0L;
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
        long maxkey = ~(0L);
        maxkey = maxkey >>> (64 - keyBits);

        Encrypter encoder = new Encrypter();
        Random generator = new Random();
        long key = generator.nextLong();
        key = key & maxkey;
        encoder.setKey(key);
        String encryptedPassword = encoder.encrypt(originalPassword);

        long startTime = System.currentTimeMillis();
        long keyInterval = maxkey / numThreads;
        long firstKey;
        long lastKey = 0;

        KeyTester[] threads = new KeyTester[numThreads];
        FoundChecker fc = new FoundChecker(threads);
        for (int m = 0; m < numThreads; m++) {
            firstKey = lastKey;
            lastKey += keyInterval;
            if (m == numThreads - 1) {
                lastKey = maxkey + 1;
            }
            threads[m] = new KeyTester(encryptedPassword, firstKey, lastKey, originalPassword.length(), fc);
            threads[m].start();
        }

        Timer timer = new Timer();
        timer.schedule(new FoundChecker(threads), 1000, 10000);

        for (int m = 0; m < numThreads; m++) {
            try {
                threads[m].join();
            } catch (InterruptedException e) {
                System.out.println(
                        "Thread " + m + " interrupted.  Exception: " + e.toString() + " Message: " + e.getMessage());
                return;
            }
        }

        // Output search time
        long elapsed = System.currentTimeMillis() - startTime;
        long keys = maxkey + 1;
        System.out.println("Completed search of " + keys + " keys at " + elapsed + " milliseconds.");
        System.exit(1);
    }
}
