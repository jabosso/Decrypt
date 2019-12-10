import java.util.TimerTask;

public class FoundChecker extends TimerTask {

    volatile boolean found;

    private KeyTester[] threads;
    FoundChecker(KeyTester[] threads){
        this.threads = threads;
        found = false;
    }

    public void run(){
        for(int i=0; i<threads.length; i++){
            System.out.println("Thread " + i + " " + threads[i].getTested());
        }
    }
}
