package API.common;

import Website.listeners.TestListener;
import constants.ConfigData;
import globals.TokenGlobal;
import helpers.ExcelHelpers;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

@Listeners(TestListener.class)
public class BaseTestAPI {

    @BeforeMethod
    public void getCustomerID(){
                Response response = given()
                .baseUri("https://parabank.parasoft.com/parabank/services/bank")
                .basePath("/login/{username}/{password}")
                .pathParam("username", "jdoen123")
                .pathParam("password", "TestPassword1!")
                .urlEncodingEnabled(false)
                .accept("application/json")
                .log().all()
                .when()
                .get();
        out.println(response.toString());
        response.getBody().prettyPrint();
        response.then().statusCode(200);
        TokenGlobal.CID = response.getBody().path("id");
        out.println(TokenGlobal.CID);
    }
}
