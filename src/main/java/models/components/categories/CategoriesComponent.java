package models.components.categories;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(value = ".top-menu")
public class CategoriesComponent extends Component {

    private final static By linkCategory = By.cssSelector("li a");

    public CategoriesComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<WebElement> linksCategory(){
        return component.findElements(linkCategory);
    }
    public List<MainCatItem> linksAllCategories(){

        return findComponents(MainCatItem.class, driver);
    }
    @ComponentCssSelector(".top-menu > li")
    public static class MainCatItem extends Component {

        // các menu trên header
        public MainCatItem(WebDriver driver, WebElement component) {
            super(driver, component);
        }
        public WebElement catItemLinkElem(){
            return component.findElement(By.tagName("a"));
        }
        // tìm list sub-menu của main menu
        public List<SublistComponent> sublistComps(){
            // Hành động phải di chuyển chuột tới component(một main menu cụ thể nào) thì mới show ra các sub-menu
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(SublistComponent.class, driver);
        }

    }
    // đây là submenu, mình tách ra thành 1 component, sub ở đây gồm có Desktops, Notesbooks, Accessories.
    @ComponentCssSelector(".sublist li a")
    public static class SublistComponent extends Component{
        public SublistComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }
}
