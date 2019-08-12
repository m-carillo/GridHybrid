package com.ump.automate.test.service.impl;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ump.automate.test.AssertElement;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * Function: 
 * @author louie.carillo
 */
public class SwitchWindowKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(SwitchWindowKeyword.class);

    private AssertElement assertElement;

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
        boolean isWorking = false;
        boolean isPresent = false;
        boolean isClickable = false;
        boolean isXpathExpressionNull = false;
        String propField = "";
        String reasonMessage = "";
        By locator = null;
      
		propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
        locator = By.xpath(propField);
		
        try {
        	if ("click then switch".equalsIgnoreCase(dataInput)){
        		isPresent = driver.findElements(locator).size() > 0;
        		try {
        			if (isPresent) {
        				int implicitWaitTime = 10;
        	        	driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        	
        	        	WebElement element=driver.findElement(locator);
        	        	JavascriptExecutor executor = (JavascriptExecutor) driver;
        	        	executor.executeScript("arguments[0].click();", element);
        	        		
        	        	isClickable = true;
        	        	
        			} else {
        				driverScript.setReason("element does not exist");
        			}
        			
        		} catch (Exception e) {
        	        isClickable = false;
        	        reasonMessage = new StringBuilder("Not able to click due to ").append(e.getMessage()).toString();
        	        log.info(reasonMessage);
        	        log.error("Error found. ", e);
        	        driverScript.setReason(reasonMessage);
        	    }
        		
        		if (isClickable) {
        			
//        			for (String winHandle : driver.getWindowHandles()) {
//            			driver.switchTo().window(winHandle);
//            		}
        			
        			ArrayList<String> newWindowTab = new ArrayList<String>(driver.getWindowHandles());
        	        String oldWindowTab = driver.getWindowHandle();
        	        newWindowTab.remove(oldWindowTab);
        	        driver.switchTo().window(newWindowTab.get(0));
        	        
        			driver.manage().window().maximize();
                	isPresent = driver.findElements(locator).size() > 0;
                    if (!isPresent){
                        driverScript.setReason("Successfully switched to new window");
                        isWorking = true;
                    } else {
                    	driverScript.setReason("Unable to switched to new window");
                    }
                    
        		} else {
        				driverScript.setReason("Unable to click the element");
        		}
        		 
        	} else if ("close then switch".equalsIgnoreCase(dataInput)){

        			Actions action = new Actions(driver);
//        			action.sendKeys(Keys.CONTROL + "w");
//       			 	action.sendKeys(Keys.ESCAPE); 
        			driver.close();
        			for (String winHandle : driver.getWindowHandles()) {
        				Thread.sleep(2000);
        				driver.switchTo().window(winHandle);
        			}
        			driver.switchTo().defaultContent();
        			isPresent = driver.findElements(locator).size() > 0;
                
            	if (isPresent){
                    driverScript.setReason("Successfully switched to parent window");
                    isWorking = true;
                } else {
                	driverScript.setReason("Unable to switch back to parent window");
                }
            	
        	} else if ("just switch".equalsIgnoreCase(dataInput)){
        		for (String winHandle : driver.getWindowHandles()) {
        			driver.switchTo().window(winHandle);
        		}
        		
        		driver.switchTo().defaultContent();
        		isPresent = driver.findElements(locator).size() > 0;
                
            	if (isPresent){
                    driverScript.setReason("Successfully switched to parent window");
                    isWorking = true;
                } else {
                	driverScript.setReason("Unable to switch back to parent window");
                }
        	}
        
      } catch (SeleniumHybridKeywordException e) {
            isWorking = false;
            log.info(e.getMessage());
            log.error("Error found. ", e);
            driverScript.setReason(e.getMessage());
      } catch (Exception e) {
        	isWorking = false;
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
