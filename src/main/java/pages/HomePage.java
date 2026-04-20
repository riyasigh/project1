package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.directory.SearchResult;
import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait mywait ;
    //Locators
    private final By loginpopupclose = By.xpath("//span[text()='✕']");
    private final By searchbox = By.xpath("//input[@class='nw1UBF v1zwn25']");

    public HomePage(WebDriver driver){
        this.driver=driver;
        this.mywait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //action
    public void closepopup(){
        mywait.until(ExpectedConditions.visibilityOfElementLocated(loginpopupclose)).click();
    }

    public boolean searchboxDisplayed(){
        WebElement searchboxd=mywait.until(ExpectedConditions.visibilityOfElementLocated(searchbox));
        return searchboxd.isDisplayed();
    }

    public SearchResultPage searchboxpage(String item){
        WebElement searchboxd=mywait.until(ExpectedConditions.visibilityOfElementLocated(searchbox));
        searchboxd.sendKeys(item, Keys.ENTER);
        return new SearchResultPage(driver);
    }


}
