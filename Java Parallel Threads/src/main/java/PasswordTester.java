
class PasswordTester extends Thread {

    private Encrypter encoder;
    private String originalPwdEncrypted;
    private long startKey;
    private long endKey;
    private long tested;
    private FoundChecker fc;
    private PasswordGenerator generator;

    public PasswordTester(String originalPwdEncrypted, long endKey, FoundChecker fc, PasswordGenerator generator)
            throws IllegalArgumentException {

        encoder = new Encrypter("DES");
        if (originalPwdEncrypted.equals("")) {
            throw new IllegalArgumentException("Password can not be empty");
        }
        this.originalPwdEncrypted = originalPwdEncrypted;
        startKey = 0;
        if (endKey < 0) {
            throw new IllegalArgumentException("No keys to test");
        }

        this.endKey = endKey;
        tested = 0L;
        this.fc = fc;
        this.generator = generator;
    }

    //at every outer loop generates a new password from the singleton generator and reset the used key to startKey
    //at every inner loop encrypts the current password with one key and tests it again the original encrypted password
    public void run() {
        while (!fc.found) {
            String pwdToTest = generator.generate();
            long testedKey = startKey;
            tested++;

            while (testedKey <= endKey && !fc.found) {
                if(testPasswordWithKey(pwdToTest, testedKey)){
                    fc.found = true;
                }
                testedKey++;
            }

        }
    }

    public boolean testPasswordWithKey(String pwdToTest, long testedKey) {
        encoder.setKey(testedKey);
        String pwdToTestEncrypted = encoder.encrypt(pwdToTest, "UTF8");
        if (pwdToTestEncrypted != null && !pwdToTest.equals("")) {
            if (originalPwdEncrypted.equals(pwdToTestEncrypted)) {
                System.out.println("Decrypted password: " + pwdToTest);
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Unable to encode password");
            return false;
        }
    }

    public String getTested() {
        return "has tested " + tested + " passwords";
    }
}
