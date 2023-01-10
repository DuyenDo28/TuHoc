package test;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.LogInPageTestFlow;
import test_flows.url.Urls;

import static test_flows.url.Urls.loginSlug;

public class TestLoginPage extends BaseTest{

    @Test
    public void testLoginPage(){

        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl.concat(loginSlug));
        LogInPageTestFlow logInPageTestFlow = new LogInPageTestFlow(driver);
        logInPageTestFlow.inputValidEmail();
        logInPageTestFlow.inputValidPassword();
        logInPageTestFlow.clickLoginBtn();
    }



}
