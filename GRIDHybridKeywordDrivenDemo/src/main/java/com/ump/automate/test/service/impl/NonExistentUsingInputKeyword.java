package com.ump.automate.test.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements the ActionKeyword and checks whether the element exist or not
 * @author louie.carillo
 */
public class NonExistentUsingInputKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(NonExistentUsingInputKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;
    
    public StoreValueKeyword StoreValueKeyword;

    /**
     * this method handles the inspection of element whether it exists or not
     * this returns true if the element can't be found
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> nonExistent()");
        }
        boolean isPresent = false;
        boolean isNotPresent = false;
        String propField = "";
        String storedValue = "";
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        String hashMapStorage = "";
        String key = "";
        By locator = null;
        
        propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
        
        StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
    	storedValue = storedValueKeyword.tempValue;
        
        if(dataSet.size() > 1){
        	System.out.println("List" + dataSet);
        	hashMapStorage = dataSet.get(0).trim();
            key = dataSet.get(1).trim();
        }
        
        if("from stored".equalsIgnoreCase(dataInput)){
        	propField = propField.replaceAll("qwerty",storedValue);
        	
        } else if ("from key".equalsIgnoreCase(hashMapStorage)) {
        	StoreMultipleValuesKeyword storage = new StoreMultipleValuesKeyword ();
    		HashMap<String, String> hashMap = storage.getHashMap();
    		
    		storedValue = hashMap.get(key);
    		propField = propField.replaceAll("qwerty",storedValue);
    		
        } else {
        	propField = propField.replaceAll("qwerty", dataInput);
        }
        
        try {
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if (!isPresent) {
                isNotPresent = true;
            }
            seleniumDriver.setDriver(driver);
        } catch (SeleniumHybridKeywordException e) {
            isNotPresent = true;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isNotPresent = true;
            log.error("Error found. ", e);
        }
        if (isNotPresent) {
            driverScript.setReason("page object does not exist...");
        } else {
            driverScript.setReason("page object exist...");
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << nonExistent()");
        }
        return isNotPresent;
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
