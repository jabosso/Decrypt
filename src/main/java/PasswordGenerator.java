import java.util.Arrays;

public class PasswordGenerator {

    private int pwdLength;
    private int[] pwdVector;
    private long maxAmount;
    private long amountGenerated;
    /*
     * static char[] charSet =
     * {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i',
     * 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','
     * C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V
     * ','W','X','Y','Z'};
     */
    static char[] charSet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public PasswordGenerator(int length) {
        pwdLength = length;
        pwdVector = new int[pwdLength];
        for (int i = 0; i < pwdLength; i++) {
            pwdVector[i] = 0;
        }
        pwdVector[pwdVector.length - 1] = -1;
        maxAmount = (long) Math.pow(charSet.length, pwdLength);
        amountGenerated = 0;
    }

    public synchronized String generate() {
        String newPwd = null;
        if (amountGenerated < maxAmount) {
            amountGenerated++;
            pwdVector[pwdVector.length - 1] += 1;
            int rest = pwdVector[pwdVector.length - 1] / charSet.length;
            pwdVector[pwdVector.length - 1] = pwdVector[pwdVector.length - 1] % charSet.length;
            int i = pwdLength - 2;
            while (rest == 1 && i >= 0) {
                if (i != 0 || pwdVector[i] < (charSet.length - 1)) {
                    pwdVector[i] = pwdVector[i] + rest;
                    rest = 0;
                    if (pwdVector[i] == charSet.length && i > 0) {
                        rest = pwdVector[i] / charSet.length;
                        pwdVector[i] = pwdVector[i] % charSet.length;
                        i--;
                    }
                } else {
                    rest = 0;
                }
            }
            newPwd = generateString();
            return newPwd;
        }
        return null;
    }

    private String generateString() {
        StringBuilder newPwd = new StringBuilder();
        for (int value : pwdVector) {
            newPwd.append(charSet[value]);
        }
        return newPwd.toString();
    }

    public boolean testedAllPwds() {
        return amountGenerated == maxAmount;
    }

    public void reset() {
        for (int i = 0; i < pwdLength; i++) {
            pwdVector[i] = 0;
        }
        amountGenerated = 0;
    }

    protected boolean isPwdVectorEmpty() {
        int[] emptyVector = new int[pwdLength];
        boolean equal = Arrays.equals(pwdVector, emptyVector);
        if (equal) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isAmountZero() {
        return amountGenerated == 0;
    }

    protected long getMaxAmount() {
        return maxAmount;
    }
}