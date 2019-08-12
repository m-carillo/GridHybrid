package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that compares the input to that of the retrieved.
 * @author edward.cervantes
 */
public class TextNotEqualKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(TextNotEqualKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(InputKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    /**
     * this method compares the input and the retrieved value.
     * returns TRUE if input and retrieved were not the same.
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> textNotEqual()");
            }
            log.info("Entering the text in " + pageObjectId);
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            By locator = By.xpath(propField);
            if (assertElement.isElementPresent(locator, driver)) {///
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement webElement = null;
                webElement = driver.findElement(locator);
                String retrievedText = "";
                retrievedText = webElement.getText();

                if ("".equals(retrievedText)) {
                    retrievedText = webElement.getAttribute("value");
                }

                if (!dataInput.equalsIgnoreCase(retrievedText)) {
                    isWorking = true;
                } else {
                    reasonMessage = new StringBuilder("input value: ").append(dataInput).append("; retrieved: ").append(retrievedText).toString();
                }
                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            reasonMessage = e.getMessage();
            log.info(e.getMessage());
            log.error("Error found. ", e);
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to get the object text due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }
        driverScript.setReason(reasonMessage);
        if (log.isDebugEnabled()) {
            log.debug("MCO << textNotEqual()");
        }
        return isWorking;
    }

    public void setAssertElement(AssertElement assertElement) {
        this.assertElement = assertElement;
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
