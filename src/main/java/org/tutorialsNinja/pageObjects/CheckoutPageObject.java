package org.tutorialsNinja.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPageObject {
	
	public WebDriver driver;
	
	public CheckoutPageObject(WebDriver driver)
	{
		this.driver= driver;
	}
	
	By checkOutTab = By.xpath("//span[contains(text(),'Checkout')]");
	public WebElement getcheckOutTab()
	{
		return driver.findElement(checkOutTab);
	}
		
	
	By breadcrumbCheckout = By.linkText("Checkout");
	public WebElement isBreadcrumbCheckoutDisplayed()
	{
		return driver.findElement(breadcrumbCheckout);
	}
	
	By email = By.id("input-email");
	public WebElement emailField()
	{
		return driver.findElement(email);
	}
	
	By passoword = By.id("input-password");
	public WebElement passwordField()
	{
		return driver.findElement(passoword);
	}
	
	By login = By.id("button-login");
	public WebElement LoginButton()
	{
		return driver.findElement(login);
	}
	
//	By newbillingdetails = By.name("payment_address");
//	public void selectNewBillingAddress()
//	{
//		Select SelectBillingAddress = new Select(driver.findElement(newbillingdetails));
//		SelectBillingAddress.selectByValue("new");
//		
//	}
//	
	By enterbillingaddress = By.xpath("//input[@name='payment_address'][@value='new']");
	public WebElement newBillingAddress()
	{
		return driver.findElement(enterbillingaddress);
	}
	
	By input_FirstName = By.id("input-payment-firstname");
	public WebElement inputFirstName()
	{
		return driver.findElement(input_FirstName);
	}
	
	By input_LastName = By.id("input-payment-lastname");
	public WebElement inputLastName()
	{
		return driver.findElement(input_LastName);
	}
	
	By input_Company = By.id("input-payment-company");
	public WebElement inputCompany()
	{
		return driver.findElement(input_Company);
	}
	
	By input_Address1 = By.id("input-payment-address-1");
	public WebElement inputAddress1()
	{
		return driver.findElement(input_Address1);
	}
	
	By input_City = By.id("input-payment-city");
	public WebElement inputCity()
	{
		return driver.findElement(input_City);
	}
	
	By input_postcode = By.id("input-payment-postcode");
	public WebElement input_Postcode()
	{
		return driver.findElement(input_postcode);
	}
	
	By dropdownCountry = By.id("input-payment-country");
	By selectIndia = By.xpath("//option[. = 'India']");
	public WebElement selectCountryIndia()
	{
		return driver.findElement(dropdownCountry).findElement(selectIndia);
		 
	}
	
	By dropdownState = By.id("input-payment-zone");
	By selectTN = By.xpath("//option[. = 'Tamil Nadu']");
	public WebElement selecStateTamilnadu()
	{
		return driver.findElement(dropdownCountry).findElement(selectTN);
		 
	}
	
	
	
	By btn_continue = By.cssSelector("input#button-payment-address");
	public WebElement btnBillingContinue()
	{
		return driver.findElement(btn_continue);
	}
	
	By btn_deliverycontinue = By.xpath("//input[@id='button-shipping-address']");
	public WebElement btnDeliveryContinue()
	{
		return driver.findElement(btn_deliverycontinue);
	}
	
	By btn_deliveryMethodcontinue = By.xpath("//input[@id='button-shipping-method']");
	public WebElement btnDeliveryMethodContinue()
	{
		return driver.findElement(btn_deliveryMethodcontinue);
	}
	
	By AgreeTerms = By.xpath("//input[@name='agree']");
	public WebElement AgreeTermsConditions()
	{
		return driver.findElement(AgreeTerms);
	}
	
	By btn_PaymentMethodcontinue = By.xpath("//input[@id='button-payment-method']");
	public WebElement btnPaymentMethodContinue()
	{
		return driver.findElement(btn_PaymentMethodcontinue);
	}
	
	By ConfirmOrder = By.xpath("//input[@id='button-confirm']");
	public WebElement Btn_ConfirmOrder()
	{
		return driver.findElement(ConfirmOrder);
	}
	
	By ProductName = By.cssSelector(".table-responsive [href]");
	public WebElement ProductNameInCart()
	{
		return driver.findElement(ProductName);
	}
	
	By quantity = By.cssSelector(".table.table-bordered > tbody > tr > td:nth-of-type(3)");
	public WebElement ProductQuantity()
	{
		return driver.findElement(quantity);
	}
	
	By unitPrice = By.cssSelector(".table.table-bordered > tbody > tr > td:nth-of-type(4)");
	public WebElement ProductUnitPrice()
	{
		return driver.findElement(unitPrice);
	}	
	
	By OrderSucess = By.cssSelector("div#content > h1");
	public WebElement OrderSucessMessage()
	{
		return driver.findElement(OrderSucess);
	}
	
}