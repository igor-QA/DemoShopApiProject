import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class AuthApi {
    final String Email = "test123@ya.ru";
    final String Password = "testtest13";
    final String COOKIE_REQUEST = "ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utmc=78382081; __utmz=78382081.1610199709.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __RequestVerificationToken=4jQhs8OVpLKvZIAiaE9ngYgXQ25WraOZljuhYUm19-MECqCGVrpggMsP9a8B7Ky2_lKtQryXH480Xzt8OJq5AhNajRFviqSzLVRyXlMVrWQ1; ASP.NET_SessionId=v0o024f4vrgupcc5zcl01hww; __utma=78382081.517907423.1610199709.1610294821.1610363192.4; __utmt=1; Nop.customer=55f5107c-b195-4def-b739-a6945fe745d3; __utmb=78382081.6.10.1610363192";

    String data = " {" + "\"Email\": \" " + Email + "\", " +
            "\"Password\": \" " + Password + "\" }";

    @BeforeAll
    static void beforeEach() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
    }

    @Step("Get new session Cookie")
    public String getSessionToken() {
        Response response = given()
                .filter(new AllureRestAssured())
                .accept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .contentType("application/x-www-form-urlencoded")
                .cookie(COOKIE_REQUEST)
                .body(data)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().response().cookies()

        System.out.println(response.asString());

    }
}



