package test.computer;

import models.components.order.StandardComputerComponent;
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

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData) {
        driver.get(demoBaseUrl.concat("/build-your-own-computer"));
        OrderComputerFlows<StandardComputerComponent> orderComputerFlow = new OrderComputerFlows<>(driver,computerData, StandardComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
       // orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.verifyProductPriceAndCustomerPayment();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.VISA);
        orderComputerFlow.confirmOrder();

    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/main/java/test_data/computer/StandardComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }

}
