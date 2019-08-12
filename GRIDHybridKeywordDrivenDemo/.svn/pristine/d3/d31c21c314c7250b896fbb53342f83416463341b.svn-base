package com.ump.automate.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements the ActionKeyword and performs checking of values of objects having the same css selector. 
 * Its method can be use for checking a group of 'something' having the same value(equal) value
 * @author edward.cervantes
 */
public class CheckCompareTextMultipleSectionByCssSelectorKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckCompareTextMultipleSectionByCssSelectorKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

//    private WebElementLocatorFactory webElementLocatorFactory;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> checkCompareTextMultipleSection()");
            }
            log.info(new StringBuilder("Entering the text in ").append(pageObjectId).toString());
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
//          By locator = webElementLocatorFactory.findLocator(propField);
            By locator = By.cssSelector(propField);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> elements = new ArrayList<WebElement>();
            elements = driver.findElements(locator);

            isWorking = true;
            for (WebElement webElement : elements) {
                String value = "";
                value = webElement.getText();
                if ("".equals(value)) {
                    value = webElement.getAttribute("value");
                }
                if (!value.equals(dataInput)) {
                    isWorking = false;
                    reasonMessage = new StringBuilder("value: ").append(value)
                            .append(" is not as expected: ")
                            .append(dataInput)
                            .toString();
                    break;
                }
                seleniumDriver.setDriver(driver);
            }
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to check the sections due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkCompareTextMultipleSection()");
        }
        return isWorking;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setPropertiesRetriever(PropertiesRetriever propertiesRetriever) {
        this.propertiesRetriever = propertiesRetriever;
    }

    public void setSeleniumDriver(SeleniumDriverInt seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
