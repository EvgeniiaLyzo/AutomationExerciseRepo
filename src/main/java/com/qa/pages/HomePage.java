package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    
    By loginSignupLink = By.xpath("//a[normalize-space()='Signup / Login']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise")) {
            throw new IllegalStateException("This is not Home Page," + " current page is: " + driver.getCurrentUrl());
        }
    }
    
    public LoginPage goToLoginPage() {
        driver.findElement(loginSignupLink).click();
        return new LoginPage(driver);
    }
    
    
}
