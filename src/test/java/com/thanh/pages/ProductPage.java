package com.thanh.pages;

import com.thanh.helpers.ExcelHelper;
import com.thanh.helpers.PropertiesHelper;
import com.thanh.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProductPage extends CategoriesPages {
    WebDriver driver;
    WebUI webUI;

    public ProductPage(WebDriver _driver) {
        super(_driver);
        driver = _driver;
        webUI = new WebUI(driver);
        PropertiesHelper.loadAllFiles();
    }

    //Object Add New Product
    By addNewProductBtn = By.xpath("//a[@class='btn btn-circle btn-info']//span[contains(text(),'Add New Product')]");
    By checkAddProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    By checkFormProduct = By.xpath("//h5[normalize-space()='Product Information']");
    //Object Information
    By productNameInput = By.xpath("//input[@placeholder='Product Name']");
    By categoryDropdown = By.xpath("//div[@id='category']//label[@class='col-md-3 col-from-label']//following-sibling::div");
    By brandDropdown = By.xpath("//label[normalize-space()='Brand']//following-sibling::div");
    By unitInput = By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']");
    By QtyInput = By.xpath("//input[@name='min_qty']");
    //Object Product Variation
    By scrollProductVariation = By.xpath("//h5[normalize-space()='Product Variation']");
    By onOffBtn = By.xpath("//div[@class='col-md-1']//span");
    By colorDropdown = By.xpath("//input[@value='Colors']//following::button[@title='Nothing selected'][1]");
    By colorLabel = By.xpath("//input[@value='Colors']");
    By attributeDropdown = By.xpath("//input[@value='Attributes']//following::button[@title='Nothing selected']");
    By attributeLabel = By.xpath("//input[@value='Attributes']");
    By sizeDropdown = By.xpath("//input[@value='Size']//following::button[@title='Nothing selected']");
    By fabricDropdown = By.xpath("//input[@value='Fabric']//following::button[@title='Nothing selected']");
    By fabricLabel = By.xpath("//input[@value='Fabric']");
    By sizeLabel = By.xpath("//input[@value='Size']");
    //Object External link
    By unitPriceInput = By.xpath("//input[@placeholder='Unit price']");
    By discountInput = By.xpath("//input[@placeholder='Discount']");
    //Object SEO Meta Tags
    By scrollSEO = By.xpath("//h5[normalize-space()='SEO Meta Tags']");
    By metaTitleInput = By.xpath("//input[@placeholder='Meta Title']");
    By savePublishBtn = By.xpath("//button[normalize-space()='Save & Publish']");

    //Object Search Product
    By checkAllProduct = By.xpath("//h1[contains(text(),'All products')]");

    public void CTRL_A_DELETE() {
        webUI.pressCTRL_A();
        webUI.pressDELETE();
    }

    public void clickAddProduct() {
        Assert.assertEquals(webUI.getElementText(addNewProductBtn), "Add New Product");
        webUI.clickElement(addNewProductBtn);
        Assert.assertEquals(webUI.getElementText(checkAddProduct), "Add New Product", "Không phải trang Add New Product");
        Assert.assertEquals(webUI.getElementText(checkFormProduct), "Product Information", "Không phải trang Add New Product");
    }

    public void inputInformation(String name) throws Exception {
        ExcelHelper.setExcelFile("src/main/resources/productData.xlsx","Information");
        webUI.setText(productNameInput, name);
        webUI.clickElement(categoryDropdown);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("cateDrop",1));
        webUI.pressElementENTER(searchDropdownInput);
        webUI.sleep(0.5);
        webUI.clickElement(brandDropdown);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("brandDrop",1));
        webUI.pressElementENTER(searchDropdownInput);
        webUI.sleep(0.5);
        webUI.setText(unitInput, ExcelHelper.getCellData("unit",1));;
        webUI.sleep(0.5);
        webUI.clickElement(QtyInput);
        CTRL_A_DELETE();
        webUI.setText(QtyInput, ExcelHelper.getCellData("Qty",1));
        webUI.sleep(0.5);

    }

    public void inputVariation() throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Product"),"Variation");
        if (webUI.getWebElement(onOffBtn).isEnabled() == true) {
            webUI.clickElement(onOffBtn);
        }
        webUI.sleep(0.5);
        webUI.clickElement(colorDropdown);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("color1",1));
        webUI.pressElementENTER(searchDropdownInput);
        CTRL_A_DELETE();
        webUI.sleep(0.5);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("color2",1));
        webUI.pressElementENTER(searchDropdownInput);
        CTRL_A_DELETE();
        webUI.sleep(0.5);
        webUI.clickElement(colorLabel);
        webUI.clickElement(attributeDropdown);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("attriDrop1",1));
        webUI.pressElementENTER(searchDropdownInput);
        webUI.sleep(1);
        CTRL_A_DELETE();
        webUI.sleep(1);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("attriDrop2",1));
        webUI.pressElementENTER(searchDropdownInput);
        webUI.sleep(0.5);
        webUI.clickElement(attributeLabel);
        webUI.sleep(0.5);
        webUI.clickElement(sizeDropdown);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("sizeDrop",1));
        webUI.pressElementENTER(searchDropdownInput);
        webUI.clickElement(sizeLabel);
        webUI.sleep(1);
        webUI.clickElement(fabricDropdown);
        webUI.setText(searchDropdownInput, ExcelHelper.getCellData("fabricDrop",1));
        webUI.pressElementENTER(searchDropdownInput);
        webUI.clickElement(fabricLabel);
        webUI.sleep(1);

    }

    public void inputExternalLink() throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Product"),"External + Meta");
        Assert.assertTrue(webUI.getWebElement(unitPriceInput).isDisplayed(), "Không tìm thấy element Unit Price");
        webUI.clickElement(unitPriceInput);
        CTRL_A_DELETE();
        webUI.setText(unitPriceInput, ExcelHelper.getCellData("unit price",1));
        webUI.sleep(0.5);
        Assert.assertTrue(webUI.getWebElement(discountInput).isDisplayed(), "Không tìm thấy element Discount");
        webUI.clickElement(discountInput);
        CTRL_A_DELETE();
        webUI.setText(discountInput, ExcelHelper.getCellData("discount price",1));
        webUI.sleep(0.5);
    }

    public void inputMeta() throws Exception {
        ExcelHelper.setExcelFile(PropertiesHelper.getValue("excelPath_Product"),"External + Meta");
        Assert.assertTrue(webUI.getWebElement(metaTitleInput).isDisplayed(), "Không phải element Meta Title");
        webUI.setText(metaTitleInput, ExcelHelper.getCellData("meta_Title",1));
    }

    public void clickSavePublish() {
        Assert.assertTrue(webUI.getWebElement(savePublishBtn).isDisplayed(), "Không phải element Save Publish");
        webUI.clickElement(savePublishBtn);
    }

    public void addNewProduct(String name) throws Exception {
        webUI.waitForPageLoaded();
        clickAddProduct();
        inputInformation(name);
        webUI.scrollToElement(scrollProductVariation);
        webUI.sleep(0.5);
        inputVariation();
        webUI.sleep(1);
        inputExternalLink();
        webUI.sleep(1);
        webUI.scrollToElement(scrollSEO);
        webUI.sleep(0.5);
        inputMeta();
        clickSavePublish();

    }

    public void indexSearch(int column, String name) {
        List<WebElement> rowNumber = driver.findElements(By.xpath("//table//tbody//tr"));
        System.out.println("Số item:" + rowNumber.size());
        for (int i = 0; i < rowNumber.size(); i++) {

            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr[" + (i + 1) + "]/td[" + column + "]/div[1]/div[2]/span[1]"));
            webUI.consoleLog("Element No: " + (i+1));
            //webUI.consoleLog("All information product: "+rowNumber.get(i).getText());
            webUI.consoleLog("Check Name Product:" + webUI.getElementText(elementCheck));
            webUI.consoleLog("Check Name Search: " + name);
            Assert.assertEquals(webUI.getElementText(elementCheck).toUpperCase(), name.toUpperCase());

        }

    }

    public void searchProduct(String name) {
        Assert.assertEquals(webUI.getElementText(checkAllProduct), "All products", "Không phải trang All Product");
        webUI.sleep(0.5);
        Assert.assertTrue(webUI.getWebElement(searchInput).isDisplayed(), "Không tìm thấy element Search");
        webUI.setText(searchInput, name);
        webUI.sleep(0.5);
        webUI.pressElementENTER(searchInput);
        webUI.sleep(1);
        indexSearch(2, name);

    }
}

