public class PasswordGenerator{

    private int passwordLength;
    private int[] passwordVector;
    private long maxAmount;
    private long amountGenerated;
    static char[] charSet = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public PasswordGenerator(int length){
        passwordLength = length;
        passwordVector = new int[passwordLength];
        for(int i=0; i<passwordLength; i++){
            passwordVector[i] = 0;
        }
        maxAmount = (long)Math.pow(charSet.length, passwordLength);
        amountGenerated = 0;
    }

    public String generate(){
        String newPassword = null;
        if(amountGenerated < maxAmount){
            amountGenerated++;
            passwordVector[passwordVector.length - 1] += 1;
            int rest = passwordVector[passwordVector.length - 1] / charSet.length;
            passwordVector[passwordVector.length - 1] = passwordVector[passwordVector.length - 1] % charSet.length ;
            int i = passwordLength - 2;
            while(rest == 1 && i >= 0) {
                if (i != 0 || passwordVector[i] < (charSet.length - 1)) {
                    passwordVector[i] = passwordVector[i] + rest;
                    rest = 0;
                    if (passwordVector[i] == charSet.length && i > 0) {
                        rest = passwordVector[i] / charSet.length;
                        passwordVector[i] = passwordVector[i] % charSet.length;
                        i--;
                    }
                }else{
                    rest = 0;
                }
            }
            newPassword = generateString();
            return newPassword;
        }
        return null;
    }

    private String generateString(){
        StringBuilder newPassword = new StringBuilder();
        for (int value : passwordVector) {
            newPassword.append(charSet[value]);
        }
        return newPassword.toString();
    }

    public boolean testedAllPassword(){
        return amountGenerated == maxAmount;
    }

    public void reset(){
        for(int i=0; i<passwordLength; i++){
            passwordVector[i] = 0;
        }
        amountGenerated = 0;
    }
}