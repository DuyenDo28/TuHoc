package test.computer;

import models.components.order.CheapComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.CreditCardType;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.userLogin.DataObjectBuilder;
import test_flows.computer.OrderComputerFlows;
import test_flows.url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {


    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData) {
        driver.get(demoBaseUrl.concat("/build-your-cheap-own-computer"));
        OrderComputerFlows<CheapComputerComponent> orderComputerFlow = new OrderComputerFlows<>(driver, computerData, CheapComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
        orderComputerFlow.verifyProductPriceAndCustomerPayment();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.DISCOVER);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/main/java/test_data/computer/CheapComputerData.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
