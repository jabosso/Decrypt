public class PasswordGenerator {

    private int pwdLength;
    private int[] pwdVector;
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
        amountGenerated = 0;
    }

    public synchronized String generate() {
        String newPwd = null;
        if (amountGenerated == 0) {
            pwdVector = new int[pwdLength];
            newPwd = generateString(pwdVector);
            amountGenerated++;
        } else if (!producedAllPwds()) {
            pwdVector = getNextPassword(pwdVector);
            newPwd = generateString(pwdVector);
        }
        return newPwd;
    }

    protected int[] getNextPassword(int[] vector) {
        int[] startingVector = vector.clone();
        vector[vector.length - 1] += 1;
        int rest = vector[vector.length - 1] / charSet.length;
        vector[vector.length - 1] = vector[vector.length - 1] % charSet.length;
        int i = pwdLength - 2;
        while (rest == 1 && i >= 0) {
            vector[i] = vector[i] + rest;
            rest = 0;
            if (vector[i] == charSet.length) {
                rest = vector[i] / charSet.length;
                vector[i] = vector[i] % charSet.length;
                i--;
            }
        }
        amountGenerated++;
        boolean empty = true;
        for (int j = 0; i < vector.length; i++) {
            if (vector[j] != 0) {
                empty = false;
            }
        }
        if (empty) {
            return startingVector;
        }
        return vector;
    }

    private String generateString(int[] vector) {
        StringBuilder newPwd = new StringBuilder();
        for (int value : vector) {
            newPwd.append(charSet[value]);
        }
        return newPwd.toString();
    }

    public boolean producedAllPwds() {
        return amountGenerated == getMaxAmount();
    }

    protected long getMaxAmount() {
        long maxAmount = (long) Math.pow(charSet.length, pwdLength);
        return maxAmount;
    }

    protected long getAmountGenerated() {
        return amountGenerated;
    }
}