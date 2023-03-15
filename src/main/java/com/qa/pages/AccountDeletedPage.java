package com.qa.pages;

import com.qa.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage {
    WebDriver driver;
    
    By accountDeletedText = By.xpath("//b[normalize-space()='Account Deleted!']");
    By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        this.driver = driver;
        Utils.googleAdsDemolisher(driver);

        if (!driver.findElement(accountDeletedText).isDisplayed()) {
            throw new IllegalStateException("This is not AccountDeleted Page," + " current page is: " + driver.getCurrentUrl());
        }
    }
    
    public HomePage continueToHomePage() {
        driver.findElement(continueBtn).click();
        return new HomePage(driver);
    }
}
