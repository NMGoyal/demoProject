package org.tutorialsNinja.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchProductsPageObject {

	WebDriver driver;

	By search = By.name("search");
	By searchBtn = By.xpath("//button[@class='btn btn-default btn-lg']");

	By product = By.xpath("//div[@class='caption']//a[contains(@href,'http://tutorialsninja.com/demo/index')]");

	// Constructor
	public SearchProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	// Search product
	public void searchProduct(String productName) {

		driver.findElement(search).sendKeys(productName); // Enter product name in Search text box
		driver.findElement(searchBtn).click();
	}

	// Method to clear search box
	public void clearSearchBox() {
		driver.findElement(search).clear();
	}

	// Method to get searched product's name for validation
	public String getProductName() {

		String productName = "";

		WebElement we_productName = driver.findElement(product);
		productName = we_productName.getText();

		return productName;

	}

	// Method to get searched product's price for validation
	public String getProductPrice() {

		String[] price = null;

		WebElement totalPrice = driver.findElement(By.xpath("//p[@class='price']"));
		price = totalPrice.getText().split("\\s");

		return price[0];

	}

}
