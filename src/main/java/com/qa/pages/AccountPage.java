package com.qa.pages;

import com.qa.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    WebDriver driver;
    
    By logoutLink = By.xpath("//a[normalize-space()='Logout']");
    By deleteAccountLink = By.xpath("//a[normalize-space()='Delete Account']");
    By loggedAsUserLink = By.xpath("//a[contains(text(), 'Logged in as')]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        Utils.googleAdsDemolisher(driver);
        if (!driver.findElement(logoutLink).isDisplayed()) {
            throw new IllegalStateException("This is not Account Page," + " current page is: " + driver.getCurrentUrl());
        }
    }
    
    public AccountDeletedPage deleteAccount() {
        driver.findElement(deleteAccountLink).click();
        return new AccountDeletedPage(driver);
    }
    
    public boolean isLogged() {
        return driver.findElement(loggedAsUserLink).isDisplayed();
    }
}
