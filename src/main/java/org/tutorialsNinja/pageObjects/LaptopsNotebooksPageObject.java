package org.tutorialsNinja.pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LaptopsNotebooksPageObject {

	WebDriver driver;
	public static Logger logger = LogManager.getLogger(LaptopsNotebooksPageObject.class);

	By laptop = By.xpath("//a[text()='Laptops & Notebooks']");
	By allLaptops = By.xpath("//a[text()='Show All Laptops & Notebooks']");
	By sortBy = By.id("input-sort");
	By listNames = By.xpath("//div[@class='caption']//a[contains(@href,'http://tutorialsninja.com/demo/index')]");

	// Constructor
	public LaptopsNotebooksPageObject(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
	}

	// Click on Laptop And Notebooks from select bar
	public void gotoLaptopPage() {

		driver.findElement(laptop).click();
		driver.findElement(allLaptops).click();
	}

	// Sort By as per the value passed from test data sheet
	public void sortByFilter(String sortAs) throws Exception {

		Select sortByFilter = new Select(driver.findElement(sortBy));
		
		try {
			sortByFilter.selectByVisibleText(sortAs);
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Test data value : /'" + sortAs + "/' is unavailable in SortBy dropdown!!!!!!!!!!!!");
			throw new Exception("Test data value is unavailable in SortBy dropdown.", e);	
		}
		
	}

	// Get list of products after applying Sort By filter
	public List<String> getListAfterSortBy() {

		List<WebElement> sortedProducts = driver.findElements(listNames);
		List<String> actualList = new ArrayList<String>();

		for (WebElement ele : sortedProducts) {
			actualList.add(ele.getText());
		}

		return actualList;
	}

	// Sort a list to get expected list result for comparison
	public List<String> expectedList(List<String> expectedList) {

		Collections.sort(expectedList);
		return expectedList;
	}
}
