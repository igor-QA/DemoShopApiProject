import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi{
    private final static String EMAIL = "test123@ya.ru";
    private final static String PWD = "testtest13";

    @Step("Get new session Cookie")
    public Map<String, String> cookies() {
/** .filter(filters().withCustomTemplates()) Для удобно читаемых логов
                .accept(ContentType.ANY), Принимаем любой контент */

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/x-www-form-urlencoded")
                .body(getData())
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(302)
                .extract().cookies();
    }
    /** потому что = application/x-www-form-urlencoded */
    private String getData() {
        return "email=" + EMAIL + "&password=" + PWD;
    }
}