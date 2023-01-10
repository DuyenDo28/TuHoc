package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    private static final By unitPriceSel = By.cssSelector(".product-unit-price");
    private static final By quantitySel = By.cssSelector(".qty-input");
    private static final By productSubTotalSel = By.cssSelector(".product-subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double unitPrice(){
        return Double.parseDouble(component.findElement(unitPriceSel).getText().trim());

    }
    public double quantityProduct(){
        return Double.parseDouble(component.findElement(quantitySel).getAttribute("value").trim());
    }
    public double subTotalSpecificProduct(){
        return Double.parseDouble(component.findElement(productSubTotalSel).getText().trim());
    }
}
