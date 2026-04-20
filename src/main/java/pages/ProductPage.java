package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait mywait ;
    //Locators
    private final By addTocart = By.xpath("//div[contains(text(),'Add to cart')]");
    private final By goTocart = By.xpath("//span[text()='Cart']");

    public ProductPage(WebDriver driver){
        this.driver=driver;
        this.mywait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    //actions

    public void AddToCart() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(addTocart).click();
    }
    public CartPage GoToCart(){
        mywait.until(ExpectedConditions.elementToBeClickable(goTocart)).click();
        return new CartPage(driver);
    }

}
