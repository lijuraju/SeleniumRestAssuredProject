package actionDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionDriver {

    private WebDriver driver;
    private WebDriverWait wait;

    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForElementToBeClickable(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBeVisible(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void enterText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }

    public String getText(By by){
        waitForElementToBeVisible(by);
       return driver.findElement(by).getText();
    }

    public boolean isDisplayed(By by){

        waitForElementToBeVisible(by);
        return driver.findElement(by).isDisplayed();
    }

    public void scrollToElement(By by){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(by));
    }
}
