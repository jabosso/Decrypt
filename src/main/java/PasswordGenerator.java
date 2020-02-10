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

    //generates one password at a time until has generated all possible passwords
    public String generate() {
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

    //considers the 8 character password as an array of integers
    //every integer defines one of the elements of the charset
    //useful if charset is not made only of numbers
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
        for (int value : vector) {
            if (value != 0) {
                empty = false;
                break;
            }
        }
        if (empty) {
            return startingVector;
        }

        return vector;
    }

    //generate password string from integer array
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
        return (long) Math.pow(charSet.length, pwdLength);
    }

    protected void getAmountGenerated() {
        if((amountGenerated / 10000 > 0) && (amountGenerated % 10000 == 0)){
            System.out.println("Tested passwords: " + amountGenerated);
        }
    }
}
