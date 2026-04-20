package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait mywait;

    public CartPage(WebDriver driver){
        this.driver=driver;
        this.mywait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private final By exactTotalAmount= By.xpath("//div[@class='css-146c3p1 r-op4f77 r-9iso6 r-1vgyyaa r-1b43r93 r-1rsjblm']");
    private final By itemPrice=By.xpath("//div[@class='css-146c3p1 r-op4f77 r-1rsjblm r-9iso6']");
    private final By promisefees=By.xpath("//div[@class='css-146c3p1 r-cnw61z r-1loqt21']");
    private final By cartSummaryScreenShot=By.xpath("(//div[@class='CTTtEa'])[2]");

    //
//    public int getTotalAmount(){
//        String actualtotal=mywait.until(ExpectedConditions.visibilityOfElementLocated(exactTotalAmount)).getText().replaceAll("[^0-9]*","");
//        int pprice=Integer.parseInt(actualtotal);
//        return pprice;
//    }
    public String getTotalAmountText() {
        return mywait.until(
                ExpectedConditions.visibilityOfElementLocated(exactTotalAmount)).getText();
    }

    public int CalculateTotalPrice(){
        List<WebElement> li=driver.findElements(itemPrice);
        int sum=0;
        for(WebElement el: li){
            String priceText= el.getText();
            priceText=priceText.replaceAll("[^0-9]*","");
            int price=Integer.parseInt(priceText);
            sum+=price;
        }

        List<WebElement> fees=driver.findElements(promisefees);
        if(fees.isEmpty()){
            sum+=7;
        }
        for(WebElement eel: fees){
            String feespriceText= eel.getText();
            feespriceText=feespriceText.replaceAll("[^0-9]*","");
            int price=Integer.parseInt(feespriceText);
            sum+=price;
        }
        return sum;

    }
    public String getSecondItemPriceText(){
        List<WebElement>items=driver.findElements(itemPrice);
        return items.get(0).getText().replaceAll("[^0-9]*","");
    }

    public void captureScreenShot(){
        WebElement element=driver.findElement(cartSummaryScreenShot);
        File source=element.getScreenshotAs(OutputType.FILE);
        File target=new File("C:\\Users\\2479652\\IdeaProjects\\IntermProject\\src\\main\\ScreenShot\\lastpagess.png");
        source.renameTo(target);
    }

}
