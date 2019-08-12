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
 * this class implements ActionKeyword and performs method that compare input to the retrieved text
 * @author edward.cervantes
 */
public class CompareTextKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CompareTextKeyword.class);

    //	public static final LoggerAdapter log = CustomLogFactory.getLogger(InputKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;
    
    public StoreValueKeyword StoreValueKeyword;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
        String reasonMessage = "";
        String storedValue = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> compareText()");
            }
            log.info(new StringBuilder("Comparing the input text from ").append(pageObjectId).toString());
            
            StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
        	storedValue = storedValueKeyword.tempValue;
        	
        	propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
        	
            if (propField.contains("qwerty")){
            	propField = propField.replaceAll("qwerty", storedValue);
            }
            
            By locator = By.xpath(propField);
            if (assertElement.isElementPresent(locator, driver)) {///
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement webElement = driver.findElement(locator);
                String retrievedString = "";
                retrievedString = webElement.getText();
                if ("".equals(retrievedString)) {
                    retrievedString = webElement.getAttribute("value");
                }

                if (dataInput.trim().equals(retrievedString.trim())) {
                    isWorking = true;
                    reasonMessage = "retrieved text is the same as expected.";
                } else {
                    reasonMessage = new StringBuilder("retrieved: ").append(retrievedString)
                            .append("; input: ")
                            .append(dataInput)
                            .toString();
                }
                seleniumDriver.setDriver(driver);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
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
            log.debug("MCO << compareText()");
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
