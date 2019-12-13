class PasswordTester extends Thread {

    private Encrypter encoder;
    private String originalPwdEncrypted;
    private long startKey;
    private long endKey;
    private long tested;
    private FoundChecker fc;
    private long testedKey;
    private PasswordGenerator generator;

    public PasswordTester(String originalPwdEncrypted, long endKey, FoundChecker fc, PasswordGenerator generator) {
        encoder = new Encrypter();
        if (originalPwdEncrypted.equals("") || originalPwdEncrypted.equals(null)) {
            try {
                throw new IllegalArgumentException("La password non puo essere vuota");
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
        this.originalPwdEncrypted = originalPwdEncrypted;
        startKey = 0;
        if (endKey < 0) {
            try {
                throw new IllegalArgumentException("Nessuna chiave da testare");
            } catch (IllegalArgumentException e) {
                throw e;
            }
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
            while (testedKey <= endKey) {
                encoder.setKey(testedKey);
                String pwdToTestEncrypted = encoder.encrypt(pwdToTest);
                if (pwdToTestEncrypted != null && !pwdToTest.equals("")) {
                    if (originalPwdEncrypted.equals(pwdToTestEncrypted)) {
                        System.out.println("Password decriptata: " + pwdToTest);
                        fc.found = true;
                    }
                } else {
                    System.out.println("Errore nella generazione della password");
                }
                testedKey++;
            }
        }

    }

    private void checkPwd(String pwd) {

    }

    public String getTested() {
        return "ha testato " + tested + " passwords";
    }

    protected boolean hasFound() {
        return fc.found;
    }
}
