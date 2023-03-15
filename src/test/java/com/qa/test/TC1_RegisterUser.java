package com.qa.test;

import com.qa.data.DataClass;
import com.qa.pages.*;
import com.qa.util.ExcelObject;
import com.qa.util.Utils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;


public class TC1_RegisterUser extends BaseTest {

    @Test(priority = 1, enabled = true, dataProvider = "registrationData")
    public void registerNewUser(String name, String email, String gender, String password, String day, String month,
                                String year, String fName, String lName, String company, String address,
                                String address2, String country, String state, String city, String zipcode, String mobile) {
        
        boolean isSignupVisible = false;
        LoginPage loginPage = null;
        try {
            test.log(LogStatus.INFO, "Verify Title of AutomationExercise site");
            loginPage = Site.goToHomePage(driver).goToLoginPage().verifyNewUserSignup();
            test.log(LogStatus.PASS, "New user signup is visible - Passed");
            isSignupVisible = true;
        } catch (Exception e) {
            Utils.handleException(e, test, driver, "New user signup is NOT visible - Failed");
        }

        assertTrue(isSignupVisible);

        boolean isEnterAccInfVisible = false;
        SignupPage signupPage = null;
        try {
            test.log(LogStatus.INFO, "Verify that Enter Account Information is visible");
            Utils.googleAdsDemolisher(driver);
            System.out.println("done");
            signupPage = loginPage.signUp(name, email);
            System.out.println("done");
            isEnterAccInfVisible = signupPage.verifyEnterAccountInformationText();
            System.out.println("done");
            test.log(LogStatus.PASS, "Enter Account Information is visible - Passed");
        } catch (Exception e) {
            Utils.handleException(e, test, driver, "Enter Account Information is NOT visible - Failed");
        }
        
        assertTrue(isEnterAccInfVisible);
        
        
//      step 3
        boolean isAccountCreated = false;
        AccountCreatedPage accCreatedPage = null;
        try {
            test.log(LogStatus.INFO, "Verify new account creation process");
            accCreatedPage = signupPage.createAccount(gender, password, day, month,
                    year, fName, lName, company, address, address2, country, state, city, zipcode, mobile);
            isAccountCreated = true;
            test.log(LogStatus.PASS, "Account has been created - Passed");
        } catch (Exception e) {
            Utils.handleException(e, test, driver, "Account has NOT been created - Failed");
        }
        
        assertTrue(isAccountCreated);
        
//        step 4
        AccountPage accountPage = null;
        boolean isLoggedAsUserVisible = false;
        try {
            test.log(LogStatus.INFO, "Verify that user logged in to account");
            accountPage = accCreatedPage.goToAccountPage();
            isLoggedAsUserVisible = accountPage.isLogged();
            test.log(LogStatus.PASS, "User logged in - Passed");
        } catch (Exception e) {
            Utils.handleException(e, test, driver, "User is NOT logged in - Failed");
        }
        
        assertTrue(isLoggedAsUserVisible);
        
//         step 5
        AccountDeletedPage accountDeletedPage = null;
        boolean isUserDeleted = false;
         try {
             test.log(LogStatus.INFO, "Verify deleting user account");
             accountDeletedPage = accountPage.deleteAccount();
             isUserDeleted = true;
             test.log(LogStatus.PASS, "User was deleted - Passed");
         } catch (Exception e) {
             Utils.handleException(e, test, driver, "User was NOT deleted - Failed");
         }
         
         assertTrue(isUserDeleted);
    }
    
    

//   **************** DATA PROVIDERS *******************   //
    
    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() throws IOException {
        return new ExcelObject(rootPath + DataClass.TESTDATA_FILE, DataClass.REGISTRATION_DATA_SHEET).getData();
    }
}
