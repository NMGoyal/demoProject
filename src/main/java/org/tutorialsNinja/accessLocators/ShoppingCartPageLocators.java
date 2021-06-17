package org.tutorialsNinja.accessLocators;

import org.tutorialsNinja.commonFuntions.CommonFuntions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPageLocators {
    WebDriver driver;
    Logger logger;
    CommonFuntions commonFuntions;
    
    public ShoppingCartPageLocators(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        commonFuntions = new CommonFuntions(this.driver, this.logger);
    }
    public WebElement shoppingCartText() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//div[@id='content']//h1[contains(text(),'Shopping Cart')]", "SHOPPING CART Text");
    }
}