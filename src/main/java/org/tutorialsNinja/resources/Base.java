package org.tutorialsNinja.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public Properties testData;

	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		String path = System.getProperty("user.dir")
				+ "\\src\\main\\java\\org\\tutorialsNinja\\resources\\data.properties";

		System.out.println("Path :- " + path);

		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		String chromeDriverPath = prop.getProperty("chromeDriverPath");
		
		System.out.println("Base :- " + browserName);
		System.out.println("Chrome Driver Path :- " + chromeDriverPath);

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}

		else if (browserName == "firefox") {
			System.setProperty("webdriver.gecko.driver",".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName == "Mozilla") {
			System.setProperty("webdriver.firefox.driver", ".\\drivers\\Mozilladriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName == "IE") {
			System.setProperty("webdriver.IE.driver", ".\\drivers\\IE.exe");
			driver = new InternetExplorerDriver();
		}

		else {
			System.out.print("Please check browser name");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		System.out.print("Screenshot attached. ");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		String filename = "_" + d.toString().replace(":", "_").replace(" ", "_");

		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + filename + ".png";
		System.out.println("Destination file location :- " + destinationFile);

		FileUtils.copyFile(source, new File(destinationFile));

		return destinationFile;
	}

}
