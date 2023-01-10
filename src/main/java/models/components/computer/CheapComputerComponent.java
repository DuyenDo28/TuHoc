package models.components.computer;

import models.components.ComponentCssSelector;
import models.components.order.ComputerEssentialSelectionsToBuyComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".product-essential")
public class CheapComputerComponent extends ComputerEssentialSelectionsToBuyComponent {

    public CheapComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        return null;
    }

    @Override
    public String selectRAMType(String type) {
        return null;
    }
}
