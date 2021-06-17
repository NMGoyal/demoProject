package org.tutorialsNinja.accessLocators;

import org.tutorialsNinja.commonFuntions.CommonFuntions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageLocators {

	    WebDriver driver;
	    Logger logger;
	    CommonFuntions commonFuntions;
	    
	    public HomePageLocators(WebDriver driver, Logger logger) {
	        this.driver = driver;
	        this.logger = logger;
	        commonFuntions = new CommonFuntions(this.driver, this.logger);
	    }
	    public WebElement myAccountDropdown() throws Throwable {
	        return commonFuntions.userDefinedFindElementByXpath("//span[contains(text(),'My Account')]", "my Account Dropdown");
	    }
	    public WebElement loginOption() throws Throwable {
	        return commonFuntions.userDefinedFindElementByXpath("//a[contains(@title,'My Account')]/parent::li[contains(@class,'dropdown')]//a[contains(text(),'Login')]", "login Option");
	    }
	    public WebElement shoppingCartButton() throws Throwable {
	        return commonFuntions.userDefinedFindElementByXpath("//a[@title='Shopping Cart']//span[contains(text(),'Shopping Cart')]", "login Option");
	    }
	    public WebElement wishListButton() throws Throwable {
	        return commonFuntions.userDefinedFindElementByXpath("//a[@id='wishlist-total']//span[contains(text(),'Wish List')]", "login Option");
	    }

	}

