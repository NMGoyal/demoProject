package org.tutorialsNinja.accessLocators;

import org.tutorialsNinja.commonFuntions.CommonFuntions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CamerasPageLocators {

	WebDriver driver;
    Logger logger;
    CommonFuntions commonFuntions;
    
    public CamerasPageLocators(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        commonFuntions = new CommonFuntions(this.driver, this.logger);
    }
    public WebElement camerasText() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//div[@id='content']//h2[contains(text(),'Cameras')]", "cameras Text");
    }
    public WebElement addToCartButton(String cameraModelName) throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//a[contains(text(),'" + cameraModelName + "')]/ancestor::div[contains(@class,'product-thumb')]//span[contains(text(),'Add to Cart')]", "add To Cart Button");
    }
    public WebElement addToCartSuccessMessage() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//div[contains(@class,'alert alert-success alert-dismissible') and contains(text(),'You have added')]//a[contains(text(),'shopping cart')]", "success Message");
    }

}
