package com.thanh.testcases;

import com.thanh.common.SetupBrowser;
import com.thanh.helpers.PropertiesHelper;
import com.thanh.pages.CategoriesPages;
import com.thanh.pages.DashboardPage;
import com.thanh.pages.LoginPage;
import com.thanh.helpers.ExcelHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CategoriesTest extends SetupBrowser {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoriesPages categoriesPages;


    @BeforeClass
    public void setupClass() throws Exception {
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginEcommerces();
        categoriesPages = dashboardPage.addCategory();
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Categories"),"Name");
    }
    @Test
    public void testCategory() throws Exception {
        String name = ExcelHelper.getCellData("name",1);
        categoriesPages.addNewCategory(name);
        categoriesPages.searchCategory(name);
    }
}
