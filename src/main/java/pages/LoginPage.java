package pages;

import actionDriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    private ActionDriver actionDriver;

    private By userName = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");

}
