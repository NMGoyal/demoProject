package org.tutorialsNinja.accessLocators;

import org.tutorialsNinja.commonFuntions.CommonFuntions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageLocators {
    WebDriver driver;
    Logger logger;
    
    CommonFuntions commonFuntions;
    
    public LoginPageLocators(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        commonFuntions = new CommonFuntions(this.driver, this.logger);
    }
    public WebElement usernameTextField() throws Throwable {
        return commonFuntions.userDefinedFindElementById("input-email", "username Text Field");
    }
    public WebElement passwordTextField() throws Throwable {
        return commonFuntions.userDefinedFindElementById("input-password", " password Text Field");
    }
    public WebElement loginButton() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//input[contains(@value,'Login')]", "login Button");
    }
    public WebElement menuBar() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//nav[@id='menu']", "menu Bar");
    }
    public WebElement camerasMenuItem() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//ul[contains(@class,'nav navbar-nav')]//a[text()='Cameras']", "CAMERAS menu option in nav bar ");
    }
    public WebElement tabletsMenuItem() throws Throwable {
        return commonFuntions.userDefinedFindElementByXpath("//ul[contains(@class,'nav navbar-nav')]//a[text()='Tablets']", "TABLETS menu option in nav bar ");
    }

}