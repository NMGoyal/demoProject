package org.tutorialsNinja.commonFuntions;

import org.tutorialsNinja.GeneralExceptions.GeneralExceptions;
import org.tutorialsNinja.resources.Base;
//import com.tutorialsNinja.InstantiateDriver.CreateDriver;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.net.InetAddress;

import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

public class CommonFuntions extends Base {
    public static WebDriver driver;
    //public static Logger logger = CreateDriver.getLoggerInstance();
    public static Logger logger = LogManager.getLogger(CommonFuntions.class);

    public CommonFuntions(WebDriver driver, Logger logger) {
        CommonFuntions.driver = driver;
        CommonFuntions.logger = logger;
    }

    public void launchApplication(String url) throws Throwable {
        try {
            if (driver != null) {
                logger.info("Launching URL -  " + url);
                driver.get(url);
                driver.manage().timeouts().pageLoadTimeout(30, SECONDS);
            } else {
                throw new GeneralExceptions("Could not launch application url as driver instance was found to be null");
            }
        } catch (GeneralExceptions generalException) {
            logger.error("Could not launch application url due to ", generalException);
        } catch (Exception e) {
            logger.error("Could not launch application url due to ", e);
        }
    }

    public WebElement userDefinedFindElementById(String idLocatorString, String elementName) throws Throwable {
        if (idLocatorString != null && driver != null) {
            try {
                driver.findElement(By.id(idLocatorString));
                logger.info(elementName + " was located successfully using locator value " + idLocatorString);
            } catch (TimeoutException timeoutException) {
                throw new GeneralExceptions(elementName + " could not be located due to ", timeoutException);
            } catch (Exception exception) {
                throw new GeneralExceptions(elementName + " with locator value " + idLocatorString + " was not found due to ", exception);
            }
        } else if (idLocatorString != null) {
            throw new GeneralExceptions("Supplied element locator for " + elementName + " is found to be null. Provide a valid locator value ");
        } else if (driver != null) {
            throw new GeneralExceptions("Driver instance was found to be null while locating " + elementName);
        }
        return driver.findElement(By.id(idLocatorString));
    }
    public WebElement userDefinedFindElementByXpath(String xpathLocatorString, String elementName) throws Throwable {
        if (xpathLocatorString != null && driver != null) {
            try {
                driver.findElement(By.xpath(xpathLocatorString));
                logger.info(elementName + " was located successfully using locator value " + xpathLocatorString);
            } catch (TimeoutException timeoutException) {
                throw new GeneralExceptions(elementName + " could not be located due to ", timeoutException);
            } catch (Exception exception) {
                throw new GeneralExceptions(elementName + " with locator value " + xpathLocatorString + " was not found due to ", exception);
            }
        } else if (xpathLocatorString != null) {
            throw new GeneralExceptions("Supplied element locator for " + elementName + " is found to be null. Provide a valid locator value ");
        } else if (driver != null) {
            throw new GeneralExceptions("Driver instance was found to be null while locating " + elementName);
        }
        return driver.findElement(By.xpath(xpathLocatorString));
    }
    public void validateAddToCartInShopCartPage() {
        List<WebElement> allRows = driver.findElements(By.xpath("//div[@id='content']//h1[contains(text(),'Shopping Cart')]/..//form[contains(@action,'checkout/cart')]//table[contains(@class,'table table-bordered')]//tr"));
        List<WebElement> allCells = driver.findElements(By.xpath("//div[@id='content']//h1[contains(text(),'Shopping Cart')]/..//form[contains(@action,'checkout/cart')]//table[contains(@class,'table table-bordered')]//td"));
        System.out.println("allRows = " + allRows.size());
        System.out.println("allCells = " + allCells.size());
        // validating Product Name of added item in shopping cart
        int productNameColPosition = 0;
        SoftAssert softAssert = new SoftAssert();
        for (int i = 1; i <= allRows.size(); i++) {
            boolean flag = false;
            for (int j = 1; j <= allCells.size(); j++) {
                if (i == 1 && allCells.get(j).getText().trim().contains("Product Name")) {
                    productNameColPosition = j;
                    System.out.println("productNameColPosition for Product Name is " + productNameColPosition);
                    flag = true;
                    break;
                }
            }
            if (flag = true)
                break;
        }
        List<WebElement> listOfActualValue = new ArrayList<WebElement>();
        for (int i = 1; i < allRows.size(); i++) {
            WebElement eachRow = driver.findElement(By.xpath("(//div[@id='content']//h1[contains(text(),'Shopping Cart')]/..//form[contains(@action,'checkout/cart')]//table[contains(@class,'table table-bordered')]//tr)[" + (i + 1) + "]//td[" + (productNameColPosition + 1) + "]//a"));
            listOfActualValue.add(eachRow);
        }
        int count = 0;
        for (int i = 0; i < listOfActualValue.size(); i++) {
            System.out.println("listOfActualValue = " + listOfActualValue.get(i).getText().trim());
            if (listOfActualValue.get(i).getText().trim().contains("Nikon D300")) {
                count++;
                System.out.println("Nikon D300 was added to cart successfully ...... ");
            }
        }
        if (count == 0) {
            softAssert.assertAll("Nikon D300 was not added into cart ");
        }
    }
}
