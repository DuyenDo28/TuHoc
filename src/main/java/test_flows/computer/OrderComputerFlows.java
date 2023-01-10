package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.checkout.*;
import models.components.order.CheapComputerComponent;
import models.components.order.ComputerEssentialSelectionsToBuyComponent;
import models.pages.CheckOutPage;
import models.pages.CheckoutOptionsPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;
import test_data.userLogin.DataObjectBuilder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlows < T extends  ComputerEssentialSelectionsToBuyComponent> {
    private final WebDriver driver;
    private final ComputerData computerData;
    private final Class<T> computerTypeToBuy;
    private PaymentMethod paymentMethod;

    private UserDataObject defaultCheckoutUser;

    public OrderComputerFlows(WebDriver driver, ComputerData computerData, Class<T> computerTypeToBuy) {
        this.driver = driver;
        this.computerData = computerData;
        this.computerTypeToBuy = computerTypeToBuy;
    }


    public void buildCompSpecAndAddToCart(){

        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerUserSelectToBuy = computerItemDetailsPage.computerComp(computerTypeToBuy);
        computerUserSelectToBuy.unselectDefaultOptions();

        String selectedProcessor = computerUserSelectToBuy.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(selectedProcessor);
       // System.out.println(selectedProcessor);
       // System.out.println(extractAdditionalPrice(selectedProcessor));
        String ramFullStr = computerUserSelectToBuy.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);

        String hddFullStr = computerUserSelectToBuy.selectHDD(computerData.getHdd());
        double additionalHddPrice = extractAdditionalPrice(hddFullStr);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {
            String fullOsStr = computerUserSelectToBuy.selectOS(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);
        }

        String fullSoftwareStr = computerUserSelectToBuy.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extractAdditionalPrice(fullSoftwareStr);

        computerUserSelectToBuy.clickOnAddToCartBtn();
        computerUserSelectToBuy.waitUntilItemAddedToCart();
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

    }


    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyProductPriceAndCustomerPayment(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComponents = shoppingCartPage.cartItemRowComponents();
        if(cartItemRowComponents.isEmpty()){
            Assert.fail("[ERR there is no product in the list");
        }else{
            double totalProductPriceByCalculation = 0;
            double totalOfSubTotal = 0;
            for (CartItemRowComponent cartItemRowComponent : cartItemRowComponents) {
                totalProductPriceByCalculation = totalProductPriceByCalculation + (cartItemRowComponent.unitPrice()* cartItemRowComponent.quantityProduct());
                totalOfSubTotal = totalOfSubTotal + cartItemRowComponent.subTotalSpecificProduct();
                //System.out.println(totalOfSubTotal);
                //System.out.println(totalProductPriceByCalculation);
            }
            System.out.println(totalProductPriceByCalculation);
            Assert.assertEquals(totalProductPriceByCalculation,totalOfSubTotal,"[ERR] Shopping cart's sub-total is incorrect");


        }

    }

    public void agreeTOSAndCheckout(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickOnCheckOutBtn();
        CheckoutOptionsPage checkoutOptionsPage = new CheckoutOptionsPage(driver);
        checkoutOptionsPage.checkoutAsGuest();

    }

    public void inputBillingAddress() {
        String defaultCheckoutUserJSONLoc = "/src/main/java/test_data/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserDataObject.class);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComp = checkOutPage.billingAddressComp();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastname(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComp.inputZIPCode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNo(defaultCheckoutUser.getPhoneNum());
        billingAddressComp.clickOnContinueBtn();
    }

    public void inputShippingAddress(){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.shippingAddressComp().clickOnContinueBtn();
    }

    public void selectShippingMethod() {
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkOutPage.shippingMethodComp();
        shippingMethodComp.selectShippingMethod(randomShippingMethod);
        shippingMethodComp.clickOnContinueButton();
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkOutPage.paymentMethodComp();
        if(paymentMethod ==null){
            throw new IllegalArgumentException("[ERR] payment method cannot be null!");
        }

        else {
            switch (paymentMethod){
                case CHECK_MONEY_ORDER:
                    paymentMethodComp.selectCheckMoneyOrderMethod();
                    break;
                case CREDIT_CARD:
                    paymentMethodComp.selectCreditCardMethod();
                    break;
                case PURCHASE_ORDER:
                    paymentMethodComp.selectPurchaseOrderMethod();
                default:
                    paymentMethodComp.selectCODMethod();
            }
            paymentMethodComp.clickOnContinueBtn();
            this.paymentMethod = paymentMethod;
        }
    }
    public void inputPaymentInfo(CreditCardType creditCardType){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentInformationComponent paymentInformationComp = checkOutPage.paymentInformationComp();
        if(this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)){
            paymentInformationComp.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckoutUser.getFirstName();
            String cardHolderLastName = this.defaultCheckoutUser.getLastName();
            paymentInformationComp.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" : "6011000990139424";
            paymentInformationComp.inputCardNumber(cardNumber);

            // Select current month and next year
            Calendar calendar = new GregorianCalendar();
            paymentInformationComp.inputExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            paymentInformationComp.inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
            paymentInformationComp.inputCardCode("123");
            paymentInformationComp.clickOnContinueBtn();
        }
        // Select current month and next year
        //  } else if(this.paymentMethod.equals(PaymentMethod.COD)){
        // TODO: add verification
        //   } else {
        // TODO: Verify cheque...
        //  }
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {

        }
    }
    public void confirmOrder(){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.confirmOrderComp().clickOnConfirmBtn();
    }
}
