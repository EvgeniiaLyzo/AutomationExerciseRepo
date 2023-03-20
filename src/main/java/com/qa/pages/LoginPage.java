package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    
    By loginEmail = By.xpath("//input[@data-qa='login-email']");
    By passwordTbx = By.xpath("//input[@data-qa='login-password']");
    By signupName = By.xpath("//input[@data-qa='signup-name']");
    By signupEmail = By.xpath("//input[@data-qa='signup-email']");
    By loginBtn = By.xpath("//button[normalize-space()='Login']");
    By signupBtn = By.xpath("//button[normalize-space()='Signup']");
    By newUserSignupElem = By.xpath("//h2[normalize-space()='New User Signup!']");
    

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Signup / Login")) {
            throw new IllegalStateException("This is not Login Page," + " current page is: " + driver.getCurrentUrl());
        }
    }
    
    public SignupPage signUp(String name, String email) {
        driver.findElement(signupName).sendKeys(name);
        driver.findElement(signupEmail).sendKeys(email);
        System.out.println("About to click on signup in 0 seconds");
        
//        not very happy with this solution
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        System.out.println("About to click on signup right now");
        driver.findElement(signupBtn).click();
        System.out.println("signup is clicked");
        return new SignupPage(driver);
    }
    
    public LoginPage verifyNewUserSignup() {
        driver.findElement(newUserSignupElem);
        return this;
    }
    
    public AccountPage loginAsCorrectUser(String login, String password) {
        driver.findElement(loginEmail).sendKeys(login);
        driver.findElement(passwordTbx).sendKeys(password);
//        System.out.println("Before click");
        WebElement element = driver.findElement(loginBtn);
        System.out.println("Found button: " + element);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("About to click on the button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        System.out.println("Click done!");
        return new AccountPage(driver);
    }

    public LoginPage loginAsIncorrectUser(String login, String password) {
        driver.findElement(loginEmail).sendKeys(login);
        driver.findElement(passwordTbx).sendKeys(password);
        driver.findElement(loginBtn).click();
        return this;
    }
}
