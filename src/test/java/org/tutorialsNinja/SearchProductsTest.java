package org.tutorialsNinja;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.tutorialsNinja.pageObjects.SearchProductsPageObject;
import org.tutorialsNinja.resources.Base;

public class SearchProductsTest extends Base {

	public static final Logger logger = LogManager.getLogger(SearchProductsTest.class);

	WebDriver driver;

	@BeforeTest
	public void initialization() throws IOException {
		
		driver = initializeDriver();
		logger.info("Driver initialized.");

		driver.get(prop.getProperty("url"));
		logger.info("URL hit : " + prop.getProperty("url"));

		driver.manage().window().maximize();

	}

	@Test(dataProvider = "Search Test Data")
	public void Search(String productName, String productPrice) {

		SearchProductsPageObject search = new SearchProductsPageObject(driver);

		logger.info("Search Product : " + productName);
		search.searchProduct(productName);

		String actualProductName = search.getProductName();
		Assert.assertEquals(actualProductName, productName);

		System.out.println("Product Searched : " + actualProductName);
		logger.info("Product Searched : " + actualProductName);

		String actualProductPrice = search.getProductPrice();
		Assert.assertEquals(actualProductPrice, productPrice);

		System.out.println("Product price is : " + productPrice);
		logger.info("Product price : " + productPrice);

		search.clearSearchBox();

	}

	@DataProvider(name = "Search Test Data")
	public Object[][] searchTestData() {

		Object[][] productDetails = null;

		try {

			File file = new File(".//test_data//SearchProduct.xlsx");
			FileInputStream fis = new FileInputStream(file);

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Product");

			int rc = ws.getLastRowNum() - ws.getFirstRowNum(); // get row count

			productDetails = new Object[rc][2];

			for (int i = 1; i <= rc; i++) {

				Row row = ws.getRow(i);

				productDetails[i - 1][0] = row.getCell(0).getStringCellValue(); // Get product name from test data sheet
				productDetails[i - 1][1] = row.getCell(1).getStringCellValue(); // Get product price from test data
																				// sheet
			}

			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("IOException occurred.");
		}

		return productDetails;
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
