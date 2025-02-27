package API.account;

import API.common.BaseTestAPI;
import globals.TokenGlobal;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

public class CustomerDetail extends BaseTestAPI {

    @Test
    public void getCustomerDetail() {
        out.println("Customer ID: " + TokenGlobal.CID);
        Response response = given()
                .baseUri("https://parabank.parasoft.com/parabank/services/bank")
                .basePath("/customers/{customer_id}")
                .pathParam("customer_id", TokenGlobal.CID)
                .urlEncodingEnabled(false)
                .accept("application/json")
                .log().all()
                .when()
                .get();
        out.println(response.toString());
        response.getBody().prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getCustomerPosition() {
        out.println("Customer ID: " + TokenGlobal.CID);
        Response response = given()
                .baseUri("https://parabank.parasoft.com/parabank/services/bank")
                .basePath("/customers/{customer_id}/positions")
                .pathParam("customer_id", TokenGlobal.CID)
                .urlEncodingEnabled(false)
                .accept("application/json")
                .log().all()
                .when()
                .get();
        out.println(response.toString());
        response.getBody().prettyPrint();
        response.then().statusCode(200);
    }
}
