package API.account;

import constants.ConfigData;
import globals.TokenGlobal;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

public class CustomerDetail{
//    AccountDetail accountDetail;

    public CustomerDetail getCustomerDetail() {
        out.println("Customer ID: " + TokenGlobal.CID);
        Response response = given()
                .baseUri(ConfigData.URI)
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
        return this;
    }

    public CustomerDetail getCustomerPosition() {
        out.println("Customer ID: " + TokenGlobal.CID);
        Response response = given()
                .baseUri(ConfigData.URI)
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
        return this;
    }

    public Response createAccount(){
        return (Response) given().baseUri(ConfigData.URI)
                .basePath("/createAccount")
                .pathParam("customerId", TokenGlobal.CID)
                .pathParam("newAccountType", 1)
                .pathParam("fromAccountId", 123)
                .urlEncodingEnabled(false)
                .accept("application/json")
                .log()
                .all()
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    public AccountDetail getAccountID(){
        Response response = given()
                .baseUri(ConfigData.URI)
                .basePath("/customers/{customerId}/accounts")
                .pathParam("customerId", TokenGlobal.CID)
                .accept("application/json")
                .log().all()
                .when()
                .get();
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, response.statusCode());
        response.statusCode();
        TokenGlobal.AID = response.getBody().path("id");
        out.println("Account ID: " + TokenGlobal.AID);
        return new AccountDetail();
    }
}
