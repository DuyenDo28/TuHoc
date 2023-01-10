package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".footer")
public class FooterComponent extends Component {

    public FooterComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public InformationColumnComponent informationColumnComp(){
        return findComponent(InformationColumnComponent.class, driver);
    }

}
