package pl.edu.pjatk.tau.luke.util;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {

    private StringUtils() {
    }
    
    public static String createRandomString(String suffix){
        String randomString = RandomStringUtils.random(10, true, false);
        return randomString+"@com.pl";
    }
    
    
}
