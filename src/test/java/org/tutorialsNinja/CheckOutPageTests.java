package org.tutorialsNinja;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.tutorialsNinja.pageObjects.CamerasPageObject;
import org.tutorialsNinja.pageObjects.CheckoutPageObject;
import org.tutorialsNinja.resources.Base;

public class CheckOutPageTests extends Base {

	private static final Logger logger = LogManager.getLogger(CheckOutPageTests.class.getName());
	public WebDriver driver;
	CamerasPageObject cameras ;
	CheckoutPageObject checkout ;
	WebDriverWait webdriverWait;
	

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		logger.info("driver is initialized");
		driver.get(prop.getProperty("url"));
		logger.info("URL is opened " + prop.getProperty("url"));
		cameras = new CamerasPageObject(driver);
		checkout = new CheckoutPageObject(driver);
		webdriverWait = new WebDriverWait(driver, 20);
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
	public void addtoCart() throws InterruptedException {
		cameras.camerasTab().click();
		cameras.isBreadcrumbCamerasDisplayed();
		cameras.buttonGridView().click();
		cameras.btnAddToCart().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(3000);
		assertEquals(cameras.getcartvalue().getText(), "1 item(s) - $98.00");
		logger.info("Cart value is"+cameras.getcartvalue().getText());
	}

	@Test(priority = 3)
	public void proceedToCheckoutPage() throws InterruptedException {
		checkout.getcheckOutTab().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		checkout.isBreadcrumbCheckoutDisplayed();
		assertTrue(true);
		logger.info("navigated to checkout Page");
	}

	@Test(priority = 4)
	public void ProceedOrderWithExistingAccount() throws InterruptedException {
		checkout.emailField().sendKeys("arun_va@hcl.com");
		checkout.passwordField().sendKeys("Password1");
		checkout.LoginButton().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		checkout.newBillingAddress().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		checkout.inputFirstName().sendKeys("arun");
		checkout.inputLastName().sendKeys("v");
		checkout.inputCompany().sendKeys("IT");
		checkout.inputAddress1().sendKeys("Chennai Tamilnadu India");
		checkout.inputCity().sendKeys("Chennai");
		checkout.input_Postcode().sendKeys("600018");
		checkout.selectCountryIndia().click();
		checkout.selecStateTamilnadu().click();
		checkout.btnBillingContinue().click();

	}

	@Test(priority = 5)
	public void AcceptTermsAndSlectPaymentMethod() throws InterruptedException {
		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address")));
		checkout.btnDeliveryContinue().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		checkout.btnDeliveryMethodContinue().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		checkout.AgreeTermsConditions().click();
		checkout.btnPaymentMethodContinue().click();
	}

	@Test(priority = 6)
	public void VerifyAndPlaceOrder() throws InterruptedException {
		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-confirm")));
		assertEquals(checkout.ProductNameInCart().getText(),"Nikon D300");
		logger.info("Product in the checkout page :  : "+checkout.ProductNameInCart().getText());
		assertEquals(checkout.ProductQuantity().getText(),"1");
		assertEquals(checkout.ProductUnitPrice().getText(),"$80.00");
		logger.info("product unit price is : "+checkout.ProductUnitPrice().getText(),"$80.00");
		checkout.Btn_ConfirmOrder().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("common-success")));
		assertEquals(checkout.OrderSucessMessage().getText(),"Your order has been placed!");
		logger.info("order confirmation as : "+checkout.OrderSucessMessage().getText());

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		logger.info("driver is closed");
	}

}
