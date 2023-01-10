package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#confirm-order-buttons-container")
public class ConfirmOrderComponent extends Component {
    public final static By confirmBtnSel = By.cssSelector(".confirm-order-next-step-button");

    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void clickOnConfirmBtn(){
       WebElement confirmButton = component.findElement(confirmBtnSel);
       confirmButton.click();
       wait.until(ExpectedConditions.invisibilityOf(confirmButton));
    }
}
