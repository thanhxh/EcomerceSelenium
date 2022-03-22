package com.thanh.testcases;

import com.thanh.common.SetupBrowser;
import com.thanh.helpers.PropertiesHelper;
import com.thanh.pages.DashboardPage;
import com.thanh.pages.ProductPage;
import com.thanh.pages.LoginPage;
import com.thanh.helpers.ExcelHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductTest extends SetupBrowser {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProductPage productPage;

    @BeforeClass
    public void setupClass() throws Exception {
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginEcommerces();
        productPage = dashboardPage.openProduct();
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Product"),"Information");

    }

    @Test
    public void testProduct() throws Exception {
        String name = ExcelHelper.getCellData("name",1);
        productPage.addNewProduct(name);
        productPage.searchProduct(name);
    }
}
