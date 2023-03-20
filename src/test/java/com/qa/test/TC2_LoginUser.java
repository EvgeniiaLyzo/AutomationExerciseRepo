package com.qa.test;

import com.qa.data.DataClass;
import com.qa.pages.AccountPage;
import com.qa.pages.LoginPage;
import com.qa.pages.Site;
import com.qa.util.ExcelObject;
import com.qa.util.Utils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class TC2_LoginUser extends BaseTest {
    
    @Test(enabled = true, dataProvider = "correctLoginData")
    public void loginAsCorrectUser(String login, String password) {
        boolean result1 = false;
        boolean result2 = false;
        
        try {
            AccountPage ap = Site.goToHomePage(driver).goToLoginPage().loginAsCorrectUser(login, password);
            result1 = ap.isLogged();
            System.out.println(result1);

            System.out.println("Is about to logout");
            
            ap.logout();
            result2 = true;
        } catch (Exception e) {
            Utils.handleException(e, test, driver, "Logged as user is not visible - Failed");
        }
        assertTrue(result1 && result2);
        
    }
    
    @Test(enabled = false, dataProvider = "incorrectLoginData")
    public void loginAsIncorrectUser(String login, String password) {
        boolean result = false;

        try {
        Site.goToHomePage(driver).goToLoginPage().loginAsIncorrectUser(login, password);
        result = true;
    } catch (Exception e) {
        Utils.handleException(e, test, driver, "Something wrong");
    }

    assertTrue(result);
}
    
    

    //   **************** DATA PROVIDERS *******************   //

    @DataProvider(name = "correctLoginData")
    public Object[][] getCorrectLoginData() throws IOException {
        return new ExcelObject(rootPath + DataClass.TESTDATA_FILE, DataClass.CORRECT_LOGIN_DATA_SHEET).getData();
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] getIncorrectLoginData() throws IOException {
        return new ExcelObject(rootPath + DataClass.TESTDATA_FILE, DataClass.INCORRECT_LOGIN_DATA_SHEET).getData();
    }
    
}
