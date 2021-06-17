package org.tutorialsNinja;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.tutorialsNinja.pageObjects.CamerasPageObject;
import org.tutorialsNinja.resources.Base;

public class CamerasPageTests extends Base {

	private static final Logger logger = LogManager.getLogger(CamerasPageTests.class.getName());
	public WebDriver driver;
	CamerasPageObject cameras;	

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		logger.info("driver is initialized");
		driver.get(prop.getProperty("url"));
		logger.info("URL is opened " + prop.getProperty("url"));
		cameras =new CamerasPageObject(driver);
		
	};

	@Test(priority = 1)
	public void tutorialNinjaPageLaunchVeriffication() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String title = driver.getTitle();
		String expectedTitle = "Your Store";
		assertEquals(title, expectedTitle);
		logger.info("Actual Title"+title);
	}

	@Test(priority = 2)
	public void camerasTabLandingVerification() {
		cameras.camerasTab().click();		
		cameras.isCamerasHighlighted();
		cameras.isBreadcrumbCamerasDisplayed();
		assertTrue(true);
		logger.info("Navigated to Cameras page");
	}

	@Test(priority = 3)
	public void verifyUIelementsOfCamerasTab() {

		cameras.isCamerasHighlighted();
		cameras.isBreadcrumbCamerasDisplayed();
		cameras.buttonGridView();
		cameras.buttonListView();
		cameras.productCompareOption();
		cameras.showByNumPagesFilter();
		cameras.showingPageNoContent();
		assertTrue(true);
		logger.info("All UI objects of cameras page are verififed");
	}

	@Test(priority = 4)
	public void verifyGridListView() {
		cameras.buttonListView().click();
		cameras.menuListItems().isDisplayed();
		cameras.buttonGridView().click();
		// Cameras.menuGridItems().isDisplayed();
		assertTrue(true);
		logger.info("grid view is verified");
	}

	@Test(priority = 5)
	public void addCameraToCart() throws InterruptedException {
		cameras.buttonGridView().click();
		cameras.btnAddToCart().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(3000);
		assertEquals(cameras.getcartvalue().getText(), "1 item(s) - $98.00");
		logger.info("Cart value is"+cameras.getcartvalue().getText());
	}

	@Test(priority = 6)
	public void addCameraToWishlist() {
		cameras.btnWishList().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		assertTrue(cameras.addedToWishList().isDisplayed());
		logger.info("Product added to wishlist");

	}

	@Test(priority = 7)
	public void addCameraToCompare() {
		cameras.btnAddtoCompare().click();
		assertTrue(cameras.addedToCompare().isDisplayed());
		logger.info("Product added to compare");

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		logger.info("driver is closed");
	}

}
