package com.ump.automate.test.browser;

import org.openqa.selenium.By;

public class WebElementLocatorFactory {

    public By findLocator(String something) {
        
        By locator = null;
        locator = By.xpath(something);
        
        if(locator==null) {
            locator = By.cssSelector(something);
        }
        return locator;
    }
    
    
//    By locator = By.xpath(propField);
//    if (assertKeyword.isElementPerformable(locator, driver)) {

}
