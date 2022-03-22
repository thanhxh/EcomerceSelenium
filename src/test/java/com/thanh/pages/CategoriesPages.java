package com.thanh.pages;

import com.thanh.helpers.ExcelHelper;
import com.thanh.helpers.PropertiesHelper;
import com.thanh.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CategoriesPages {
    WebDriver driver;
    WebUI webUI;


    public CategoriesPages(WebDriver _driver) {
        driver = _driver;
        webUI = new WebUI(driver);

    }

    //Object Add Category
    By checkCategoryForm = By.xpath("//h5[normalize-space()='Category Information']");
    By nameInput = By.xpath("//input[@id='name']");
    By parentCateDropdown = By.xpath("//label[normalize-space()='Parent Category']/following-sibling::div");
    public By searchDropdownInput = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    By orderNumberInput = By.xpath("//input[@id='order_level']");
    By typeDropdown = By.xpath("//label[normalize-space()='Type']//following-sibling::div");
    By digitalInput = By.xpath("//span[normalize-space()='Digital']");
    By bannerBtn = By.xpath("//div[5]//div[1]//div[1]//div[2]");
    By imageBtn = By.xpath("//div[@title='JUMBO.png']//img[@class='img-fit']");
    By addFileBtn = By.xpath("//button[normalize-space()='Add Files']");
    By metaTypeInput = By.xpath("//input[@placeholder='Meta Title']");
    By metaDesInput = By.xpath("//textarea[@name='meta_description']");
    By filterDropdown = By.xpath("//label[normalize-space()='Filtering Attributes']//following-sibling::div");
    By saveBtn = By.xpath("//button[normalize-space()='Save']");

    //Object Search Category
    public By searchInput = By.xpath("//input[@id='search']");
    By allCategoryText = By.xpath("//h1[normalize-space()='All categories']");
    By categoriesText = By.xpath("//h5[normalize-space()='Categories']");


    public void inputName(String name) {
        webUI.setText(nameInput, name);
    }

    public void dynamicDropdown(String field) throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Categories"),"Dynamic Dropdown");
        if (field.equals("Parent Category")) {
            webUI.clickElement(parentCateDropdown);
            webUI.setText(searchDropdownInput, ExcelHelper.getCellData("parent_cate",1));
            webUI.pressElementENTER(searchDropdownInput);
        }


        if (field.equals("Filtering Attributes")) {
            webUI.clickElement(filterDropdown);
            webUI.setText(searchDropdownInput, ExcelHelper.getCellData("filtering",1));
            webUI.pressElementENTER(searchDropdownInput);
        }

    }

    public void staticDropdown(String field) {
        if (field.equals("Type")) {
            webUI.clickElement(typeDropdown);
            webUI.clickElement(digitalInput);
        }
    }

    public void inputOrderNumber() throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Categories")," Order + MetaType + MetaDes");
        Assert.assertTrue(webUI.getWebElement(orderNumberInput).isDisplayed(), "Không phải element Order Number");
        webUI.setText(orderNumberInput, ExcelHelper.getCellData("order",1));
    }

    public void chooseFile(String field) {
        if (field.equals("Banner")) {
            webUI.clickElement(bannerBtn);
            webUI.clickElement(imageBtn);
            webUI.clickElement(addFileBtn);
        }
    }

    public void inputMetaType() throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Categories")," Order + MetaType + MetaDes");
        Assert.assertTrue(webUI.getWebElement(metaTypeInput).isDisplayed(), "Không phải element Meta Type");
        webUI.setText(metaTypeInput, ExcelHelper.getCellData("metaIn",1));
    }

    public void inputMetaDes() throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Categories")," Order + MetaType + MetaDes");
        Assert.assertTrue(webUI.getWebElement(metaDesInput).isDisplayed(), "Không phải element Meta Des");
        webUI.setText(metaDesInput, ExcelHelper.getCellData("metaDes",1));
    }

    public void clickSave() {
        Assert.assertTrue(webUI.getWebElement(saveBtn).isDisplayed(), "Không phải element Save");
        webUI.clickElement(saveBtn);
    }

    public void addNewCategory(String name) throws Exception {
        Assert.assertEquals(webUI.getElementText(checkCategoryForm), "Category Information", "Không phải trang Information");
        inputName(name);
        webUI.sleep(0.5);
        dynamicDropdown("Parent Category");
        webUI.sleep(0.5);
        inputOrderNumber();
        webUI.sleep(0.5);
        staticDropdown("Type");
        webUI.sleep(0.5);
        chooseFile("Banner");
        webUI.sleep(1);
        inputMetaType();
        inputMetaDes();
        webUI.sleep(0.5);
        dynamicDropdown("Filtering Attributes");

        webUI.scrollToElement(saveBtn);
        clickSave();

    }

    public void indexSearch(int column, String name) {
        List<WebElement> rowNumber = driver.findElements(By.xpath("//table//tbody//tr"));
        for (int i = 0; i < rowNumber.size(); i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody//tr[" + (i+1) + "]//td[" + column + "]"));
            webUI.consoleLog("Item no: " + (i+1));
            //webUI.consoleLog("All information product: "+rowNumber.get(i).getText());
            webUI.consoleLog("Check name Product: " + webUI.getElementText(elementCheck));
            webUI.consoleLog("Check Name Search:" +name);
            Assert.assertEquals(webUI.getElementText(elementCheck).toUpperCase(), name.toUpperCase());
        }
    }

    public void searchCategory(String name) {

        Assert.assertEquals(webUI.getElementText(allCategoryText), "All categories");
        webUI.sleep(0.5);
        Assert.assertEquals(webUI.getElementText(categoriesText), "Categories");
        webUI.sleep(0.5);
        Assert.assertTrue(webUI.getWebElement(searchInput).isDisplayed(), "Không hiển thị element Search");
        webUI.sleep(0.5);
        webUI.setText(searchInput, name);
        webUI.sleep(0.5);
        webUI.pressElementENTER(searchInput);
        webUI.sleep(1);
        indexSearch(2, name);

    }


}



