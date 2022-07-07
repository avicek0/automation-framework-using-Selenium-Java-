//This class is created to hide our emailid password so that outside people wont have access to it.
//Our password was "123456" and we encoded it in "www.base64encode.org" site & the encoded password is MTIzNDU2.



package utils;

import drivers.DriverSingleton;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class Utils {
    public static String decode64(String encodedStr){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedStr.getBytes()));
    }



    //This code is written to take screenshot of any error that might take place during testing.

    public static boolean takeScreenshot(){
        File file = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(file, new File(Constants.SCREENSHOTS_FOLDER + generateRandomString(Constants.SCREENSHOT_NAME_LENGTH) + Constants.SCREENSHOT_EXTENSION));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static String generateRandomString(int length){
        String seedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Random random = new Random();
        while(i < length){
            sb.append(seedChars.charAt(random.nextInt(seedChars.length())));
            i++;
        }
        return sb.toString();
    }


}
