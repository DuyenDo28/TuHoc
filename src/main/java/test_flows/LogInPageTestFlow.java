package test_flows;

import models.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import test_data.userLogin.DataObjectBuilder;
import test_data.userLogin.UserListData;

import java.util.Random;

public class LogInPageTestFlow {

    private final WebDriver driver;
    UserListData [] userListData;

    Random ranIndexOfList = new Random();
    public LogInPageTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void inputValidEmail(){

        String defaultCheckoutUserJSONLoc = "/src/main/java/test_data/userLogin/UsersList.json";
        userListData = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserListData[].class);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearUserName();
        loginPage.inputUserName(userListData[0].getUserName());

    }

    public void inputValidPassword(){
        String defaultCheckoutUserJSONLoc = "/src/main/java/test_data/userLogin/UsersList.json";
        userListData = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserListData[].class);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearPassWord();
        loginPage.inputPassword(userListData[0].getPassWord());
    }
    public void clickLoginBtn() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginBtn();
    }


}
