package com.thanh.common;

import com.thanh.utils.WebUI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class SetupBrowser {

    public WebDriver driver;
    public JavascriptExecutor js;
    public WebUI webUI;

    @Parameters({"BrowserName"})
    @BeforeClass
    public void Setup(@Optional("chrome") String browserName) {
        createBrowser(browserName);
        js = (JavascriptExecutor) driver;
        webUI = new WebUI(driver);
    }
    public WebDriver getDriver(){
        return driver;
    }

    public WebDriver createBrowser(String browserName) {

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                //Khởi tạo Chrome Driver
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("Started Chrome Browser: " + driver);
                driver.manage().window().maximize();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                System.out.println("Started Edge Browser: " + driver);
                driver.manage().window().maximize();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                System.out.println("Started Firefox Browser: " + driver);
                driver.manage().window().maximize();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("Started Chrome Browser with default: " + driver);
                driver.manage().window().maximize();
                break;

        }
        return driver;
    }

    @AfterClass
    public void Close() {
        driver.quit();
        System.out.println("Closed Driver");

    }
}
