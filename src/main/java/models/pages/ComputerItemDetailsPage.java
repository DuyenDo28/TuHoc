package models.pages;

import models.components.order.ComputerEssentialSelectionsToBuyComponent;
import org.openqa.selenium.WebDriver;

public class ComputerItemDetailsPage extends BasePage{

    public ComputerItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public <T extends ComputerEssentialSelectionsToBuyComponent> T computerComp(Class<T> computerTypeToBuy){
        return findComponent(computerTypeToBuy, driver);
    }


}
