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
 * this class implements the ActionKeyword and performs checking of values of object having the same css selector if it has or contains the input.
 * @author edward.cervantes
 */
public class CheckContainsTextMultipleSectionByCssSelectorKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CheckContainsTextMultipleSectionByCssSelectorKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> checkContainsTextMultipleSection()");
            }
            log.info("Entering the text in " + pageObjectId);
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.cssSelector(propField);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> elements = new ArrayList<WebElement>();
            elements = driver.findElements(locator);

            for (WebElement webElement : elements) {
                String value = "";
                value = webElement.getText();
                if ("".equals(value)) {
                    value = webElement.getAttribute("value");
                }
                if (!value.toLowerCase().contains(dataInput.trim().toLowerCase())) {
                    isWorking = false;
                    reasonMessage = new StringBuilder("value retrieved: ").append(value)
                            .append(" input text: ")
                            .append(dataInput)
                            .toString();
                    break;
                } else {
                    isWorking = true;
                }
                driverScript.setReason(reasonMessage);
                seleniumDriver.setDriver(driver);
            }
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to count the object due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << checkContainsTextMultipleSection()");
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
