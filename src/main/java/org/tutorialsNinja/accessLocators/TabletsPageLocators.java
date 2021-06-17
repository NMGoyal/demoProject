package org.tutorialsNinja.accessLocators;

import org.tutorialsNinja.commonFuntions.CommonFuntions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TabletsPageLocators {
    WebDriver driver;
    Logger logger;
    CommonFuntions commonFuntions;
    
    public TabletsPageLocators(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        commonFuntions = new CommonFuntions(this.driver, this.logger);
    }
    public WebElement tabletsText() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//div[@id='content']//h2[contains(text(),'Tablets')]", "tablets Text");
    }
    public WebElement wishListButton(String tabletModelName) throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//a[contains(text(),'" + tabletModelName + "')]/ancestor::div[contains(@class,'product-thumb')]//button[contains(@data-original-title,'Add to Wish List')]", "tablet Model Name");
    }
    public WebElement successMessage() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//div[contains(@class,'alert alert-success alert-dismissible') and contains(text(),'You have added')]//a[contains(text(),'wish list')]", "success Message");
    }
    public WebElement wishListErrorMessage() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//div[contains(@class,'alert alert-success alert-dismissible')]//a[contains(text(),'create an account')]", "wishList Error Message");
    }
}