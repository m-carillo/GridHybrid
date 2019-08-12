package com.ump.automate.test.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that inputs/inserts the user input
 * @author edward.cervantes
 */
public class InputFromStoredKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(InputFromStoredKeyword.class);

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
        //String reasonMessage = "";
        boolean isPresent = false;
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        String hashMapStorage = "";
        String key = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> input()");
            }
            log.info("Entering the text in " + pageObjectId);
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            //			System.out.println("propField: " + propField);
            By locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if(dataSet.size() > 1){
            	hashMapStorage = dataSet.get(0).trim();
                key = dataSet.get(1).trim();
            }
        	System.out.println("Data Set: " + dataSet);
            if (isPresent) {
            	if("from key".equalsIgnoreCase(hashMapStorage)){
            		StoreMultipleValuesKeyword storage = new StoreMultipleValuesKeyword ();
            		HashMap<String, String> hashMap = storage.getHashMap();
            		
            		System.out.println("Key: '" + key + "'");
            		storedValue = hashMap.get(key);
            		System.out.println("Stored Value: " + storedValue);
            		
            	} else {
            		 System.out.println("Get from Stored");
            		StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
            		storedValue = storedValueKeyword.tempValue;
            	}
            	
            }
            if (assertElement.isElementPerformable(locator, driver)) {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.presenceOfElementLocated(locator)); /*examining the xpath for a search box*/
                WebElement webElement = driver.findElement(locator);
                webElement.clear();
                webElement.sendKeys(storedValue.trim());
                seleniumDriver.setDriver(driver);
                isWorking = true;
                int implicitWaitTime = (10);
                driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to input data due to ").append(e.getMessage()).append("...").toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << input()");
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
