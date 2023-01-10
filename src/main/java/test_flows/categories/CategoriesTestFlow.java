package test_flows.categories;

import models.components.categories.CategoriesComponent;
import models.pages.HomePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test_flows.url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriesTestFlow {

    private final WebDriver driver;

    public CategoriesTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyCategoriesIsClickable() {

        // CategoriesComponent categoriesComponent=null;
        HomePage homePage = new HomePage(driver);
        CategoriesComponent categoriesComp = homePage.categoriesComp();

        //List<String> actualCategoriesLinks = new ArrayList<>();
        for (WebElement link : categoriesComp.linksCategory()) {
            link.click();
            driver.navigate().back();
        }
    }

    public void verifyASpecificCategoryIsClickable() {

        HomePage homePage = new HomePage(driver);
        CategoriesComponent CategoriesComp = homePage.categoriesComp();
        List<CategoriesComponent.MainCatItem> topCategoriesComps = CategoriesComp.linksAllCategories();
        if (topCategoriesComps.isEmpty()) {
            Assert.fail("[ERR] There is no item on top menu!");
        } else {
            CategoriesComponent.MainCatItem randomACategory = topCategoriesComps.get(new SecureRandom().nextInt(topCategoriesComps.size()));
            // CategoriesComponent.MainCatItem randomACategory= topCategoriesComps.get(1);
            String randomMainCategoryHref = randomACategory.catItemLinkElem().getAttribute("href");
            System.out.println(randomMainCategoryHref);
            List<CategoriesComponent.SublistComponent> sublistComponents = randomACategory.sublistComps();
            if (sublistComponents.isEmpty()) {
                randomACategory.catItemLinkElem().click();
                System.out.println(sublistComponents);
            } else {
                int randomIndex = new SecureRandom().nextInt(sublistComponents.size());
                System.out.println(sublistComponents.size());
                CategoriesComponent.SublistComponent randomSubItemCatComp = sublistComponents.get(randomIndex);
                randomMainCategoryHref = randomSubItemCatComp.getComponent().getAttribute("href");
                System.out.println(randomMainCategoryHref);
                randomSubItemCatComp.getComponent().click();
            }

        }
    }

    public void VerifyHeaderInformation() {

        HomePage homePage = new HomePage(driver);
        CategoriesComponent CategoriesComp = homePage.categoriesComp();
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList("BOOKS", "COMPUTERS", "Desktops", "Notebooks",
                                                       "Accessories", "ELECTRONICS", "Camera, photo",
                                                       "Cell phones", "APPAREL & SHOES", "DIGITAL DOWNLOADS",
                                                       "JEWELRY", "GIFT CARDS");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/books", baseUrl + "/computers", baseUrl + "/desktops", baseUrl + "/notebooks",
                baseUrl + "/accessories", baseUrl + "/electronics",  baseUrl + "/camera-photo", baseUrl + "/cell-phones", baseUrl + "/apparel-shoes",
                baseUrl + "/digital-downloads", baseUrl + "/jewelry", baseUrl + "/gift-cards");

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();
        List<CategoriesComponent.MainCatItem> topCategoriesComps = CategoriesComp.linksAllCategories();
        for (CategoriesComponent.MainCatItem topCategoriesComp : topCategoriesComps) {
            actualLinkTexts.add(topCategoriesComp.catItemLinkElem().getText().trim());
            actualHrefs.add(topCategoriesComp.catItemLinkElem().getAttribute("Href"));
              if(!topCategoriesComp.sublistComps().isEmpty()){
                  for (CategoriesComponent.SublistComponent sublistComp : topCategoriesComp.sublistComps()) {
                      actualLinkTexts.add(sublistComp.getComponent().getText().trim());
                      actualHrefs.add(sublistComp.getComponent().getAttribute("Href"));
                  }
              }
            //System.out.println("actualink là "+ actualLinkTexts);
        }

        System.out.println("actualink là "+ actualLinkTexts);
       // System.out.println("actualHref là "+ actualHrefs);
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Actual and expected texts are different!");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Actual href and expected href texts are different!");

    }
}

