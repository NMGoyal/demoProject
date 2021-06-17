package org.tutorialsNinja;

import org.tutorialsNinja.GeneralExceptions.GeneralExceptions;
import org.tutorialsNinja.accessLocators.CamerasPageLocators;
import org.tutorialsNinja.accessLocators.HomePageLocators;
import org.tutorialsNinja.accessLocators.LoginPageLocators;
import org.tutorialsNinja.accessLocators.ShoppingCartPageLocators;
//import org.tutorialsNinja.InstantiateDriver.*;
import org.tutorialsNinja.commonFuntions.CommonFuntions;
import org.tutorialsNinja.resources.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AddToCartTest extends Base {

	public static final Logger logger = LogManager.getLogger(AddToCartTest.class);

	WebDriver driver;
	WebDriverWait wait;
	CommonFuntions commonFuntions;

	HomePageLocators homePageLocators;
	CamerasPageLocators camerasPageLocators;
	LoginPageLocators loginPageLocators;
	ShoppingCartPageLocators shoppingCartPageLocators;

	@BeforeTest
	public void initialization() throws IOException {

		driver = initializeDriver();
		logger.info("Driver initialized.");

		driver.get(prop.getProperty("url"));
		logger.info("URL hit : " + prop.getProperty("url"));

		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, 30);

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'My Account')]"))));

		commonFuntions = new CommonFuntions(driver, logger);
		homePageLocators = new HomePageLocators(driver, logger);
		camerasPageLocators = new CamerasPageLocators(driver, logger);
		loginPageLocators = new LoginPageLocators(driver, logger);
		shoppingCartPageLocators = new ShoppingCartPageLocators(driver, logger);

	}

	public static Properties config_properties = null;
	public static String global_properties_path = null;
	static String cameraModelName;
	static String tabletModelName;
	static String username;
	static String password;

	public AddToCartTest() throws Throwable {
		config_properties = new Properties();
		global_properties_path = System.getProperty("user.dir")
				+ "\\src\\main\\java\\org\\tutorialsNinja\\resources\\data.properties";
		File file = new File(global_properties_path);
		if (file.exists()) {
			InputStream input = null;
			try {
				input = new FileInputStream(file);
				config_properties.load(input);
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("File not found: " + global_properties_path);
		}
		username = config_properties.getProperty("username").trim();
		password = config_properties.getProperty("password").trim();
		cameraModelName = config_properties.getProperty("camera_model_name").trim();
		tabletModelName = config_properties.getProperty("tablet_model_name").trim();
	}

	@Test(priority = 1)
	public void loginPageScenarios() throws Throwable {
		if (driver == null) {
			throw new GeneralExceptions("WebDriver instance was found to be null ");
		} else if (logger == null) {
			throw new GeneralExceptions("Logger instance was found to be null ");
		}
//    2. Click on “My account” dropdown and choose “Login” option
		logger.info(" Running LOGIN page scenarios ......  ");
		try {
			homePageLocators.myAccountDropdown().click();
		} catch (Exception e) {
			logger.error("Could not click MY ACCOUNT dropdown due to ", e);
		}

		try {
			wait.until(ExpectedConditions.attributeContains(
					By.xpath("//a[contains(@title,'My Account')]/parent::li[contains(@class,'dropdown')]"), "class",
					"open"));
		} catch (Exception e) {
			logger.error("Could not expand MY ACCOUNT dropdown due to ", e);
		}
		try {
			homePageLocators.loginOption().click();
		} catch (Exception e) {
			logger.error("Could not click LOGIN option due to ", e);
		}
		try {
			driver.getCurrentUrl().endsWith("account/login");
		} catch (Exception e) {
			logger.error("Could not navigate to LOGIN page after clicking LOGIN button ", e);
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(loginPageLocators.usernameTextField()));
		} catch (Exception e) {
			throw new GeneralExceptions("LOGIN page was not successfully loaded ", e);
		}

		try {
			loginPageLocators.usernameTextField().sendKeys(username);
		} catch (Exception e) {
			logger.error("Could not send USERNAME to USERNAME text field due to ", e);
		}

		try {
			loginPageLocators.passwordTextField().sendKeys(password);
		} catch (Exception e) {
			logger.error("Could not send PASSWORD to PASSWORD text field due to ", e);
		}

		try {
			loginPageLocators.loginButton().click();
		} catch (Exception e) {
			logger.error("Could not click LOGIN button in LOGIN page due to ", e);
		}

		try {
			driver.getCurrentUrl().endsWith("account/account");
		} catch (Exception e) {
			throw new GeneralExceptions("LOGIN page was not successfully loaded ", e);
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(loginPageLocators.menuBar()));
		} catch (Exception e) {
			logger.error("Could not login after providing credentials due to ", e);
		}
	}

	@Test(priority = 2)
	public void addCamerasItemToCart() throws Throwable {
		if (driver == null) {
			throw new GeneralExceptions("WebDriver instance was found to be null ");
		} else if (logger == null) {
			throw new GeneralExceptions("Logger instance was found to be null ");
		}
//            2.Click on  "Cameras" menu from nav bar  It should navigate to Cameras page whose url should end with "path=33"
		logger.info(" Running Add Cameras Item To Cart scenarios ......  ");
		try {
			loginPageLocators.camerasMenuItem().click();
		} catch (Exception e) {
			logger.error("Could not click CAMERAS menu item in nav bar due to ", e);
		}

		try {
			driver.getCurrentUrl().endsWith("path=33");
		} catch (Exception e) {
			logger.error("Could not navigate to CAMERAS page after clicking CAMERAS menu item in nav bar due to ", e);
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(camerasPageLocators.camerasText()));
		} catch (Exception e) {
			throw new GeneralExceptions("CAMERAS page was not successfully loaded ", e);
		}

//            3.Click on “ADD TO CART” for item "Nikon D300"   I should be able to see success message as " Success: You have added Nikon D300 to your shopping cart!"
		try {
			wait.until(ExpectedConditions.visibilityOf(camerasPageLocators.addToCartButton(cameraModelName)));
		} catch (Exception e) {
			throw new GeneralExceptions(cameraModelName
					+ " is not present in the CAMERAS page hence could not be added to cart. Exception details - ", e);
		}

		try {
			camerasPageLocators.addToCartButton(cameraModelName).click();
		} catch (Exception e) {
			logger.error("Could not click CAMERAS menu item in nav bar due to ", e);
		}

		try {
			wait.until(ExpectedConditions.visibilityOf(camerasPageLocators.addToCartSuccessMessage()));
		} catch (Exception e) {
			throw new GeneralExceptions(cameraModelName + " could not be added to CART due to ", e);
		}

//      4.Click on “Shopping Cart” button within the page It should navigate to Checkout page whose url should end with "checkout/cart"
		try {
			wait.until(ExpectedConditions.visibilityOf(homePageLocators.shoppingCartButton()));
		} catch (Exception e) {
			throw new GeneralExceptions("Could not find SHOPPING CART button in the Home Page ", e);
		}
		try {
			homePageLocators.shoppingCartButton().click();
		} catch (Exception e) {
			logger.error("Could not click SHOPPING CART button due to ", e);
		}

		try {
			driver.getCurrentUrl().endsWith("checkout/cart");
		} catch (Exception e) {
			logger.error("Could not navigate to SHOPPING CART page after clicking SHOPPING CART button due to ", e);
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(shoppingCartPageLocators.shoppingCartText()));
		} catch (Exception e) {
			throw new GeneralExceptions(" SHOPPING CART page could not be loaded successfully due to ", e);
		}

	}

	@Test(dependsOnMethods = "addCamerasItemToCart")
	public void shoppingCartWebTable() {
//            5.I check shopping cart list for "Nikon D300"    "It should show correct details of the product added and validate:
		commonFuntions.validateAddToCartInShopCartPage();
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
		logger.info("driver instance quit successfully");
	}
}
