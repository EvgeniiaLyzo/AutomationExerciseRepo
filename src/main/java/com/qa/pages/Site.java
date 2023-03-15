package com.qa.pages;

import org.openqa.selenium.WebDriver;

public class Site {
    public static final String BASE_URL = "https://www.automationexercise.com/";
    
    public static HomePage goToHomePage(WebDriver driver) {
        driver.get(BASE_URL);
        return new HomePage(driver);
    }
    
    public static LoginPage goToLoginPage(WebDriver driver) {
        driver.get(BASE_URL + "login");
        return new LoginPage(driver);
    }

    public static SignupPage goToSignupPage(WebDriver driver, String name, String email) {
        goToLoginPage(driver).signUp(name, email);
        return new SignupPage(driver);
    }
}
