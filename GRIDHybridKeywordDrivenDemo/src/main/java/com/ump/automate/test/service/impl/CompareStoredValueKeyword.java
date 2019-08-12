package com.ump.automate.test.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * Function:
 * @author louie.carillo
 */
public class CompareStoredValueKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(CompareStoredValueKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;
    
    public StoreValueKeyword StoreValueKeyword;
    

    /**
     * Function: 
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> assert()");
        }
        boolean isPresent = false;
        boolean isWorking = false;
        String propField = "";
        By locator = null;
        String fieldValue = "";
        String storedValue = "";
        String reasonMessage = "";
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        String hashMapStorage = "";
        String key = "";
        try {
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            
            if(dataSet.size() > 1){
            	System.out.println("List" + dataSet);
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
            		System.out.println("Stored Value: " + storedValue);
            		System.out.println("Field Value: " + fieldValue);
            	
            	}
            	
            	fieldValue = driver.findElement(locator).getText();
            	if("".equals(fieldValue)){
            		fieldValue = driver.findElement(locator).getAttribute("value");
            		System.out.println("Value: " + fieldValue);
            		
            		if (fieldValue == null || "".equals(fieldValue)){
            			isPresent = false;
            			reasonMessage = "field does not contain a value";
                        log.info(reasonMessage);
                        driverScript.setReason(reasonMessage);
            		}
            	}
            	
            	if(storedValue.equalsIgnoreCase(fieldValue)){
            		isWorking = true;
            		System.out.println("retrieved text is the same as expected");
            		driverScript.setReason("Field Value: [" + fieldValue + "]");
            	} else {
            		reasonMessage = new StringBuilder("retrieved: ").append(fieldValue)
                            .append("; input: ")
                            .append(dataInput)
                            .toString();
            	}
            	
            } else {
                driverScript.setReason("page object does not exist");
            }
        } catch (SeleniumHybridKeywordException e) {
            isPresent = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        } catch (Exception e) {
            isPresent = false;
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << assert()");
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
}
