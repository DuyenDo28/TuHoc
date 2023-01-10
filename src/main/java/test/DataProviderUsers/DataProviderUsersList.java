package test.DataProviderUsers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.userLogin.DataObjectBuilder;
import test_data.userLogin.UserListData;

public class DataProviderUsersList {

    @Test(dataProvider = "userListData", groups = {"smoke"})

    public void testDataProvider(UserListData userListData) {
        System.out.println(userListData);
    }

    @DataProvider
    public UserListData[] userListData() {
        String fileLocation = "/src/main/java/test_data/userLogin/UserList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, UserListData[].class);
    }
}
