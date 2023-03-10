package models.pages;

import models.components.Component;
import models.components.categories.CategoriesComponent;
import models.components.global.FooterComponent;
import models.components.global.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends Component {

    protected WebDriver driver;

    public BasePage (WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }
    public FooterComponent footerComp(){
        return findComponent(FooterComponent.class,driver);
    }

    public CategoriesComponent categoriesComp(){
        return findComponent(CategoriesComponent.class, driver);

    }
    public HeaderComponent headerComp(){
        return findComponent(HeaderComponent.class, driver);
    }

}
