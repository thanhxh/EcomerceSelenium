package com.thanh.pages;

import com.thanh.helpers.PropertiesHelper;
import com.thanh.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    WebDriver driver;
    JavascriptExecutor js;
    WebUI webUI;


    public LoginPage(WebDriver _driver){
        driver = _driver;
        webUI = new WebUI(driver);
        PropertiesHelper.loadAllFiles();

    }




    //Page Login
    By emailInput = By.xpath("//input[@id='email']");
    By passwordInput = By.xpath("//input[@id='password']");
    By logInBtn = By.xpath("//button[normalize-space()='Login']");
    //Page ForgotPassword
    By fogotPassLabel = By.xpath("//a[normalize-space()='Forgot password ?']");
    By checkForgotPass = By.xpath("//h1[normalize-space()='Forgot password?']");
    By emailForgotInput = By.xpath("//input[@placeholder='Email']");
    By resetPassBtn = By.xpath("//button[normalize-space()='Send Password Reset Link']");

    public DashboardPage loginEcommerces() throws Exception {
        driver.get(PropertiesHelper.getValue("URL"));
        webUI.waitForPageLoaded();
        Assert.assertEquals(webUI.getCurrentURL(),"https://ecommerce.anhtester.com/login","Không phải trang Login Ecommerces");
        enterEmail();
        enterPassword();
        clickLogIn();

        return new DashboardPage(driver);
    }
    public void forgotPassEcommerces() throws Exception {
        driver.get(PropertiesHelper.getValue("URL"));
        webUI.waitForPageLoaded();
        clickForgotPass();
        Assert.assertEquals(webUI.getElementText(checkForgotPass),"Forgot password?");
        enterEmailForgot();
        webUI.clickElement(resetPassBtn);
    }

    public void enterEmail() throws Exception {
        Assert.assertTrue(webUI.getWebElement(emailInput).isDisplayed(),"Không tìm thấy element Email");
        webUI.setText(emailInput,PropertiesHelper.getValue("email"));


    }
    public void enterPassword() throws Exception {
        Assert.assertTrue(webUI.getWebElement(passwordInput).isDisplayed(),"Không tìm thấy element Password");
        webUI.setText(passwordInput,PropertiesHelper.getValue("password"));

    }

    public void clickLogIn(){
        Assert.assertTrue(webUI.getWebElement(logInBtn).isDisplayed(),"Không tìm thấy element Log In");
        webUI.clickElement(logInBtn);
    }

    public void clickForgotPass(){
        Assert.assertTrue(webUI.getWebElement(fogotPassLabel).isDisplayed(),"Không phải element Forgot Pass");
        webUI.clickElement(fogotPassLabel);
    }
    public void enterEmailForgot() throws Exception {
        Assert.assertTrue(webUI.getWebElement(emailForgotInput).isDisplayed(),"Không phải element Email Forgot");
        webUI.setText(emailForgotInput,PropertiesHelper.getValue("emailForgot"));
    }
    public void verifyDashboard(){
        Assert.assertEquals(webUI.getCurrentURL(),"https://ecommerce.anhtester.com/admin","Không phải trang Dashboard");
    }

}
