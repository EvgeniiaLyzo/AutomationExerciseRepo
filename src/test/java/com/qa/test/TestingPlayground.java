package com.qa.test;

import com.qa.pages.SignupPage;
import com.qa.pages.Site;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestingPlayground extends BaseTest {
    
    @Test(dataProvider = "data", enabled = false)
    public void test(String s1, String s2) {
        System.out.println(s1);
        System.out.println(s2);
    }
    
    @Test
    public void test1() {
        try {
            Site.goToSignupPage(driver, "eve", "eve@gmail.com");
            SignupPage sp = new SignupPage(driver);
            sp.clickNewsletter();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
//    ******************************
    
    @DataProvider(name = "data")
    public String[][] dataPr() {
        String[][] data = {{"firstName", "lastName"}, {"sss", "rrr"}};
        return data;
    }
}
