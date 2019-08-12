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
 * this class implements ActionKeyword and performs method that checks if the element is present and is visible
 * @author louie.carillo
 */
public class AssertUsingInputKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(AssertUsingInputKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;
    
    public StoreValueKeyword StoreValueKeyword;

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
        By locator = null;
        String storedValue = "";
        String key = "";
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        Boolean isDataSetFromKey = false;
        try {
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
        	storedValue = storedValueKeyword.tempValue;
            
            if(dataSet.size() > 1){
            	System.out.println("List" + dataSet);
                key = dataSet.get(1).trim();
                isDataSetFromKey = true;
            }
            
            if("from stored".equalsIgnoreCase(dataInput)){
            	propField = propField.replaceAll("qwerty",storedValue);
            }  else if (isDataSetFromKey){
            	StoreMultipleValuesKeyword storage = new StoreMultipleValuesKeyword ();
        		HashMap<String, String> hashMap = storage.getHashMap();
        		
        		System.out.println("Key: '" + key + "'");
        		storedValue = hashMap.get(key);
        		System.out.println("Stored Value: " + storedValue);
        		propField = propField.replaceAll("qwerty",storedValue);
        		
            } else {
            	propField = propField.replaceAll("qwerty", dataInput);
            }
            
            locator = By.xpath(propField);
            isPresent = assertElement.isElementPresent(locator, driver);
            if (isPresent) {
                driverScript.setReason("page object exist");
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
