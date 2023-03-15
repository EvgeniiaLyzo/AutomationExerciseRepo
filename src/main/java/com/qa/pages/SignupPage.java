package com.qa.pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignupPage {
    WebDriver driver;
    
    By enterAccountInformationText = By.xpath("//b[normalize-space()='Enter Account Information']");
    By titleMrChbx = By.id("id_gender1");
    By titleMrsChbx = By.id("id_gender2");
    By nameTbx = By.id("name");
    By emailTbx = By.id("email");
    By passwordTbx = By.id("password");
    By newsletterChbx = By.id("newsletter");
    By specialOffersChbx = By.id("optin");
    By daySelect = By.id("days");
    By monthSelect = By.id("months");
    By yearSelect = By.id("years");
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By companyTbx = By.id("company");
    By addressTbx = By.id("address1");
    By address2Tbx = By.id("address2");
    By countrySelect = By.id("country");
    By stateTbx = By.id("state");
    By cityTbx = By.id("city");
    By zipcodeTbx = By.id("zipcode");
    By mobileTbx = By.id("mobile_number");
    By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Automation Exercise - Signup")) {
            throw new IllegalStateException("This is not Signup Page," + " current page is: " + driver.getCurrentUrl());
        }
    }
    
    public AccountCreatedPage createAccount(String gender, String password, String day, String month, String year,
                              String fName, String lName, String company, String address,
                              String address2, String country, String state, String city,
                              String zipcode, String mobile) {
        if (gender.equalsIgnoreCase("m")) {
            driver.findElement(titleMrChbx).click();
        } else {
            driver.findElement(titleMrsChbx).click();
        }
        driver.findElement(passwordTbx).sendKeys(password);
        new Select(driver.findElement(daySelect)).selectByValue(day);
        new Select(driver.findElement(monthSelect)).selectByValue(month);
        new Select(driver.findElement(yearSelect)).selectByValue(year);
        myClick(newsletterChbx);
        myClick(specialOffersChbx);
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(companyTbx).sendKeys(company);
        driver.findElement(addressTbx).sendKeys(address);
        driver.findElement(address2Tbx).sendKeys(address2);
        new Select(driver.findElement(countrySelect)).selectByValue(country);
        driver.findElement(stateTbx).sendKeys(state);
        driver.findElement(cityTbx).sendKeys(city);
        driver.findElement(zipcodeTbx).sendKeys(zipcode);
        driver.findElement(mobileTbx).sendKeys(mobile);
        myClick(createAccountBtn);
        
        return new AccountCreatedPage(driver);
    }

    private void myClick(By elementSelector) {
        WebElement element = driver.findElement(elementSelector);
        JavascriptExecutor jse = (JavascriptExecutor) driver; 
//        element.click();
        jse.executeScript("arguments[0].click();", element);
    }

    public boolean verifyEnterAccountInformationText() {
        return driver.findElement(enterAccountInformationText).isDisplayed();
    }
    
    public void clickNewsletter() {
        driver.findElement(newsletterChbx).click();
    }
}
