package com.thanh.testcases;

import com.thanh.common.SetupBrowser;
import com.thanh.helpers.ExcelHelper;
import com.thanh.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends SetupBrowser {


    private LoginPage loginPage;
    private ExcelHelper excel;
    @BeforeClass
    public void setupClass() throws Exception {


        loginPage = new LoginPage(driver);



    }

    @Test
    public void logInEcommerces() throws Exception {
        //Đọc data từ file Excel
        loginPage.loginEcommerces();
        loginPage.verifyDashboard();

    }

    @Test
    public void forgotPassword() throws Exception {
        loginPage.forgotPassEcommerces();
    }

}
