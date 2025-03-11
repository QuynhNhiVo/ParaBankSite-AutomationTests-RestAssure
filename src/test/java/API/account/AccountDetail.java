package API.account;

import constants.ConfigData;
import globals.TokenGlobal;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class AccountDetail {

    public AccountDetail createNewAccount(int type){
        Response response = given()
                .baseUri(ConfigData.URI)
                .basePath("/createAccount")
                .queryParam("customerId", TokenGlobal.CID)
                .queryParam("newAccountType", type)
                .queryParam("fromAccountId", TokenGlobal.AID)
                .accept("application/json")
                .log().all()
                .when()
                .post();
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200, response.statusCode());
        return this;
    }

    public AccountDetail createNewAccount(){
        Response response = given()
                .baseUri(ConfigData.URI)
                .basePath("/createAccount")
                .queryParam("customerId", TokenGlobal.CID)
                .queryParam("newAccountType", 1)
                .queryParam("fromAccountId", TokenGlobal.AID)
                .accept("application/json")
                .log().all()
                .when()
                .post();
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200, response.statusCode());
        return this;
    }


}
