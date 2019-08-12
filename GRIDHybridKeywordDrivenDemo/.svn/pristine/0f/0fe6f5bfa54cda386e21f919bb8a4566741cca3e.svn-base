package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.browser.SeleniumDriverInt;
//import com.pointwest.ump.agilyloggerapi.util.CustomLogFactory;
//import com.pointwest.ump.agilyloggerapi.util.LoggerAdapter;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;
import com.ump.automate.test.util.properties.PropertiesRetriever;

/**
 * this class implements ActionKeyword and performs method that navigate to the specified URL
 * @author edward.cervantes
 */
public class NavigateKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(NavigateKeyword.class);

    private DriverScript driverScript;

    private PropertiesRetriever propertiesRetriever;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        String propField = "";
        boolean isWorking;
        String reasonMessage = "";
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> navigate()");
            }
            propField = propertiesRetriever.retrievePropertiesFile("navi").getProperty(dataInput);
            log.info("Navigating to URL '" + propField + "' ");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(propField);
            seleniumDriver.setDriver(driver);
            isWorking = true;
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to navigate due to ").append(e.getMessage()).append("...").toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << navigate()");
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
