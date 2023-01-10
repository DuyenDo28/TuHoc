package test.global.categories;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.categories.CategoriesTestFlow;
import test_flows.url.Urls;

public class categoriesTest {

    @Test
    public void testInformationCategories(){
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);
        //Assert.fail(" Demo taking screenshot when test is failed!");
        CategoriesTestFlow categoriesTestFlow = new CategoriesTestFlow(driver);
       // categoriesTestFlow.verifyCategoriesIsClickable();
        categoriesTestFlow.verifyASpecificCategoryIsClickable();
        driver.close();
    }

    @Test
    public void TestHeaderInformation() {
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);
        //Assert.fail(" Demo taking screenshot when test is failed!");
        CategoriesTestFlow categoriesTestFlow = new CategoriesTestFlow(driver);
        categoriesTestFlow.VerifyHeaderInformation();
        driver.close();
    }

}
