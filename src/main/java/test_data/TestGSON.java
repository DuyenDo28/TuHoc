package test_data;

import com.google.gson.Gson;
import test_data.userLogin.DataObjectBuilder;
import test_data.userLogin.UserListData;

import java.util.Arrays;

public class TestGSON {

    // Convert from JSON to Java Object
    // Convert Java Object to GSON
    public static void main(String[] args) {
      //  exploreGsonFeatures();
        testDataBuilder();

    }

    private static void testDataBuilder() {

        String fileLocation = "/src/main//java/test_data/userLogin/UsersList.json";
        // ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        UserListData[] userListData = DataObjectBuilder.buildDataObjectFrom(fileLocation, UserListData[].class);
        System.out.println(Arrays.toString(userListData));
    }



}
