import java.util.TimerTask;

public class FoundChecker extends TimerTask {

    private KeyTester[] keyTesters;
    boolean found = false;
    FoundChecker(KeyTester[] keyTesters){
        this.keyTesters = keyTesters;
    }

    public void run(){
        for(int i=0; i<keyTesters.length; i++){

            if(keyTesters[i].hasFound()){
                System.exit(1);
            }
        }
    }
}
