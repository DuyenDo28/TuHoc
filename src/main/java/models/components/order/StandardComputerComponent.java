package models.components.order;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import javax.xml.bind.Element;
import java.util.List;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialSelectionsToBuyComponent {

    private static By productAttributeSel = By.cssSelector("select[id^=\"product_attribute\"]");

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    //cach 1, neu viet tuong minh ra se dung cach nay
    // cach 2 dùng utility method vì selectProcessType và SelectRamType giong nhau

    //cach1
  /*  @Override
    public String selectProcessorType(String type) {

        String strProcessorType = null;
        List<WebElement> listOfProductAttributeSel = component.findElements(productAttributeSel);
        WebElement listOfProcessorType = listOfProductAttributeSel.get(0);

        Select select = new Select(listOfProcessorType);
        List<WebElement> allOptionsOfProcessor = select.getOptions();
        for (WebElement option : allOptionsOfProcessor) {

            String currentOptionText = option.getText();
            System.out.println(currentOptionText);
            String optionTextWithoutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithoutSpaces.startsWith(type)) {

                // select.selectByVisibleText(optionTextWithoutSpaces);
                strProcessorType = optionTextWithoutSpaces;
                select.selectByVisibleText(currentOptionText);
                break;
            }
        }
        return strProcessorType;
    }
    */

 // cach 2
    @Override
    public String selectProcessorType(String type) {

        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownElem =
                component.findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownElem, type);
    }

    private String selectOption(WebElement dropdownElem, String type) {
        Select select = new Select(dropdownElem);
        List<WebElement> allOption = select.getOptions();
        String fullStrOption = null;
        for (WebElement option : allOption) {
            String currentOptionText = option.getText();
            String optionTextWithoutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithoutSpaces.startsWith(type)) {
                fullStrOption = currentOptionText;
                break;
            }
        }
        if (fullStrOption == null) {
            throw new RuntimeException("[ERR] The option" + type + "is not existing to select!");
        }
        select.selectByVisibleText(fullStrOption);
        return fullStrOption;
    }

    //Cach 1
    /*@Override
    public String selectRAMType(String type) {

        String strRAMType = null;
        List<WebElement> listOfProductAttributeSel = component.findElements(productAttributeSel);
        WebElement listOfRamType = listOfProductAttributeSel.get(1);

        Select select = new Select(listOfRamType);
        List<WebElement> allOptionsOfRAM = select.getOptions();
        for (WebElement option : allOptionsOfRAM) {
            String currentOptionText = option.getText();
            String optionTextWithoutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithoutSpaces.startsWith(type)) {

                // select.selectByVisibleText(optionTextWithoutSpaces);
                strRAMType = optionTextWithoutSpaces;
                select.selectByVisibleText(currentOptionText);
                break;
            }
        }

        return strRAMType;
    }*/
    // cach 2
    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownElem =
                component.findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownElem, type);
    }
}
