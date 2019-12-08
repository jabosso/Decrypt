import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {

    int passwordLength;

    PasswordGenerator(int length){
        passwordLength = length;
    }

    public String generate(){
        String result = RandomStringUtils.randomAlphanumeric(passwordLength);
        return result;
    }
}