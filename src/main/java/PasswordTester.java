class PasswordTester extends Thread {

    private Encrypter encoder;
    private String originalPwdEncrypted;
    private long startKey;
    private long endKey;
    private long tested;
    private FoundChecker fc;
    private long testedKey;
    private PasswordGenerator generator;

    public PasswordTester(String originalPwdEncrypted, long endKey, FoundChecker fc, PasswordGenerator generator)
            throws IllegalArgumentException {

        encoder = new Encrypter("DES");
        if (originalPwdEncrypted.equals("") || originalPwdEncrypted.equals(null)) {
            throw new IllegalArgumentException("La password non puo essere vuota");
        }
        this.originalPwdEncrypted = originalPwdEncrypted;
        startKey = 0;
        if (endKey < 0) {
            throw new IllegalArgumentException("Nessuna chiave da testare");
        }
        this.endKey = endKey;
        tested = 0L;
        this.fc = fc;
        this.generator = generator;
    }

    public void run() {
        while (!fc.found) {
            String pwdToTest = generator.generate();
            testedKey = startKey;
            tested++;
            while (testedKey <= endKey && !fc.found) {
                fc.found = testPasswordWithKey(pwdToTest, testedKey);
                testedKey++;
            }
        }
    }

    public boolean testPasswordWithKey(String pwdToTest, long testedKey) {
        encoder.setKey(testedKey);
        String pwdToTestEncrypted = encoder.encrypt(pwdToTest, "UTF8");
        if (pwdToTestEncrypted != null && !pwdToTest.equals("")) {
            if (originalPwdEncrypted.equals(pwdToTestEncrypted)) {
                System.out.println("Password decriptata: " + pwdToTest);
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Errore nella codifica della password");
            return false;
        }
    }

    public String getTested() {
        return "ha testato " + tested + " passwords";
    }
}
