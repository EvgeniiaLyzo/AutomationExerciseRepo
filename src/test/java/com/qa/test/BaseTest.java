package com.qa.test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    WebDriver driver = null;
    ExtentReports report;
    Properties prop = new Properties();
    String baseUrl;
    String rootPath = null;
    Logger log = LogManager.getLogger(this);
    ExtentTest test;

    @BeforeTest
    public void setup() {

        try {

//			environ configuration
            log.info("Initiating logs for tests");

            rootPath = System.getProperty("user.dir");
            FileInputStream fis = new FileInputStream(rootPath + "/config.properties");
            prop.load(fis);
            fis.close();

//			URl configuration
            baseUrl = prop.getProperty("base.url");

//			Report configuration
            report = new ExtentReports(rootPath + "/Reports/Results.html");
            log.info("Initiating reports");

        } catch (Exception e) {
            System.out.println("Problem occured while setup");
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDown() {
        report.flush();

    }

    @BeforeMethod
    public void initBrowserAndReport() {
        test = report.startTest("Test started");

//		browser config.properties

        String browser = prop.getProperty("browser");
        if (browser.equalsIgnoreCase("CH")) {
            log.info("Initiating tests on Chrome browser");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("ED")) {
            log.info("Initiating tests on Edge browser");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void closeBrowserAndReport() {
        report.endTest(test);
        driver.quit();

    }

}
