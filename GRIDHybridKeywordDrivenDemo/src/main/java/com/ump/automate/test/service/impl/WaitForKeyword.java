package com.ump.automate.test.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ump.automate.test.browser.SeleniumDriverInt;
//import com.pointwest.ump.agilyloggerapi.util.CustomLogFactory;
//import com.pointwest.ump.agilyloggerapi.util.LoggerAdapter;
import com.ump.automate.test.execution.DriverScript;
import com.ump.automate.test.service.ActionKeyword;

/**
 * this class implements the ActionKeyword and performs method delays the process
 * @author edward.cervantes
 */
public class WaitForKeyword implements ActionKeyword {

    private static final Logger log = Logger.getLogger(WaitForKeyword.class);

    private DriverScript driverScript;

    private SeleniumDriverInt seleniumDriver;

    @Override
    public boolean isPerform(String pageObjectId, String dataInput, WebDriver driver) throws Exception {
        boolean isWorking = false;
        String reasonMessage = "";
        int timeout = 0;
        try {
            if (log.isDebugEnabled()) {
                log.debug("MCI >> waitFor()");
            }
            dataInput = dataInput.replaceAll("([a-zA-z &!@#$%^*():;'<>/?|\"+,-]+)", "");
            log.info("Wait for " + dataInput + " seconds...");
            timeout = Integer.valueOf(dataInput.trim());
            Thread.sleep(timeout * 1000);
            driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
            //			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            seleniumDriver.setDriver(driver);
            isWorking = true;
        } catch (Exception e) {
            isWorking = false;
            reasonMessage = new StringBuilder("Not able to wait due to ").append(e.getMessage()).append("...").toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            driverScript.setReason(reasonMessage);
        }
        if (log.isDebugEnabled()) {
            log.debug("MCO << waitFor()");
        }
        return isWorking;
    }

    public void setDriverScript(DriverScript driverScript) {
        this.driverScript = driverScript;
    }

    public void setSeleniumDriver(SeleniumDriverInt seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }
}
