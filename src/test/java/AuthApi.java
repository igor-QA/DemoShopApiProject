import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi {
    private final static String EMAIL = "test123@ya.ru";
    private final static String PASSWORD = "testtest13";
    private final static String COOKIE_REQUEST = "ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utmc=78382081; __utmz=78382081.1610199709.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __RequestVerificationToken=4jQhs8OVpLKvZIAiaE9ngYgXQ25WraOZljuhYUm19-MECqCGVrpggMsP9a8B7Ky2_lKtQryXH480Xzt8OJq5AhNajRFviqSzLVRyXlMVrWQ1; ASP.NET_SessionId=v0o024f4vrgupcc5zcl01hww; __utma=78382081.517907423.1610199709.1610294821.1610363192.4; __utmt=1; Nop.customer=55f5107c-b195-4def-b739-a6945fe745d3; __utmb=78382081.6.10.1610363192";

    @Step("Get new session Cookie")
    public Map<String, String> cookies() {

        Map<String, String> cookies = given()
                .filter(new AllureRestAssured())  //.filter(filters().withCustomTemplates()) Для логов
                .accept(ContentType.ANY) //Принимаем любой контент
                .contentType("application/x-www-form-urlencoded")
                .cookie(COOKIE_REQUEST)
                .body(data())
                .basePath("/login")
        .when()
                .post()
        .then()
                .log().all()
                .statusCode(302)
                .extract().cookies();

        return cookies;
    }

    private String data() {
        return "email=" + EMAIL + "&password=" + PASSWORD;
    }
}