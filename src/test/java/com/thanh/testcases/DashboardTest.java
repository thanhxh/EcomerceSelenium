package com.thanh.testcases;

import com.thanh.common.SetupBrowser;
import com.thanh.pages.DashboardPage;
import com.thanh.pages.LoginPage;
import org.testng.annotations.BeforeClass;

public class DashboardTest extends SetupBrowser {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeClass
    public void setupClass() throws Exception {
        loginPage = new LoginPage(driver);

        dashboardPage = loginPage.loginEcommerces();

    }


}
