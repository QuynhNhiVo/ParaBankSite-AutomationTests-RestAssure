package API.common;

import Website.common.BaseTest;
import Website.listeners.TestListener;
import Website.ParaBank.pages.LoginPage;
import globals.TokenGlobal;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

@Listeners(TestListener.class)
public class BaseTestAPI extends BaseTest {
    LoginPage loginPage;

    @Parameters({"row"})
    @BeforeMethod
    public void getCustomerID(@Optional("1") int row) {
        loginPage = new LoginPage();
        loginPage.customerApi(row);
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
