package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final static By usernameSel = By.id("Email");
    private final static By passwordSel = By.cssSelector("#Password");
    private final static By loginBtnSel = By.cssSelector("[value='Log in']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void inputUserName(String userName){
        driver.findElement(usernameSel).sendKeys(userName);

    }
    public void inputPassword(String passWord){
        driver.findElement(passwordSel).sendKeys(passWord);

    }

    public void clearUserName(){
        driver.findElement(usernameSel).clear();

    }
    public void clearPassWord(){
        driver.findElement(passwordSel).clear();

    }

    public void clickLoginBtn(){
        driver.findElement(loginBtnSel).click();

    }


}
