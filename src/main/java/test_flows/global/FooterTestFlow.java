package test_flows.global;


import models.components.global.FooterColumnComponent;
import models.components.global.FooterComponent;
import models.pages.BasePage;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test_flows.url.Urls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;


    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyInformationColumn(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use",
                "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/sitemap", baseUrl + "/shipping-returns", baseUrl + "/privacy-policy", baseUrl + "/conditions-of-use",
                baseUrl + "/about-us", baseUrl + "/contactus");
        verifyFooterColumn(footerColumnComp, expectedLinkTexts,expectedHrefs);

    }

    private void verifyFooterColumn(
            FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs) {

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        for(WebElement link : footerColumnComponent.linksElem()){
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }
        if(actualLinkTexts.isEmpty() || actualHrefs.isEmpty()){
            Assert.fail("[ERR] Texts or hyperlinks is empty in footer column");
        }

        // actualLinkTexts.equals(actualHrefs);

        // verify link text
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Actual and expected link texts are different!");

        // verify hrefs
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Actual and expected hrefs are different!");


    }

    public void verifyFooterComponent(){
        BasePage basePage = new HomePage(driver);
        FooterComponent footerComp = basePage.footerComp();
        verifyInformationColumn(footerComp.informationColumnComp());
   //     verifyCustomerService(footerComp.customerServiceColumnComp());
//        verifyAccountColumn(footerComp.accountColumnComp());
//        verifyFollowUsColumn(footerComp.followUsColumnComp());
    }

}
