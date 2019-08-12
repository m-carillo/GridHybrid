package com.ump.automate.test.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class BasicOperatorKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(BasicOperatorKeyword.class);

    private AssertElement assertElement;

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;
    
    public StoreValueKeyword StoreValueKeyword;
    
    public static double result;

    /**
     * Function: 
     */
    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> assert()");
        }
        boolean isWorking = false;
        boolean isPresent = false;
        String propField = "";
        String fieldValue = "";
        By locator = null;
        String fromStoredValue = "";
        String reasonMessage = "";
        List<String> dataSet = Arrays.asList(dataInput.split(";"));
        
        try {
             StoreValueKeyword storedValueKeyword = new StoreValueKeyword ();
             fromStoredValue = storedValueKeyword.tempValue;
             
             String firstOperand = dataSet.get(0).trim();
             String operator = dataSet.get(1).trim();
             String inputValue = dataSet.get(2).trim();
             
             double firstOperandValue = Double.NaN;
             double dataValue = Double.NaN;
             if(("stored").equalsIgnoreCase(firstOperand)){
            	 firstOperandValue = Double.parseDouble(fromStoredValue.replaceAll(",", ""));
            	 //System.out.println("Stored: " + firstOperandValue);
             } else if (("partial").equalsIgnoreCase(firstOperand)){
            	 firstOperandValue = result;
            	 //System.out.println("Partial: " + firstOperandValue);
             } else if(firstOperand.contains("Key")){
            	 
            	StoreMultipleValuesKeyword storage = new StoreMultipleValuesKeyword ();
         		HashMap<String, String> hashMap = storage.getHashMap();
         		
         		System.out.println("Key: " + firstOperand.replaceAll("Key:", "").trim());
         		firstOperand = hashMap.get(firstOperand.replaceAll("Key:", "").trim());
         		System.out.println("First Operand: " + firstOperand);
         		
         		firstOperandValue = Double.parseDouble(firstOperand.replaceAll(",", ""));
         		if((firstOperandValue == Double.NaN)){
         			isWorking = false;
         			reasonMessage = "incorrect data set";
                    log.info(reasonMessage);
                    driverScript.setReason(reasonMessage);
         		}
            	 
             }
            	if(("get value").equalsIgnoreCase(inputValue)){
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
                    }
                    inputValue = fieldValue;
                    dataValue = Double.parseDouble(inputValue.replaceAll(",", ""));
                    //System.out.println("Field value: " + inputValue);
            	} else if(firstOperand.contains("Key")) {
               	 
                	StoreMultipleValuesKeyword storage = new StoreMultipleValuesKeyword ();
             		HashMap<String, String> hashMap = storage.getHashMap();
             		
             		System.out.println("Key: " + inputValue.replaceAll("Key:", "").trim());
             		inputValue = hashMap.get(inputValue.replaceAll("Key:", "").trim());
             		System.out.println("Stored Value: " + fromStoredValue);
            	}  
                //System.out.println("Input Value: " + inputValue);
            	dataValue = Double.parseDouble(inputValue.replaceAll(",", ""));
         		if((dataValue == Double.NaN)){
         			isWorking = false;
         			reasonMessage = "incorrect data set";
                    log.info(reasonMessage);
                    driverScript.setReason(reasonMessage);
         		}
         		
            	if ("add".equalsIgnoreCase(operator)){
            		result = firstOperandValue + dataValue;
            	} else if ("subtract from".equalsIgnoreCase(operator)){ //Input minus Stored
            		result = dataValue - firstOperandValue;
            	} else if ("less".equalsIgnoreCase(operator) || "minus".equalsIgnoreCase(operator)){ //Stored minus Input
            		result = firstOperandValue - dataValue;
            	} else if ("multiply".equalsIgnoreCase(operator)){ 
            		result = firstOperandValue * dataValue;
            	} else if ("divide by".equalsIgnoreCase(operator)){ //Stored / Input
            		result = firstOperandValue / dataValue;
            	} else if ("divide".equalsIgnoreCase(operator)){ //Input / Stored
            		result = dataValue / firstOperandValue;
            	}
            	System.out.println("1st Operand: " + firstOperandValue);
            	System.out.println("Data Value: " + dataValue);
            	System.out.println("Result: [" + result + "]");
            	BigDecimal bd = new BigDecimal(result);
            	bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            	result = bd.doubleValue();
            	
            	System.out.println("Formatted Result: [" + result + "]");
            	this.result = result;
            	
            	driverScript.setReason("Field Value: [" + dataValue + "]; Result: [" + result + "]");
            	isWorking = true;
        }  catch (Exception e) {
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
