package org.tutorialsNinja.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CamerasPageObject {
	
	public WebDriver driver;
	
	public CamerasPageObject(WebDriver driver)
	{
		this.driver= driver;
	}
	
	
	By camerasTabLocator = By.linkText("Cameras");
	public WebElement camerasTab()
	{
		return driver.findElement(camerasTabLocator);
	}
	
	By breadcrumbCameras = By.xpath("//div/ul[@class='breadcrumb']/li[2]");
	public WebElement isBreadcrumbCamerasDisplayed()
	{
		return driver.findElement(breadcrumbCameras);
	}
	
	By camerasHighlighted = By.xpath("//*[@class='list-group-item active'] [contains(text(),'Cameras ')]");
	public WebElement isCamerasHighlighted()
	{
		return driver.findElement(camerasHighlighted);
	}
	
	By gridview = By.id("grid-view");
	public WebElement buttonGridView()
	{
		return driver.findElement(gridview);
	}
	
	By listView = By.id("list-view");
	public WebElement buttonListView()
	{
		return driver.findElement(listView);
	}
	
	By sortBy = By.xpath("//label[contains(text(),'Sort By:')]");
	public WebElement sortByFilter()
	{
		return driver.findElement(sortBy);
	}
	
	By showByNumPages = By.xpath("//label[contains(text(),'Show:')]");
	public WebElement showByNumPagesFilter()
	{
		return driver.findElement(showByNumPages);
	}
	
	By productCompare = By.xpath("//a[@id='compare-total']");
	public WebElement productCompareOption()
	{
		return driver.findElement(productCompare);
	}
	
	By showingPageNo = By.xpath("//div[contains(text(),'Showing 1 to')]");
	public WebElement showingPageNoContent()
	{
		return driver.findElement(showingPageNo);
	}
	
	By listItems = By.cssSelector("#content .row:nth-child(3) [class='product-layout product-list col-xs-12']");
	public WebElement menuListItems()
	{
		return driver.findElement(listItems);
	}
	
	By gridItems = By.xpath("#content .row:nth-child(3) [class='product-layout product-list col-xs-12']");
	public WebElement menuGridItems()
	{
		return driver.findElement(gridItems);
	}
	
	
	By carValue = By.cssSelector("span#cart-total");
	public WebElement getcartvalue()
	{
		return driver.findElement(carValue);
	}
	
	By Addtocart = By.xpath("//div[2]/div/div[2]/div[@class='button-group']/button[1]");
	public WebElement btnAddToCart()
	{
		return driver.findElement(Addtocart);
	}
	
	By wishList = By.xpath("//div[2]/div/div[2]/div[@class='button-group']/button[2]");
	public WebElement btnWishList()
	{
		return driver.findElement(wishList);
	}
	
	By addtocompare = By.xpath("//div[2]/div/div[2]/div[@class='button-group']/button[3]");
	public WebElement btnAddtoCompare()
	{
		return driver.findElement(addtocompare);
	}
	
	By wishlistadded = By.xpath("//span[contains(text(),'Wish List (1)')]");
	public WebElement addedToWishList()
	{
		return driver.findElement(wishlistadded);
	}
	
	By compareAdded = By.linkText("Product Compare (1)");
	public WebElement addedToCompare()
	{
		return driver.findElement(compareAdded);
	}
	
//	By wishlistadded = By.cssSelector("//span[contains(text(),'Wish List (1)')]");
//	public WebElement addedToWishList()
//	{
//		return driver.findElement(wishlistadded);
//	}
	
}