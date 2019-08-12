package com.ump.automate.test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestClass {

    WebDriver driver = null;
    DesiredCapabilities capabilities;

    void someMethod(String browserName) {
        String geckoLocal = "C:\\Users\\edward.cervantes\\Desktop\\HybridKeywordDrivenDemo-0.0.1-SNAPSHOT\\resources\\geckodriver.exe";
        String ieLocal = "C:\\Users\\edward.cervantes\\Desktop\\HybridKeywordDrivenDemo-0.0.1-SNAPSHOT\\resources\\IEDriverServer.exe";
        String chromeLocal = "C:\\Users\\edward.cervantes\\Desktop\\HybridKeywordDrivenDemo-0.0.1-SNAPSHOT\\resources\\chromedriver.exe";
        
        if ("Mozilla".equalsIgnoreCase(browserName) || "Firefox".equalsIgnoreCase(browserName)
                || "MozillaFirefox".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.gecko.driver", geckoLocal);

            //              System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
            capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            // for UnhandledAlertException.
            // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
            // and the switchTo().alert() couldn't find it.
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            driver = new FirefoxDriver(capabilities);

            driver.manage().window().maximize();

        } else if ("IE".equalsIgnoreCase(browserName) || "InternetExplorer".equalsIgnoreCase(browserName)
                || "iexplorer".equalsIgnoreCase(browserName)) {
            capabilities = new DesiredCapabilities().internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            // for UnhandledAlertException.
            // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
            // and the switchTo().alert() couldn't find it.
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            
            System.setProperty("webdriver.ie.driver", ieLocal);
            driver = new InternetExplorerDriver(capabilities);

            driver.manage().window().maximize();
        } else if ("Chrome".equalsIgnoreCase(browserName) || "GoogleChrome".equalsIgnoreCase(browserName)) {
            capabilities = new DesiredCapabilities().chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, true);
            // for UnhandledAlertException.
            // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
            // and the switchTo().alert() couldn't find it.
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            capabilities.setBrowserName("chrome");
            System.setProperty("webdriver.chrome.driver", chromeLocal);
            driver = new ChromeDriver();
            
            driver.manage().window().maximize();
        }
        
        driver.get("http://www.google.com/");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("C:\\screenshots\\" + browserName + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.someMethod("Mozilla");
        testClass.someMethod("ie");
        testClass.someMethod("chrome");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        Date date = new Date();
//        String toDate = dateFormat.format(date); //2016/11/16 12:08:43    
//        System.out.println(toDate);
    }
}
