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
 * this class implements ActionKeyword and performs checking of list
 * order(DESCENDING)
 * @author edward.cervantes
 */
public class IsDescendingKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(IsAscendingKeyword.class);

    // public static final LoggerAdapter log = CustomLogFactory.getLogger(ClickKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = true;
        String propField = "";
        String reasonMessage = "";
        List<String> retrievedValueList = new ArrayList<String>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> isDescending()");
            }
            log.info("Checking if objects are in 'descending' order...");
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
                retrievedValueList.add(value.trim());
            }

            for (int i = 1; i < retrievedValueList.size(); i++) {
                if (retrievedValueList.get(i - 1).compareTo(retrievedValueList.get(i)) < 0) {
                    isWorking = false;
                    reasonMessage = "Values are not arranged in descending format...";
                    break;
                }
            }

            driverScript.setReason(reasonMessage);
            seleniumDriver.setDriver(driver);
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able check order due to ").append(e.getMessage()).append("...").toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << isDescending()");
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
