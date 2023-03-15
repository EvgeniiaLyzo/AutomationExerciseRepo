package com.qa.util;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static void takeSnapshot(WebDriver driver, ExtentTest test) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/Reports/ScreenShots/" + generateScreenshotName();
        try {
            FileHandler.copy(file, new File(path));
            test.log(LogStatus.INFO, "SnapShot-->" + test.addScreenCapture(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void handleException(Exception e, ExtentTest test, WebDriver driver, String message) {
        test.log(LogStatus.FAIL, message);
        Utils.takeSnapshot(driver, test);
        System.out.println();
        System.out.println("*********************  EXCEPTION DESCRIPTION  ****************");
        e.printStackTrace();
    }

    public static boolean reportBaseOnElement(WebDriver driver, ExtentTest test, String xpath, Logger log) {
        try {
            driver.findElement(By.xpath(xpath));
            test.log(LogStatus.PASS, "Element FOUND");
            log.info("Element FOUND");
            takeSnapshot(driver, test);
            return true;
        } catch (NoSuchElementException e) {
            log.error("Element not found");
            log.error(e);
            test.log(LogStatus.FAIL, "Element NOT FOUND");
            takeSnapshot(driver, test);
            return false;
        }
    }

    public static void googleAdsDemolisher(WebDriver driver) {
        
        JavascriptExecutor js = (JavascriptExecutor) driver; 
        
        List<WebElement> ads = driver.findElements(By.cssSelector("ins.adsbygoogle"));
        for (WebElement ad : ads) {
            System.out.println("Removing ad");
            js.executeScript("arguments[0].style.display = 'none'", ad);
        }
    }


    public static String generateScreenshotName() {
        return "ss" + String.valueOf(System.currentTimeMillis()) + ".png";

    }

    public static void generateRandomEmail() {
        try {
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

