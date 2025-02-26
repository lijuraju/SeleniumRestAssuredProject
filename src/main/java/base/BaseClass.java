package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    protected Properties prop;
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);

        //Intialize the driver based on browser defined in config.properties

        String browser = prop.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }

        else if (browser.equalsIgnoreCase("firefox")) {
            //Initialize the driver for firefox
            driver = new FirefoxDriver();
        }

        else if (browser.equalsIgnoreCase("edge")) {
           driver = new EdgeDriver();
        }
        else {
            System.out.println("Invalid browser");
        }

        //Implicit wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        //maximize the browser
        driver.manage().window().maximize();

        //navigate to URL
        driver.get(prop.getProperty("url"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
