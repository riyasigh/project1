package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    private final WebDriver driver;
    private final WebDriverWait mywait ;
    //Locators
    private final By sortLowToHigh = By.xpath("//div[normalize-space()='Price -- Low to High']");
    private final By productlist = By.xpath("//div[@class='RGLWAk']");

    public SearchResultPage(WebDriver driver){
        this.driver=driver;
        this.mywait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //action
    public void SortLowToHigh() throws InterruptedException {
        driver.findElement(sortLowToHigh).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(1000);

    }
    public List<WebElement> getProductlist(){
        return driver.findElements(productlist);
    }

    public void clickProduct(int index){
        getProductlist().get(index).click();
    }
}
