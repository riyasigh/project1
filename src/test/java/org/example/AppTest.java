package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultPage;
import utils.DriverFactory;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppTest
{
    private WebDriver driver;
    private String browser,url,search;

    @BeforeClass
    public void setUp() throws IOException {
        ExcelUtils.createWorkbook();

        browser= ExcelUtils.readCell(0);
        url=ExcelUtils.readCell(1);
        search=ExcelUtils.readCell(2);

        // 3. Launch browser
        driver = DriverFactory.initDriver(browser);
    }

    @Test
    public void addTwoProducts() throws InterruptedException, IOException {
        driver.get(url);
        HomePage homePage=new HomePage(driver);
        homePage.closepopup();
        Assert.assertTrue(homePage.searchboxDisplayed(),"Flipkart home page did not load.");

        SearchResultPage searchResultPage=homePage.searchboxpage(search);

        searchResultPage.SortLowToHigh();
        //product1
        searchResultPage.clickProduct(0);
        Set<String> windowlist=driver.getWindowHandles();
        List<String> windowidsList=new ArrayList<>(windowlist);
        String parentWindow=windowidsList.get(0);
        String childWindow=windowidsList.get(1);

        driver.switchTo().window(childWindow);

        ProductPage productPage1=new ProductPage(driver);
        productPage1.AddToCart();

        //cartpage opens
        CartPage cartPage1=productPage1.GoToCart();
        String product1Amt=cartPage1.getTotalAmountText();
        System.out.println("Product 1 amount: "+product1Amt);

        driver.close();
        driver.switchTo().window(parentWindow);

        //product2
        searchResultPage.clickProduct(1);
        List<String> widsList=new ArrayList<>(driver.getWindowHandles());
        String child2=widsList.get(1);
        driver.switchTo().window(child2);

        ProductPage productPage2=new ProductPage(driver);
        productPage2.AddToCart();

        CartPage cartPage2=productPage2.GoToCart();
        String product2Amt=cartPage2.getTotalAmountText();
        System.out.println("Product 2 amount: " + product2Amt);

        //validate
        int expected=cartPage2.CalculateTotalPrice();
        int actual=Integer.parseInt(cartPage2.getTotalAmountText().replaceAll("[^0-9]*",""));

        System.out.println("Expected: " + expected + " | Actual: " + actual);

        String status = (expected == actual) ? "Pass" : "Fail";
        System.out.println("Validation: " + status);

        // Screenshot
        cartPage2.captureScreenShot();
        ExcelUtils.writeResults(product1Amt, product2Amt, expected, actual, status);
        System.out.println("Results written to Excel successfully!");

        // TestNG assertion
        Assert.assertEquals(actual, expected, "Cart total mismatch! Expected: " + expected + " | Actual: " + actual);
        }
        @AfterClass
        public void tearDown() {
            DriverFactory.quitDriver();
        }

}




