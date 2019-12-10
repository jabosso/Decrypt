import javax.crypto.*;

class KeyTester extends Thread {

    private Decrypter encoder;
    private SealedObject encryptedPassword;
    private long startKey;
    private long endKey;
    private PasswordGenerator generator;
    private long tested;
    private FoundChecker fc;
    private long testedKey;

    public KeyTester(SealedObject mySealed, long startKey, long endKey, int passwordLength, FoundChecker fc) {
        encoder = new Decrypter();
        encryptedPassword = mySealed;
        this.startKey = startKey;
        this.endKey = endKey;
        generator = new PasswordGenerator(passwordLength);
        tested = 0L;
        this.fc = fc;
        testedKey = startKey;
    }

    public void run() {
        while(!fc.found && testedKey< endKey){
            encoder.setKey ( testedKey );
            String decodedPassword = encoder.decrypt(encryptedPassword);
            if(decodedPassword != null){
                while(!fc.found && !generator.testedAllPassword()) {
                    String passwordToTest = generator.generate();
                    if (passwordToTest != null) {
                        tested++;
                        if (decodedPassword.equals(passwordToTest)) {
                            System.out.println("Password decrypted: " + decodedPassword);
                            fc.found = true;
                        }
                    }else{
                        System.out.println("ciccia");
                    }
                }
                generator.reset();
            }
            testedKey++;
        }
    }

    public String getTested(){
        return "has tested " + (tested/1000) + "k password and " + (testedKey - startKey) + " keys";
    }
}


