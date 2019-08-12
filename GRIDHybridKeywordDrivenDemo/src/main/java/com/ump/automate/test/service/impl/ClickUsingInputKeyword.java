package com.ump.automate.test.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs the CLICK functionality using input text through xpath identifier
 * @author edward.cervantes
 * @modified by louie.carillo
 */
public class ClickUsingInputKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(ClickUsingInputKeyword.class);

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
        String key = "";
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        Boolean isDataSetFromKey = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> clickUsingInput()");
            }
            log.info(new StringBuilder("Clicking on Webelement: ").append(pageObjectId).toString());
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
           
            if(dataSet.size() > 1){
            	System.out.println("List" + dataSet);
                key = dataSet.get(1).trim();
                isDataSetFromKey = true;
            }

            if("from stored".equalsIgnoreCase(dataInput)){
                StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
            	storedValue = storedValueKeyword.tempValue;
            	propField = propField.replaceAll("qwerty",storedValue);
            	
            } else if (isDataSetFromKey){
            	StoreMultipleValuesKeyword storage = new StoreMultipleValuesKeyword ();
        		HashMap<String, String> hashMap = storage.getHashMap();
        		
        		System.out.println("Key: '" + key + "'");
        		storedValue = hashMap.get(key);
        		System.out.println("Stored Value: " + storedValue);
        		propField = propField.replaceAll("qwerty",storedValue);
        		
            } else {
            	propField = propField.replaceAll("qwerty", dataInput);
            }
            
            By locator = By.xpath(propField);
            if (assertElement.isElementPerformable(locator, driver)) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
                WebElement webElement = null;
                webElement = driver.findElement(locator);

// Actions are not implemented in geckodriver
//              Actions actions = new Actions(driver);
//              actions.moveToElement(webElement).click().perform();
                webElement.click();
                Thread.sleep(5000);
                //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
              
                seleniumDriver.setDriver(driver);
                isWorking = true;
                int implicitWaitTime = 10;
                driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            }
        } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to click due to ").append(e.getMessage()).toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << click()");
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
