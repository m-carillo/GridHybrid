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
 * this class implements the ActionKeyword and performs method that counts element with the same CSS class 
 * having text values that has the same value as in the input
 * input format(count, value to compare)
 * input format(value to compare, count)
 * @author edward.cervantes
 */
public class CountCompareTextByCssSelectorKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CountCompareTextByCssSelectorKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(InputKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        String input = "";
        int elemSize = 0;
        int dataCount = 0;
        String[] strList = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> countByCssSelector()");
            }
            log.info("Comparing the number of elements having the input text value to that of the input number value...");

            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.cssSelector(propField);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> elements = new ArrayList<WebElement>();
            elements = driver.findElements(locator);

            // check the input
            if (!(dataInput.contains(",") || dataInput.contains(";"))) {
                reasonMessage = "Please check the input. Input format(count, value to compare) or (value to compare, count). Only use ',' or ';' as a separator.";
            } else {
                strList = dataInput.split("[,;]+");
                int strSize = strList.length;

                // check if inputs are just equal to allowed
                if (strSize > 2) {
                    reasonMessage = "data entered too much for allowed. Max is two(2). Only use ',' or ';' to separate";
                    driverScript.setReason(reasonMessage);
                } else {
                    //convert the input
                    try {
                        dataCount = Integer.valueOf(strList[0]);
                        input = strList[1];
                    } catch (Exception e) {
                        dataCount = Integer.valueOf(strList[1]); // if ever the string number input is on the right side of delimiter
                        input = strList[0];
                    }
                    for (WebElement webElement : elements) {
                        String value = "";
                        value = webElement.getText();
                        if ("".equals(value)) {
                            value = webElement.getAttribute("value");
                        }
                        // checked if retrieved value contains input value
                        if (value.trim().equals(input.trim())) {
                            elemSize++;
                        }
                    }
                }
                if (dataCount == elemSize) {
                    isWorking = true;
                }
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
