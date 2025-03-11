package API.common;

import API.model.lombok.RegisterLombok;
import Website.common.BaseTest;
import Website.listeners.TestListener;
import globals.TokenGlobal;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

@Listeners(TestListener.class)
public class BaseTestAPI extends BaseTest {
    RegisterCustomer registerCustomer;
    RegisterLombok registerLombok;

//    @Parameters({"row"})
    @BeforeMethod
    public void getCustomerID() {
        registerCustomer = new RegisterCustomer();
        registerLombok = registerCustomer.customerApi();
        Response response = given()
                .baseUri("https://parabank.parasoft.com/parabank/services/bank")
                .basePath("/login/{username}/{password}")
                .pathParam("username", registerLombok.getUsername())
                .pathParam("password", registerLombok.getPassword())
                .urlEncodingEnabled(false)
                .accept("application/json")
                .log().all()
                .when()
                .get();
        out.println(response.toString());
        response.getBody().prettyPrint();
        response.then().statusCode(200);
        TokenGlobal.CID = response.getBody().path("id");
        out.println("Customer ID: " + TokenGlobal.CID);
    }
}
