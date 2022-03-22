package com.thanh.pages;

import com.thanh.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {

    WebDriver driver;
    WebUI webUI;

    public DashboardPage(WebDriver _driver){
        driver = _driver;
        webUI = new WebUI(driver);
    }

    By menuProduct = By.xpath("//span[normalize-space()='Products']");
    //Object Category
    By addNewCategoryBtn = By.xpath("//span[normalize-space()='Add New category']");

    By allCategoryText = By.xpath("//h1[normalize-space()='All categories']");
    By categoriesText = By.xpath("//h5[normalize-space()='Categories']");
    //Object Product




    public void childMenuClick (String menuChild){
        webUI.clickElement(By.xpath("//span[normalize-space()='" +menuChild+ "']"));
    }
    public CategoriesPages addCategory(){

        webUI.clickElement(menuProduct);
        webUI.waitForPageLoaded();
        childMenuClick("Category");
        Assert.assertEquals(webUI.getElementText(allCategoryText),"All categories");
        Assert.assertEquals(webUI.getElementText(categoriesText),"Categories");
        Assert.assertEquals(webUI.getElementText(addNewCategoryBtn),"Add New category");
        webUI.clickElement(addNewCategoryBtn);

        return new CategoriesPages(driver);

    }
    public ProductPage openProduct(){
        webUI.clickElement(menuProduct);
        webUI.waitForPageLoaded();
        childMenuClick("All products");
        Assert.assertEquals(webUI.getCurrentURL(),"https://ecommerce.anhtester.com/admin/products/all","Không phải trang All Products");

        return new ProductPage(driver);
    }

}
