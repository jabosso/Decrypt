import java.util.TimerTask;

public class ProgressPrinter extends TimerTask {


    private PasswordGenerator generator;

    ProgressPrinter(PasswordGenerator generator) {
        this.generator = generator;
    }

    public void run() {
        System.out.println("Tested passwords: " + generator.getAmountGenerated());
    }
}
