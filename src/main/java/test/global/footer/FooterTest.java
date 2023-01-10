package test.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import test_flows.url.Urls;

public class FooterTest {


    @Test()
    public void testInformationColumn() {
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);

        //Assert.fail(" Demo taking screenshot when test is failed!");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();


    }
}
