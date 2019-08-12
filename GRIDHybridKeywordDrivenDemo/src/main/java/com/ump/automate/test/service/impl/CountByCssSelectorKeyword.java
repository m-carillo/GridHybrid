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
 * USES CSS
 * this class implements the ActionKeyword and performs method that counts the specific element that uses the same CSS class 
 * input format(count)
 * @author edward.cervantes
 */
public class CountByCssSelectorKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CountByCssSelectorKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        int elemSize = 0;
        int dataCount = 0;

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> countByCssSelector()");
            }
            log.info("Comparing the elements size and the input...");

            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.cssSelector(propField);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> elements = new ArrayList<WebElement>();
            elements = driver.findElements(locator);
            elemSize = elements.size();

            dataInput = dataInput.replaceAll("([a-zA-z &!@#$%^*():;'<>/?|\"+,-]+)", "").trim();
            dataCount = Integer.valueOf(dataInput);

            if (dataCount == elemSize) {
                isWorking = true;
            }
            reasonMessage = new StringBuilder("expected: ").append(dataCount).append(" retrieved: ").append(elemSize).toString();
            driverScript.setReason(reasonMessage);
            seleniumDriver.setDriver(driver);
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
            log.debug("MCO << countByCssSelector()");
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
