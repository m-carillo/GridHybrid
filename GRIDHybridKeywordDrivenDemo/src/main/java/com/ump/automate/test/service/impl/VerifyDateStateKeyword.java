package com.ump.automate.test.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ump.automate.test.browser.SeleniumDriverInt;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * function
 * @author louie.carillo
 */
public class VerifyDateStateKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(VerifyDateStateKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        boolean isElementPresent = false;
        boolean isDateEnabled;
        String propField = "";
        String reasonMessage = "";
        String month = "";
        String year = "";
        String day = "";
        Date date = null;
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        String givenTime = "";
        int inputDay = 0;
        String expectedDateStatus = "";
        
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> datePicker()");
            }
            log.info(new StringBuilder("Verifying Date: ").append(pageObjectId).toString());
            
            if(dataSet.size() > 1){
            	givenTime = dataSet.get(0).trim();
            	inputDay = Integer.parseInt(dataSet.get(1).trim());
            	expectedDateStatus = dataSet.get(2).trim();
            }
            
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            date = new Date();
            Calendar cal = Calendar.getInstance();
            
            if("future".equalsIgnoreCase(givenTime)){
	            cal.add(Calendar.DATE, inputDay);
	            date = cal.getTime();   
            } else if ("past".equalsIgnoreCase(givenTime)){
            	cal.add(Calendar.DATE, -inputDay);
	            date = cal.getTime();
            }
            System.out.println("Formatted Date: " + df.format(date));
            List<String> inputDate = Arrays.asList(df.format(date).split("-"));
        	month = inputDate.get(1).toString();
        	//System.out.println("Month: " + month);
        	year = inputDate.get(2).toString();
        	//System.out.println("Year: " + year);
        	day = inputDate.get(0).toString();
        	//System.out.println("Day: " + day);
        	
            propField = propertiesRetriever.retrievePropertiesFile("objRepo").getProperty(pageObjectId);
            //System.out.println("Input Date Xpath: " + propField);
            List <String> dateLocator = Arrays.asList(propField.split(";"));
            
            String monthXpath = dateLocator.get(1).toString();
            String yearXpath = dateLocator.get(2).toString();
            String dayXpath = dateLocator.get(0).toString().replaceAll("day", day.replaceAll("^0+", ""));
            
            By monthLocator = By.xpath(monthXpath);
            //System.out.println(monthLocator);
            By yearLocator = By.xpath(yearXpath);
            //System.out.println(yearLocator);
            By dayLocator = By.xpath(dayXpath);
            //System.out.println(dayLocator);
            
            
            if(driver.findElement(monthLocator).isDisplayed()){
            	WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(monthLocator));
                WebElement webElement = driver.findElement(monthLocator);
                Select dropdown = new Select(webElement);
                log.info("selecting " + month + " from the dropdown");
                int index = 0;
                String dropdownText = "";
                for (WebElement option : dropdown.getOptions()) {
                    dropdownText = option.getText();
                    if ("".equals(dropdownText)) {
                        dropdownText = option.getAttribute("value");
                    }
                    if (dropdownText.trim().equalsIgnoreCase(month.trim()))
                        break;
                    index++;
                }
                dropdown.selectByIndex(index);
                seleniumDriver.setDriver(driver);
                isWorking = true;
            }

            if(driver.findElement(yearLocator).isDisplayed()){
            	WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(yearLocator));
                WebElement webElement = driver.findElement(yearLocator);
                Select dropdown = new Select(webElement);
                log.info("selecting " + year + " from the dropdown");
                int index = 0;
                String dropdownText = "";
                for (WebElement option : dropdown.getOptions()) {
                    dropdownText = option.getText();
                    if ("".equals(dropdownText)) {
                        dropdownText = option.getAttribute("value");
                    }
                    if (dropdownText.trim().equalsIgnoreCase(year.trim()))
                        break;
                    index++;
                }
                dropdown.selectByIndex(index);
                seleniumDriver.setDriver(driver);
                isWorking = true;
            }
            
            int implicitWaitTime = 5;
            driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(dayLocator));
            isElementPresent = driver.findElements(dayLocator).size() > 0;
            
            if (isElementPresent) {
            	isDateEnabled = driver.findElement(dayLocator).isEnabled() && !driver.findElement(dayLocator).getAttribute("class").contains("disabled");
                if("is enabled".equalsIgnoreCase(expectedDateStatus)){
                	if (isDateEnabled){
                		isWorking = true;
                		
                	} else {
                		System.out.println("Date is expected to be enabled");
                	}
                	
                } else if ("is disabled".equalsIgnoreCase(expectedDateStatus)){
                	if (!isDateEnabled){
                		isWorking = true;
                	} else {
                		System.out.println("Date is expected to be disabled");
                	}
                } else {
                	System.out.println("Incorrect data set");
                }
                	driverScript.setReason(df.format(date) + " should be " + expectedDateStatus);
            }
        }  

        catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to verify due to ").append(e.getMessage()).toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << click()");
        }
        return isWorking;
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
