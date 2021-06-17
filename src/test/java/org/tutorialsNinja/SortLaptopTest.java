package org.tutorialsNinja;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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

import org.tutorialsNinja.pageObjects.LaptopsNotebooksPageObject;
import org.tutorialsNinja.resources.Base;

public class SortLaptopTest extends Base {

	public static final Logger logger = LogManager.getLogger(SortLaptopTest.class);

	WebDriver driver;

	@BeforeTest
	public void initialization() throws IOException {

		driver = initializeDriver();
		logger.info("Driver initialized.");

		driver.get(prop.getProperty("url"));
		logger.info("URL hit : " + prop.getProperty("url"));

		driver.manage().window().maximize();

	}

	@Test(dataProvider = "SortBy Test Data")
	public void SortProducts(String sortAs) throws Exception {

		LaptopsNotebooksPageObject laptop = new LaptopsNotebooksPageObject(driver, logger);

		logger.info("Go To Laptops And NoteBooks page.");
		laptop.gotoLaptopPage();

		logger.info("Sort By : " + sortAs);
		laptop.sortByFilter(sortAs);

		List<String> actualList = laptop.getListAfterSortBy();
		List<String> expectedList = laptop.expectedList(actualList);

		Assert.assertEquals(actualList, expectedList);
		System.out.println("Sorting Complete. List sorted as per : " + sortAs);
		logger.info("Sorting Complete. List sorted as per : " + sortAs);

	}

	@DataProvider(name = "SortBy Test Data")
	public Object[] searchTestData() throws Exception {

		Object[] sortAs = null;

		try {

			File file = new File(".//test_data//SortLaptop.xlsx");
			FileInputStream fis = new FileInputStream(file);

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("SortAs");

			int rc = ws.getLastRowNum() - ws.getFirstRowNum(); // get row count

			sortAs = new Object[rc];

			for (int i = 1; i <= rc; i++) {

				Row row = ws.getRow(i);
				sortAs[i - 1] = row.getCell(0).getStringCellValue(); // Get sorting type from test data sheet
			}

			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException occurred!!!!!!");
		}

		return sortAs;
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
