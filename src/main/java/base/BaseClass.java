package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class BaseClass {

    protected static Properties prop;
    protected static WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        System.out.println("Setting up the test" + this.getClass().getSimpleName());
        launchBrowser();
        configureBrowser();
        staticWait(2);
    }

    @BeforeSuite
    public void loadConfig() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
    }

    private void launchBrowser() {
        String browser = prop.getProperty("browser");

        switch (browser.toLowerCase()) {

            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid Browser");
        }

    }

    //Configure browser settings
    private void configureBrowser() {
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));


    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void staticWait(int seconds){

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }


}
