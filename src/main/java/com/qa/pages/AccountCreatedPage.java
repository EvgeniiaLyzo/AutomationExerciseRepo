package com.qa.pages;

import com.qa.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountCreatedPage {
    WebDriver driver;
    
    By accountedCreatedText = By.xpath("//b[normalize-space()='Account Created!']");
    By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.findElement(accountedCreatedText).isDisplayed()) {
            throw new IllegalStateException("This is not Account Created Page," + " current page is: " + driver.getCurrentUrl());
        }
    }
    
    public AccountPage goToAccountPage() {
        boolean continueFound;
        do {
            Utils.googleAdsDemolisher(driver);
            try {
                WebElement webElement = driver.findElement(continueBtn);
                if (webElement.isDisplayed()) {
                    System.out.println("is about to click continue");
                    webElement.click();
                    continueFound = true;
                } else {
                    continueFound = false;
                }
            } catch (Exception e) {
                continueFound = false;
            }
        } while (continueFound);
        return new AccountPage(driver);
    }
    
}
