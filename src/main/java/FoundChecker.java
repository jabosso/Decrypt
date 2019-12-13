import java.util.TimerTask;

public class FoundChecker extends TimerTask {

    volatile boolean found;

    private PasswordTester[] threads;

    protected FoundChecker() {
        found = false;
    }

    FoundChecker(PasswordTester[] threads) {
        this.threads = threads;
        found = false;
    }

    public void run() {
        for (int i = 0; i < threads.length; i++) {
            System.out.println("Thread " + i + " " + threads[i].getTested());
        }
    }
}
