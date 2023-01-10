package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".cart-footer .totals")
public class TotalComponent extends Component {

    private static final By checkoutBtnSel = By.cssSelector("#checkout");
    private static final By tosSel = By.cssSelector("#termsofservice");


    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void agreeTOS(){
        component.findElement(tosSel).click();
    }
    public void clickOnCheckOutBtn(){
        component.findElement(checkoutBtnSel).click();
    }


}
