package com.thanh.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebUI {

    int ELEMENT_LOAD_TIMEOUT = 5;
    int timeoutWaitForPageLoaded = 20;

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;
    Select select;

    public WebUI(WebDriver _driver) {
        driver = _driver;
        wait = new WebDriverWait(driver, ELEMENT_LOAD_TIMEOUT);
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consoleLog(Object obj) {
        System.out.println(obj);
    }

    /**
     * Chuyển đổi đối tượng dạng By sang WebElement
     * Để tìm kiếm một element
     *
     * @param by là đối tượng By
     * @return Trả về là một đối tượng WebElement
     */
    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    /**
     * Chuyển đổi đối tượng dạng By sang WebElement
     * Để tìm kiếm nhiều element
     *
     * @param by là đối tượng By
     * @return Trả về là Danh sách đối tượng WebElement
     */
    public List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    public boolean verifyElementExist(By by) {
        List<WebElement> listElement = driver.findElements(by); //List element tìm được
        if (listElement.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setText(By element, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        getWebElement(element).sendKeys(value);
    }
    public void clearText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        getWebElement(element);
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        getWebElement(element).click();
    }

    public void scrollToElement(By by) {
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));

    }

    public void moveToElement(By by) {
        actions.moveToElement(getWebElement(by)).build().perform();
    }

    //Handle Select Dropdown
    public void selectOptionByText(By by, String Text) {
        select = new Select(getWebElement(by));
        select.selectByVisibleText(Text);
    }

    public void selectOptionByValue(By by, String Value) {
        select = new Select(getWebElement(by));
        select.selectByValue(Value);
    }

    public void selectOptionByIndex(By by, int Index) {
        select = new Select(getWebElement(by));
        select.selectByIndex(Index);
    }

    //Handle Keyboard
    public void pressENTER() {
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void pressElementENTER(By by) {
        actions.moveToElement(getWebElement(by)).sendKeys(Keys.ENTER).perform();
    }

    public void pressF11() {
        actions.sendKeys(Keys.F11).build().perform();
    }

    public void pressElementDELETE(By by) {
        actions.moveToElement(getWebElement(by)).sendKeys(Keys.DELETE).perform();
    }

    public void pressDELETE() {
        actions.sendKeys(Keys.DELETE).perform();
    }

    public void pressESC() {
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void pressCTRL_A() {
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

    }

    public String getCurrentURL() {
        waitForPageLoaded();
        consoleLog("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public String getElementText(WebElement webElement) {
        return webElement.getText();
    }

    public String getElementText(By by) {
        return getWebElement(by).getText();
    }

    public boolean verifyElementText(By by, String text) {
        System.out.println(getWebElement(by).getText());
        return getWebElement(by).getText().equals(text);
    }

    //Wait

    public void waitForPageLoaded() {
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {

            wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            error.getMessage();
        }

    }

}
