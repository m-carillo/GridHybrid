package com.ump.automate.test.browser.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ump.automate.test.browser.Browser;
import com.ump.automate.test.exception.SeleniumHybridKeywordException;
import com.ump.automate.test.util.ContextHandler;
import com.ump.automate.test.util.properties.InternalPropertiesResource;

/**
 * this class implements Browser and performs method that opens the browser from the local machine
 * @author edward.cervantes
 */
public class LocalBrowser implements Browser {

    private static final Logger log = Logger.getLogger(LocalBrowser.class);

    private InternalPropertiesResource internalPropertiesResource;

    private ContextHandler contextHandler;
    
    private String chromeLocal = "";

    private String edgeLocal = "";

    private String geckoLocal = "";

    private String ieLocal = "";

    private LocalBrowser(InternalPropertiesResource internalPropertiesResource) {
        chromeLocal = internalPropertiesResource.getChromeLocationProp();
        edgeLocal = internalPropertiesResource.getEdgeLocationProp();
        geckoLocal = internalPropertiesResource.getGeckoLocationProp();
        ieLocal = internalPropertiesResource.getIeLocationProp();
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
            if("".equals(browserName)) {
                throw new SeleniumHybridKeywordException("Cannot open the browser due to blank input.");
            } else {
                browserName = browserName.trim();
            }
            if ("Mozilla".equalsIgnoreCase(browserName) || "Firefox".equalsIgnoreCase(browserName)
                    || "MozillaFirefox".equalsIgnoreCase(browserName)) {
                System.setProperty("webdriver.gecko.driver", geckoLocal);

                //				System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new FirefoxDriver(capabilities);

                driver.manage().window().maximize();
                browserMsg = "Mozilla browser started";
                contextHandler.putSomething("browser", "mozilla");

            } else if ("IE".equalsIgnoreCase(browserName) || "InternetExplorer".equalsIgnoreCase(browserName)
                    || "iexplorer".equalsIgnoreCase(browserName)) {
                capabilities = new DesiredCapabilities().internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                System.setProperty("webdriver.ie.driver", ieLocal);
                driver = new InternetExplorerDriver(capabilities);

                driver.manage().window().maximize();
                browserMsg = "IE browser started";
            } else if ("Chrome".equalsIgnoreCase(browserName) || "GoogleChrome".equalsIgnoreCase(browserName)) {
                capabilities = new DesiredCapabilities().chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, true);
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                capabilities.setBrowserName("chrome");
                System.setProperty("webdriver.chrome.driver", chromeLocal);
                driver = new ChromeDriver();
                
                driver.manage().window().maximize();
                browserMsg = "Chrome browser started";
            } else if ("Edge".equalsIgnoreCase(browserName) || "MicrosoftWebDriver".equalsIgnoreCase(browserName)
                    || "MicrosoftEdge".equalsIgnoreCase(browserName)) {
                capabilities = new DesiredCapabilities().edge();
                // for UnhandledAlertException.
                // The default behaviour was set to "ACCEPT", thus the alert was closed automatically, 
                // and the switchTo().alert() couldn't find it.
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                System.setProperty("webdriver.edge.driver", edgeLocal);
                driver = new EdgeDriver(capabilities);

                driver.manage().window().maximize();
                browserMsg = "Microsoft Edge browser started";
           } else {
                throw new SeleniumHybridKeywordException("The browser currently cannot open the stated browser.");
            }
            log.info(browserMsg);
            int implicitWaitTime = (10);
            driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        } catch(SeleniumHybridKeywordException e) {
            log.info(e.getMessage());
            throw new SeleniumHybridKeywordException(e.getMessage());
        } catch (Exception e) {
            reasonMessage = new StringBuilder("Not able to open the Browser ").append("'"+browserName+"'").append(" due to ").append(e.getMessage())
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

    public void setContextHandler(ContextHandler contextHandler) {
        this.contextHandler = contextHandler;
    }
}
