package com.ump.automate.test.browser.impl;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ump.automate.test.browser.Browser;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.util.PlatformVMFactory;
import com.ump.automate.test.util.properties.ExternalPropertiesResource;

/**
 * this class implements Browser and performs method that opens the browser through grid/vm
 * @author edward.cervantes
 */
public class GridBrowser implements Browser {

    private static final Logger log = Logger.getLogger(GridBrowser.class);

    private ExternalPropertiesResource externalPropertiesResource;

    private PlatformVMFactory platformVMFactory;

    private Platform vmPlatform;

    private String vmNodeURL = "";

    private GridBrowser(PlatformVMFactory platformVMFactory) {
        vmPlatform = platformVMFactory.setVMPlatform();
    }

    @Override
    public WebDriver openBrowser(String browserName) throws SeleniumHybridKeywordException, Exception {
        if (log.isDebugEnabled()) {
            log.debug("MCI >> openBrowser()");
        }

        String browserMsg = "";
        String reasonMessage = "";
        WebDriver driver = null;
        DesiredCapabilities capabilities;

        try {
            log.info("Opening Browser...");
            if ("".equals(browserName)) {
                throw new SeleniumHybridKeywordException("Cannot open the browser due to blank input.");
            } else {
                browserName = browserName.trim();
            }
            vmNodeURL = externalPropertiesResource.getVmNodeURLProp();

            if ("Mozilla".equalsIgnoreCase(browserName) || "Firefox".equalsIgnoreCase(browserName)
                    || "MozillaFirefox".equalsIgnoreCase(browserName)) {
                capabilities = DesiredCapabilities.firefox();
                capabilities.setPlatform(vmPlatform);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(new URL(vmNodeURL), capabilities);

                browserMsg = "Mozilla browser started";
            } else if ("IE".equalsIgnoreCase(browserName) || "InternetExplorer".equalsIgnoreCase(browserName)
                    || "iexplorer".equalsIgnoreCase(browserName)) {
                capabilities = new DesiredCapabilities().internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setPlatform(vmPlatform);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
               
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
                
                driver = new RemoteWebDriver(new URL(vmNodeURL), capabilities);

                browserMsg = "IE browser started";
            } else if ("Chrome".equalsIgnoreCase(browserName) || "GoogleChrome".equalsIgnoreCase(browserName)) {
                capabilities = new DesiredCapabilities().chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(vmPlatform);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(new URL(vmNodeURL), capabilities);

                browserMsg = "Chrome browser started";
            } else if ("Edge".equalsIgnoreCase(browserName) || "MicrosoftWebDriver".equalsIgnoreCase(browserName)
                    || "MicrosoftEdge".equalsIgnoreCase(browserName)) {
                capabilities = new DesiredCapabilities().edge();
                capabilities.setBrowserName("edge");
                capabilities.setPlatform(vmPlatform);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(new URL(vmNodeURL), capabilities);

                browserMsg = "Microsoft Edge browser started";
            } else {
                throw new SeleniumHybridKeywordException("The browser currently cannot open the stated browser.");
            }
            driver.manage().window().maximize();
            log.info(browserMsg);
            int implicitWaitTime = (10);
            driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        } catch (SeleniumHybridKeywordException e) {
            log.info(e.getMessage());
            throw new SeleniumHybridKeywordException(e.getMessage());
        } catch (WebDriverException e) {
            reasonMessage = new StringBuilder("Driver cannot be seen. Please add the driver/s located on the resources folder of the extracted zip file, to the same folder directory of the selenium-server-standalone.jar in the node machine").toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
            throw new SeleniumHybridKeywordException(reasonMessage);
        } catch (Exception e) {
            reasonMessage = new StringBuilder("Not able to open the Browser due to ").append(e.getMessage())
                    .append("...")
                    .toString();
            log.info(reasonMessage);
            log.error("Error found. ", e);
        }

        if (log.isDebugEnabled()) {
            log.debug("MCO << openBrowser()");
        }
        return driver;
    }

    public void setExternalPropertiesResource(ExternalPropertiesResource externalPropertiesResource) {
        this.externalPropertiesResource = externalPropertiesResource;
    }
}