package models.components.order;

import models.components.Component;
import models.components.computer.BaseItemDetailsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialSelectionsToBuyComponent extends BaseItemDetailsComponent {

    private final static By allOptionsSel = By.cssSelector(".option-list input");

    public ComputerEssentialSelectionsToBuyComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    public String selectHDD(String type) {
        return selectCompOption(type);
    }
    public String selectOS(String type) {
        return selectCompOption(type);
    }
    public String selectSoftware(String type){
        return selectCompOption(type);
    }

    protected String selectCompOption(String type) {
        String selectorStr = "//label[contains(text(),\"" + type + "\")]";
        //By optionSel = By.xpath(selectorStr);
        By optionSel = By.xpath("//label[contains(text(),\"" + type + "\")]");
        WebElement optionElem = null;
        try {
            optionElem = component.findElement(optionSel);
        } catch (Exception e) {
           throw new IllegalArgumentException("there is no ComputerEssentialSelection to select");
        }
        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("The option: " + type + " is not existing to select");
        }
    }
    public void unselectDefaultOptions(){
        List<WebElement> allOptionsElem = component.findElements(allOptionsSel);
        allOptionsElem.forEach(option -> {
            if(option.getAttribute("checked") !=null){
                option.click();
            }
        });
    }

}
