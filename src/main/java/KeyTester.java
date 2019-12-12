class KeyTester extends Thread {

    private Encrypter encoder;
    private String originalPwdEncrypted;
    private long startKey;
    private long endKey;
    private PasswordGenerator generator;
    private long tested;
    private FoundChecker fc;
    private long testedKey;

    public KeyTester(String originalPwdEncrypted, long startKey, long endKey, int pwdLength, FoundChecker fc) {
        encoder = new Encrypter();
        this.originalPwdEncrypted = originalPwdEncrypted;
        this.startKey = startKey;
        this.endKey = endKey;
        generator = new PasswordGenerator(pwdLength);
        tested = 0L;
        this.fc = fc;
        testedKey = startKey;
    }

    public void run() {
        while (!fc.found && testedKey <= endKey) {
            encoder.setKey(testedKey);
            while (!fc.found && !generator.testedAllPwds()) {
                String pwdToTest = generator.generate();
                String pwdToTestEncrypted = encoder.encrypt(pwdToTest);
                if (pwdToTest != null) {
                    tested++;
                    if (originalPwdEncrypted.equals(pwdToTestEncrypted)) {
                        System.out.println("Password decrypted: " + pwdToTest);
                        fc.found = true;
                    }
                } else {
                    System.out.println("ciccia");
                }
            }
            generator.reset();
            testedKey++;
        }

    }

    public String getTested() {
        return "has tested " + (tested / 1000) + "k password and " + (testedKey - startKey) + " keys";
    }
}
