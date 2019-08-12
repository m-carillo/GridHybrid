package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
 * Function
 * @author louie.carillo
 */
public class ClickUntilElementPresentKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(ClickUntilElementPresentKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;
    
    public StoreValueKeyword StoreValueKeyword;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String propField = "";
		String expectedElement = "";
        String reasonMessage = "";
        String storedValue = "";
		Boolean isElementNotPresent = null;
		Boolean isPagination = false;
		By locator = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> click()");
            }
            log.info(new StringBuilder("Clicking on Webelement: ").append(pageObjectId).toString());
            StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
        	storedValue = storedValueKeyword.tempValue;
            
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
			expectedElement = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(dataInput).replaceAll("qwerty", storedValue);
            
			if(propField.contains("qwerty")){
				isPagination = true;
			} else {
				locator = By.xpath(propField);
			}
			
			By expectedElementLocator = By.xpath(expectedElement);
//            	int implicitWaitTime = 5;
//            	driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
//        	WebDriverWait wait = new WebDriverWait (driver, implicitWaitTime);
//        	WebElement myDynamicElement = (new WebDriverWait(driver, implicitWaitTime)).until(ExpectedConditions.elementToBeClickable(locator));
   	
            	WebElement elementToClick = driver.findElement(locator);
            	JavascriptExecutor executor = (JavascriptExecutor) driver;
            	        
			for (int i = 0 ; i < 15; i++){
				isElementNotPresent = driver.findElements(expectedElementLocator).size() <= 0;
				if (isElementNotPresent){
//					wait.until(ExpectedConditions.elementToBeClickable(locator));
//					myDynamicElement = driver.findElement(locator);
//					myDynamicElement.click();
					executor.executeScript("arguments[0].click();", elementToClick);
					System.out.println("Page: [" + (i+2) + "]");

			            
					} else {
						break;
					}
					
				}
				
				if(isElementNotPresent){
					driverScript.setReason("Element NOT found");
				} else {
					driverScript.setReason("Element found");
					seleniumDriver.setDriver(driver);
					isWorking = true;
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
