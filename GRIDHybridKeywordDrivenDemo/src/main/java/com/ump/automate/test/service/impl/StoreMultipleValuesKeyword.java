package com.ump.automate.test.service.impl;

import java.util.HashMap;

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
public class StoreMultipleValuesKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(StoreMultipleValuesKeyword.class);

    private AssertElement assertElement;
    
    private static HashMap<String, String> hashMap = new HashMap<String, String>();
    
    public HashMap <String, String> getHashMap() {
        return hashMap;
    }
    
    public void setHashMap(HashMap <String, String> hashMap) {
        this.hashMap = hashMap;
    }

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    /**
     * method that to be called in Excel file to perform checking if page element exist
     */
    
    
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> assert()");
        }
        boolean isPresent = false;
        String propField = "";
        String fieldValue = "";
        String reasonMessage = "";
        By locator = null;
        try {
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if (isPresent) {
            	fieldValue = driver.findElement(locator).getText();
            	//System.out.println("Field Text Value: " + fieldValue);
            	if("".equals(fieldValue)){
            		fieldValue = driver.findElement(locator).getAttribute("value");
            		//System.out.println("Field Value: " + fieldValue);
            		
            		if (fieldValue == null || "".equals(fieldValue)){
            			isPresent = false;
            			reasonMessage = "field does not contain a value";
                        log.info(reasonMessage);
                        driverScript.setReason(reasonMessage);
            		}
            	}
                hashMap.put(dataInput, fieldValue);
                
                System.out.println("Data input:" + hashMap.get(dataInput));
                
                setHashMap(hashMap);
                driverScript.setReason("Field Value: " + fieldValue);
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
        return isPresent;
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
